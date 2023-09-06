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
int random = 0;
int count_no = 0;


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			Statement st = con.createStatement();
			ResultSet countRs = st.executeQuery("select count(r_no) from omm_roulette"); //전체 r_no의 카운트값 조회.
			if (countRs.next()) {
		        count_no = countRs.getInt(1); //첫번째 컬럼 정수로 불러오기. 카운트 값 가져오기라고 보면됨.
		    }
		    countRs.close();
		    random = (int)Math.floor(Math.random()*count_no)+1; //전체 no중 1개 뽑기.
		    
		    /* 랜덤값따라 3개의 값 출력하는 sql문이지만 no순 정렬되기때문에 불편함을 느껴서 수정
		    if( random<2){
		    	rs = st.executeQuery("SELECT * FROM omm_roulette WHERE r_no IN (" + (random + 1) + "," + random + "," + (random +2) + ")");
		    }
		    else if(random == count_no){
		    	rs = st.executeQuery("SELECT * FROM omm_roulette WHERE r_no IN (" + (random - 2) + "," + random + "," + (random - 1) + ")");
		    }
		    else{
		    	rs = st.executeQuery("SELECT * FROM omm_roulette WHERE r_no IN (" + (random - 1) + "," + random + "," + (random + 1) + ")");
		    }
		    */
			 //현재 고민중 : 	list로 뽑아두고 그 중에 자바스크립트에서 애니메이션 하게하다가 string no를 랜덤한 녀석으로 뽑아서 멈추게할지
			 //				아니면 read로 랜덤한 몇가지가 뽑히게 할건데 룰렛이 돌아가는 것처럼 보이게 할 것인지.
			 //1.의 경우는 list가 늘어나면 늘어날수록 애니메이션이 느려질것같고, 2.의 경우는 룰렛을 어떻게 잘 만드느냐에 따라 달라질것 같다.
			// 현재 랜덤으로 read하는것까지 만듬. 여기서 위아래 보일 녀석들 만들기(random값 +-1 해주고, -나+할때 없는 번호면 -2나+2로 바꿔주면될듯)(완료)
			//todo : 위아래 배치될 메뉴 만들기(완료 -- sql문 3개를 가져오니까 6일때 465 이렇게 나오는게 아니라 456으로 나옴. 차라리 random값만 불러오고 더미데이터로 돌리던지 아싸리 더미데이터 돌다가 random값 나오게하자.
			//todo2 : 랜덤값 출력완료, 랜덤값에 따른 카테고리 추천 완료.(like높은 순 추천으로 보강할듯) -> 자바스크립트로 돌아갈 list용 하나 깔기.(예상: list가 돌아감. 마지막쯤에 타이밍맞춰서 랜덤값이 같이 올라옴. 멈춤.)		
			ResultSet rs = st.executeQuery("SELECT * FROM omm_roulette WHERE r_no ="+ random );
				rs.next();
			    String no = rs.getString("r_no");
			    String menu = rs.getString("r_menu");
			    String category = rs.getString("r_category");
			    String id = rs.getString("r_id");
			    String like = rs.getString("r_total_like");
%>
				<!-- 현재는 이 페이지에서 표현된다. 아래 표현태그때문에. 이걸 index에서 보이게 하려면 ajax나 session으로 처리하는거 챗gpt가 추천함 -->
			<%=no %> <%=menu %> <%=category %> <%=id %> <%=like %><br>
			
			 <a href="proclike.jsp?num=<%=no %>">마음에 들면 좋아요!</a><br>
			<!-- form 방식으로 파라미터 넘기려다가 url방식으로 변경 -->
<%
			
			 
			ResultSet subRs = st.executeQuery("SELECT * FROM omm_roulette WHERE r_category = (select r_category from omm_roulette where r_no = " + random + ") and r_no !=" + random + " order by rand() limit 5");

			while(subRs.next()) {
			String no_sub = subRs.getString("r_no");
		    String menu_sub = subRs.getString("r_menu");
		    String category_sub = subRs.getString("r_category");
		    String id_sub = subRs.getString("r_id");
		    String like_sub = subRs.getString("r_total_like");
%>
	    	<p>이건어떄? <%=menu_sub %></p><br>
	    	<%
			}
			
			subRs.close();
			rs.close();
			st.close();
			con.close();
		}catch (Exception e) {		
			e.printStackTrace();
			}	
		%>
		
</body>
</html>