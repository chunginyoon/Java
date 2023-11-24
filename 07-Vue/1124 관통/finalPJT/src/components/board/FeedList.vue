<script setup>
import { useRouter } from "vue-router";
import { ref, computed } from "vue";
import { useFeedStore } from "@/stores/feed";

const feedStore = useFeedStore(); //feed.js 참조
const router = useRouter();

const posts = computed(() => feedStore.posts);
const pageable = computed(() => feedStore.pageable); // 페이지 정보 가져오기(현재 페이지(pageNumber), 사이즈(pageSize))
const totalPages = computed(() => feedStore.totalPages); //총 페이지
const currentPage = ref(0);
const pageSize = ref(0);

const init = async () => {
  await feedStore.getPosts();
  currentPage.value = pageable.value.pageNumber;
  pageSize.value = pageable.value.pageSize;
};

init();

const moveView = (postId) => {
  console.log("상세 피드로 이동");
  router
    .push({ name: "board-feedview", params: { postId: postId } })
    .then(() => {
      window.scrollTo(0, 0);
    });
};
</script>

<template>
  <div class="container">
    <h1>전체 피드</h1>
    <v-row>
      <v-col v-for="post in posts" :key="post.id" cols="12" sm="6" md="4">
        <v-card class="feed">
          <v-img
            @click="moveView(post.id)"
            :src="`http://localhost:8080/images/${post.image[0].imageUrl}`"
            class="align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            width="300"
            height="250"
            cover
          >
            <v-card-title
              class="text-white"
              v-text="post.content"
            ></v-card-title>
          </v-img>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
              size="small"
              color="surface-variant"
              variant="text"
              icon="mdi-heart"
            ></v-btn>

            <v-btn
              size="small"
              color="surface-variant"
              variant="text"
              icon="mdi-bookmark"
            ></v-btn>

            <v-btn
              size="small"
              color="surface-variant"
              variant="text"
              icon="mdi-share-variant"
            ></v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <!-- <PageNavigation
      @pageChange="onPageChange"
      :current-page="currentPage"
      :total-page-count="totalPageCount"
      :navigation-size="5"
    /> -->
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
  text-decoration: none;
}

.container {
  /* width: 56vw; */
  margin: 0 auto;
}
</style>
