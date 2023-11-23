<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	<img src="${root}/resources/img/logo.png" alt="이미지 가져오기">
	메인 화면
</h1>

<a href="${root}/book/list">책 목록 조회</a>
<br>
<a href="${root}/book/regist">책 등록</a>

</body>
</html>
