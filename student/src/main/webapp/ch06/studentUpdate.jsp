<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>학생 정보 수정</title>
</head>
<body>
    <h2>학생정보 수정</h2>
    <form action="/students?action=update&id=${s.id}" method="post">
        이름<input type="text" name="name" value="${s.name}" disabled>
        학교<input type="text" name="univ" value="${s.univ}">
        생일<input type="date" name="birth" value="${s.birth}" disabled>
        이메일<input type="email" name="email" value="${s.email}">
        <input type="submit" value="수정">
    </form>
</body>
</html>

