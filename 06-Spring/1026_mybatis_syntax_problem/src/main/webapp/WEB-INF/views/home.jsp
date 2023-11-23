<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
	<title>Home</title>
</head>
<body>

<ul>
	<li><a href="${root }/insert">도시정보등록 및 등록된 도시의 ID정보조회(selectKey)</a></li>
	<li><a href="${root }/update">마지막으로 등록된 도시정보수정(selectKey)</a></li>
	<li><a href="${root }/selectCoCi">모든 나라의 나라 및 도시정보조회(join)</a></li>
	<li><a href="${root }/selectCoCl">모든 나라의 나라 및 언어정보조회(join)</a></li>

</ul>

</body>
</html>
