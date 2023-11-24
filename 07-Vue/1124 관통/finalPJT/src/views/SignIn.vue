<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
const authStore = useAuthStore();

const router = useRouter();

const signInForm = ref({
  userName: "", //아이디
  nickname: "", //닉네임
  password: "", //비밀번호
});

const idValid = computed(() => {
  return /^[A-Za-z0-9]+$/.test(signInForm.value.userName);
});

const passwordValid = computed(() => {
  return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(
    signInForm.value.password
  );
});

const signIn = async () => {
  try {
    await authStore.signIn(signInForm.value);
    router.push("/");
  } catch (error) {
    console.log("회원가입 실패 에러:", error);
    alert("이미 가입된 회원입니다");
  }
};
</script>

<template>
  <div>
    <section class="signin">
      <h1>Sign In</h1>
      <div class="signin__card">
        <h2><strong>Welcome!</strong> 회원가입을 시작합니다.</h2>
        <form @submit.prevent="signIn">
          <input
            type="text"
            v-model="signInForm.userName"
            placeholder="아이디를 입력하세요."
            maxlength="20"
          />
          <div v-if="!idValid">유효하지 않은 아이디 입니다.</div>
          <button>아이디 중복체크</button>
          <input
            type="text"
            v-model="signInForm.nickname"
            placeholder="닉네임을 입력하세요."
            maxlength="20"
          />

          <input
            type="password"
            v-model="signInForm.password"
            placeholder="비밀번호를 입력하세요."
            maxlength="16"
          />
          <div v-if="!passwordValid">유효하지 않은 비밀번호 입니다.</div>
          <input type="password" placeholder="비밀번호 확인" maxlength="16" />
          <input type="submit" value="회원가입" />
          <p>* 비밀번호는 6자리 이상으로 설정해주세요!</p>
        </form>
        <div class="actions">
          <a>로그인</a>
          <a href="javascript:void(0)">아이디 찾기</a>
          <a href="javascript:void(0)">비밀번호 찾기</a>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.signin {
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
