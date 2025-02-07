<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.Country"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>국가 정보 등록</title>
</head>
<body>
	<h1>수정 페이지</h1>
	<a href="<%=request.getContextPath()%>/country?action=list">목록으로</a>
	
	<% Country country = (Country) request.getAttribute("country"); %>
	<form action="<%=request.getContextPath()%>/country" method="post">
		<input type="hidden" name="action" value="update">
		<fieldset>
			<label><input type="text" name="code" value="<%=country.getCode()%>" readonly> : 코드</label>
			<br>
			<label><input type="text" name="name" value="<%=country.getName()%>"> : 국가명</label>
			<br>
			<select name="continent" >
				<option value="Asia">아시아</option>
				<option value="Europe">유럽</option>
			</select>
			<br>
			<label><input type="number" name="gnp" value="<%=country.getGnp()%>"> : gnp</label>
			
		</fieldset>
		<button type="submit">등록</button>
	</form>
	<script >
		document.querySelector("[name=continent]").value="<%=country.getContinent()%>";
	</script>
</body>
</html>