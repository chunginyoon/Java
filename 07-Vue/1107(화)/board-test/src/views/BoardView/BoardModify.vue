<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import axios from "axios";

//현재 articleNo를 얻어와야 함
const route = useRoute();
console.log("현재 라우트 정보:", route);

const article = ref({});

const getModifyArticle = async () => {
  const url = "http://localhost/vue/board/modify/" + route.params.articleNo;

  const response = await axios.get(url);
  console.log("axios의 응답: ", response);

  article.value = response.data;
};

getModifyArticle();

const router = useRouter();

//수정 요청 보내고 수정 성공 시 목록 페이지 이동
const modify = async () => {
  try {
    const url = "http://localhost/vue/board";
    const response = await axios.put(url, article.value);

    console.log("수정에 대한 응답 : ", response);

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
    <h1>게시글 수정</h1>
    <form @submit.prevent="modify">
      <label
        >제목<input type="text" v-model="article.subject" required
      /></label>
      <br />
      <textarea
        cols="30"
        rows="10"
        v-model="article.content"
        required
      ></textarea>
      <button>등록</button>
    </form>
  </div>
</template>

<style scoped></style>
