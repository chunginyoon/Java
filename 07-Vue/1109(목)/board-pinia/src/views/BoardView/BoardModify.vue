<script setup>
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { ref } from "vue";
//1. 객체 얻기
import { useBoardStore } from "../../stores/board";
const boardStore = useBoardStore();

const router = useRouter();
const route = useRoute();

const modifyForm = ref({});

const setModifyInfo = async () => {
  //1. store의 article 데이터 세팅
  await boardStore.getArticle(route.params.articleNo);
  //2. article 데이터를 통해 modifyForm 세팅
  modifyForm.value = { ...boardStore.article };
};

setModifyInfo();

const modify = async () => {
  if (!confirm("이대로 수정하시겠습니까?")) return;

  const response = await boardStore.modifyArticle(modifyForm.value);
  console.log("수정 요청에 대한 응답:", response);

  router.push("/board");
};
</script>

<template>
  <div>
    <h1>게시글 수정</h1>
    <form @submit.prevent="modify">
      <label
        ><input type="text" v-model.trim="modifyForm.subject" required />
        제목</label
      >
      <br />
      <textarea
        cols="30"
        rows="10"
        v-model="modifyForm.content"
        required
      ></textarea>
      <button>수정</button>
    </form>
  </div>
</template>

<style scoped></style>
