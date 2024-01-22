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
	
	<%-- <%
    System.out.println("proclike.jsp 페이지가 실행되었습니다.");

	String num = request.getParameter("num");
	System.out.println("num 확인"+num);
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
	Statement st = con.createStatement(); //공통부분 묶는거 해야할듯.
	
    // 현재 날짜와 주를 가져오기
    Calendar cal = Calendar.getInstance();
    int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
    int currentMonth = cal.get(Calendar.MONTH) + 1;

    // 좋아요 업데이트 쿼리
	String updateQuery = "UPDATE omm_roulette " +
	                     "SET r_total_like = r_total_like + 1, " +
	                     "    r_weekly_like = r_weekly_like + 1, " +
	                     "    r_month_like = r_month_like + 1 " +
	                     "WHERE r_no = " + num;
	System.out.println("Update Query: " + updateQuery);

    // 쿼리 실행
    int rowsAffected = st.executeUpdate(updateQuery);

    if (rowsAffected > 0) {
        out.println("좋아요가 업데이트되었습니다.");

        // 한 주가 지나면 주간 좋아요 초기화
        String resetWeeklyLikeQuery = "UPDATE omm_roulette " +
                                      "SET r_weekly_like = 0 " +
                                	  "WHERE DATE_FORMAT(r_like_time, '%Y%u') < " + currentWeek;
        st.executeUpdate(resetWeeklyLikeQuery);
        System.out.println("weekly like reset for week" + currentWeek);
    	// 한 달이 지나면 주간 좋아요 초기화
        String resetMonthLikeQuery = "UPDATE omm_roulette " +
                                      "SET r_month_like = 0 " +
                                      "WHERE YEARWEEK(r_like_time) < " + currentMonth;
        st.executeUpdate(resetMonthLikeQuery);
    } else {
        out.println("좋아요 업데이트에 실패했습니다.");
    }

    st.close();
    con.close();
} catch (Exception e) {
    e.printStackTrace();
}
response.sendRedirect("rouletteList.jsp");
%> --%>
<!-- todo: 지난 주간 좋아요 혹은 지난 월간 좋아요 페이지를 어떻게 남길것인가? 서버에 저장?매주말,월말에?  이방법도 가능하지만, 스케쥴러를 이용할 수 도있다.  Spring이라면 Task Scheduler
그러나 jsp는 내장기능 없으므로 Java의 Timer 및 TimerTask 클래스를 사용-->
</body>
</html>