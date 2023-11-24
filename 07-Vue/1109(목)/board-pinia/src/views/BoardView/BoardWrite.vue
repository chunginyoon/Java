<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

//1. 객체 얻기
import { useBoardStore } from "../../stores/board";
const boardStore = useBoardStore();

const router = useRouter();

const writeForm = ref({
  userId: "ssafy",
  subject: "",
  content: "",
});

//등록 요청 보내고 등록 성공 시 목록 페이지 이동
const write = async () => {
  try {
    if (!confirm("이대로 등록하시겠습니까?")) return;

    const response = await boardStore.writeArticle(writeForm.value);
    console.log("등록에 대한 응답 : ", response);

    alert("등록 성공");
    // router.push("/board/list")
    router.push({ path: "/board/list" });
  } catch (error) {
    //등록 시 에러 발생
    console.log("에러 내용:", error);
    alert("등록 실패");
  }
};
</script>

<template>
  <div>
    <h1>게시글 등록</h1>
    <form @submit.prevent="write">
      <label
        ><input type="text" v-model.trim="writeForm.subject" required />
        제목</label
      >
      <br />
      <textarea
        cols="30"
        rows="10"
        v-model="writeForm.content"
        required
      ></textarea>
      <button>등록</button>
    </form>
  </div>
</template>

<style scoped></style>
