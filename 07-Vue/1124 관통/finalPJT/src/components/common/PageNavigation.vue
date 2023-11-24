<script setup>
import { computed } from "vue";

const props = defineProps({
  currentPage: Number,
  totalPageCount: Number,
  navigationSize: Number,
});
const emit = defineEmits(["pageChange"]);
const navigationSize = parseInt(props.navigationSize);

const startPage = computed(() => {
  return parseInt(props.currentPage / navigationSize) * navigationSize + 1;
});

const endPage = computed(() => {
  let lastPage =
    parseInt(props.currentPage / navigationSize) * navigationSize +
    navigationSize;
  return props.totalPageCount < lastPage ? props.totalPageCount : lastPage;
});

const endRange = computed(() => {
  return (
    parseInt((props.totalPageCount - 1) / navigationSize) * navigationSize <
    props.currentPage
  );
});

function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
  //   return Array(end - start + 1)
  //     .fill()
  //     .map((val, i) => start + i);
}

function onPageChange(pg) {
  console.log(pg + "로 이동!!!");
  emit("pageChange", pg - 1);
}

// 총 페이지가 5개 이하면 이전/다음 버튼을 보여주지 않음
const buttonDisplay = computed(() => props.totalPageCount > 5);

// 다음 버튼 비활성화 조건
const nextButtonDisabled = computed(
  () => endPage.value >= props.totalPageCount
);

// 이전 버튼 비활성화 조건
const previousButtonDisabled = computed(() => startPage.value <= 1);
</script>

<template>
  <div class="pagination_box">
    <v-row no-gutters align="center" justify="center">
      <v-btn
        rounded
        v-if="buttonDisplay"
        depressed
        text
        :class="[
          'pagination__previous-btn',
          { 'is-disabled': previousButtonDisabled },
        ]"
        :disabled="previousButtonDisabled"
        @click="onPageChange(startPage == 1 ? 1 : startPage - 1)"
      >
        <img
          src="@/assets/img/black-arrow-left.svg"
          alt="arrow"
          class="pagination__previous-image"
      /></v-btn>

      <template v-for="page in range(startPage, endPage)" :key="page">
        <li
          :class="currentPage === page - 1 ? 'page-item active' : 'page-item'"
        >
          <a href="#" @click="onPageChange(page)">{{ page }}</a>
        </li>
      </template>

      <v-btn
        rounded
        v-if="buttonDisplay"
        depressed
        text
        :class="['pagination__next-btn', { 'is-disabled': nextButtonDisabled }]"
        :disabled="nextButtonDisabled"
        @click="onPageChange(endRange ? totalPage : endPage + 1)"
      >
        <img
          src="@/assets/img/black-arrow-right.svg"
          alt="arrow"
          class="pagination__next-image"
      /></v-btn>
    </v-row>
  </div>
</template>

<style scoped>
li {
  flex-basis: 40px;
  color: black;
  text-align: center;
}
li > a {
  text-decoration: none;
  color: inherit;
  display: inline-block;
  font-weight: bold;
}
li > a:hover {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  color: #ffffff;
  background-color: gainsboro;
}
.page-item.active > a {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: #2e9cdf;
  color: #ffffff;
  cursor: ponter;
  font-weight: 500;
  pointer-events: none;
}
</style>
