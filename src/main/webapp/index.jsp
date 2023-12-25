<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.omm.lunch.dao.DaoBoard"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="reset.css">
<link rel="stylesheet" type="text/css" href="index.css">
<script type="text/javascript"
	src="index.js?ver=<%= System.currentTimeMillis() %>"></script>
</head>
<body>
	<div id="header">
		<div id="header_wrap">
			<div id="logo">
				<a href="index.jsp"><img alt="*" src="logo"></a>
			</div>
			<div id="header_tab">
				<ul> 
					<li class="tab_head">베스트 게시판</li>
					<li><a href="/lunch/bestboard">best</a></li><!-- 마우스 오버해서 아래로 li 펼쳐지게 하기. -->
					<li><a href="/lunch/weeklyBest">이번주 최다선택</a></li>
					<li><a href="/lunch/monthBest">이번달 최고만족</a></li>
					<li>먹잘알</li>
					
				</ul>
				<ul>
					<li class="tab_head">이거어때?</li><!-- 마우스 오버해서 아래로 li 펼쳐지게 하기. -->
					<li><a href="/addMenu.jsp">메뉴추가하기</a></li>
					<li>메뉴인증!</li> <!-- 나온데로 먹은거 인증하는 페이지. -->
				</ul>
				<ul>
				<li class="tab_head">게시판</li><!-- 이부분 없애도될듯 -->
				<li><a href="/lunch/freeList?category=free">자유게시판</a></li>
				<li><a href="/lunch/freeList?category=req">건의게시판</a></li>
				</ul>
			</div>
			<div id="user">
				<a href="login.jsp">로그인</a>
				<a href="signup.jsp">회원가입</a>
				<!-- div감싸지말고 wrap 제일 오른쪽에 붙여둘까? -->
			</div>
		</div>
	</div>
	
	<div id="container">
		<div id="roulette_wrap">
			<div id="roulette">
				<a href="/lunch/roulette">오늘 뭐먹지?</a>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>