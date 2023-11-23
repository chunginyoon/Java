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
  </head>
  <body>
    <div class="container py-3">
      <header>
        <!-- 상단 네비게이션 바 -->
     <%@ include file="/common/nav.jsp" %>
      </header>
      <div class="info">
        
        
      </div>
    </div>

    <script src="${root }/js/myPage.js"></script>
  </body>
</html>
    