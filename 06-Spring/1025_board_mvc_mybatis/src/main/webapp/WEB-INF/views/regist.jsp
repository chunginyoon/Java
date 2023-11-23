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
	<h1>상품 정보 등록</h1>
	<form action="${pageContext.request.contextPath}/board/regist" method="post">
		<fieldset>
			<label><input type="text" name="code"> 고유번호</label> <br>
			<label><input type="text" name="model"> 모델명</label> <br>
			<label><input type="number" name="price"> 가격</label> <br>
		</fieldset>
		<input type="submit" value="등록">
		<a href="${pageContext.request.contextPath}/">취소</a>
	</form>
</body>
</html>