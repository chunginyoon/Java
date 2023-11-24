<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";
import { useFeedStore } from "@/stores/feed";

const feedStore = useFeedStore(); //feed.js 참조
const router = useRouter();

const images = ref([]);
const previewImages = ref([]);

const handleFileChange = (event) => {
  const files = event.target.files;

  if (files) {
    // FileReader를 사용하여 각 파일의 이미지를 미리보기
    for (let i = 0; i < files.length; i++) {
      images.value.push(files[i]); // 선택된 이미지 파일을 배열에 저장
      const reader = new FileReader();
      reader.onload = () => {
        // 이미지를 배열에 추가
        previewImages.value.push(reader.result);
      };
      reader.readAsDataURL(files[i]);
    }
  }
};

const form = ref(null);

const submitForm = async () => {
  // FormData 객체 생성
  const formData = new FormData(form.value);

  // formData를 서버로 전송하는 로직을 추가하면 됩니다.
  // 이 예제에서는 간단하게 콘솔에 formData를 출력합니다.
  //   console.log("폼 데이터:", formData);
  for (const pair of formData.entries()) {
    console.log(pair[0] + ", " + pair[1]);
  }

  // 여기에서 서버로 formData를 전송하는 코드를 추가하면 됩니다.
  try {
    await feedStore.registFeed(formData);
    router
      .push({
        name: "home",
      })
      .then(() => {
        window.scrollTo(0, 0);
      });
  } catch (error) {
    console.error("에러 발생:", error);
  }
};
</script>

<template>
  <div class="container">
    <h1>피드 만들기</h1>
    <v-card class="mx-auto" width="800" :variant="variant"
      ><v-card-item>
        <!--피드 form-->
        <form @submit.prevent="submitForm" ref="form">
          <!-- 텍스트 입력 -->
          <textarea name="content" placeholder="내용을 입력하세요"></textarea>

          <!-- 여러 개의 사진 업로드 -->
          <input
            name="imageFiles"
            type="file"
            @change="handleFileChange"
            accept="image/*"
            multiple
          />

          <!-- 폼 제출 버튼 -->
          <button id="btn" type="submit">전송</button>
        </form></v-card-item
      ></v-card
    >

    <!-- 이미지 미리보기 -->
    <div v-for="(image, index) in previewImages" :key="index" class="images">
      <img :src="image" alt="이미지 미리보기" />
    </div>
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

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-left: 40px;
}

textarea {
  height: 200px;
  width: 95%;
  margin-top: 15px;
  padding-left: 10px;
  border: 1px solid #8b8a8a;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 14px;
}

.images {
  display: flex;
}

#btn {
  display: block;
  position: relative;
  float: left;
  width: 75px;
  height: 40px;
  padding: 0;
  margin-left: 300px;
  margin-bottom: 20px;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  box-shadow: 0px 5px 0px 0px #1e8185;
}

#btn:hover {
  margin-top: 20px;
  margin-bottom: 5px;
  box-shadow: 0px 0px 0px 0px hsl(182, 63%, 32%);
}
</style>
