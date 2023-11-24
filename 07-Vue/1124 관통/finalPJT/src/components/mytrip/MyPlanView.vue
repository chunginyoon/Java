<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useMyTripStore } from "@/stores/mytrip";
import VKakaoMap2 from "@/components/common/VKakaoMap2.vue";
// import { storeToRefs } from "pinia";

const mytripStore = useMyTripStore(); //board.js 참조
const router = useRouter();
const route = useRoute();

const planId = ref(route.params.id); //전달받은 파라미터 추출

const plan = computed(() => mytripStore.plan); //여행에 관련된 총 정보
const items = computed(() => mytripStore.totalPlans); //여행 기간동안 총 계획
const dateRange = computed(() => mytripStore.dateRange);

//reformItems.value[selectedDay] : 선택한 날짜의 계획들
const reformItems = ref([]); //각 일자별 계획 배열 reformItems.value[0] : 1일차 장소들 들어있음
const selectedDay = ref(0); //선택한 날짜 => 지도에 표시됨

const dayItems = computed(() => reformItems.value[selectedDay.value]);

//일자별 버튼 클릭했을 때
const selectDay = (day) => {
  selectedDay.value = day;
  console.log("일자 선택", reformItems.value);
};
//일자별 계획 담기
// const showPlan = (nday) => {
//   const list = [];
//   for (const p of totalPlans.value) if (p.nday === nday) list.push(p);
//   dayPlans.value = list;
// };

const dateDiff = ref(0);

const init = async () => {
  await mytripStore.getPlan(planId.value);
  dateDiff.value = mytripStore.calculateDateDifference(
    plan.value.startDate,
    plan.value.endDate
  );
  //2차원 형태로 데이터 변경
  for (let day = 1; day <= dateDiff.value; day++) {
    const arr = items.value.filter((item) => item.nday == day);
    reformItems.value.push(arr);
  }
};
init();

//여행 추가 버튼
const dialog = ref(false);
const keyword = ref("");

