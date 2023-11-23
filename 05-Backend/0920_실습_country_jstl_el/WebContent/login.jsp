<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="${pageContext.request.contextPath}/member" method="post">
		<input type="hidden" name="action" value="login">
		<fieldset>
			<label><input type="text" name="memberId" value= "${cookie.rememberId.value}"> : 아이디</label>
			<br>
			<input type="checkbox" name="isRemember" ${empty cookie.rememberId?"":"checked"}>아이디 기억하기
			<br>
			<label><input type="password" name="password"> : 비밀번호</label>
			
		</fieldset>
		<button type="submit">로그인</button>
	</form>
</body>
</html>