<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="model.Country"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>등록 페이지</title>
</head>
<body>
	<h1>수정 페이지</h1>
	<a href="<%=request.getContextPath()%>/country?action=list">목록으로</a>
	<%-- post로 보내면 물음표 없애기. form 태그는 주소를 자기 맘대로 설정하므로 get 방식은 hidden을 사용해야 한다
	<form action="<%=request.getContextPath()%>/country" method="get">
		<input type="hidden" name="action" value="regist">
	--%>
	<%
		Country country = (Country) request.getAttribute("country");
	%>
	<form action="<%=request.getContextPath()%>/country" method="post">
		<input type="hidden" name="action" value="update">
		<fieldset>
			<label><input type="text" name="code" value="<%=country.getCode()%>"> : 코드</label>
			<br>
			<label><input type="text" name="name" value="<%=country.getName()%>"> : 국가명</label>
			<br>
			<select name="continent">
				<option value="Asia">아시아</option>
				<option value="Europe">유럽</option>
			</select>
			<br>
			<label><input type="text" name="gnp" value="<%=country.getGnp()%>"> : gnp</label>
		</fieldset>
		<button type="submit">수정</button>
	</form>
	<script>
	document.querySelector("[name=continent]").value="<%=country.getContinent()%>";
	</script>
</body>
</html>