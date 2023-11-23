<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       <div class="d-flex flex-column flex-md-row align-items-md-center pb-3 mb-4 border-bottom">
                <a href="${root}" class="fs-4 title">Where is My home</a>
                <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
                    <a class="me-3 py-2 text-dark text-decoration-none" href="${root}/member?action=mvLogin" id="signIn">로그인</a>
                    <a class="me-3 py-2 text-dark text-decoration-none" href="${root }/member?action=signUp" id="signUp">회원가입</a>
                    <a class="me-3 py-2 text-dark text-decoration-none d-none" href="${root }/member?action=logout" id="logOut" >로그아웃</a>
                    <a class="me-3 py-2 text-dark text-decoration-none d-none" href="${root }/member?action=myPage" id="myPage">myPage</a>
                    <a class="me-3 py-2 text-dark text-decoration-none" href="${root }/article?action=list" id="bulletin-board">공지사항</a>
                    
                </nav>
          </div>
</body>
</html>