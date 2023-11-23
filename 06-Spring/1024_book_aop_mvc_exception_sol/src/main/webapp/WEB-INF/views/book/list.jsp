<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 목록</title>
</head>
<body>
	<h1>책 목록 조회 화면</h1>
	<a href="${pageContext.request.contextPath}">메인</a>
	<table>
		<tr>
			<th>책 번호</th>
			<th>제목</th>
			<th>저자</th>
			<th>가격</th>
		</tr>
		<c:forEach var="book" items="${list}">
			<tr>
				<td>${book.no}</td>
				<td><a href="${pageContext.request.contextPath}/book/detail?no=${book.no}">${book.title}</a></td>
				<td>${book.author}</td>
				<td>${book.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>