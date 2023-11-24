<script setup>
import PageNavigation from "../../components/navigation/PageNavigation.vue";
import SearchBar from "../../components/SearchBar.vue";
import { RouterLink, useRouter } from "vue-router";
import { ref, computed } from "vue";
import { useBoardStore } from "../../stores/board";

//1. store 객체 얻어오기
const boardStore = useBoardStore();

//2. 반응형 데이터 연결하기
const articles = computed(() => {
  return boardStore.articles; //읽기 전용으로 articles 사용 가능
});
const totalPageCount = computed(() => boardStore.totalPageCount);

const params = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
  pgno: 1, //조회할 페이지 번호
  spp: 20, //한번에 얻어올 게시글 개수
});

//목록 조회 필요
boardStore.getArticles(params.value);

const router = useRouter();
const moveDetail = (articleNo) => {
  router.push({ name: "article-detail", params: { articleNo } });
};

const changePage = (pageNum) => {
  console.log("페이지 변경 , 페이지 번호:", pageNum);
  params.value.pgno = pageNum;
  getArticles();
};
const getSearchArticles = (searchKeyword) => {
  console.log("BoardList의 조건 검색 메소드 호출:", searchKeyword);

  params.value.key = searchKeyword.key;
  params.value.word = searchKeyword.word;
  params.value.pgno = 1;

  boardStore.getArticles(params.value);
};
</script>

<template>
  <div>
    <h1>게시판 목록</h1>
    <RouterLink :to="{ name: 'article-write' }">글쓰기</RouterLink>

    <SearchBar
      @search-event="getSearchArticles"
      :options="[
        { value: 'userid', text: '작성자' },
        { value: 'article_no', text: '글번호' },
        { value: 'subject', text: '제목' },
      ]"
    ></SearchBar>

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
        v-for="article in articles"
        :key="article.articleNo"
        @click="moveDetail(article.articleNo)"
      >
        <td>{{ article.articleNo }}</td>
        <td>{{ article.subject }}</td>
        <td>{{ article.hit }}</td>
        <td>{{ article.registerTime }}</td>
        <td>{{ article.userId }}</td>
      </tr>
    </table>
    <PageNavigation
      v-on:change-page="changePage"
      :current-page="params.pgno"
      :total-page-count="totalPageCount"
      :navigation-size="5"
    ></PageNavigation>
  </div>
</template>

<style scoped>
.article-item:hover {
  background-color: aquamarine;
}
</style>
