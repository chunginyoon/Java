<script setup>
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { ref } from "vue";

const router = useRouter();
const route = useRoute();

const article = ref({});

const getModifyInfo = async () => {
  const url = `http://localhost/vue/board/modify/${route.params.articleNo}`;
  const response = await axios.get(url);
  article.value = response.data;
};

getModifyInfo();

const modify = async () => {
  const url = `http://localhost/vue/board`;
  const response = await axios.put(url, article.value);

  console.log("수정 요청에 대한 응답:", response);

  router.push("/board");
};
</script>

<template>
  <div>
    <h1>게시글 수정</h1>
    <form @submit.prevent="modify">
      <label
        ><input type="text" v-model.trim="article.subject" required />
        제목</label
      >
      <br />
      <textarea
        cols="30"
        rows="10"
        v-model="article.content"
        required
      ></textarea>
      <button>수정</button>
    </form>
  </div>
</template>

<style scoped></style>
