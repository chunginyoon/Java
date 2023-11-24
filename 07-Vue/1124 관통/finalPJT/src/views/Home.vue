<script setup>
import { useRouter } from "vue-router";
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
import defaultImg from "@/assets/img/defaultImg.png";

const boardStore = useBoardStore();
const contents = computed(() => boardStore.contents);
const sidos = computed(() => boardStore.sidos);
const posts = computed(() => boardStore.posts);
const viewPost = computed(() => boardStore.viewPost);

const router = useRouter();

//상세 페이지 불러올 때 사용할 파라미터
const params = ref({
  sido: "", //시도 코드
  gugun: "", //구군 코드
  category: "", //관광지 타입
  word: "", //키워드
  size: "10", //한번에 가져올 데이터의 개수
  page: "", //가져올 페이지
  sort: "desc", //정렬 순서(오름차순, 내림차순)
  orderBy: "avgScore", //정렬 기준
});

const init = async () => {
  //상단 슬라이드
  await boardStore.getContents(params.value);
  console.log("contents", contents.value);

  //제주도의 관광지 50개 가져오기
  params.value.category = 12;
  params.value.sido = 39;
  params.value.size = 50;
  await boardStore.getPosts(params.value);
  params.value.category = ""; //파라미터 초기화
  params.value.sido = "";
  params.value.size = "";

  //조회수 제일 많은 컨텐츠 50개 가져오기
  params.value.orderBy = "readCount";
  params.value.size = 50;
  await boardStore.getViewPost(params.value);
  params.value.orderBy = "";
  params.value.size = "";

  checkImg();
  console.log("전체보기", contents.value);
};

const checkImg = () => {
  for (let i = 0; i < contents.value.length; i++) {
    if (!contents.value[i].firstImage) {
      contents.value[i].firstImage = defaultImg;
    }
  }
};

init();

const moveView = (contentId) => {
  console.log("view로 이동");
  router
    .push({ name: "board-content-view", params: { contentId: contentId } })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const searchBySidoName = (keyword) => {
  router
    .push({
      name: "board-keyword",
      params: { keyword: keyword },
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const createFeed = () => {
  router
    .push({
      name: "board-uploadfeed",
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const feedList = () => {
  router
    .push({
      name: "board-feedlist",
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
};
</script>

<template>
  <div class="container">
    <v-carousel cycle height="600" hide-delimiters>
      <v-carousel-item
        v-for="(content, index) in contents"
        :key="content.contentId"
        :src="content.firstImage"
        cover
        :visible="index === 0"
      >
        <v-row align="center" justify="center">
          <v-col cols="12" sm="4" md="2">
            <v-btn
              id="btn"
              rounded="xl"
              size="large"
              :ripple="false"
              @click="moveView(content.contentId)"
            >
              자세히 보기
            </v-btn>
          </v-col>
        </v-row>
      </v-carousel-item>
    </v-carousel>
    <!--시도 보여주기-->
    <section>
      <h1>여행이 돌아왔어요!&#127757;</h1>
      <div class="images">
        <v-sheet class="mx-auto" max-width="100%">
          <v-slide-group show-arrows>
            <v-slide-group-item v-for="sido in sidos" :key="sido.sidoCode">
              <v-card
                @click="searchBySidoName(sido.sidoName)"
                class="mr-5"
                width="300"
                height="250"
                theme="dark"
              >
                <v-img id="sido" :src="sido.sidoImg" :cover="true"
                  ><v-card-title>{{ sido.sidoName }}</v-card-title></v-img
                >
              </v-card>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </div>
    </section>
    <!--실시간 피드 보여주기-->
    <section class="hotplace">
      <div id="feedHead">
        <h1 @click="feedList" style="cursor: pointer">
          지금 한국에서 제일 인기있는 여행지&#127775;
        </h1>
        <v-col cols="12" sm="3" md="2">
          <v-btn
            block
            rounded="xs"
            size="small"
            prepend-icon="mdi-plus"
            variant="outlined"
            @click="createFeed"
            >피드 올리기</v-btn
          >
        </v-col>
      </div>
      <div class="images">
        <v-sheet class="mx-auto" max-width="100%">
          <v-slide-group show-arrows>
            <v-slide-group-item v-for="post in posts" :key="post.contentId">
              <v-card v-if="post.firstImage" class="feed">
                <v-img
                  @click="moveView(post.contentId)"
                  :src="post.firstImage"
                  class="align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  width="300"
                  height="250"
                  cover
                >
                  <v-card-title
                    class="text-white"
                    v-text="post.title"
                  ></v-card-title>
                </v-img>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-heart"
                    @click="like = !like"
                    :class="{ isSelected: like }"
                  ></v-btn>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-bookmark"
                  ></v-btn>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-share-variant"
                  ></v-btn>
                </v-card-actions>
              </v-card>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </div>
    </section>
    <!--조회수 순으로-->
    <section class="hotplace">
      <div id="feedHead">
        <h1>클릭수가 제일 높은 여행지&#127966;</h1>
      </div>
      <div class="images">
        <v-sheet class="mx-auto" max-width="100%">
          <v-slide-group show-arrows>
            <v-slide-group-item v-for="vp in viewPost" :key="vp.contentId">
              <v-card v-if="vp.firstImage" class="feed">
                <v-img
                  @click="moveView(vp.contentId)"
                  :src="vp.firstImage"
                  class="align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  width="300"
                  height="250"
                  cover
                >
                  <v-card-title
                    class="text-white"
                    v-text="vp.title"
                  ></v-card-title>
                </v-img>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-heart"
                  ></v-btn>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-bookmark"
                  ></v-btn>

                  <v-btn
                    size="small"
                    color="surface-variant"
                    variant="text"
                    icon="mdi-share-variant"
                  ></v-btn>
                </v-card-actions>
              </v-card>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </div>
    </section>
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
  padding-top: 20px;
}

section {
  margin-top: 40px;
}

section > h1 {
  font-size: 30px;
  margin-bottom: 20px;
}

.hotplace {
  margin-top: 50px;
}

.images {
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

#btn {
  margin: 520px auto;
}

#sido {
  object-fit: cover;
  width: 100%;
  height: 100%;
}

#feedHead {
  display: flex;
}
.feed {
  margin-right: 20px;
}
.isSelected {
  color: rgb(213, 58, 58);
}
</style>
