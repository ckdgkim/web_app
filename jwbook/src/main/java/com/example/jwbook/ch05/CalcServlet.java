package com.example.jwbook.ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/ch05/calc")
public class CalcServlet extends HttpServlet {
  Calculator cal = new Calculator();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String number1 = req.getParameter("n1");
    String number2 = req.getParameter("n2");
    String operator = req.getParameter("op");

    log.info("number 1 ==> {}", number1);
    log.info("number 2 ==> {}", number2);
    log.info("op ==> {}", operator);

    double result = 0.0;
    String errorMessage = null;

    try {
      double n1 = Double.parseDouble(number1);
      double n2 = Double.parseDouble(number2);

      cal.setN1(n1);
      cal.setN2(n2);
      cal.setOp(operator);

      // 연산자에 따라 계산
      switch (operator) {
        case "+":
          result = n1 + n2;
          break;
        case "-":
          result = n1 - n2;
          break;
        case "*":
          result = n1 * n2;
          break;
        case "/":
          if (n2 != 0) {
            result = n1 / n2;
          } else {
            errorMessage = "0으로 나눌 수 없습니다.";
          }
          break;
        default:
          errorMessage = "올바른 연산자가 아닙니다.";
          break;
      }
    } catch (NumberFormatException e) {
      errorMessage = "잘못된 숫자 형식입니다.";
    }

    String path = "/ch05/";
    req.setAttribute("calculator", cal);
    req.setAttribute("result", result);
    req.setAttribute("number1", number1);
    req.setAttribute("number2", number2);
    req.setAttribute("operator", operator);
    req.setAttribute("errorMessage", errorMessage);

    req.getRequestDispatcher(path + "calcResult.jsp").forward(req, resp);
  }
}
