<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyHome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/pricing/" />
    <link rel="stylesheet" href="${root }/css/login.css"/>
    
</head>
<body>
    <div class="container py-3">
        <header>
       		<%@ include file="/common/nav.jsp" %>

       
        </header>

        <!-- main start -->
        <main class="login-main">
            <div class="p-3 pb-md-4 mx-auto text-center">
                <div id="container">
                    <div class="loginBox">
                        <div id="loginBoxTitle">
                            My home Login
                        </div>

                        <div id="inputBox">
                            <div class="input-form-box">
                                <span>아이디</span>
                                <input type="text" name="uid" class="form-control"/>
                            </div>

                            <div class="input-form-box">
                                <span>비밀번호</span>
                                <input type="password" name="upw" class="form-control"/>
                            </div>

                            <div class="button-login-box">
                                <button id="login-btn"type="button" class="btn btn-primary btn-xs" style="width:100%;">
                                    로그인
                                </button>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </main>

        <footer class="pt-4 my-md-5 pt-md-5 border-top">
            <p style="color : black">hello world</p>
        </footer>
        
        
    </div>

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${root }/js/login.js"></script>
</body>
</html>