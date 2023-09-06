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
	String num = request.getParameter("num");
	System.out.println("num 확인"+num);
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
	Statement st = con.createStatement(); //공통부분 묶는거 해야할듯.
	
	String sql = "UPDATE omm_roulette SET r_total_like = r_total_like + 1 WHERE r_no = "+num;
	System.out.println("전송할 sql문:"+sql);
	int resultCount = st.executeUpdate(sql);//좋아요 카운트 올라가게함
	if(resultCount == 1){
		System.out.println("좋아요추가");
	}
	else{
		System.out.println("추가실패");
	}
} catch(Exception e) {
	e.printStackTrace();
}
response.sendRedirect("rouletteList.jsp"); //todo: 룰렛으로 돌아가는게 아니라 좋아요 누른 녀석 개별 페이지로 이동하게 해주면 좋을듯
%>
</body>
</html>