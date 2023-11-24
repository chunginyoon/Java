<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, getModifyArticle, modifyArticle } from "@/api/board";

const route = useRoute();
const router = useRouter();

const prop = defineProps({ type: String });

const article = ref({ subject: "", content: "" });
const isUserId = ref(false);

if (prop.type === "modify") {
  let articleno = route.params.articleno;
  getModifyArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      isUserId.value = true;
    },
    (error) => {
      console.log(error);
    }
  );
}

const subjectErrMsg = ref("");
const contentErrMsg = ref("");
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 확인해 주세요!!!";
    } else subjectErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => article.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 확인해 주세요!!!";
    } else contentErrMsg.value = "";
  },
  { immediate: true }
);

// watchEffect(() => {
//   console.log("watchEffect call article >>> ", article.value);
//   let len1 = article.value.subject.length;
//   console.log("제목 len :: " + len1);
//   if (len1 == 0 || len1 > 30) {
//     subjectErrMsg.value = "제목을 확인해 주세요!!!";
//   }
//   let len2 = article.value.subject.length;
//   console.log("내용 len :: " + len2);
//   if (len2 == 0 || len2 > 500) {
//     contentErrMsg.value = "제목을 확인해 주세요!!!";
//   }
//   console.log(subjectErrMsg.value + "    " + contentErrMsg.value);
// });

function onSubmit() {
  // event.preventDefault();

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value);
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value);
  } else {
    prop.type === "regist" ? writeArticle() : updateArticle();
  }
}

function writeArticle() {
  console.log("글등록하자!!", article.value);
  registArticle(
    article.value,
    (response) => {
      let msg = "글등록 처리시 문제 발생했습니다.";
      if (response.status == 201) msg = "글등록이 완료되었습니다.";
      alert(msg);
      moveList();
    },
    (error) => console.log(error)
  );
}

function updateArticle() {
  console.log("글수정하자!!", article.value);
  modifyArticle(
    article.value,
    (response) => {
      let msg = "글수정 처리시 문제 발생했습니다.";
      if (response.status == 200) msg = "글정보 수정이 완료되었습니다.";
      alert(msg);
      moveList();
    },
    (error) => console.log(error)
  );
}

function moveList() {
  router.push({ name: "article-list" });
}
</script>

<template>
  <div class="col-md-12 text-start">
    <form @submit.prevent="onSubmit" method="POST" action="">
      <div class="mb-3">
        <label for="userid" class="form-label">작성자 ID : </label>
        <input
          type="text"
          class="form-control"
          v-model="article.userId"
          :disabled="isUserId"
          placeholder="작성자ID..."
        />
      </div>
      <div class="mb-3">
        <label for="subject" class="form-label">제목 : </label>
        <input type="text" class="form-control" v-model="article.subject" placeholder="제목..." />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용 : </label>
        <textarea
          class="form-control"
          v-model="article.content"
          name="content"
          rows="10"
        ></textarea>
      </div>
      <div class="col-auto text-center">
        <button
          type="submit"
          id="btn-register"
          class="btn btn-outline-primary mb-3 me-2"
          v-if="type === 'regist'"
        >
          글작성
        </button>
        <button type="submit" id="btn-register" class="btn btn-outline-primary mb-3 me-2" v-else>
          글수정
        </button>
        <button type="button" class="btn btn-outline-success mb-3" @click="moveList">목록</button>
      </div>
    </form>
  </div>
</template>

<style scoped></style>
