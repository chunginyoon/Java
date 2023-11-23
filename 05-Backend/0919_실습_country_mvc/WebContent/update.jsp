<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="model.Country"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� ������</title>
</head>
<body>
	<h1>���� ������</h1>
	<a href="<%=request.getContextPath()%>/country?action=list">�������</a>
	<%-- post�� ������ ����ǥ ���ֱ�. form �±״� �ּҸ� �ڱ� ����� �����ϹǷ� get ����� hidden�� ����ؾ� �Ѵ�
	<form action="<%=request.getContextPath()%>/country" method="get">
		<input type="hidden" name="action" value="regist">
	--%>
	<%
		Country country = (Country) request.getAttribute("country");
	%>
	<form action="<%=request.getContextPath()%>/country" method="post">
		<input type="hidden" name="action" value="update">
		<fieldset>
			<label><input type="text" name="code" value="<%=country.getCode()%>"> : �ڵ�</label>
			<br>
			<label><input type="text" name="name" value="<%=country.getName()%>"> : ������</label>
			<br>
			<select name="continent">
				<option value="Asia">�ƽþ�</option>
				<option value="Europe">����</option>
			</select>
			<br>
			<label><input type="text" name="gnp" value="<%=country.getGnp()%>"> : gnp</label>
		</fieldset>
		<button type="submit">����</button>
	</form>
	<script>
	document.querySelector("[name=continent]").value="<%=country.getContinent()%>";
	</script>
</body>
</html>