<script setup></script>

<template>
  <section class="signin">
    <h1>탈퇴하기</h1>
    <div class="signin__card">
      <h2>본인 확인</h2>
      <form>
        <input
          type="password"
          placeholder="비밀번호를 입력하세요."
          name="userPassword"
        />
        <input type="submit" value="탈퇴하기" />
      </form>
      <!-- <div class="actions">
          <a href="${pageContext.request.contextPath}/member?action=mvlogin"
            >로그인</a
          >
          <a href="javascript:void(0)">아이디 찾기</a>
          <a href="javascript:void(0)">비밀번호 찾기</a>
        </div> -->
    </div>
  </section>
</template>

<style scoped>
.signin {
  margin-top: 120px;
  padding: 100px 0 150px;
  background-image: url("../images/main_bg.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-attachment: fixed;
  background-size: cover;
}
.signin h1 {
  color: #fff;
  background-color: #5a4e4e;
  border-radius: 6px 6px 0 0;
  padding-top: 15px;
  width: 500px;
  height: 70px;
  margin: 0 auto;
  font-size: 30px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 0px;
}
.signin__card {
  width: 500px;
  margin: 0 auto;
  border-radius: 0 0 6px 6px;
  background-color: #f6f5f0;
  box-shadow: 2px 2px 20px rgba(0, 0, 0, 0.3);
  color: #555;
}
.signin__card h2 {
  padding: 30px;
  font-size: 18px;
  text-align: center;
  border-bottom: 1px solid #ddd;
}
.signin__card h2 strong {
  font-weight: 700;
  color: #006633;
}
.signin__card form {
  padding: 30px 22px;
}
.signin__card form input {
  width: 100%;
  margin-bottom: 12px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 14px;
}
.signin__card form [type="submit"] {
  background-color: #454141;
  border: none;
  color: #fff;
  font-size: 20px;
  cursor: pointer;
}
.signin__card p {
  font-size: 12px;
  color: #006633;
  text-align: center;
}
.signin__card .actions {
  display: flex;
  border-top: 1px solid #ddd;
}
.signin__card .actions a {
  flex-grow: 1;
  flex-basis: 0;
  text-align: center;
  padding: 20px;
  color: #555;
  font-size: 14px;
  border-right: 1px solid #ddd;
}
.signin__card .actions a:last-child {
  border-right: none;
}
.signin__card .actions a:hover {
  text-decoration: underline;
}
</style>
