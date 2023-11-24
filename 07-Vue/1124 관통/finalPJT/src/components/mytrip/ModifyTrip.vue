<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useMyTripStore } from "@/stores/mytrip";

const mytripStore = useMyTripStore(); //board.js 참조
const router = useRouter();
const route = useRoute();

const planId = ref(route.params.id); //전달받은 파라미터 추출
const plan = computed(() => mytripStore.plan); //여행에 관련된 총 정보
mytripStore.getPlan(planId.value);

const mytrip = ref({
  projectName: "",
  startDate: "",
  endDate: "",
  memo: "",
});

mytrip.value.projectName = plan.value.projectName;
mytrip.value.memo = plan.value.memo;

const step = ref(1);

const nextStep = () => {
  step.value++;
};

const prevStep = () => {
  step.value--;
};

const modifyPlan = async () => {
  await mytripStore.modifyTrip(planId.value, mytrip.value);
  await router.push({ name: "mytrip-plan" }).then(() => {
    window.scrollTo(0, 0);
  });
};

const startDate = ref(new Date());
const endDate = ref(new Date());
const startDateTitle = ref("여행 시작 일자를 선택하세요");
const endDateTitle = ref("여행이 종료 일자를 선택하세요");

const formatDate = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더하고 두 자릿수로 포맷팅
  const day = String(date.getDate()).padStart(2, "0"); // 두 자릿수로 포맷팅

  return `${year}-${month}-${day}`;
};

const formatTitle = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더하고 두 자릿수로 포맷팅
  const day = String(date.getDate()).padStart(2, "0"); // 두 자릿수로 포맷팅

  return `${year}년 ${month}월 ${day}일`;
};

const startDateChange = () => {
  const formattedDate = formatDate(startDate.value);
  mytrip.value.startDate = formattedDate;
  console.log("시작 날짜: ", mytrip.value.startDate);

  const formattedTitle = formatTitle(startDate.value);
};

const endDateChange = () => {
  const formattedDate = formatDate(endDate.value);
  mytrip.value.endDate = formattedDate;
  console.log("종료 날짜: ", mytrip.value.endDate);

  const formattedTitle = formatTitle(endDate.value);
};
</script>

<template>
  <div class="container">
    <h1>여행 계획 수정하기</h1>
    <v-stepper
      :items="['여행 이름', '여행 계획', '메모']"
      v-model="step"
      hide-actions
    >
      <template v-slot:item.1>
        <v-card>
          <v-card-title>Step 1</v-card-title>
          <div class="card">
            <input
              id="projectName"
              v-model="mytrip.projectName"
              type="text"
              placeholder="여행 제목을 입력해주세요"
              maxlength="100"
            />

            <v-btn
              :disabled="!mytrip.projectName || mytrip.projectName.length === 0"
              @click="nextStep"
              id="next"
              >다음</v-btn
            >
          </div>
          <p
            id="title"
            v-if="!mytrip.projectName || mytrip.projectName.length === 0"
          >
            제목을 입력해주세요.
          </p>
        </v-card>
      </template>

      <template v-slot:item.2>
        <v-card>
          <v-card-title>Step 2</v-card-title>
          <v-col>
            <v-row justify="space-between">
              <v-col>
                <v-date-picker
                  id="calaendar"
                  v-model="startDate"
                  @click="startDateChange"
                  show-adjacent-months
                  :title="startDateTitle"
                ></v-date-picker>
              </v-col>

              <v-col>
                <v-date-picker
                  id="calaendar"
                  v-model="endDate"
                  @click="endDateChange"
                  :min="startDate"
                  :title="endDateTitle"
                ></v-date-picker>
              </v-col>
            </v-row>
          </v-col>
          <div class="buttons">
            <v-btn @click="prevStep">이전</v-btn>
            <v-btn @click="nextStep">다음</v-btn>
          </div>
        </v-card>
      </template>

      <template v-slot:item.3>
        <v-card>
          <v-card-title>Step 3</v-card-title>
          <input
            id="memo"
            v-model="mytrip.memo"
            type="text"
            placeholder="메모를 입력해주세요"
            maxlength="100"
          />
          <div class="buttons">
            <v-btn @click="prevStep">이전</v-btn>
            <v-btn @click="modifyPlan">완료</v-btn>
          </div>
        </v-card>
      </template>
    </v-stepper>
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
  width: 56vw;
  margin: 0 auto;
}

#projectName {
  height: 40px;
  width: 300px;
  margin-top: 15px;
  margin-left: 15px;
  padding-left: 10px;
  border: 1px solid #8b8a8a;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 14px;
}

#memo {
  height: 100px;
  width: 300px;
  margin-top: 15px;
  margin-left: 15px;
  padding-left: 10px;
  border: 1px solid #8b8a8a;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 14px;
}

.card {
  display: flex;
}

#next {
  margin-top: 18px;
  margin-left: 20px;
}

#title {
  margin-left: 20px;
  color: rgb(221, 62, 62);
}

#calaendar {
  margin-left: 55px;
}

.buttons {
  margin-left: 430px;
}
</style>
