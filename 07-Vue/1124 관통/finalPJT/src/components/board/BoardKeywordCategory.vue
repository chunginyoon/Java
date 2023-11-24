<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed, watch } from "vue";
import { useBoardStore } from "@/stores/board";

const boardStore = useBoardStore(); //board.js 참조
const router = useRouter();
const route = useRoute();

// const keyword = ref(route.params.keyword); //넘겨받은 contentId 추출
const contents = computed(() => boardStore.contents);

//상세 페이지 불러올 때 사용할 파라미터
const params = ref({
  sido: "", //시도 코드
  gugun: "", //구군 코드
  category: "", //관광지 타입
  word: "", //키워드
  size: "24", //한번에 가져올 데이터의 개수
  page: "", //가져올 페이지
  sort: "", //정렬 순서(오름차순, 내림차순)
  orderBy: "", //정렬 기준
});

// params.value.word = keyword.value;

watch(
  () => route.params.keyword,
  (value) => {
    params.value.word = value;
    boardStore.getContents(params.value);
  },
  {
    immediate: true,
  }
);

const categories = ref([
  { categoryId: "", categoryType: "전체보기" },
  { categoryId: 12, categoryType: "관광지" },
  { categoryId: 14, categoryType: "문화시설" },
  { categoryId: 15, categoryType: "축제공연행사" },
  { categoryId: 25, categoryType: "여행코스" },
  { categoryId: 28, categoryType: "레포츠" },
  { categoryId: 32, categoryType: "숙박" },
  { categoryId: 38, categoryType: "쇼핑" },
  { categoryId: 39, categoryType: "음식점" },
]);

// const init = async () => {
//   await boardStore.getContents(params.value);
//   console.log("전체보기", contents.value);
// };

// init();

//카테고리 바꿀 때마다 실행
const getContent = async (categoryId) => {
  params.value.category = categoryId;
  console.log("카테고리 선택", params.value.category);
  await boardStore.getContents(params.value);
};

const moveView = (contentId) => {
  router
    .push({ name: "board-content-view", params: { contentId: contentId } })
    .then(() => {
      window.scrollTo(0, 0);
    });
};
</script>

<template>
  <div class="container">
    <!--'도시'에 대한 검색 결과-->
    <h1>'{{ route.params.keyword }}'에 대한 검색 결과</h1>
    <!--카테고리 박스-->
    <v-row justify="space-around">
      <v-col cols="auto">
        <v-sheet class="py-4 px-1">
          <v-chip-group filter mandatory selected-class="text-primary">
            <v-chip
              v-for="category in categories"
              :key="category.categoryId"
              @click="getContent(category.categoryId)"
              variant="outlined"
              size="x-large"
            >
              {{ category.categoryType }}
            </v-chip>
          </v-chip-group>
        </v-sheet>
      </v-col>
    </v-row>
    <!--컨텐트 표시-->
    <v-row>
      <v-col
        v-for="content in contents"
        :key="content.contentId"
        cols="12"
        sm="6"
        md="4"
      >
        <v-card
          class="mx-auto my-12"
          max-width="374"
          @click="moveView(content.contentId)"
        >
          <template v-slot:loader="{ isActive }">
            <v-progress-linear
              :active="isActive"
              color="deep-purple"
              height="4"
              indeterminate
            ></v-progress-linear>
          </template>

          <!--사진-->
          <v-img cover height="250" :src="content.firstImage"></v-img>

          <v-card-item>
            <v-card-title>{{ content.title }}</v-card-title>

            <v-card-subtitle>
              <span>{{ content.add1 }}</span>
              <span>{{ content.add2 }}</span>
              <v-icon
                color="error"
                icon="mdi-fire-circle"
                size="small"
              ></v-icon>
            </v-card-subtitle>
          </v-card-item>

          <v-card-text>
            <v-row align="center" class="mx-0">
              <v-rating
                :model-value="4.5"
                color="amber"
                density="compact"
                half-increments
                readonly
                size="small"
              ></v-rating>

              <div class="text-grey ms-4">
                {{ content.avgScore }} {{ content.readCount }}
              </div>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
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
  width: 60vw;
  margin: 0 auto;
}

.category {
  display: flex;
  justify-content: space-between;
}

.category > a {
  display: block;
  position: relative;
  float: left;
  width: 100px;
  height: 50px;
  padding: 0;
  margin: 33px 0 10px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  box-shadow: 0px 5px 0px 0px #1e8185;
  cursor: pointer;
}

.category > a:hover {
  margin-top: 38px;
  margin-bottom: 5px;
  box-shadow: 0px 0px 0px 0px hsl(182, 63%, 32%);
}
</style>
