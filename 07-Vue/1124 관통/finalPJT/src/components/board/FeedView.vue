<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useFeedStore } from "@/stores/feed";

const feedStore = useFeedStore(); //feed.js 참조
const router = useRouter();
const route = useRoute();

const postId = ref(route.params.postId); //파라미터 가져오기
const post = computed(() => feedStore.post);
const images = computed(() => feedStore.images);

const init = async () => {
  await feedStore.getPost(postId.value);
};

init();
</script>

<template>
  <div class="container">
    <h1>피드 상세 페이지</h1>
    <span>{{ post.content }}</span>
    <!--사진-->
    <div v-for="img in images" :key="img.id" class="img">
      <v-img :src="`http://localhost:8080/images/${img.imageUrl}`"></v-img>
    </div>

    <!-- <div class="like" @click="toggleLike">
      <div class="like-part">
        <img
          v-if="isLiked"
          :src="@/assets/img/full-heart.svg"
          alt="full-heart"
          :class="{ selected: isLiked }"
        />
        <img
          v-if="!isLiked"
          src="@/assets/img/empty-heart.svg"
          alt="full-heart"
          :class="{ selected: !isLiked }"
        />
      </div>
      좋아요 {{ numberOfLikes }}개
    </div> -->
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
