package com.example.jwbook.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String number1 = req.getParameter("n1");
    String number2 = req.getParameter("n2");
    String operator = req.getParameter("op");

    log.info("number 1 ==> {}", req.getParameter("n1"));
    log.info("number 2 ==> {}", req.getParameter("n2"));
    log.info("op ==> {}", req.getParameter("op"));

    double result = 0.0;
    String errorMessage = null;

    try {
      double n1 = Double.parseDouble(number1); // 첫 번째 숫자
      double n2 = Double.parseDouble(number2); // 두 번째 숫자

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
          if (n2 != 0) { // 나눗셈의 경우 0으로 나누는지 체크
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
      // 숫자 형식이 잘못된 경우 예외 처리
      errorMessage = "잘못된 숫자 형식입니다.";
    }

    // 요청 속성에 결과와 에러 메시지를 추가
    String path = "/ch04/";
    req.setAttribute("result", result);
    req.setAttribute("number1", number1);
    req.setAttribute("number2", number2);
    req.setAttribute("operator", operator);
    req.setAttribute("errorMessage", errorMessage);

    // 결과를 CalcResult.jsp로 포워딩
    req.getRequestDispatcher(path + "calcResult.jsp").forward(req, resp);
  }
}
