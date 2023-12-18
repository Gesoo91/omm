<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Calendar" %>
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
	
    // 현재 날짜와 주를 가져오기
    Calendar cal = Calendar.getInstance();
    int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);

    // 좋아요 업데이트 쿼리
    String updateQuery = "UPDATE omm_roulette " +
                         "SET r_total_like = r_total_like + 1, r_weekly_like = r_weekly_like + 1 " +
                         "WHERE r_no = " + num;

    // 쿼리 실행
    int rowsAffected = st.executeUpdate(updateQuery);

    if (rowsAffected > 0) {
        out.println("좋아요가 업데이트되었습니다.");

        // 한 주가 지나면 주간 좋아요 초기화
        String resetWeeklyLikeQuery = "UPDATE omm_roulette " +
                                      "SET r_weekly_like = 0 " +
                                      "WHERE YEARWEEK(r_like_time) < " + currentWeek;

        st.executeUpdate(resetWeeklyLikeQuery);
    } else {
        out.println("좋아요 업데이트에 실패했습니다.");
    }

    st.close();
    con.close();
} catch (Exception e) {
    e.printStackTrace();
}
response.sendRedirect("rouletteList.jsp");
%>

</body>
</html>