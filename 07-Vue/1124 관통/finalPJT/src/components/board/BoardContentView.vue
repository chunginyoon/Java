<script setup>
import { useRoute } from "vue-router";
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
import { useMyTripStore } from "@/stores/mytrip";
import { useAuthStore } from "@/stores/auth";
import defaultImg from "@/assets/img/defaultImg.png";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
// import { storeToRefs } from 'pinia';

const boardStore = useBoardStore(); //board.js 참조
const mytripStore = useMyTripStore(); //mytrip.js 참조
const route = useRoute();

const contentId = ref(route.params.contentId); //넘겨받은 contentId 추출

const content = computed(() => boardStore.content); //게시글 정보
const description = computed(() => boardStore.description); //게시글 설명

//게시글 기본 사진 없으면 defaultImg로 바꾸기
const checkImg = () => {
  if (!content.value.firstImage) content.value.firstImage = defaultImg;
};

//초기 세팅
const init = async () => {
  await boardStore.getContent(contentId.value); //게시글 정보 가져오기
  boardStore.getDescription(contentId.value); //게시글 설명 가져오기
  checkImg(); //사진 빈 값인지 체크
  console.log("전체보기", content.value);
};

init(); //게시글 불러오기

const numberOfLikes = computed(() => boardStore.numberOfLikes); //게시글 좋아요 개수
const comments = computed(() => boardStore.comments); //게시글에 달린 총 댓글 목록
const numberOfComments = computed(() => boardStore.numberOfComments); //댓글 개수

boardStore.getComments(contentId.value); //모든 댓글 가져오기
boardStore.getLikes(contentId.value); //좋아요 개수 가져오기

const isLiked = computed(() => boardStore.isLiked); //나의 좋아요 여부 얻어오기
boardStore.getMyLike(contentId.value);

const toggleLike = async () => {
  await boardStore.clickLike(contentId.value); //사용자가 좋아요 버튼 누르기
  await boardStore.getLikes(contentId.value); //좋아요 개수 다시 가져오기
  await boardStore.getMyLike(contentId.value); //나의 좋아요 여부
  console.log(isLiked.value);
};

//스크롤해서 후기 섹션으로 이동
const targetElement = ref(null);
const scrollToReviews = () => {
  // targetElement.value를 사용하여 ref에 접근합니다.
  targetElement.value.scrollIntoView({ behavior: "smooth", block: "start" });
};

const authStore = useAuthStore();
const userName = computed(() => authStore.user.userName); //사용자의 아이디 추출

//댓글 등록
const userReview = ref({
  rating: 0,
  comment: "",
});
const writeReview = () => {
  boardStore.writeReview(userReview.value, contentId.value);
  location.reload();
};

//댓글 수정
const commentId = ref(-1);
const modifyComment = ref({
  rating: 0,
  comment: "",
});
const clickModify = (comment) => {
  console.log("수정할 댓글", comment);
  commentId.value = comment.id;
  modifyComment.value.rating = comment.rating;
  modifyComment.value.comment = comment.comment;
};
const modifyReview = async (modifyComment) => {
  await boardStore.modifyReview(
    contentId.value,
    commentId.value,
    modifyComment
  );
  commentId.value = -1;
  location.reload();
};

//댓글 삭제
const deleteReview = (id) => {
  console.log("삭제할 댓글 아이디", id);
  if (confirm("댓글을 삭제하시겠습니까?")) {
    boardStore.deleteReview(contentId.value, id);
  }
  location.reload();
};

//================ 여행 일정에 추가하기================//
const planId = ref("");
const newlocation = ref({
  //나의 여행에 등록할 객체
  day: "",
  attractionId: "",
  memo: "",
});
//현재 contentId 저장
newlocation.value.attractionId = contentId.value;

//사용자의 여행 계획 불러오기
const plans = computed(() => mytripStore.plans);
mytripStore.getPlans();

//추가하기 버튼 누르면 나의 여행 리스트 보여주기
const planSheet = ref(false); //나의 여행 목록 sheet 보여줄지 결정하는 변수
const getTrip = () => {
  planSheet.value = !planSheet.value;
};

//해당 여행의 일수 가져오기
const plan = computed(() => mytripStore.plan); //여행에 관련된 총 정보
const dateRange = computed(() => mytripStore.dateRange);
const dateDiff = ref(0);

