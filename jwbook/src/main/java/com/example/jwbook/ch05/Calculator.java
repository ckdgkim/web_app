package com.example.jwbook.ch05;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculator {
  private double n1; // int를 double로 변경
  private double n2; // int를 double로 변경
  private String op;

  public double calc() {
    double result = 0.0;
    switch (op) {
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
        }
        break;
      default:
        // 올바른 연산자가 아닐 경우 처리
        break;
    }
    return result;
  }
}
