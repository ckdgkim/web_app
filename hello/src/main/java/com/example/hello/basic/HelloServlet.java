package com.example.hello.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println(req.getMethod() + " " + req.getProtocol());
    log.debug("debug : get method {}" , req.getMethod());
    log.warn("warning : get method = {}" , req.getMethod());
    log.error("error : get method = {}" , req.getMethod());
    log.trace("trace : get method = {}" , req.getMethod());
    log.info("info : get method = {} , protocol = {}", req.getMethod(), req.getProtocol());
  }
}
