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
    <link rel="stylesheet" href="${root }/css/signup.css"/>
    
</head>
<body>
    <div class="container py-3">
        <header>
                <%@ include file="/common/nav.jsp" %>

       
        </header>

        <!-- main start -->
        <main class="signUp-main">
        <div class="input-form-background row">
            <div class="input-form col-md-12 mx-auto">
                <h4 class="mb-3">회원가입</h4>
                <form class="submit-form" action="./index.html">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" id="name" placeholder="" value="">
                            <div class="invalid-feedback">
                                이름을 입력해주세요.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">

                        <label for="ID">아이디</label>
                        <input type="text" class="form-control" id="ID"/>
                        <div class="invalid-feedback">아이디를 입력해주세요</div>
                    </div>


                    <div class="mb-3">
                        <label for="password">비밀번호</label>
                        <input type="password" class="form-control" id="pw">
                        <div class="invalid-feedback">비밀번호를 입력해주세요</div>
                    </div>

                    <div class="mb-3">
                        <label for="address">주소</label>
                        <input type="text" class="form-control" id="address" placeholder="대전광역시 서구"/>
                        <div class="invalid-feedback">주소를 입력해주세요</div>
                    </div>

                    <div class="mb-3">
                        <label for="phone">전화번호</label>
                        <input type="text" class="form-control" id="phone" placeholder="010-0000-0000"/>
                        <div class="invalid-feedback">전화번호를 입력해주세요</div>
                    </div>
                    <div class="mb-4">
                        <button class="btn btn-primary btn-lg btn-block align-self-center" type="submit">가입완료</button>

                    </div>
                </form>


            </div>
        </div>
        </main>

        <footer class="pt-4 my-md-5 pt-md-5 border-top">
            <p style="color : black">hello world</p>
        </footer>
        
        
    </div>

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${root }/js/signUp.js"></script>
</body>
</html>