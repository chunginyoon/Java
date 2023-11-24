<script setup>
import { computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useBoardStore } from "@/stores/board";

const boardStore = useBoardStore();
const article = computed(() => boardStore.article); //store 데이터를 반응형으로 가져오기

const router = useRouter();
const route = useRoute();

boardStore.getArticle(route.params.articleNo);

const deleteArticle = async () => {
  try {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    //await를 사용한 이유?
    //=> 삭제가 완료된 이후 목록화면으로 가야 삭제가 된 목록을 불러옴
    //=> await를 사용하지 않으면? 삭제가 안된 상태의 목록을 먼저 조회해 올 수 있음
    await boardStore.deleteArticle(article.value.articleNo);
    router.push("/board");
    alert("삭제 완료");
  } catch (error) {
    console.log("삭제 에러:", error);
    alert("삭제 실패");
  }
};
</script>

<template>
  <div>
    <h1>게시판 상세</h1>
    <!-- 목록 화면 이동 -->
    <RouterLink to="/board">목록</RouterLink>
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
        <td>{{ article.content }}</td>
      </tr>
    </table>
    <!-- 수정 화면 이동 -->
    <RouterLink
      :to="{
        name: 'article-modify',
        params: { articleNo: article.articleNo },
      }"
      >수정</RouterLink
    >
    <!-- 삭제 button -->
    <button @click="deleteArticle">삭제</button>
  </div>
</template>

<style scoped></style>
