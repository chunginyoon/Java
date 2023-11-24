<script setup>
import { ref } from "vue";
import axios from "axios";
import { RouterLink, useRouter } from "vue-router";

const router = useRouter();

const list = ref([]);

const getBoardList = async () => {
  const url = "http://localhost/vue/board";

  const response = await axios.get(url);
  console.log("axios의 응답: ", response);

  list.value = response.data.articles;
};

getBoardList();

const moveDetail = (articleNo) => {
  router.push({ name: "article-detail", params: { articleNo } });
};
</script>

<template>
  <div>
    <h1>게시판 목록</h1>
    <RouterLink :to="{ name: 'article-write' }">글쓰기</RouterLink>
    <table>
      <tr>
        <th>글 번호</th>
        <th>글 제목</th>
        <th>조회수</th>
        <th>작성일</th>
        <th>작성자</th>
      </tr>
      <tr
        class="article-item"
        v-for="article in list"
        :key="article.articleNo"
        @click="moveDetail(article.articleNo)"
      >
        <th>{{ article.articleNo }}</th>
        <th>{{ article.subject }}</th>
        <th>{{ article.hit }}</th>
        <th>{{ article.registerTime }}</th>
        <th>{{ article.userId }}</th>
      </tr>
    </table>
  </div>
</template>

<style scoped>
.article-item:hover {
  background-color: aquamarine;
}
</style>
