<script setup>
import { computed } from "vue";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const nickname = computed(() => authStore.user.nickname);

const router = useRouter();
</script>

<template>
  <div class="container">
    <h1>회원정보 수정</h1>
    <div class="mypage">
      <div class="profile">
        <div class="img"><img src="@/assets/img/profile.jpg" /></div>
        <button>프로필 사진 수정</button>
      </div>

      <div class="form">
        <form>
          <input type="text" v-model="nickname" placeholder="닉네임" />
          <br />
          <input type="text" placeholder="비밀번호를 입력하세요." />
          <br />
          <input type="text" placeholder="비밀번호 확인" />
          <br />
          <button @click="moveModify">수정하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
  text-decoration: none;
}

.container {
  width: 60vw;
  height: 100vh;
  margin: 0 auto;
}

.mypage {
  margin: 0 auto;
  display: flex;
  width: 80%;
}

.profile {
  width: 40%;
}

.img {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  overflow: hidden;
}
.img > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile > button {
  margin-left: 40px;
  margin-top: 20px;
  height: 30px;
}

.form {
  width: 60%;
  margin-top: 40px;
}

form > input {
  width: 60%;
  margin-bottom: 12px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 14px;
}

form > button {
  width: 60%;
  height: 40px;
}
</style>
