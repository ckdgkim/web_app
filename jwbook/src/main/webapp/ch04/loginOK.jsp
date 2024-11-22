<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <%! String id =""; %>
    <%
        id = (String)session.getAttribute("id"")
        if(id.equals("")) {
            response.sendRedirect("login.jsp");
        } else {
            out.println("<h1> 로그인 완료</h1>");
            out.println(id + "님 안녕하세요");
        }
        %>
    </body>
</body>
</html>