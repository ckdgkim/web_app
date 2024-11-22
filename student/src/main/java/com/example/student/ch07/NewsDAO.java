package com.example.student.ch07;

import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewsDAO {
  // DB connection 을 얻어 온다.
  public Connection getConnection(){
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3305/backend?serverTimezone=Asia/Seoul";

    Connection conn = null;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL,"root","1234");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return conn;
  }

  // DB connection 을 닫는다.

  // CRUD
  //1.뉴스 목록 가져오기
  public List<News> findAll() {
    Connection conn = getConnection();
    List<News> newList = new ArrayList<>();

    //1. sql
    String sql = "SELECT aid, title, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate FROM news";
    //2. pstmt
    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(sql);
      //3. pstmt를 이용하여 쿼리 실행
      ResultSet rs = pstmt.executeQuery();
      //4. 결과를 리스트에 담는다.
      while (rs.next()) {
        News news = new News();
        news.setAid(rs.getInt("aid"));
        news.setTitle(rs.getString("title"));
        news.setDate(rs.getString("cdate"));

        newList.add(news);
      }
      //5. 사용한 리소스를 닫아준다.
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    //6. 반환한다.
    return newList;
  }
//2. aid로 뉴스 가져오기
  public News findByAid(int aid) throws Exception{
    News news = null;
    //1) connection을 얻어낸다.
    Connection conn = getConnection();
    //2) sql을 작성한다.
    String sql = "select aid, title, img, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate, content from news where aid = ?";
    //3) pstmt 에 sql을 적용한다, 매개변수가 있다면 argument를 설정한다.
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1,aid);
    //4) pstmt sql문을 실행한다. ==> rs에 담는다.
    ResultSet rs = pstmt.executeQuery();
    //5) 가져온 데이터를 처리한다.
    if (rs == null){
      log.info("result = {}", rs);
    } else {
      rs.next();
      news = new News();
      news.setAid(rs.getInt("aid"));
      news.setTitle(rs.getString("title"));
      news.setImg(rs.getString("img"));
      news.setDate(rs.getString("cdate"));
      news.setContent(rs.getString("content"));
      log.info("result = {}", news);
    }
    //6)리소스를 닫는다.
    rs.close();
    pstmt.close();
    conn.close();
    return news;
  }

  //3. 새로운 뉴스 등록하기
// 3. 새로운 뉴스 등록하기
  public int addNews(News news) throws Exception {
    Connection conn = getConnection();
    String sql = "INSERT INTO news (title, img, date, content) VALUES (?, ?, ?, ?)";
    PreparedStatement pstmt = null;
    int result = 0;

    try {
      // PreparedStatement에 SQL 쿼리 설정
      pstmt = conn.prepareStatement(sql);
      // 각 매개변수 설정
      pstmt.setString(1, news.getTitle());
      pstmt.setString(2, news.getImg());
      pstmt.setString(3, news.getDate());
      pstmt.setString(4, news.getContent());

      // 쿼리 실행 (삽입된 레코드 수 반환)
      result = pstmt.executeUpdate();
      log.info("News added successfully: {}", news);
    } catch (SQLException e) {
      // 예외 발생 시 처리
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      // 리소스 닫기
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    }
    // 삽입된 레코드 수 반환
    return result;
  }
}
