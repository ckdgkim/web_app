package com.example.student.ch06;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentDAO {
  // driver
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3305/backend?serverTimezone=Asia/Seoul";
  // db connection
  Connection conn ;
  // Statement
  PreparedStatement pstmt ;
  // sql

  public void open(){
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL, "root", "1234");
    } catch (Exception e) {
      e.printStackTrace();
      log.error("데이터베이스 연결 실패: {}", e.getMessage());
    } finally {
      if (conn != null) {
        log.info("데이터베이스에 접속 완료...");
      } else {
        log.error("데이터베이스 연결이 설정되지 않았습니다.");
      }
    }
  }

  public void close(){
    try {
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      log.info("데이터베이스 종료");
    }
  }

  public void insert(Student s){
//        open();
    String sql = "insert into student(name, univ, birth, email) values(?,?,?,?)";

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, s.getName());
      pstmt.setString(2, s.getUniv());
      pstmt.setDate(3, s.getBirth());
      pstmt.setString(4, s.getEmail());

      pstmt.executeUpdate();
      log.info("insert =" + pstmt.executeUpdate());
    } catch (SQLException e) {
      e.printStackTrace();
    }
//        finally {
//            close();
//        }
  }
  public List<Student> findAll(){
    //  open();
    List<Student> students = new ArrayList<Student>();

    String sql = "select * from student";
    try {
      pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      log.info("findAll rs = " + rs);

      while(rs.next()) {
        Student s = new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("univ"),
            rs.getDate("birth"),
            rs.getString("email")
        );
        students.add(s);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  public Student findById(int id){
    //  open();
    Student student = null;

    String sql = "select * from student where id = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      log.info("findById rs =" + rs);

      while(rs.next()){
        student = new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("univ"),
            rs.getDate("birth"),
            rs.getString("email")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
//        finally {
//            close();
//        }
    return student;
  }

  public void updateStudent(Student s) {
    // univ , email 만 수정가능
    String sql = "update student set univ = ?, email = ? where id = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, s.getUniv());
      pstmt.setString(2, s.getEmail());
      pstmt.setInt(3, s.getId());

      int rc = pstmt.executeUpdate();
      log.info("updateStudent: {}", rc);
      if (rc == 1) {
        log.info("수정 완료");
      } else {
        log.error("수정 오류");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public void deleteStudent(int id) {
    //1. sql 작성
    String sql = "delete from student where id = ?";
    try {
      //2. pstmt 준비
      pstmt = conn.prepareStatement(sql);
      //3. pstmt 매개변수 설정
      pstmt.setInt(1, id);
      //4. pstmst의 sql을 실행
      int rowsDeleted = pstmt.executeUpdate();
      log.info("deleteStudent: " + rowsDeleted);
      if (rowsDeleted > 0) {
        log.info("학생이 성공적으로 삭제되었습니다. ID: {}", id);
      } else {
        log.warn("학생 삭제에 실패했습니다. 존재하지 않는 ID: {}", id);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      log.error("학생 삭제 중 오류 발생: {}", e.getMessage());
    }
  }

}