<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<a href="${root}">메인</a>
	<form action="${root}/member/login" method="post">
		<fieldset>
			<label><input type="text" name="memberId"> 아이디</label> <br>
			<label><input type="text" name="password"> 비밀번호</label> <br>
		</fieldset>
		<input type="submit" value="로그인">
	</form>
</body>
</html>