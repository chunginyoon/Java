<script setup>
import { useRoute, RouterLink } from "vue-router";
import { ref } from "vue";
import axios from "axios";

//articleNo를 얻어와야 함
const route = useRoute();
console.log("현재 라우트 정보:", route);

const article = ref({});

const getArticle = async () => {
  const url = "http://localhost/vue/board/" + route.params.articleNo;

  const response = await axios.get(url);
  console.log("axios의 응답: ", response);

  article.value = response.data;
};

getArticle();
</script>

<template>
  <div>
    <h1>게시판 상세</h1>
    <table>
      <tr>
        <th>글 번호</th>
        <td>{{ article.articleNo }}</td>
      </tr>
      <tr>
        <th>글 제목</th>
        <td>{{ article.subject }}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>{{ article.hit }}</td>
      </tr>
      <tr>
        <th>작성일</th>
        <td>{{ article.registerTime }}</td>
      </tr>
      <tr>
        <th>작성자</th>
        <td>{{ article.userId }}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td>{{ article.content }}</td>
      </tr>
    </table>
    <RouterLink
      :to="{ name: 'article-modify', params: { articleNo: article.articleNo } }"
      >수정하기</RouterLink
    >
  </div>
</template>

<style scoped></style>
