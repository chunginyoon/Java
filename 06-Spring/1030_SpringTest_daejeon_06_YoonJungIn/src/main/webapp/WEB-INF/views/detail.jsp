<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SSAFY</title>
<style>
form>label {
	display: inline-block;
	width: 100px;
}

form>:input {
	display: inline-block;
	width: 100px;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<form>
		<label for="ano" class="inputtitle">이슈 번호</label>
		<input type="text" name="ano" id="ano" value="${attendance.ano}" readonly>
		<br>
		<label for="usernumber" class="inputtitle">학번</label>
		<input type="text" name="userNumber" id="usernumber" value="${attendance.userNumber}" readonly>
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
		<label for="issuedate" class="inputtitle">날짜</label>
		<input type="date" name="issueDate" id="issuedate" value="${attendance.issueDate}" readonly> 
		<br>
		<label for="issue" class="inputtitle">사유</label>
		<input type="text" name="issue" id="issue" value="${attendance.issue}" readonly>
		<br>
	</form>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
