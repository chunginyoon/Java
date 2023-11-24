<script setup>
import { computed } from "vue";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const user = computed(() => authStore.user);

const router = useRouter();

const moveModify = () => {
  console.log("회원정보 수정으로 이동");
  router.push({ name: "mypage-modify" }).then(() => {
    window.scrollTo(0, 0);
  });
};

const moveSignOut = () => {
  console.log("탈퇴 페이지로 이동");
  router.push({ name: "mypage-signout" }).then(() => {
    window.scrollTo(0, 0);
  });
};
</script>

<template>
  <div class="container">
    <h1>마이페이지</h1>
    <div class="mypage">
      <div class="profile">
        <div class="img"><img src="@/assets/img/profile.jpg" /></div>
        <button>프로필 사진 수정</button>
      </div>

      <div class="form">
        <form>
          <input type="text" v-model="user.userName" readonly />
          <br />
          <input type="text" v-model="user.nickname" readonly />
          <br />
          <div class="buttons">
            <button @click="moveModify">회원정보 수정</button>
            <button @click="moveSignOut">회원 탈퇴</button>
          </div>
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

.buttons {
  display: flex;
  width: 60%;
  justify-content: space-between;
}

.buttons > button {
  width: 49%;
  height: 40px;
}
</style>
