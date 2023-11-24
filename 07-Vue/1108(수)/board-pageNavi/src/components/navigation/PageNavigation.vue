<script setup>
import { computed } from "vue";

defineEmits(["changePage"]);

const props = defineProps(["currentPage", "totalPageCount", "navigationSize"]);
console.log("전달받은 props: ", props);

const startPage = computed(() => {
  return (
    parseInt((props.currentPage - 1) / props.navigationSize) *
      props.navigationSize +
    1
  );
});

const endPage = computed(() => {
  const result =
    parseInt((props.currentPage - 1) / props.navigationSize) *
      props.navigationSize +
    props.navigationSize;
  return props.totalPageCount < result ? props.totalPageCount : result;
});

const pages = computed(() => {
  const arr = [];
  for (let i = startPage.value; i <= endPage.value; i++) arr.push(i);
  return arr;
});
</script>

<template>
  <div>페이지 네비게이션</div>

  <ul>
    <li>
      <a href="#" @click.prevent="$emit('changePage', 1)">&laquo;</a>
    </li>
    <li v-show="startPage > 1">
      <a href="#" @click.prevent="$emit('changePage', startPage - 1)">prev</a>
    </li>
    <li
      v-for="page in pages"
      :key="page"
      :class="{ active: page == props.currentPage }"
    >
      <a href="#" @click.prevent="$emit('changePage', page)">{{ page }}</a>
    </li>
    <li v-show="endPage < props.totalPageCount">
      <a href="#" @click.prevent="$emit('changePage', endPage + 1)">next</a>
    </li>
    <li>
      <a href="#" @click.prevent="$emit('changePage', props.totalPageCount)"
        >&raquo;</a
      >
    </li>
  </ul>
</template>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  list-style-type: none;
}
li {
  flex-basis: 40px;
}
.active > a {
  cursor: none;
  color: black;
}
</style>
