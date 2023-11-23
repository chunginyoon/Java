<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<form action="${root}/member/login" method="POST">
		<fieldset>
			<label><input type="text" name="memberId"> : 아이디</label> <br>
			<label><input type="text" name="password"> : 비밀번호</label><br>
		</fieldset>
		<button type="submit">로그인</button>
	</form>

</body>
</html>