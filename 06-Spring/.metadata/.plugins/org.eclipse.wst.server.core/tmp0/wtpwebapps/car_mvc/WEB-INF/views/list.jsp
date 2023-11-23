<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자동차 목록</title>
</head>
<body>
	<h1>자동차 목록 조회 화면</h1>

	<table>
		<tr>
			<th>자동차 번호</th>
			<th>차량 번호</th>
			<th>모델</th>
			<th>가격</th>
			<th>브랜드</th>
		</tr>
		<c:forEach var="car" items="${list}">
			<tr>
				<td><a href="${root }/car/detail?carNo=${car.carNo}">${car.carNo}</a></td>
				<td>${car.number}</td>
				<td>${car.model}</td>
				<td>${car.price}</td>
				<td>${car.brand}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>