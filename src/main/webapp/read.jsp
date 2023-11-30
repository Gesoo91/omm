<%@page import="com.omm.lunch.dto.Dto"%>
<%@page import="com.omm.lunch.dao.DaoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Dto d=(Dto)request.getAttribute("post");
String category = request.getParameter("category");
%>
카테고리:<%=d.category%>
<hr>
<%=d.no%>
<%=d.id%>
<%=d.title%>
<hr>
<%=d.text%>
<hr>
<a href="/lunch/del?category=<%=category%>&no=<%=d.no%>">삭제</a>
<a href="/lunch/edit_insert?category=<%=category%>&no=<%=d.no%>">수정</a>
<a href="/lunch/freeList?category=<%=category%>">리스트로</a>
<a href="/">홈으로</a>
</body>
</html>