const getDays = async (id) => {
  planSheet.value = !planSheet.value;
  daySheet.value = !daySheet.value;
  await mytripStore.getPlan(id);
  dateDiff.value = mytripStore.calculateDateDifference(
    plan.value.startDate,
    plan.value.endDate
  );
  planId.value = id;
};

//몇일차 선택하면 객체에 저장
const selectDay = (day) => {
  newlocation.value.day = day;
};

const daySheet = ref(false);
//여행 일차와 메모 창 띄울지 결정하는 변수
//완료 버튼 누르면 나의 여행에 등록
const addNewPlace = () => {
  mytripStore.registNewPlace(planId.value, newlocation.value);
  daySheet.value = !daySheet.value;
};
</script>

<template>
  <div class="container">
    <div class="info">
      <!--제목 주소-->
      <div class="title">
        <br />
        <div id="title">{{ content.title }}</div>
        <span>{{ content.addr1 }} {{ content.addr2 }}</span>
        <span></span>
      </div>
      <!--별점-->
      <div class="rate" @click="scrollToReviews">
        <v-rating
          half-increments
          readonly
          :length="5"
          :size="19"
          :model-value="content.avgScore"
          color="amber"
          active-color="amber"
        />
        {{ content.avgScore }}
      </div>
    </div>
    <!--사진-->
    <div class="img"><v-img :src="content.firstImage"></v-img></div>
    <!--설명-->
    <span>{{ description }}</span>
    <!--지도-->
    <div class="map">
      <VKakaoMap
        v-if="Object.keys(content).length > 0"
        :contentLocation="{
          lat: content.latitude,
          lng: content.longitude,
        }"
      />
    </div>
    <v-divider :thickness="4" color="info"></v-divider>

    <!--리뷰 창-->
    <div class="review" ref="targetElement">
      <div class="head">
        <div class="reviewInfo">
          <!--댓글 개수-->
          <div id="review">후기 {{ numberOfComments }}개</div>
          <br />
          <div class="score">
            <div id="score">
              <div class="rating">
                <v-rating
                  half-increments
                  readonly
                  :length="5"
                  :size="21"
                  :model-value="content.avgScore"
                  color="amber"
                  active-color="amber"
                />
                <!--평점-->
                <span id="cavg">{{ content.avgScore }}</span>
              </div>
            </div>
          </div>
        </div>
        <!--좋아요&추가-->
        <div class="likeAdd">
          <div class="add" @click="getTrip">
            <div class="add-part">
              <img
                class="add-icon"
                src="@/assets/img/add.svg"
                alt="add-project"
              />

              <!--나의 여행 리스트 보여주기-->
              <!-- <ul v-if="showmytrip">
                <li
                  v-for="plan in plans"
                  :key="plan.id"
                  @click="getDays(plan.id)"
                >
                  {{ plan.projectName }} {{ plan.startDate }}~{{ plan.endDate }}
                </li>
              </ul> -->
              <v-bottom-sheet v-model="planSheet" inset>
                <v-card class="text-center" height="400">
                  <v-card-text>
                    <span class="project-title"
                      >나의 여행 계획 프로젝트 리스트</span
                    >
                    <ul class="items">
                      <li
                        class="project"
                        v-for="plan in plans"
                        :key="plan.id"
                        @click="getDays(plan.id)"
                      >
                        <div class="project-1">{{ plan.projectName }}</div>
                        <div class="project-2">
                          {{ plan.startDate }}~{{ plan.endDate }}
                        </div>
                      </li>
                    </ul>

                    <br />
                    <br />
                    <br />
                  </v-card-text>
                </v-card>
              </v-bottom-sheet>
              <!--여행 일정과 메모 추가-->
              <v-bottom-sheet v-model="daySheet" inset>
                <v-card class="text-center" height="400">
                  <v-card-text>
                    <span class="project-title"
                      >몇 일차에 여행을 떠나실건가요?</span
                    >
                    <div class="daysAndMemo">
                      <a
                        class="project"
                        :class="{ toggle: active }"
                        @click="selectDay(index + 1)"
                        v-for="(date, index) in dateRange"
                        :key="date"
                      >
                        <div>{{ date }}</div>
                        <div id="date">{{ index + 1 }}일차</div>
                      </a>
                    </div>

                    <br />
                    <br />

                    <input
                      v-model="newlocation.memo"
                      type="text"
                      placeholder="메모를 입력하세요"
                      style="
                        border: 1px solid;
                        border-radius: 5px;
                        width: 300px;
                        height: 50px;
                        padding-left: 10px;
                      "
                    />
                    <br />
                    <v-btn
                      variant="tonal"
                      class="mt-4"
                      @click="daySheet = !daySheet"
                    >
                      취소
                    </v-btn>
                    <v-btn variant="tonal" class="mt-4" @click="addNewPlace">
                      완료
                    </v-btn>
                  </v-card-text>
                </v-card>
              </v-bottom-sheet>
            </div>
            add
          </div>

          <div class="like" @click="toggleLike">
            <div class="like-part">
              <img
                v-if="isLiked"
                src="@/assets/img/full-heart.svg"
                alt="full-heart"
                :class="{ selected: isLiked }"
              />
              <img
                v-if="!isLiked"
                src="@/assets/img/empty-heart.svg"
                alt="full-heart"
                :class="{ selected: !isLiked }"
              />
            </div>
            좋아요 {{ numberOfLikes }}개
          </div>
        </div>
      </div>
      <!--댓글 작성란-->
      <form @submit.prevent="writeReview" class="write">
        <div class="form">
          <span>별점을 선택하세요 </span>
          <v-rating
            v-model="userReview.rating"
            :max="5"
            :size="21"
            :half-increments="true"
            color="amber"
            background-color="amber"
          ></v-rating>
          <input
            type="text"
            id="textInput"
            v-model="userReview.comment"
            placeholder="댓글을 입력하세요"
            maxlength="100"
          />
          <p v-if="userReview.comment.length == 0">내용을 입력해주세요.</p>
        </div>
        <div class="submit">
          <button id="btn" type="submit">등록</button>
        </div>
      </form>
      <!--아직 댓글이 없는 경우-->
      <div class="emptyComments" v-if="numberOfComments == 0">
        <span><strong>아직 댓글이 없습니다</strong></span>
      </div>
      <!--댓글이 있는 경우-->
      <div class="comments" v-else>
        <div class="comment" v-for="comment in comments" :key="comment.id">
          <div v-if="commentId != comment.id" class="nonModify">
            <div class="userInfo">
              <v-rating
                half-increments
                readonly
                :length="5"
                :size="21"
                :model-value="comment.rating"
                color="amber"
                active-color="amber"
              />
              <span id="nickname">{{ comment.nickname }} </span>
              <span id="time">{{ comment.registeredAt.split("T")[0] }} </span>
              <!--사용자가 남긴 댓글이면 수정 삭제 버튼 보이기-->
              <div v-if="userName == comment.userName" class="access">
                <div @click="clickModify(comment)">수정</div>
                <div @click="deleteReview(comment.id)">삭제</div>
              </div>
            </div>
            <h4>{{ comment.comment }}</h4>
          </div>
          <!--댓글 수정-->
          <form
            v-else
            @submit.prevent="modifyReview(modifyComment)"
            class="modify"
          >
            <div class="form">
              <span>별점을 선택하세요 </span>
              <v-rating
                v-model="modifyComment.rating"
                :max="5"
                :size="20"
                :half-increments="true"
                color="amber"
                background-color="grey lighten-2"
              ></v-rating>
              <input
                type="text"
                id="textInput"
                v-model="modifyComment.comment"
                maxlength="100"
              />
              <p v-if="modifyComment.comment.length == 0">
                내용을 입력해주세요.
              </p>
            </div>
            <div class="submit">
              <button id="btn" type="submit">수정</button>
            </div>
          </form>
        </div>
        <v-divider :thickness="2" color="#616161"></v-divider>
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
  width: 56vw;
  margin: 0 auto;
}

