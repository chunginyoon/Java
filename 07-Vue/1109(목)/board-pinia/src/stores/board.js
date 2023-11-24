import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useBoardStore = defineStore("board", () => {
  /* =======게시글 목록 Start========== */
  const articles = ref([]); //목록 데이터
  const totalPageCount = ref(0); //전체 페이지 개수
  /*
  Response
{
    config:"",
    data:{
        articles:[{}],
        totalPageCount:12,
        currentPage:10,
    },
    status:200
}
  */
  const getArticles = async (params) => {
    const { data } = await axios.get(`/api/board`, {
      params: params,
    });
    console.log("getArticles(params) 응답데이터:", data);
    articles.value = data.articles;
    totalPageCount.value = data.totalPageCount;
  };
  /* =======게시글 목록 End========== */

  /* =======게시글 상세 Start========== */
  const article = ref({});

  const getArticle = async (articleNo) => {
    const { data } = await axios.get(`/api/board/${articleNo}`);
    console.log(`getArticle(${articleNo}) 요청 응답: `, data);
    article.value = data;
  };

  /* =======게시글 상세 End========== */

  /* =======게시글 등록 Start========== */
  const writeArticle = async (writeForm) => {
    console.log("writeArticle() 요청 데이터 :", writeForm);
    return await axios.post(`/api/board`, writeForm);
  };
  /* =======게시글 등록 End========== */

  /* =======게시글 수정 Start========== */
  const modifyArticle = async (modifyForm) => {
    console.log("modifyArticle() 요청 데이터 :", modifyForm);
    return await axios.put(`/api/board`, modifyForm);
  };

  /* =======게시글 수정 End========== */

  /* =======게시글 삭제 Start========== */
  const deleteArticle = async (articleNo) => {
    console.log(`deleteArticle(${articleNo}) 요청 보냄`);
    resolve(axios.delete(`/api/board/${articleNo}`));
  };

  /* =======게시글 삭제 End========== */

  return {
    articles,
    totalPageCount,
    getArticles,
    article,
    getArticle,
    writeArticle,
    deleteArticle,
  };
});
