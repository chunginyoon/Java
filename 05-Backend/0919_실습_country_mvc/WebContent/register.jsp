<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� ������</title>
</head>
<body>
	<h1>��� ������</h1>
	<a href="<%=request.getContextPath()%>/country?action=list">�������</a>
	<%-- post�� ������ ����ǥ ���ֱ�. form �±״� �ּҸ� �ڱ� ����� �����ϹǷ� get ����� hidden�� ����ؾ� �Ѵ�
	<form action="<%=request.getContextPath()%>/country" method="get">
		<input type="hidden" name="action" value="regist">
	--%>
	<form action="<%=request.getContextPath()%>/country" method="post">
		<input type="hidden" name="action" value="regist">
		<fieldset>
			<label><input type="text" name="code"> : �ڵ�</label>
			<br>
			<label><input type="text" name="name"> : ������</label>
			<br>
			<select
				name="continent">
				<option value="Asia">�ƽþ�</option>
				<option value="Europe">����</option>
			</select>
			<br>
			<label><input type="text" name="gnp"> : gnp</label>
		</fieldset>
		<button type="submit">���</button>
	</form>
</body>
</html>