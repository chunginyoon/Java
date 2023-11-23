<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<nav>
	
	<ul>
		<c:choose>
			<c:when test="${empty userInfo}">
				<li><a href="${root}/member/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li>${userInfo.memberId}님 로그인</li>
				<li><a href="${root}/member/logout">로그아웃</a></li>		
			</c:otherwise>
		</c:choose>
	</ul>
</nav>