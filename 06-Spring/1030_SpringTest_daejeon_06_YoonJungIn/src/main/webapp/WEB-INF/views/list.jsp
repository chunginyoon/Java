<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SSAFY</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th {
	background: gray;
}

th, td {
	border: 1px dotted black;
	text-align: center;
}

#selectedDel {
	text-align: right;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<form method="" action="">
		<table>
			<tr>
				<th>이슈번호</th>
				<th>학번</th>
				<th>교육생명</th>
				<th>반</th>
				<th>지역</th>
				<th>날짜</th>
				<th>사유</th>
				<th>삭제</th>
			</tr>

			<c:forEach var="user" items="${list}">
				<c:if test="${user.userNumber eq userInfo.userNumber}">
					<c:forEach var="attend" items="${user.attendance}">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/user/detail?ano=${attend.ano}">${attend.ano}</a></td>
							<td>${attend.userNumber}</td>
							<td>${user.name}</td>
							<td>${user.rclass}</td>
							<td>${user.rname}</td>
							<td>${attend.issueDate}</td>
							<td>${attend.issue}</td>
							<td>
								<label class="checkbox-inline">
									<input type="checkbox" name="chk" class="chk" onclick="chkClicked()">
								</label>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</table>

		<input type="submit" value="선택항목삭제">
	</form>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	
	<script type="text/javascript">
	</script>
</body>
</html>