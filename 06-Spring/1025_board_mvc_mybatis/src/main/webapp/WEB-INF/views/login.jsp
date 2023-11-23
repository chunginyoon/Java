<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 사이트</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<h1>로그인</h1>
	<form action="${pageContext.request.contextPath}/member/login" method="post">
		<fieldset>
			<label><input type="text" name="memberId"> 아이디</label> <br>
			<label><input type="text" name="password"> 비밀번호</label> <br>
		</fieldset>
		<input type="submit" value="로그인">
		<a href="${pageContext.request.contextPath}/">취소</a>
	</form>
</body>
</html>