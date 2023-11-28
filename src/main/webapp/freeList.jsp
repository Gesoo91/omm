<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM omm_board");//omm_board 불러오기
			
			while(rs.next()){
		    String no = rs.getString("l_no");
		    String title = rs.getString("l_title");
		    String text = rs.getString("l_text");
		    String id = rs.getString("l_id");
		    String category = rs.getString("l_category");
 %>
 <%=no %> <%=title %> <%=text %> <%=id %> <%=category %> <br>
 <%
			}

			rs.close();
			st.close();
			con.close();
	}catch (Exception e) {		
		e.printStackTrace();
		}	
 %>
</body>
</html>