import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/views/BoardView/BoardList.vue";
import BoardDetail from "@/views/BoardView/BoardDetail.vue";
import BoardWrite from "@/views/BoardView/BoardWrite.vue";
import BoardModify from "@/views/BoardView/BoardModify.vue";

// console.log("HomView import 정보 : ", HomeView);

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
        },
        //BoardModify
        {
          name: "article-modify",
          path: "modify/:articleNo",
          component: BoardModify,
        },
      ],
    },
  ],
});

export default router;