const searchByKeyword = (keyword) => {
  console.log("홈에서 키워드로 검색", keyword.value);
  router
    .push({
      name: "board-keyword",
      params: { keyword: keyword },
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
  keyword.value = "";
};

//드래그

const startDrag = (event, day, priority) => {
  event.dataTransfer.dropEffect = "move";
  event.dataTransfer.effectAllowed = "move";
  event.dataTransfer.setData("startDay", day);
  event.dataTransfer.setData("startPriority", priority);
};

const onDrop = (event, day, priority) => {
  const startDay = event.dataTransfer.getData("startDay");
  const startPriority = event.dataTransfer.getData("startPriority");

  const moveItem = reformItems.value[startDay].splice(startPriority, 1)[0]; //원본 요소 제거

  // console.log(`시작: ${startIdx}, 놓을 곳의 인덱스: ${dropIdx}`);
  // console.log('제거된 요소:', moveItem);
  reformItems.value[day].splice(priority, 0, moveItem); //원본요소를 원하는 위치에 추가
};

//계획 수정
const modifyPlans = ref([]);
const modify = async () => {
  modifyPlans.value = []; //초기화
  let priority = 0;
  for (let day = 0; day < dateDiff.value; day++)
    for (let i = 0; i < reformItems.value[day].length; i++) {
      //nday와 priority 수정
      reformItems.value[day][i].nday = day + 1;
      reformItems.value[day][i].priority = priority;

      let obj = {};
      obj.day = reformItems.value[day][i].nday;
      obj.priority = reformItems.value[day][i].priority;
      obj.attractionId = reformItems.value[day][i].attractionInfo.contentId;
      obj.memo = reformItems.value[day][i].memo;

      modifyPlans.value.push(obj);
      priority++;
    }
  await mytripStore.modifyPlan(planId.value, modifyPlans.value);
  console.log("수정할 데이터", modifyPlans.value);
  location.reload();
};

//계획 단건 삭제
const deletePlan = async (id) => {
  await mytripStore.deleteDayPlan(planId.value, id);
  location.reload();
};
</script>

<template>
  <div class="container">
    <div class="head">
      <h1>{{ plan.projectName }}여행 계획하기</h1>
      <!--추가하기 버튼-->
      <v-sheet class="position-relative" min-height="50">
        <div class="position-absolute d-flex w-100 h-100">
          <v-btn @click="dialog = !dialog"> 추가하기 </v-btn>
        </div>

        <v-fade-transition hide-on-leave>
          <v-card
            v-if="dialog"
            append-icon="$close"
            class="mx-auto"
            elevation="16"
            max-width="500"
            title="장소를 검색해보세요"
          >
            <template v-slot:append>
              <v-btn
                icon="$close"
                variant="text"
                @click="dialog = false"
              ></v-btn>
            </template>

            <v-divider></v-divider>

            <div class="py-12 text-center">
              <!-- <v-icon
                  class="mb-6"
                  color="success"
                  icon="mdi-check-circle-outline"
                  size="128"
                ></v-icon> -->

              <input
                v-model="keyword"
                @keyup.enter="searchByKeyword(keyword)"
                type="text"
                placeholder="키워드를 검색해보세요"
              />
            </div>

            <v-divider></v-divider>

            <div class="pa-4 text-end">
              <v-btn
                class="text-none"
                color="medium-emphasis"
                min-width="92"
                rounded
                variant="outlined"
                @click="dialog = false"
              >
                완료
              </v-btn>
            </div>
          </v-card>
        </v-fade-transition>
      </v-sheet>

      <div class="schedule">
        <div class="day">여행 일정</div>
        <p>
          <span class="highlight">{{ plan.startDate }}</span
          >부터 <span class="highlight">{{ plan.endDate }}</span
          >일까지,
        </p>
        총 <span class="highlight">{{ dateDiff }}</span
        >일 여행!
        <p>일정을 즐겁게 즐겨주세요!</p>
      </div>
    </div>
    <div class="view">
      <div class="planArea">
        <div
          @click="selectDay(day)"
          class="drop-zone"
          v-for="(date, day) in dateRange"
          :key="date"
          draggable="true"
          @drop="onDrop($event, day, 0)"
          @dragenter.prevent
          @dragover.prevent
        >
          <div class="button">
            <a title="Button push lightblue" class="button btnPush btnLightBlue"
              ><div class="nday">{{ date }} {{ day + 1 }}일차</div>
            </a>
          </div>
          <!-- 드래거블 -->
          <div
            v-for="(item, priority) in reformItems[day]"
            :key="item.id"
            draggable="true"
            @dragstart="startDrag($event, day, priority)"
            @drop.stop="onDrop($event, day, priority)"
            @dragenter.prevent
            @dragover.prevent
          >
            <div class="item">
              <div class="item-title"><strong>제목</strong>: {{ item.attractionInfo.title }}</div>
              <div class="item-memo"><strong>메모</strong>: {{ item.memo }}</div>
              <div class="item-del" @click="deletePlan(item.id)">삭제</div>
            </div>
          </div>
          <!-- END 드래거블-->
          <br />
          <br />
          <v-divider :thickness="3"></v-divider>
        </div>
        <!--수정하기 버튼-->
        <v-btn @click="modify"> 수정하기 </v-btn>
      </div>
      <!--카카오맵-->
      <div v-if="dayItems.length > 0" class="map">
        <VKakaoMap2 :dayItems="dayItems"></VKakaoMap2>
      </div>
      <div class="map" v-else>저장한 장소가 없습니다.</div>
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

body {
  font-family: "Arial", sans-serif;
  background-color: #f8f8f8;
  text-align: center;
  margin: 0;
  padding: 0;
}

.head > h1 {
  padding-top: 20px;
}

.schedule {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 300px;
  margin: 20px 0;
  padding: 20px;
}

.day {
  font-size: 18px;
  font-weight: bold;
  color: #3498db; /* 강조하는 날짜의 색상 */
  margin-bottom: 10px;
}
.nday {
  width: 100%;
  text-align: center;
  font-size: 20px;
  border: 1px solid #333;
  border-radius: 5px; /* 테두리 모서리를 둥글게 만듦 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 그림자 효과 */
  background-color: #fff; /* 배경 색상 */
  color: #333; /* 텍스트 색상 */
  /* border-bottom: 3px solid #3498db; */
}

.item {
  width: 100%;
  border: 1px solid #333;
  border-radius: 5px; /* 테두리 모서리를 둥글게 만듦 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 그림자 효과 */
  background-color: #fff; /* 배경 색상 */
  color: #333; /* 텍스트 색상 */
  /* border-bottom: 3px solid #3498db; */
}

.item-del {
      display: inline-block;
      padding: 3px 16px;
      background-color: #e74c3c; /* 배경 색상 */
      color: #fff; /* 텍스트 색상 */
      border: 1px solid #c0392b; /* 테두리 색상 */
      border-radius: 4px; /* 테두리 모서리를 둥글게 만듦 */
      cursor: pointer; /* 포인터 모양으로 변경하여 클릭 가능함을 나타냄 */
      text-align: center;
      text-decoration: none;
      font-size: 14px;
      font-weight: bold;
      transition: background-color 0.3s ease; /* 배경 색상 변경 시 부드러운 효과 */
    }
.highlight {
  background-color: #006a4c; /* 강조 효과 색상 */
  color: #fff;
  padding: 3px;
  border-radius: 5px;
  font-size: 15px;
}

.container {
  width: 56vw;
  margin: 0 auto;
}

.button {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.button > a {
  /* display: block;
  position: relative;
  float: left;
  width: 100px;
  height: 70px;
  padding: 0;
  margin: 33px 0 10px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  box-shadow: 0px 5px 0px 0px #1e8185; */
  cursor: pointer;
}

/* .buttons > a:hover {
  margin-top: 38px;
  margin-bottom: 5px;
  box-shadow: 0px 0px 0px 0px hsl(182, 63%, 32%);
} */

#date {
  margin-top: 3px;
}

.view {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.planArea {
  width: 33%;
}
.drop-zone {
  background-color: #d8f7ff;
}
.map {
  width: 65%;
}
</style>
