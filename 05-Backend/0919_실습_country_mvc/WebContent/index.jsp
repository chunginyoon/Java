<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 기능 몽록
     전체 조회 페이지 반환(/country?action=list)
     국가코드 활용한 상세 조회(/country?action=view&code=ABW)
     국가 정보 등록 페이지 이동 요청(/country?action=mvRegist)
     국가 정보 등록 요청 Post /country
     	body => action=regist&code=AAA&name=ee...
     국가 정보 수정 페이지 이동 요청(/country?action=mvUpdate)
     국가 정보 수정 요청 Post /country
     	body => action=update&code=AAA&name=ee...
     국가 정보 삭제(/country?action=delete&code=ABW)
      --%>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	메인 화면
	<br>
	<a href="<%=root%>/country?action=list">전체 목록
		조회</a>
	<%-- ?는 get으로 request를 받아올 수 있다 --%>
	<br>
	<a href="<%=root%>/country?action=mvRegist">국가
		정보 등록 페이지</a>
</body>
</html>