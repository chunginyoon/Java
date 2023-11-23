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
	<table>
		<tr>
			<th>code</th>
			<th>모델</th>
			<th>가격</th>
		</tr>
		<tr>
			<td>${product.code}</td>
			<td>${product.model}</td>
			<td>${product.price}</td>
		</tr>
	</table>
	<br>
	<a
		href="${pageContext.request.contextPath}/board/delete?code=${product.code}">삭제</a>
</body>
</html>