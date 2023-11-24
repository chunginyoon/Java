<script setup>
import { computed } from "vue";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const isLogin = computed(() => !!authStore.token); //!!의 경우 해당 정보가 truthy하다면 true를 반환
const userId = computed(() => authStore.user.userId);

const logout = () => {
  if (!confirm("로그아웃 하시겠습니까?")) return;
  authStore.logout();
};
</script>

<template>
  <header>
    <ul>
      <li>
        <RouterLink to="/">홈</RouterLink>
      </li>
      <li>
        <RouterLink to="/board">게시판</RouterLink>
      </li>
    </ul>
    <ul>
      <template v-if="!isLogin">
        <li><RouterLink to="/login">로그인</RouterLink></li>
        <li><RouterLink to="">회원가입</RouterLink></li>
      </template>
      <template v-else>
        <li>{{ userId }}님 로그인</li>
        <li><button @click="logout">로그아웃</button></li>
      </template>
    </ul>
  </header>
</template>

<style scoped></style>
