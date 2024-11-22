<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <%
        } else {
    %>
        <hr>
        ${calculator.n1} ${calculator.op} ${calculator.n2} 의 결과는 ${calculator.calc()} 입니다.
    <%
        }
    %>
    <a href="/calcForm.html">다시 계산하기</a>
</body>
</html>