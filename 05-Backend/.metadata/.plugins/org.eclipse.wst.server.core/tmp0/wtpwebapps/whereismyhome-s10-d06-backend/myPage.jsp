<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>마이페이지</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${root }/css/myPage.css" />
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
  </head>
  <body>
    <div class="container py-3">
      <header>
        <!-- 상단 네비게이션 바 -->
     <%@ include file="/common/nav.jsp" %>
      </header>
      
              <!-- main start -->
        <main class="signUp-main">
        <div class="input-form-background row">
            <div class="input-form col-md-12 mx-auto">
                <h4 class="mb-3">Info</h4>
                <form class="submit-form" method="post" action="${root}/member?action=updateMypage">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="name">이름</label>
                            <input name="name" type="text" class="form-control" id="name" placeholder="" value="${member.name}">
                            <div class="invalid-feedback">
                                이름을 입력해주세요.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">

                        <label for="ID">아이디</label>
                        <input name="id" type="text" class="form-control" id="ID" value="${member.memberId}" disabled/>
                        <div class="invalid-feedback">아이디를 입력해주세요</div>
                    </div>


                    <div class="mb-3">
                        <label for="password">비밀번호</label>
                        <input name="password" type="password" class="form-control" id="pw" value="${member.password}">
                        <div class="invalid-feedback">비밀번호를 입력해주세요</div>
                    </div>

                    <div class="mb-3">
                        <label for="address">주소</label>
                        <input name="address" type="text" class="form-control" id="address" placeholder="대전광역시 서구"  value="${member.address}"/>
                        <div class="invalid-feedback">주소를 입력해주세요</div>
                    </div>

                    <div class="mb-3">
                        <label for="phone">전화번호</label>
                        <input name="tel" type="text" class="form-control" id="phone" placeholder="010-0000-0000" value="${member.tel}"/>
                        <div class="invalid-feedback">전화번호를 입력해주세요</div>
                    </div>
                    <div class="mb-4">
                        <button id="updateBtn" class="btn btn-primary btn-lg btn-block align-self-center" >수정하기</button>
                        <button id="deleteBtn" class="btn btn-lg btn-block align-self-center" >탈퇴하기</button>
                    </div>
                </form>


            </div>
        </div>
        </main>
        
    </div>

    <script src="${root }/js/myPage.js"></script>
  </body>
</html>
    