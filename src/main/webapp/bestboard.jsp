<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<div id="logo">
			<a href="index.jsp">practice board</a>
		</div>
	</div>
	<hr>
	<div id="list">
	순위|메뉴이름|카테고리|좋아요수<br>
		<%
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from omm_roulette order by r_total_like desc");
			 
			while(rs.next()){
				String num = rs.getString("r_no"); //todo : 순서가 아니라 순위로 변경해야함, a링크에 read.jsp를 개별 음식 혹은 카테고리별 게시판으로 넘어가게해야함.
				String menu = rs.getString("r_menu");
				String category = rs.getString("r_category");
				String like = rs.getString("r_total_like");
				
			%>
				<!-- todo : 필터 기능 추가 필요 ajax로 해주면 좋을 것 같.다. -->
		<%=num %> <a href="read.jsp?num=<%=num%>"><%=menu %></a> <%=category %> <%=like %><br> <%--mysql-connector-j-8.-.33.jar을 안넣어서 안나오더라 --%>
		<%
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		}	
		%>
	</div>
	<div id="write">
	<a href="write.jsp">글작성</a>
	</div>
</body>
</html>