.info {
  display: flex;
  justify-content: space-between;
}

.rate {
  margin-top: 55px;
  margin-left: 10px;
  cursor: pointer;
}

.rate > span {
  margin-left: 10px;
}

#add {
  margin-top: 56px;
  margin-left: 20px;
}

.daysAndMemo {
  display: flex;
  width: 80%;
  justify-content: space-between;
  margin: 20px auto;
}

.img {
  width: 100%;
  margin-bottom: 30px;
}
#title {
  font-size: 30px;
}
.map {
  margin: 40px 0;
}

.head {
  display: flex;
  justify-content: space-between;
}

#review {
  font-size: 25px;
  margin-right: 20px;
}
.reviewInfo {
  display: flex;
  width: 70%;
}

.score {
  display: flex;
}
#score {
  font-size: 20px;
}
#cavg {
  margin-left: 10px;
}
.rating {
  /* margin-top: 10px; */
}

.likeAdd {
  padding-top: 10px;
  display: flex;
  justify-content: flex-end;
}
.add {
  width: 65.28px;
  cursor: pointer;
  justify-content: center;
  text-align: center;
}
.add-icon {
  width: 70%;
}
.items {
  display: flex;
  justify-content: space-around;
}

.like {
  /* width: 40%; */
  cursor: pointer;
  justify-content: center;
}
.like-part {
  text-align: center;
}

