<script setup>
import { ref, computed, watch } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const isLogin = computed(() => !!authStore.token); //!!의 경우 해당 정보가 truthy하다면 true를 반환
const nickname = computed(() => authStore.user.nickname);

const router = useRouter();

const contents = ref([
  { contentId: 12, contentType: '관광지' },
  { contentId: 14, contentType: '문화시설' },
  { contentId: 15, contentType: '축제공연행사' },
  { contentId: 25, contentType: '여행코스' },
  { contentId: 28, contentType: '레포츠' },
  { contentId: 32, contentType: '숙박' },
  { contentId: 38, contentType: '쇼핑' },
  { contentId: 39, contentType: '음식점' },
]);

const moveHome = () => {
  console.log('홈으로 이동');
  router.push({ name: 'home' }).then(() => {
    window.scrollTo(0, 0);
  });
  keyword.value = '';
};

const keyword = ref('');
watch(keyword, (newValue, oldValue) => {
  console.log(
    '입력값이 변경되었습니다. 이전 값:',
    oldValue,
    '새 값:',
    newValue
  );
});
const searchByKeyword = (keyword) => {
  console.log('홈에서 키워드로 검색', keyword.value);
  router
    .push({
      name: 'board-keyword',
      params: { keyword: keyword },
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
};

const moveMyTrip = () => {
  console.log('나의 여행으로 이동');
  router.push({ name: 'mytrip-plan' });
  keyword.value = '';
};

const moveLogin = () => {
  console.log('로그인 페이지로 이동');
  router.push({ name: 'login' });
  keyword.value = '';
};

const moveSignIn = () => {
  console.log('회원가입 페이지로 이동');
  router.push({ name: 'signin' });
  keyword.value = '';
};

const moveMyPage = () => {
  console.log('마이 페이지로 이동');
  router.push({ name: 'mypage-info' });
  keyword.value = '';
};
const logout = () => {
  if (!confirm('로그아웃 하시겠습니까?')) return;
  authStore.logout();
  router.push({ path: '/' });
  keyword.value = '';
};

const moveListContent = (contentId, contentType) => {
  console.log('컨텐트별 페이지로 이동');
  router
    .push({
      name: 'board-list-sido',
      params: { contentId: contentId, contentType: contentType },
    })
    .then(() => {
      window.scrollTo(0, 0);
    });
  keyword.value = '';
};
</script>

<template>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap"
    rel="stylesheet"
  />
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/icon?family=Material+Icons"
  />
  <header>
    <div class="inner">
      <div class="logo">
        <img src="@/assets/img/logo.png" @click="moveHome()" />
        <div class="search">
          <input
            v-model="keyword"
            @keyup.enter="searchByKeyword(keyword)"
            type="text"
            placeholder="키워드를 검색해보세요"
          />
        </div>
      </div>
      <div class="sub-menu">
        <ul class="menu">
          <!-- 로그인 전 -->
          <template v-if="!isLogin" class="emptyInfo">
            <li>
              <a id="login" @click="moveLogin">로그인</a>
            </li>
            <li>
              <a id="signin" @click="moveSignIn">회원가입</a>
            </li>
          </template>
          <!--로그인 후 -->
          <template v-else class="loginInfo">
            <li>
              <a id="nickname">{{ nickname }}님</a>
            </li>
            <li>
              <a @click="moveMyTrip">나의 여행 </a>
            </li>
            <li>
              <a @click="moveMyPage">마이페이지</a>
            </li>
            <li>
              <a @click="logout">로그아웃</a>
            </li>
          </template>
        </ul>
      </div>
      <!--카테고리 메뉴 바-->

      <ul class="main-menu">
        <li class="item" v-for="content in contents" :key="content.contentId">
          <div
            class="item__name"
            @click="moveListContent(content.contentId, content.contentType)"
          >
            {{ content.contentType }}
          </div>
        </li>
      </ul>
    </div>
  </header>
</template>

<style scoped>
a {
  text-decoration: none;
  color: black;
}

img {
  display: block;
}

.inner {
  width: 1300px;
  margin: 0 auto;
  position: relative;
}

* {
  margin: 0;
  padding: 0;
  list-style: none;
  text-decoration: none;
}

/* .container {
  width: 60vw;
  height: 100vh;
  margin: 0 auto;
} */

/* ========= 헤더 ========= */

header {
  width: 100%;
  background-color: #f6f5f0;
  border-bottom: 1px solid #c8c8c8;
  margin: 0 auto;
  height: 200px;
}
header > .inner {
  width: 1100px;
  margin: 0 auto;
  position: relative;
  height: 120px;
}

header .logo {
  display: flex;
  height: 75px;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 10px;
  /* margin-top: 30px; */
  margin: auto;
}

.logo > img {
  width: 300px;
  cursor: pointer;
}

header .sub-menu {
  position: absolute;
  top: 10px;
  right: 0px;
  display: flex;
}

header .sub-menu ul.menu {
  display: flex;
  font-family: Arial, sans-serif;
}

header .sub-menu ul.menu li {
  position: relative;
}
header .sub-menu ul.menu li::before {
  content: '';
  width: 1px;
  height: 15px;
  background-color: #e5e5e5;
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto 0;
}
header .sub-menu ul.menu li:first-child:before {
  display: none;
}

header .sub-menu ul.menu li a {
  font-size: 15px;
  padding: 11px 16px;
  display: block;
  color: #656565;
}
header .sub-menu ul.menu li a:hover {
  color: #000;
}
header .main-menu .item:hover .item__name {
  /* background-color: #2c2a29; */
  color: #009996;
  border-bottom: 1px solid transparent;
  transition: border-bottom-color 3s ease-out;
  border-width: 0 0 3px;
}
.search > input {
  height: 55px;
  width: 270px;
  margin-top: 15px;
  margin-left: 25px;
  padding-left: 10px;
  border: 1px solid #8b8a8a;
  border-radius: 4px;
  outline: none;
  box-sizing: border-box;
  font-size: 20px;
}
/*  */

ul.main-menu .item {
  margin: 0 10px;

  padding: 10px;
}

header .main-menu {
  display: inline-flex;
  width: 100%;
  justify-content: center;
  position: absolute;
  top: 145px;
  z-index: 1;
}

header .main-menu .item .item__name {
  justify-content: center;
  box-sizing: content-box;
  /* width: 150px; */
  padding: 10px 20px 10px 20px;
  font-family: Arial, sans-serif;
  font-size: 15px;
}
/* .info {
  margin-left: 300px;
  display: flex;
}

.info > a {
  display: block;
  position: relative;
  float: left;
  width: 100px;
  height: 50px;
  padding: 0;
  margin: 33px 0 10px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  box-shadow: 0px 5px 0px 0px #1e8185;
} */

/* .info > a:hover {
  margin-top: 38px;
  margin-bottom: 5px;
  box-shadow: 0px 0px 0px 0px hsl(182, 63%, 32%);
} */

/* .emptyInfo {
  margin-top: 25px;
} */

.emptyInfo > a {
  cursor: pointer;
}

/* .loginInfo {
  display: flex;
} */

/* #login {
  margin-top: 37px;
  margin-left: 30px;
}

#signin {
  margin-top: 37px;
} */

/* #nickname {
  margin-left: 50px;
  margin-top: 50px;
} */
.img {
  margin-top: 10px;
  cursor: pointer;
}

.dropdown {
  display: inline-block;
  position: relative;
}

.dropdown-options {
  display: none;
  position: absolute;
  overflow: auto;
  height: 130px;
  width: 150px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0px 10px 10px 0px rgba(0, 0, 0, 0.4);
}

.dropdown:hover .dropdown-options {
  display: block;
}

.dropdown-options > a {
  display: block;
  color: #000000;
  padding: 0;
  text-decoration: none;
  padding: 20px 0;
  text-align: center;
  cursor: pointer;
}

.dropdown-options a:hover {
  color: #0a0a23;
  background-color: #ddd;
  border-radius: 5px;
}
.img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: tomato;
  margin-left: 40px;
  margin-top: 20px;
  overflow: hidden;
}

.img > img {
  max-height: 100%;
  width: auto;
  display: block;
  object-fit: cover;
}

.menu {
  display: flex;
  justify-content: space-between;
  color: black;
}

header .main-menu {
  display: flex;
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 1;
}

/* .menu > li {
  border-right: none;
  padding: 0px 10px 0px 10px;
  margin: 5px 0px 5px 0px;
  width: calc(100% / 6);
  height: 50px;
  text-align: center;
  line-height: 50px;
  font-size: 20px;
} */

.content {
  cursor: pointer;
}
</style>
