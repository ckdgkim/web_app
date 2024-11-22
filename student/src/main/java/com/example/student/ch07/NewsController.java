package com.example.student.ch07;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/news")
public class NewsController extends HttpServlet {
  private NewsDAO newsDAO = new NewsDAO();
  private ServletContext ctx;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    newsDAO = new NewsDAO();
    ctx = getServletContext();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    String method = req.getMethod();
    String action = req.getParameter("action");
    log.info("action {}", action);
    log.info("method {}", method);
    String path = "/ch07/";
    String view = "";

    if (action == null) {
      resp.sendRedirect("/news?action=list");
    } else {
        switch (action) {
          case "list" :
            view = list(req, resp);
            ctx.getRequestDispatcher(path + view).forward(req, resp);
            break;
          case "info" :
            view = info(req, resp);
            ctx.getRequestDispatcher(path + view).forward(req, resp);
            break;
          case "create" :
            view = create(req, resp);
            if (view.startsWith("redirect:")){ //"redirect:/news?action=list";
              view.substring(10);
              resp.sendRedirect(view);
            }
            break;
        }
    }
  }

  private String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<News> newsList = newsDAO.findAll();
    req.setAttribute("news", newsList);
    return "newsList.jsp";
  }

  private String info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int aid = Integer.parseInt(req.getParameter("aid"));
    News news = null;
    try {
      news = newsDAO.findByAid(aid);
    } catch (Exception e) {
        e.printStackTrace();
    }
    log.info("news list {}" , news);
    req.setAttribute("news", news);
    return "newsInfo.jsp";
  }

  private String create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    News news = new News();
    news.setTitle(req.getParameter("title"));
    news.setContent(req.getParameter("content"));
    news.setImg(req.getParameter("img"));
    try {
      newsDAO.addNews(news);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/news?action=list";

  }
}






















