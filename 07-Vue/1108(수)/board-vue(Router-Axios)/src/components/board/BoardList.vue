<script setup>
import { ref, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board";

import BoardListItem from "@/components/board/item/BoardListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

const router = useRouter();

const articles = ref([]);

const currentPage = ref(1);
const totalPage = ref(0);

const param = reactive({
  pgno: currentPage.value,
  spp: import.meta.env.VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getArticleList();
});

function getArticleList() {
  listArticle(
    param,
    ({ data }) => {
      articles.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
}

function onPageChange(value) {
  param.pgno = value;
  getArticleList();
}

function moveWrite() {
  router.push({ name: "article-write" });
}
</script>

<template>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-md-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글목록</mark>
        </h2>
      </div>
      <div class="col-md-12">
        <div class="row align-self-center mb-2">
          <div class="col-md-2 text-start">
            <button
              type="button"
              class="btn btn-outline-primary btn-sm"
              @click="moveWrite()"
            >
              글쓰기
            </button>
          </div>
          <div class="col-md-7 offset-3">
            <form class="d-flex">
              <select
                v-model="param.key"
                class="form-select form-select-sm ms-5 me-1 w-50"
                aria-label="검색조건"
              >
                <option value="" selected>검색조건</option>
                <option value="article_no">글번호</option>
                <option value="subject">제목</option>
                <option value="userid">작성자</option>
              </select>
              <div class="input-group input-group-sm">
                <input
                  type="text"
                  v-model="param.word"
                  class="form-control"
                  placeholder="검색어..."
                />
                <button
                  class="btn btn-dark"
                  type="button"
                  @click="getArticleList"
                >
                  검색
                </button>
              </div>
            </form>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">글번호</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">조회수</th>
              <th scope="col">작성일</th>
            </tr>
          </thead>
          <tbody>
            <BoardListItem
              v-for="article in articles"
              :key="article.articleNo"
              :article="article"
            ></BoardListItem>
          </tbody>
        </table>
      </div>
      <PageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @page-change="onPageChange"
      ></PageNavigation>
    </div>
  </div>
</template>

<style scoped></style>
