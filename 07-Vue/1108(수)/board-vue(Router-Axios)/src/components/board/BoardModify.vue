<script setup>
import BoardInputFormItem from "./item/BoardInputFormItem.vue";
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글수정</mark>
        </h2>
      </div>
      <BoardInputFormItem type="modify"></BoardInputFormItem>
    </div>
  </div>
</template>

<style scoped></style>
