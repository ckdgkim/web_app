<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>학생 정보 입력</title>
</head>
<body>
    <form action="/students?action=insert" method="post">
        <input type="text" name="name">
        <input type="text" name="univ">
        <input type="date" name="birth">
        <input type="email" name="email">
        <input type="submit" value="등록">
    </form>
</body>
</html>