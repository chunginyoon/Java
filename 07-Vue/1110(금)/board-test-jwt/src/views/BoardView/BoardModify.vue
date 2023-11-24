<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import { useBoardStore } from "../../stores/board";

const boardStore = useBoardStore();

const router = useRouter();
const route = useRoute();

const modifyForm = ref({});

//수정 정보 초기데이터 세팅
const setModifyInfo = async () => {
  await boardStore.getArticle(route.params.articleNo);
  modifyForm.value.articleNo = boardStore.article.articleNo;
  modifyForm.value.subject = boardStore.article.subject;
  modifyForm.value.content = boardStore.article.content;
};
setModifyInfo();

const modify = async () => {
  try {
    if (!confirm("이대로 수정하시겠습니까?")) return;
    await boardStore.modifyArticle(modifyForm.value);
    router.push("/board");
    alert("수정 성공");
  } catch (error) {
    console.log("수정 에러 내용:", error);
    alert("수정 실패");
  }
};
</script>

<template>
  <div>
    <h1>게시글 수정</h1>
    <form @submit.prevent="modify">
      <label><input type="text" v-model.trim="modifyForm.subject" required /> 제목</label>
      <br />
      <textarea cols="30" rows="10" v-model="modifyForm.content" required></textarea>
      <br />
      <button>수정</button>
    </form>
  </div>
</template>

<style scoped></style>
