<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SSAFY</title>
<style type="text/css">
h1, h4 {
	text-align: center;
}

a {
	margin: 10px;
}

.inputtitle {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<h1>출결 관리</h1>
	<c:choose>
		<c:when test="${empty userInfo}">
			<fieldset>
				<h1>로그인 해주세요.</h1>
				<form action="${pageContext.request.contextPath}/user/login" method="post">
					<label class="inputtitle">아이디</label> <input type="text" name="id"> <br>
					<label class="inputtitle">비밀번호</label> <input type="password" name="pw"> <br>
					<input type="submit" value="로그인">
				</form>
			</fieldset>
		</c:when>
	</c:choose>
	<hr>
	<h4>
		<a href="${pageContext.request.contextPath}/user/regist">출결 이슈 등록</a>
	</h4>
	<h4>
		<a href="${pageContext.request.contextPath}/user/list">출결 이슈 목록</a>
	</h4>

</body>
</html>