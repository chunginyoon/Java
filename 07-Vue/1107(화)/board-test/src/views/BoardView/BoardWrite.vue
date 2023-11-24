<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const articleForm = ref({
  userId: "ssafy",
  subject: "",
  content: "",
});

// 등록 요청 보내고 등록 성공 시 목록 페이지 이동
const write = async () => {
  try {
    const url = "http://localhost/vue/board";
    const response = await axios.post(url, articleForm.value);

    console.log("등록에 대한 응답 : ", response);

    alert("등록 성공!");
    // router.push("/board/list");
    router.push({ path: "/board/list" });
  } catch (error) {
    // 등록 시 에러 발생
    console.log("에러 내용 : ", error);
    alert("등록 실패");
  }
};
</script>

<template>
  <div>
    <h1>게시글 등록</h1>
    <form @submit.prevent="write">
      <label
        >제목<input type="text" v-model="articleForm.subject" required
      /></label>
      <br />
      <textarea
        cols="30"
        rows="10"
        v-model="articleForm.content"
        required
      ></textarea>
      <button>등록</button>
    </form>
  </div>
</template>

<style scoped></style>
