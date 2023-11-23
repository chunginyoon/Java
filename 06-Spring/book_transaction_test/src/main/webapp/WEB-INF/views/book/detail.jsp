<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 상세화면</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	<h1>책 상세 조회 화면</h1>
	<a href="${root}/book/list">목록</a>
	<table>
		<tr>
			<th>책 번호</th>
			<th>제목</th>
			<th>저자</th>
			<th>가격</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>${book.no}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.price}</td>
			<td>${book.content}</td>
		</tr>
	</table>
	<button onclick="deleteBook()">삭제</button>
	<script>
		function deleteBook(){
			if(confirm("정말 삭제하시겠습니까?")){
				location.href="${root}/book/delete?no=${book.no}"
			}
		}
	</script>
</body>
</html>