<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String root=request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file = "/include/nav.jsp" %>
	메인 화면
	<br>
	<a href="<%=root%>/country?action=list">전체 목록 조회</a>
	<br>
	<a href="<%=request.getContextPath()%>/country?action=mvRegist">국가 정보 등록 페이지</a>
</body>
</html>