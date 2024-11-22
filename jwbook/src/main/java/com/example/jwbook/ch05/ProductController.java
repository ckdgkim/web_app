package com.example.jwbook.ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/products")
public class ProductController extends HttpServlet {
  private ProductService ps = new ProductService();
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String view = "";
    String path = "/ch05/";
    log.info("action = {}", action);

    if (action == null){
      resp.sendRedirect("/products?action=list");
    } else  {
    if(action.equals("list")) {
      // 모델에 데이터 담자.
      List<Product> products = ps.findAll();
      req.setAttribute("products", products);
      req.getRequestDispatcher(path + "productList.jsp").forward(req, resp);
    } else if(action.equals("info")) {
      // 모델에 데이터 담자.
      long id = Long.parseLong(req.getParameter("id"));
      Product product = ps.findById(id);
      req.setAttribute("product", product);
      req.getRequestDispatcher(path + "productInfo.jsp").forward(req, resp);
    }
  }
  }
}