.project {
  display: inline-block;
  padding: 8px 16px;
  background-color: #d9aa8a; /* 배경 색상 */
  color: #fff; /* 텍스트 색상 */
  border: 1px solid #d9aa8a; /* 테두리 색상 */
  border-radius: 4px; /* 테두리 모서리를 둥글게 만듦 */
  cursor: pointer; /* 포인터 모양으로 변경하여 클릭 가능함을 나타냄 */
  text-align: center;
  text-decoration: none;
  font-size: 14px;
  font-weight: bold;
  transition: background-color 0.3s ease; /* 배경 색상 변경 시 부드러운 효과 */
}

.project:hover {
  cursor: pointer;
  background-color: #333; /* 호버 시 배경 색상 변경 */
}

.toggle:active {
  cursor: pointer;
  background-color: #333; /* 호버 시 배경 색상 변경 */
}

.project-title {
  font-size: 24px; /* 글자 크기 */
  color: #5d8bdb; /* 강조 색상 */
  font-weight: bold; /* 글자 굵게 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2); /* 글자 그림자 추가 */
}
.selected {
  color: red;
  width: 70%;

  margin: 0 auto;
}

.write {
  width: 100%;
  /* margin-left: 50px; */
  display: flex;
  justify-content: space-between;
  align-content: center;
  padding: 20px 0;
}
.submit {
  display: flex;
  justify-content: center;
  align-items: center;
}
.userInfo {
  display: flex;
}

.userInfo > span {
  margin-left: 30px;
}

#nickname {
  margin-left: 20px;
  margin-top: 5px;
}

#time {
  margin-left: 30px;
  margin-top: 5px;
}

.form {
  /* margin-bottom: 20px; */
}
.write {
  display: flex;
  align-content: center;
}

.form > input {
  width: 100%;
  height: 50px;
  border: 1px solid black;
}

.form > p {
  color: red;
}

.comment {
  width: auto;
  padding: 20px;
  border: 1px solid #ddd; /* 테두리 색상 */
  border-radius: 8px; /* 테두리 모서리를 둥글게 만듦 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  margin: 10px 0;
}
.access {
  padding-left: 80px;
  margin-left: 50px;
  display: flex;
  right: 0;
}
.access > div {
  display: block;
  width: 60px;
  /* height: 40px; */
  text-align: center;
  margin-right: 5px; /* 수정과 삭제 버튼 사이의 간격 조절 */
  padding: 10px;
  color: #333; /* 글자 색상 */
  cursor: pointer; /* 포인터 모양으로 변경하여 클릭 가능함을 나타냄 */
  border: 2px solid #333;
  border-radius: 4px; /* 둥근 테두리 적용 */
  cursor: pointer;
  font-weight: 700;
  box-sizing: border-box;
  transition: background-color 0.4s ease; /* 배경 색상 변경 시 부드러운 효과 */
}

.access > div:hover {
  background-color: #333; /* 호버 시 배경 색상 변경 */
  color: #fff;
}

#btn {
  display: block;
  position: relative;
  float: left;
  width: 75px;
  height: 40px;
  padding: 0;
  margin: 15px 0 15px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  box-shadow: 0px 5px 0px 0px #1e8185;
}

#btn:hover {
  margin-top: 20px;
  margin-bottom: 5px;
  box-shadow: 0px 0px 0px 0px hsl(182, 63%, 32%);
}

.access > span {
  cursor: pointer;
}
</style>
