<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자동차 등록</title>
</head>
<body>
	<h1>자동차 등록 화면</h1>
	<form action="${root}/car/regist" method="POST">
		<input type="hidden" name="action" value="regist">
		<fieldset>
			<label><input type="text" name="number"> : 차량 번호</label> <br>
			<label><input type="text" name="model"> : 모델</label> <br>
			<label><input type="number" name="price"> : 가격</label> <br>
			<label><input type="text" name="brand"> : 브랜드</label>
		</fieldset>
		<button type="submit">등록</button>
	</form>
</body>
</html>