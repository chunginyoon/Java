<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 등록</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	<h1>책 등록</h1>
	<a href="${root}">메인</a>
	<form action="${root}/book/regist" method="post">
		<fieldset>
			<label><input type="text" name="title"> 제목</label>
			<br>
			<label><input type="text" name="author"> 저자</label>
			<br>
			<label><input type="number" name="price"> 가격</label>
			<br>
			<label><textarea name="content"></textarea> 내용</label>
		</fieldset>
		<input type="submit" value="등록">
	</form>
</body>
</html>