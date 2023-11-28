<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.omm.lunch.board.Board"%>
<%@page import="com.omm.lunch.dao.DaoBoard"%>
<%@page import="com.omm.lunch.dto.Dto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ArrayList<Dto> posts = (ArrayList<Dto>)request.getAttribute("posts");
System.out.println("posts: " + posts); // 확인을 위한 로그
System.out.println("posts size in JSP: " + posts.size());
for(int i=0;i<posts.size();i=i+1){
 %>
 <%=posts.get(i).no %> <%=posts.get(i).title %> <%=posts.get(i).text %> <%=posts.get(i).id %> <%=posts.get(i).category %> <br>
 <%
			}
 %>
</body>
</html>