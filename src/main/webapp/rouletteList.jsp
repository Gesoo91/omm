<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.omm.lunch.dto.RouletteDto" %>
<%@ page import="com.omm.lunch.dao.DaoBoard" %>
<%@ page import="com.omm.lunch.dao.DaoBoard.RouletteResult" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
DaoBoard dao = new DaoBoard();
RouletteResult rouletteResult = dao.performRoulette();
RouletteDto rouletteDto = rouletteResult.getRouletteResult();
ArrayList<RouletteDto> subRouletteResults = rouletteResult.getSubRouletteResults();
%>

no: <%= rouletteDto.no %>
menu: <%= rouletteDto.menu %>
category: <%= rouletteDto.category %>
id: <%= rouletteDto.id %>
like: <%= rouletteDto.total_like %>
<a href="proclike.jsp?num=<%= rouletteDto.no %>">마음에 들면 좋아요!</a><br>

<% for (int i = 0; i < subRouletteResults.size(); i++) { %>
    <p>이건어떄?:</p> <%= subRouletteResults.get(i).menu %><br>
<% } %>
</body>
</html>
