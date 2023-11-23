<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<style>
#loginInfo {
	text-align: right;
}

#head {
	text-align: center;
}

.inputtitle {
	display: inline-block;
	width: 100px;
	background: silver;
	text-align: center;
}
</style>

<div id="loginInfo">

	<c:choose>
		<c:when test="${not empty userInfo}">
			<p>${userInfo.name}(${userInfo.position})님 로그인되었습니다.</p>
			<a href="${root}/user/logout">로그아웃</a>
		</c:when>
	</c:choose>
</div>

<h1 id="head">${title}</h1>
<hr>
