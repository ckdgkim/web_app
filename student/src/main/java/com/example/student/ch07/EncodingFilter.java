package com.example.student.ch07;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/news")
public class EncodingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest)request;
    if (true) { //로그인하지 않은 상황
      log.info("로그인 하지 않아서 종료됨 ^^^^^^^^^^^^^^: {}", "abc");
    } else { // 로그인한 상황}
    req.setCharacterEncoding("UTF-8");
    log.info("character encoding is^^^^^^^^^^^^^^: {}", req.getCharacterEncoding());
    chain.doFilter(request, response);
  }
}
