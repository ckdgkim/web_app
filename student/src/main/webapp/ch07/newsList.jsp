<%--
Created by IntelliJ IDEA.
User: KOSTA
Date: 2024-10-22
Time: 오전 11:42
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>학생 목록 조회</title>
</head>
<body>
<ul>
<c:forEach var="n" varStatus="i" items="${news}">
<li> [${i.count}]
   <a href="/news?action=info&aid=${n.aid}">${nret.title}</a>
     ${n.date}
  </li>
</c:forEach>
</ul>
    <hr>
    <form action="/news?action=create" method="post">
        title <input type="text" name="title">
        img <input type="text" name="img">
        content <textarea name="content" cols="20" rows="10"></textarea>
        button <input type="submit" value="등록">
    </form>
</body>
</html>