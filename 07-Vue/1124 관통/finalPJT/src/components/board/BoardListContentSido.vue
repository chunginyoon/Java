<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
const boardStore = useBoardStore();
const router = useRouter();
const route = useRoute();

const sidos = computed(() => boardStore.sidos);
const contentId = ref(route.params.contentId);
const contentType = ref(route.params.contentType);

const moveContentGugun = (contentId, contentType, sidoCode, sidoName) => {
  router
    .push({
      name: "board-list-gugun",
      params: {
        contentId: contentId,
        contentType: contentType,
        sidoCode: sidoCode,
        sidoName: sidoName,
      },
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
};
</script>

<template>
  <div class="container">
    <section>
      <h1>{{ contentType }}</h1>
      <div class="images">
        <v-row>
          <v-col
            v-for="sido in sidos"
            :key="sido.sidoCode"
            cols="12"
            sm="6"
            md="4"
            lg="3"
          >
            <v-card
              @click="
                moveContentGugun(
                  contentId,
                  contentType,
                  sido.sidoCode,
                  sido.sidoName
                )
              "
              width="285"
              height="250"
            >
              <v-img
                :src="sido.sidoImg"
                alt="Card Image"
                width="100%"
                height="80%"
                :cover="true"
              ></v-img>
              <v-card-title>{{ sido.sidoName }}</v-card-title>
            </v-card>
          </v-col>
        </v-row>
        <!-- <v-card
          v-for="s in sido"
          :key="s.sidoCode"
          class="mx-auto"
          max-width="200"
          height="200"
          image="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          theme="dark"
        >
          <v-card-title>{{ s.sidoName }}</v-card-title></v-card
        > -->
        <!-- <div v-for="s in sido" :key="s.sidoCode" class="pic">
          <img src="@/assets/img/picture1.jpg" />
        </div> -->
      </div>
    </section>
  </div>
  <!-- <h1>컨텐트</h1> -->
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

section {
  width: 100%;
}

section > h1 {
  font-size: 30px;
  margin-bottom: 20px;
}

.images {
  display: flex;
}

.pic {
  width: 200px;
  height: 200px;
  border-radius: 15%;
  overflow: hidden;
  margin-right: 10px;
  position: relative;
}

.pic > img {
  /* max-height: 100%;
    width: auto;
    display: block; */
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
