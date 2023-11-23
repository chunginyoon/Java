<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>공지사항</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/pricing/" />
<link rel="stylesheet" href="${root }/css/board.css" />
<link rel="stylesheet" href="${root }/css/main.css" />
<!-- <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/pricing/" />
		    <link href="./css/bootstrap.min.css" rel="stylesheet" /> -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=851dd145bf86d3fad49cd359e65ecdf8&libraries=services"></script>
<link rel="stylesheet"
	href="http://www.songdobeer.com/songdo_html/lib/simplyscroll/simplyscroll.css" />
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="http://www.songdobeer.com/songdo_html/lib/simplyscroll/simplyscroll.min.js"></script>
<link rel="stylesheet"
	href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="/common/nav.jsp"%>

		</header>

		<!-- main start -->
		<main>
			<div class="row justify-content-center">
				<div class="col-lg-8 col-md-10 col-sm-12">
					<h2 class="my-3 py-3 shadow-sm bg-light text-center">
						<mark class="sky">글목록</mark>
					</h2>
				</div>
				<div class="col-lg-8 col-md-10 col-sm-12">
					<div class="row align-self-center mb-2">
						<div class="col-md-2 text-start">
							<button type="button" id="btn-mv-register"
								class="btn btn-outline-primary btn-sm" onclick="write()">글쓰기</button>
						</div>
						<div class="col-md-7 offset-3">
							<form class="d-flex" id="form-search" action="">
								<input type="hidden" name="action" value="list" /> <input
									type="hidden" name="pgno" value="1" /> <select name="key"
									id="key" class="form-select form-select-sm ms-5 me-1 w-50"
									aria-label="검색조건">
									<option value="" selected>검색조건</option>
									<option value="article_no">글번호</option>
									<option value="subject">제목</option>
									<option value="user_id">작성자</option>
								</select>
								<div class="input-group input-group-sm">
									<input type="text" name="word" id="word" class="form-control"
										placeholder="검색어..." />
									<button id="btn-search" class="btn btn-dark" type="button">검색</button>
								</div>
							</form>
						</div>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articles}">
								<tr>
									<th scope="row">${article.articleNo}</th>
									<td class="text-start"><a href="#"
										class="article-title link-dark" data-no="${article.articleNo}"
										style="text-decoration: none"> ${article.subject} </a></td>
									<td>${article.userId}</td>
									<td>${article.hit}</td>
									<td>${article.registerTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">${navigation.navigator}</div>
			</div>
			<!-- 			<form id="form-param" method="get" action="">
				<input type="hidden" id="p-action" name="action" value=""> <input
					type="hidden" id="p-pgno" name="pgno" value=""> <input
					type="hidden" id="p-key" name="key" value=""> <input
					type="hidden" id="p-word" name="word" value="">
			</form> -->
		</main>
	</div>
	<script>
		function write() {
			location.href = "${root}/article?action=mvwrite"
		}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
