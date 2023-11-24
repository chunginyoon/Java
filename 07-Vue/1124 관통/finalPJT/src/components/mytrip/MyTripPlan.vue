<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useMyTripStore } from "@/stores/mytrip";
import { useBoardStore } from "@/stores/board";
// import { storeToRefs } from 'pinia';

const mytripStore = useMyTripStore(); //mytrip.js 참조
const boardStore = useBoardStore();
const router = useRouter();

const myLikes = computed(() => boardStore.myLikes);
const plans = computed(() => mytripStore.plans);
mytripStore.getPlans();
boardStore.getMyLikeList();
console.log(myLikes);
const createNewTrip = () => {
  router.push({ name: "mytrip-newplan" }).then(() => {
    window.scrollTo(0, 0);
  });
};

const moveToView = (id) => {
  router.push({ name: "mytrip-viewplan", params: { id: id } }).then(() => {
    window.scrollTo(0, 0);
  });
};

const moveToAttraction = (contentId) => {
  router
    .push({ name: "board-content-view", params: { contentId } })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const deleteTrip = (id) => {
  mytripStore.deletePlan(id);
  location.reload();
};

const ModifyTrip = (id) => {
  router.push({ name: "mytrip-modifyplan", params: { id: id } }).then(() => {
    window.scrollTo(0, 0);
  });
};
</script>

<template>
  <div class="container">
    <div class="like-list">
      <h1>내가 찜한 관광지</h1>

      <div v-if="myLikes.length === 0" class="empty">
        <span>내가 찜한 관광지가 없습니다.</span>
      </div>
      <div v-else class="view">
        <v-container>
          <v-row align="center" justify="center">
            <v-col
              v-for="like in myLikes"
              class="likes"
              :key="like.id"
              cols="auto"
            >
              <v-card class="mx-auto" width="400" :variant="variant">
                <v-card-item>
                  <div>
                    <div
                      class="text-overline mb-1"
                      @click="moveToAttraction(like.contentId)"
                    >
                      <v-img
                        :width="300"
                        aspect-ratio="16/9"
                        cover
                        :src="like.firstImage"
                        ><template v-slot:placeholder>
                          <div
                            class="d-flex align-center justify-center fill-height"
                          >
                            <v-progress-circular
                              color="grey-lighten-4"
                              indeterminate
                            ></v-progress-circular>
                          </div> </template
                      ></v-img>
                      <div class="text-h6 mb-1">{{ like.title }}</div>
                      <div class="text-h6 mb-3">
                        {{ like.addr1 }}
                      </div>
                      <div class="text-caption">
                        <v-rating
                          half-increments
                          readonly
                          :length="5"
                          :size="21"
                          :model-value="like.avgScore"
                          color="amber"
                          active-color="amber"
                        />
                        <v-text>{{ like.avgScore }}</v-text>
                      </div>
                    </div>
                  </div>
                </v-card-item>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
    <div class="project-list">
      <h1>나의 여행 프로젝트</h1>
      <v-col cols="12" sm="6" md="7" style="margin: 0 auto;">
        <v-btn block rounded="xs" size="x-large" prepend-icon="mdi-plus" variant="outlined" @click="createNewTrip">새 여행 만들기</v-btn>
      </v-col>
      

      <div v-if="plans.length === 0" class="empty">
        <span>아직 계획한 여행이 없습니다</span>
      </div>
      <div v-else class="view">
        <v-container>
          <v-row align="center" justify="center">
            <v-col
              v-for="plan in plans"
              class="plans"
              :key="plan.id"
              cols="auto"
            >
              <v-card class="mx-auto" width="400" :variant="variant">
                <v-card-item>
                  <div>
                    <div
                      class="text-overline mb-1"
                      @click="moveToView(plan.id)"
                    >
                      <div class="text-h6 mb-1">{{ plan.projectName }}</div>
                      <div class="text-h6 mb-3">
                        <v-card-subtitle
                          >{{ plan.startDate }}~{{
                            plan.endDate
                          }}</v-card-subtitle
                        >
                      </div>
                      <div class="text-caption">
                        <v-text>{{ plan.memo }}</v-text>
                      </div>
                    </div>
                  </div>
                </v-card-item>

                <v-card-actions>
                  <v-btn variant="tonal" @click="ModifyTrip(plan.id)"> 수정 </v-btn>
                  <v-btn variant="tonal" @click="deleteTrip(plan.id)"> 삭제 </v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </div>
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
  display: flex;
}

.like-list .project-list {
  /* display: flex; */
  flex: 1;
}
.like-list {
  /* background-color: yellow; */
  margin:0 20px;
}
.like-list > h1 {
  text-align: center;
  padding:20px 0;
}
.project-list {
  /* background-color: aqua; */
  margin:0 20px;
}
.project-list > h1 {
  text-align: center;
  padding: 20px 0;
}

.project-list > p {
  margin-left: 15px;
  margin-top: 15px;
  cursor: pointer;
}

.plans {
  cursor: pointer;
  display: flex;
}
</style>
