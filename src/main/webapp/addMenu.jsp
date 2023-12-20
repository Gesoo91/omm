<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/lunch/addMenu">
		<input type="hidden" name="no">
		<input type="text" name="menu" placeholder="메뉴 이름을 적어주세요">
		<select name="category">
			<option value="한식"> 한식</option>
			<option value="일식"> 일식</option>
			<option value="중식"> 중식</option>
			<option value="양식"> 양식</option>
			<option value="양식"> 기타</option>
		</select>
		<input type="text" name="id"><!--세션이나 쿠키로 id가져오기 / 이게시판 들어오려면 로그인먼저 하게하기 -->
		<input type="submit" value="메뉴추가">
	</form>
	<!--procaddMenu 를 만들어서 name값 getParameter 해서 만들어주던가 컨트롤러에 잘 넣던가.  -->
</body>
</html>