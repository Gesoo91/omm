<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.omm.lunch.dto.RouletteDto"%>
<%@page import="com.omm.lunch.db.Dao"%>
<%@page import="com.omm.lunch.dao.DaoBoard"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<div id="logo">
			<a href="/">practice board</a>
		</div>
	</div>
	<hr>
	<div id="list">
	순위|메뉴이름|카테고리|주간좋아요|토탈좋아요<br>
	</div> 
	
	
	
	<%
	
	String pageNum=request.getParameter("page");
	if(pageNum==null){
		pageNum="1";
	}
	
	DaoBoard dao = new DaoBoard();	
	int count = dao.getPostCountBest();	// 전체 글 수
	
	//총 페이지 수 구하기
	int totalPage = 0;
	if(count % 5 == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
		totalPage = count / 5;
	}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
		totalPage = count / 5 + 1;
	}
	
	
	
	ArrayList<RouletteDto> posts=dao.listBest(pageNum);
	
	for(int i=0;i<posts.size();i=i+1){
	%>
	<%=i+1+"위"%>
	<%-- <%=posts.get(i).no%> --%>
	<%=posts.get(i).menu%> <%=posts.get(i).category%> <%="주간좋아요:"+posts.get(i).weekly_like%>	<%=posts.get(i).total_like%>
	<hr>
	<%
	}
	%>
	<hr>
	
	
	
	<%
	for(int i=1;i<=totalPage;i=i+1){
	%>
	
	<a href="bestboard.jsp?page=<%=i%>"><%=i%></a>
	
	<%
	}
	%>
	<fieldset id="log">
		<legend>로그</legend>
	전체 글 수: <%=count %><br>
	전체 페이지 수: <%=totalPage %><br>
	현재 페이지 번호: <%=pageNum %><br>
	</fieldset>
	
		<!-- <div id="write">
		<a href="write.jsp">글작성</a>
		</div> -->
</body>
</html>