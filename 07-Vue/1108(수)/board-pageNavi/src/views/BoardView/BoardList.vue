<script setup>
import PageNavigation from "../../components/navigation/PageNavigation.vue";
import SearchBar from "../../components/SearchBar.vue";
import { RouterLink, useRouter } from "vue-router";
import { ref } from "vue";
import axios from "axios";

const router = useRouter();

const params = ref({
  key: "", //조건 검색 시 컬럼명
  word: "", //해당 컬럼에 일치하는 데이터
  pgno: 1, //조회할 페이지 번호
  spp: 20, //한번에 얻어올 게시글 개수
});

const totalPageCount = ref(0); //전체 페이지 개수

const list = ref([]);

const getBoardList = async () => {
  const url = "http://localhost/vue/board";

  const response = await axios.get(url, {
    params: params.value,
  });
  console.log("axios의 응답:", response);

  list.value = response.data.articles;
  totalPageCount.value = response.data.totalPageCount;
};

getBoardList();

const moveDetail = (articleNo) => {
  router.push({ name: "article-detail", params: { articleNo } });
};

const changePage = (pageNum) => {
  console.log("페이지 변경 , 페이지 번호:", pageNum);
  params.value.pgno = pageNum;
  getBoardList();
};
const getSearchArticles = (searchKeyword) => {
  console.log("BoardList의 조건 검색 메소드 호출:", searchKeyword);

  params.value.key = searchKeyword.key;
  params.value.word = searchKeyword.word;
  params.value.pgno = 1;

  getBoardList();
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
        v-for="article in list"
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
      @change-page="changePage"
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
