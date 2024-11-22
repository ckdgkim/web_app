<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>계산 결과</title>
</head>
<body>
    <h2>계산기 결과</h2>
    <hr>
    <%
        // 에러 메시지가 있는 경우 표시
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <%
        } else {
            // 에러가 없으면 계산 결과를 표시
            String number1 = (String) request.getAttribute("number1");
            String number2 = (String) request.getAttribute("number2");
            String operator = (String) request.getAttribute("operator");
            double result = (double) request.getAttribute("result");
    %>
        <p><%= number1 %> <%= operator %> <%= number2 %> = <%= result %></p>
    <%
        }
    %>
    <a href="/calcForm.html">다시 계산하기</a>
</body>
</html>
