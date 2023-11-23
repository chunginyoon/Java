<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<nav>
	<%-- 로그인 하지 않은 경우 메뉴 --%>
	<c:if test="${empty sessionScope.memberInfo }">
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/member/join">회원가입</a></li>
		</ul>

	</c:if>

	<%-- 로그인 한 경우 메뉴 --%>
	<c:if test="${not empty sessionScope.memberInfo }">
		<ul>
			<li>${memberInfo.memberId}님로그인</li>
			<li><button type="button" onclick="logout()">로그아웃</button></li>
		</ul>
	</c:if>
</nav>
<script>
	function logout() {
		location.href = "${pageContext.request.contextPath}/member/logout";
	}
</script>