import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheBoardView from "../views/TheBoardView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/board",
      name: "board",
      component: TheBoardView,
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      // component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          //children은 자동으로 앞에 슬래쉬 붙여주므로 슬래쉬 사용하지 않는다
          path: "list",
          name: "article-list", //이름은 unique하게 붙인다
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleNo",
          name: "article-view", //이름은 unique하게 붙인다
          component: () => import("@/components/board/BoardList.vue"),
        },
      ],
    },
  ],
});

export default router;
