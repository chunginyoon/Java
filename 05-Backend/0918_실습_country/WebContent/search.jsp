<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.Country"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>나라 목록 상세 조회</h1>
	<table>
		<tr>
			<th>나라코드</th>
			<th>국가명</th>
			<th>대륙</th>
			<th>GNP</th>
		</tr>
		<%
		Country country = (Country) request.getAttribute("code");
			out.write("<tr>");
			out.write("<td>"+country.getCode()+"</td>");
			out.write("<td>"+country.getName()+"</td>");
			out.write("<td>"+country.getContinent()+"</td>");
			out.write("<td>"+country.getGnp()+"</td>");
			out.write("</tr>");
		%>
	</table>
</body>
</html>