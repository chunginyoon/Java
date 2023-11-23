<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>MyHome</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/5.2/examples/pricing/"
    />
    <link rel="stylesheet" href="${root }/css/main.css" />
    <!-- <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/pricing/" />
    <link href="./css/bootstrap.min.css" rel="stylesheet" /> -->
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=851dd145bf86d3fad49cd359e65ecdf8&libraries=services"
    ></script>
    <link
      rel="stylesheet"
      href="http://www.songdobeer.com/songdo_html/lib/simplyscroll/simplyscroll.css"
    />
    <script src="http://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="http://www.songdobeer.com/songdo_html/lib/simplyscroll/simplyscroll.min.js"></script>
    <link
      rel="stylesheet"
      href="https://unpkg.com/swiper@8/swiper-bundle.min.css"
    />
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
  </head>
  <body>

    <div class="container">
      <header>
       <%@ include file="/common/nav.jsp" %>
	
      </header>

      <!-- main start -->
      <main>
        
        <!-- 상단 명함 -->
        <section class="text-center">
          <img src="${root}/img/수정left.png" class="img-qua display-6"></img>
          <h5 class="display-6 fw-normal">Where is My home?</h5>
          <p class="fs-6 text-muted">아파트 실거래 정보 조회 1인자</p>
          <img src="${root }/img/수정right.png" class="img-qua display-6">
          
        </section>

        <!-- 대검색창 -->
        <section>
          <div>
            <!-- <h2>Start hiring today</h2> -->
            <div class="d-flex select-boxes justify-content-center">

              <!-- 조회 버튼 -->
              <div class="d-flex align-item-center">
                <button id="aptBtn">Search</button>
              </div>
            </div>
          </div>
        </section>

        <!-- Slides -->
        <section>
          <div class="swiper">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img src="${root }/img/건물1.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물2.jpeg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물3.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물4.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물5.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물6.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물7.png" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물8.jpg" alt="" />
              </div>
              <div class="swiper-slide">
                <img src="${root }/img/건물9.jpg" alt="" />
              </div>
            </div>
            <div class="swiper-pagination"></div>
          </div>
        </section>
      
      </main>

      <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <p style="color: black">&copy;ssafy</p>
      </footer>
    </div>
	<script>
	const aptBtn = document.querySelector("#aptBtn");
	aptBtn.addEventListener("click", function () {
	  

	  open("${root}/home?action=mvSearch", "_self");
	  
	});
	
	</script>

    <script src="${root }/js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
    