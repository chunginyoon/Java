<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	메인 화면 입니다.
	<%
		String root = request.getContextPath();
		System.out.println("프로젝트 루트 경로 : "+ root);
	%>
	<a href="<%=root%>/country?action=list">목록 페이지</a>
	<a href="<%=root%>/country?action=detail">상세 조회 페이지</a>
	
	<form action="/country">
		<input type="hidden" name="action" value="register">
		code: <input type="text" name = "code">
		name: <input type="text" name = "name">
		continent: <input type="text" name = "continent">
		gnp: <input type="text" name = "gnp">
		<input type="submit" value="등록">
	</form>
</body>
</html>