<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.member.model.MemberDto"%>
<%
	String root = request.getContextPath();

MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

String checked = "";
String saveId = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if ("ssafy_id".equals(cookie.getName())) {
	checked = " checked";
	saveId = cookie.getValue();
	break;
		}
	}
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link href="<%=root%>/assets/css/app.css" rel="stylesheet" />
<title>SSAFY</title>
</head>
<body>
	<%
		if (memberDto == null) {
	%>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">로그인</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-login" method="POST" action="">
					<input type="hidden" name="action" value="login" />
					<div class="form-check mb-3 float-end">
						<input class="form-check-input" type="checkbox" value="ok"
							id="saveid" name="saveid" <%=checked%> /> <label
							class="form-check-label" for="saveid"> 아이디저장 </label>
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디..." value="<%=saveId%>" />
					</div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd"
							placeholder="비밀번호..." />
					</div>
					<div class="text-danger mb-2">${msg}</div>
					<div class="col-auto text-center">
						<button type="button" id="btn-login"
							class="btn btn-outline-primary mb-3">로그인</button>
						<button type="button" id="btn-mv-join"
							class="btn btn-outline-success mb-3">회원가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script>
      document.querySelector("#btn-mv-join").addEventListener("click", function () {
    	location.href = "<%=root%>/user?action=mvjoin";
      });
      
      document.querySelector("#btn-login").addEventListener("click", function () {
        if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-login");
          form.setAttribute("action", "<%=root%>
		/user");
						form.submit();
					}
				});
	</script>
	<%
} else {
%>
	<div class="container">
		<!-- 로그인 사용자 정보(로그아웃) 출력 -->

		<div class="row justify-content-center">
			<div class="row justify-content-center">
				<div class="col-lg-8 col-md-10 col-sm-12 m-3 text-end">
					<strong><%=memberDto.getUserName()%></strong> (<%=memberDto.getUserId()%>)님
					안녕하세요. <a href="<%=root%>/user?action=logout">로그아웃</a><br />
				</div>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12 text-center">
				<a href="<%=root%>/article?action=mvwrite">글쓰기</a><br /> <a
					href="<%=root%>/article?action=list&pgno=1&key=&word=">글목록</a>
			</div>
		</div>
	</div>
	<%
}
%>
</body>
</html>
