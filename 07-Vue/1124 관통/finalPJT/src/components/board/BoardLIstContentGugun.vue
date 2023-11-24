<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
import PageNavigation from "@/components/common/PageNavigation.vue";

const boardStore = useBoardStore();
const guguns = computed(() => boardStore.guguns); //구군 정보 저장
const contents = computed(() => boardStore.contents); //구군에 해당되는 모든 컨텐츠
const totalContents = computed(() => boardStore.totalContents); //구군에 해당되는 컨텐츠 총 개수
const pageable = computed(() => boardStore.pageable); // 페이지 정보 가져오기(현재 페이지(pageNumber), 사이즈(pageSize))
const totalPageCount = computed(() => boardStore.totalPages); //총 페이지
const currentPage = ref(0);
const pageSize = ref(0);

// 전체 페이지 수
//console.log("전체 페이지 수", totalPageCount.value);
// 현재 페이지
//console.log("현재 페이지 idx", pageable.value.pageNumber);
// 한페이지 당 사이즈
//console.log("한페이지 당 사이즈", pageable.value.pageSize);

const router = useRouter();
const route = useRoute();

//라우터로부터 전달받은 파라미터들
const sidoName = ref(route.params.sidoName);
const sidoCode = ref(route.params.sidoCode);
const contentId = ref(route.params.contentId);
const contentType = ref(route.params.contentType);

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

//시도, 카테고리 값 설정
params.value.sido = sidoCode.value;
params.value.category = contentId.value;

//초기값은 전체보기-전체조회
const selectedOption = ref("");

const init = async () => {
  //시도에 따른 구군 가져오기
  await boardStore.getGugun(sidoCode.value);
  //구군에 따른 컨텐츠 가져오기
  await boardStore.getContents(params.value);

  //총 페이지 계산
  // if (totalContents.value > 0)
  //   totalPageCount.value =
  //     parseInt((totalContents.value - 1) / params.value.size) + 1;
  // else totalPageCount.value = 0;

  currentPage.value = pageable.value.pageNumber;
  pageSize.value = pageable.value.pageSize;

  // 페이지 관련 값 할당하기
  // 전체 페이지 수
  //console.log("전체 페이지 수", totalPageCount.value);
  // 현재 페이지
  console.log("현재 페이지 idx", currentPage.value);
  // 한페이지 당 사이즈
  console.log("한페이지 당 사이즈", pageSize.value);

  console.log(
    "전체 게시글 개수, 전체 페이지",
    totalContents.value,
    totalPageCount.value
  );

  //console.log("pageable 조회", pageable.value);
};

init();

//구군 바꿀 때마다 실행
const getContent = async (gugunCode) => {
  params.value.gugun = gugunCode;
  // console.log("params", params.value);
  // console.log("selectedOption", gugunCode.value);
  // console.log("구군 선택", params.value.gugun);
  await boardStore.getContents(params.value);
};

//카드 누르면 상세 조회 페이지로 이동
const moveView = (contentId) => {
  router
    .push({ name: "board-content-view", params: { contentId: contentId } })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const onPageChange = async (val) => {
  currentPage.value = val;
  params.value.page = val;
  await boardStore.getContents(params.value);
};
</script>

<template>
  <div class="container">
    <h1>'{{ sidoName }}'의 {{ contentType }}</h1>
    <!-- <v-select
      width="50%"
      label="지역 선택"
      v-for="gugun in guguns"
      :key="gugun.gugunCode"
      multiple
      variant="outlined"
    ></v-select> -->

    <br />
    <!-- <v-select
      @update:modelValue="getContent"
      v-model="selectedOption"
      label="지역 선택"
      :items="guguns"
      item-title="gugunName"
      item-value="gugunCode"
      variant="outlined"
    ></v-select> -->
    <v-row justify="space-around">
      <v-col cols="auto">
        <v-sheet class="py-4 px-1">
          <v-chip-group filter mandatory selected-class="text-primary">
            <v-chip
              v-for="gugun in guguns"
              :key="gugun.gugunCode"
              @click="getContent(gugun.gugunCode)"
              variant="outlined"
              size="x-large"
            >
              {{ gugun.gugunName }}
            </v-chip>
          </v-chip-group>
        </v-sheet>
      </v-col>
    </v-row>

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
    <PageNavigation
      @pageChange="onPageChange"
      :current-page="currentPage"
      :total-page-count="totalPageCount"
      :navigation-size="5"
    />
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
</style>
