<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Where Is My Home</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${root }/css/priceSearch.css">
    
  </head>
  <body>
    <div class="container py-3">
      <header>
        <!-- 상단 네비게이션 바 -->
        <%@ include file="/common/nav.jsp" %>

        <!-- 상단 메인 -->
        <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
          <img src="" alt="">
          <h5 class="display-6 fw-normal">전국 아파트 실거래 정보 검색서비스</h5>
          <p class="fs-6 text-muted">
            동별, 아파트별 거래 가격을 확인해보세요.<br />키워드 검색을 통해 주변의 인프라 시설도
            검색해보세요 !!
          </p>
          <p class="fs-6 text-muted"></p>
        </div>
      </header>

      <main>
        <!-- 셀렉트 박스 -->
        <h3>세부 검색</h3>
        <div class="d-flex select-boxes align-items-center">
          <!-- 시 -->
          <div class="form-floating">
            <select class="form-select" id="sido" aria-label="Floating label select example">
              <option selected>전체</option>
            </select>
            <label for="floatingSelect">시 선택</label>
          </div>

          <!-- 구 -->
          <div class="form-floating">
            <select class="form-select" id="gugun" aria-label="Floating label select example">
              <option selected>전체</option>
            </select>
            <label for="floatingSelect">구 선택</label>
          </div>

          <!-- 동 -->
          <div class="form-floating">
            <select class="form-select" id="dong" aria-label="Floating label select example">
              <option selected>전체</option>
            </select>
            <label for="floatingSelect">동 선택</label>
          </div>
      <!-- 년도 -->
      <div class="form-floating">
        <select class="form-select" id="year" aria-label="Floating label select example">
          <option selected>전체</option>
        </select>
        <label for="floatingSelect">년도 선택</label>
      </div>

      <!-- 월 -->
      <div class="form-floating">
        <select class="form-select" id="month" aria-label="Floating label select example">
          <option selected>전체</option>
        </select>
        <label for="floatingSelect">월 선택</label>
      </div>
          <!-- 조회 버튼 -->
          <div class="d-flex align-item-center">
            <button id="aptBtn" class="btn btn-primary h-100">조회</button>
          </div>
        </div>
      </main>
    <!-- 상단 navbar end -->
    <!--정보-->
    <div class="info border-white">
      <div class="tradeAndAptInfo border-black">
        <!--거래 정보-->
        <div id="description" class="apt border-white">거래정보</div>
        

      </div>
      <!--지도-->
      <div id="map" class="map border-white">지도</div>
      
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=851dd145bf86d3fad49cd359e65ecdf8&libraries=services,clusterer,drawing"></script>
    <script src="${root }/js/priceSearch.js"></script>
    
    
  </body>
</html>
