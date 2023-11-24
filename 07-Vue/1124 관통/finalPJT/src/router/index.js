import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "../stores/auth";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import MyTrip from "@/views/MyTrip.vue";
import MyPage from "@/views/MyPage.vue";
import SignIn from "@/views/SignIn.vue";
import Board from "@/views/Board.vue";
import MyPageModify from "@/components/member/MyPageModify.vue";
import MyPageInfo from "@/components/member/MyPageInfo.vue";
import SignOut from "@/components/member/SignOut.vue";
import BoardListContentSido from "@/components/board/BoardListContentSido.vue";
import BoardListContentGugun from "@/components/board/BoardListContentGugun.vue";
import BoardContentView from "@/components/board/BoardContentView.vue";
import BoardKeywordCategory from "@/components/board/BoardKeywordCategory.vue";
import MyTripPlan from "@/components/mytrip/MyTripPlan.vue";
import CreateNewTrip from "@/components/mytrip/CreateNewTrip.vue";
import MyPlanView from "@/components/mytrip/MyPlanView.vue";
import ModifyTrip from "@/components/mytrip/ModifyTrip.vue";
import UploadFeed from "@/components/board/UploadFeed.vue";
import FeedList from "@/components/board/FeedList.vue";
import FeedView from "@/components/board/FeedView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //HomeView
    {
      path: "/",
      name: "home",
      component: Home,
    },
    //BoardView
    {
      path: "/board",
      component: Board,
      children: [
        {
          path: "content/:contentId/:contentType",
          name: "board-list-sido",
          component: BoardListContentSido,
        },
        {
          path: "content/:contentId/:contentType/:sidoCode/:sidoName",
          name: "board-list-gugun",
          component: BoardListContentGugun,
        },
        {
          path: "content/:contentId",
          name: "board-content-view",
          component: BoardContentView,
        },
        {
          path: "keyword/:keyword",
          name: "board-keyword",
          component: BoardKeywordCategory,
        },
        {
          path: "uploadfeed",
          name: "board-uploadfeed",
          component: UploadFeed,
        },
        {
          path: "feedlist",
          name: "board-feedlist",
          component: FeedList,
        },
        {
          path: "feedview/:postId",
          name: "board-feedview",
          component: FeedView,
        },
      ],
    },
    //LoginView
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    //SignInView
    {
      path: "/signin",
      name: "signin",
      component: SignIn,
    },
    //MyTripView
    {
      path: "/mytrip",
      name: "mytrip",
      component: MyTrip,
      meta: { requiresAuth: true },
      children: [
        {
          path: "myplan",
          name: "mytrip-plan",
          component: MyTripPlan,
        },
        {
          path: "newplan",
          name: "mytrip-newplan",
          component: CreateNewTrip,
        },
        {
          path: "viewplan/:id",
          name: "mytrip-viewplan",
          component: MyPlanView,
        },
        {
          path: "modifyplan/:id",
          name: "mytrip-modifyplan",
          component: ModifyTrip,
        },
      ],
    },
    //MyPage
    {
      path: "/mypage",
      component: MyPage,
      redirect: "/mypage/mypageinfo",
      children: [
        { name: "mypage-info", path: "mypageinfo", component: MyPageInfo },
        { name: "mypage-modify", path: "modify", component: MyPageModify },
        { name: "mypage-signout", path: "signout", component: SignOut },
      ],
    },
  ],
});

//로그인, 또는 관리자 권한이 필요한 경우 제어 ( next()의 경우, 원래 이동하려 했던 라우트로 이동 )
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  //로그인 필요
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (!authStore.token) {
      alert("로그인 권한 필요");
      next({ path: "/login" }); // 인증되지 않은 경우, 로그인 페이지로 리다이렉트
    } else {
      //관리자 권한 필요
      if (to.matched.some((record) => record.meta.requiresAdmin)) {
        // 관리자가 아닌 경우 홈으로
        if (authStore.user.role !== "admin") {
          alert("관리자 권한 필요");
          next({ path: "/" });
        } else next(); // 관리자인 경우, 해당 경로로 이동
      } else next(); // 인증된 경우 해당 경로로 이동
    }
  } else next(); // 인증이 필요하지 않은 경우 해당 경로로 이동
});

export default router;
