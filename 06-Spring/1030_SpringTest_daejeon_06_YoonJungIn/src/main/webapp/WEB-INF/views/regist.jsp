<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	#result{
		display: none;
	}
</style>
<title>SSAFY</title>
</head>

<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<form id="registForm" action="${pageContext.request.contextPath}/user/regist" method="post">
	<label for="ano" class="inputtitle">이슈 번호</label><input type="text" name="ano" id="ano"> <br>
	<c:choose>
		<c:when test="${userInfo.position eq '관리자'}">
		<label for="usernumber" class="inputtitle">학번</label>
		<input type="text" name="userNumber" id="usernumber">
		<br>
		<label for="name" class="inputtitle">교육생명</label>
		<input type="text" name="name" id="name">
		<br>
		<label for="rclass" class="inputtitle">반</label>
		<input type="number" name="rclass" id="rclass">
		<br>
		<label for="rname" class="inputtitle">지역</label>
		
		<input type="text" name="rname" id="rname">
		<br>
		</c:when>
		<c:otherwise>
		<label for="usernumber" class="inputtitle">학번</label>
		<input type="text" name="userNumber" id="usernumber" value="${userInfo.userNumber}" readonly>
		<br>
		<label for="name" class="inputtitle">교육생명</label>
		<input type="text" name="name" id="name" value="${userInfo.name}" readonly>
		<br>
		<label for="rclass" class="inputtitle">반</label>
		<input type="number" name="rclass" id="rclass" value="${userInfo.rclass}" readonly>
		<br>
		<label for="rname" class="inputtitle">지역</label>
		<input type="text" name="rname" id="rname" value="${userInfo.rname}" readonly>
		<br>
		</c:otherwise>
	</c:choose>
		<label for="issuedate" class="inputtitle">날짜</label>
		<input type="date" name="issueDate" id="issuedate">
		<br>
		<label for="issue" class="inputtitle">사유</label>
		<input type="text" name="issue" id="issue" >
		<br>
		<button type="submit">출결 이슈 등록</button>
		<button type="reset">초기화</button>
	</form>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</html>