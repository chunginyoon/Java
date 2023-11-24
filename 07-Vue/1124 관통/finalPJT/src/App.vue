<script setup>
import { RouterView } from "vue-router";
import Header from "./components/common/Header.vue";
import Footer from "./components/common/Footer.vue";
</script>

<template>
  <v-app id="app">
    <Header></Header>
    <RouterView></RouterView>
    <Footer></Footer>
  </v-app>
</template>

<style scoped></style>
