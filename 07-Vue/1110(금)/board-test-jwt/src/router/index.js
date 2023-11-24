import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/views/BoardView/BoardList.vue";
import BoardDetail from "@/views/BoardView/BoardDetail.vue";
import BoardWrite from "@/views/BoardView/BoardWrite.vue";
import BoardModify from "@/views/BoardView/BoardModify.vue";
import LoginForm from "@/views/LoginForm.vue";
import { useAuthStore } from "../stores/auth";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //HomeView
    {
      path: "/",
      component: HomeView,
    },
    //BoardView
    {
      path: "/board",
      component: BoardView,
      redirect: "/board/list",
      children: [
        //BoardList
        {
          path: "list",
          component: BoardList,
        },
        //BoardDetail
        {
          name: "article-detail",
          path: "detail/:articleNo",
          component: BoardDetail,
        },
        //BoardWrite
        {
          name: "article-write",
          path: "write",
          component: BoardWrite,
          meta: { requiresAuth: true },
        },
        //BoardModify
        {
          name: "article-modify",
          path: "modify/:articleNo",
          component: BoardModify,
          meta: { requiresAuth: true },
          //관리자 권한 또한 필요한 경우
          // meta: { requiresAuth: true, requiresAdmin: true },
        },
      ],
    },
    //로그인 페이지
    {
      path: "/login",
      name: "login",
      component: LoginForm,
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
