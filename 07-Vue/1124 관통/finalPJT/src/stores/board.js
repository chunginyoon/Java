import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/utils/boardAxios";

import Seoul from "@/assets/img/sido/Seoul.jpg";
import Incheon from "@/assets/img/sido/Incheon.jpg";
import Daejeon from "@/assets/img/sido/Daejeon.jpg";
import Daegu from "@/assets/img/sido/Daegu.jpg";
import Gwangju from "@/assets/img/sido/Gwangju.jpg";
import Busan from "@/assets/img/sido/Busan.jpg";
import Ulsan from "@/assets/img/sido/Ulsan.jpg";
import Sejong from "@/assets/img/sido/Sejong.jpg";
import Gyeonggi from "@/assets/img/sido/Gyeonggi.jpg";
import Gangwon from "@/assets/img/sido/Gangwon.jpg";
import Chungbuk from "@/assets/img/sido/Chungbuk.jpg";
import Chungnam from "@/assets/img/sido/Chungnam.jpg";
import Gyeongbuk from "@/assets/img/sido/Gyeongbuk.jpg";
import Gyeongnam from "@/assets/img/sido/Gyeongnam.jpg";
import Jeonbuk from "@/assets/img/sido/Jeonbuk.jpg";
import Jeonnam from "@/assets/img/sido/Jeonnam.jpg";
import Jeju from "@/assets/img/sido/Jeju.jpg";

export const useBoardStore = defineStore("board", () => {
  /* ======시도 Start============== */
  const sidos = ref([
    {
      sidoCode: 1,
      sidoName: "서울",
      sidoImg: Seoul,
    },
    {
      sidoCode: 2,
      sidoName: "인천",
      sidoImg: Incheon,
    },
    {
      sidoCode: 3,
      sidoName: "대전",
      sidoImg: Daejeon,
    },
    {
      sidoCode: 4,
      sidoName: "대구",
      sidoImg: Daegu,
    },
    {
      sidoCode: 5,
      sidoName: "광주",
      sidoImg: Gwangju,
    },
    {
      sidoCode: 6,
      sidoName: "부산",
      sidoImg: Busan,
    },
    {
      sidoCode: 7,
      sidoName: "울산",
      sidoImg: Ulsan,
    },
    {
      sidoCode: 8,
      sidoName: "세종특별자치시",
      sidoImg: Sejong,
    },
    {
      sidoCode: 31,
      sidoName: "경기도",
      sidoImg: Gyeonggi,
    },
    {
      sidoCode: 32,
      sidoName: "강원도",
      sidoImg: Gangwon,
    },
    {
      sidoCode: 33,
      sidoName: "충청북도",
      sidoImg: Chungbuk,
    },
    {
      sidoCode: 34,
      sidoName: "충청남도",
      sidoImg: Chungnam,
    },
    {
      sidoCode: 35,
      sidoName: "경상북도",
      sidoImg: Gyeongbuk,
    },
    {
      sidoCode: 36,
      sidoName: "경상남도",
      sidoImg: Gyeongnam,
    },
    {
      sidoCode: 37,
      sidoName: "전라북도",
      sidoImg: Jeonbuk,
    },
    {
      sidoCode: 38,
      sidoName: "전라남도",
      sidoImg: Jeonnam,
    },
    {
      sidoCode: 39,
      sidoName: "제주도",
      sidoImg: Jeju,
    },
  ]);
  /* ======시도 End============== */

  /* ======구군 얻기 Start============== */
  const guguns = ref([]);
  const getGugun = async (sidoCode) => {
    const { data } = await axios.get(`/api/v1/cities/${sidoCode}`);
    console.log("구군 데이터", data);
    guguns.value = data.result.guguns;
    const selectAll = { gugunName: "전체보기", gugunCode: "" };
    guguns.value.unshift(selectAll);
  };
  /* ======구군 End============== */

  /* ======목록 Start============== */
  const contents = ref([]);
  const totalContents = ref(0); // 전체 게시글의 개수
  const pageable = ref({}); // 페이지 정보(현재 페이지 정보, 한페이지 당 사이즈)
  const totalPages = ref(0);
  const getContents = async (params) => {
    const { data } = await axios.get(`/api/v1/attractions`, {
      params,
    });

    console.log("getContents 응답 데이터 : ", data);
    pageable.value = data.result.pageable; // 페이지어블 객체 가져오기
    contents.value = data.result.content; // 게시글 가져오기
    totalPages.value = data.result.totalPages; // 전체 페이지 수 가져오기
    totalContents.value = data.result.totalElements; // 전체 게시글 수 가져오기
  };
  /* ======목록 End================ */

  /* ======실시간 한국 핫플 Start============== */
  const posts = ref([]);
  const getPosts = async (params) => {
    const { data } = await axios.get(`/api/v1/attractions`, {
      params,
    });

    console.log("getPosts 응답 데이터 : ", data);
    posts.value = data.result.content; // 게시글 가져오기
  };
  /* ======실시간 한국 핫플 End================ */

  /* ======조회수 순으로 조회 Start============== */
  const viewPost = ref([]);
  const getViewPost = async (params) => {
    const { data } = await axios.get(`/api/v1/attractions`, {
      params,
    });

    console.log("getViewPost 응답 데이터 : ", data);
    viewPost.value = data.result.content; // 게시글 가져오기
  };
  /* ======조회수 순으로 조회 End================ */

  /* ======상세 페이지 정보 Start============== */
  const content = ref({});
  const getContent = async (contentId) => {
    const { data } = await axios.get(`/api/v1/attractions/${contentId}`);
    console.log("getContent의 응답 데이터 : ", data);

    content.value = data.result;
  };
  /* ======상세 페이지 정보 End============== */

  /* ======상세 페이지 설명 Start============== */
  const description = ref({});
  const getDescription = async (contentId) => {
    const { data } = await axios.get(
      `/api/v1/attractions/${contentId}/description`
    );
    console.log("getDescription 응답 데이터 : ", data);

    description.value = data.result.description;
  };
  /* ======상세 페이지 설명 End============== */

  /* ======상세 페이지 댓글 목록&개수 Start============== */
  const comments = ref([]);
  const numberOfComments = ref(0); //총 댓글 수
  // const totalPageCount = ref(0);
  const getComments = async (contentId) => {
    const { data } = await axios.get(
      `/api/v1/attractions/${contentId}/comments`
    );

    console.log("getComments 응답 데이터 : ", data);

    comments.value = data.result.content;
    numberOfComments.value = data.result.totalElements;

    // totalPageCount.value = data.totalPageCount;
  };
  /* ======상세 페이지 댓글 목록&개수 End============== */

  /* ======댓글 등록 Start================ */
  const writeReview = async (userReview, contentId) => {
    console.log("writeReview() 요청, 등록데이터 : ", userReview);
    return await axios.post(
      `/api/v1/attractions/${contentId}/comments`,
      userReview
    );
  };
  /* ======댓글 등록 End================ */

  /* ======댓글 수정 Start================ */
  const modifyReview = async (contentId, commentId, modifyComment) => {
    console.log("modifyReview() 요청, 수정데이터 : ", modifyComment);
    return await axios.put(
      `/api/v1/attractions/${contentId}/comments/${commentId}`,
      modifyComment
    );
  };
  /* ======댓글 수정 End================ */

  /* ======댓글 삭제 Start================ */
  const deleteReview = async (contentId, commentId) => {
    console.log("삭제 요청 보냄 삭제 글번호:", commentId);
    return await axios.delete(
      `/api/v1/attractions/${contentId}/comments/${commentId}`
    );
  };
  /* ======댓글 삭제 End================ */

  /* ======상세 페이지 좋아요 개수 Start============== */
  const numberOfLikes = ref(0); //게시글의 총 좋아요 수
  const getLikes = async (contentId) => {
    const { data } = await axios.get(
      `/api/v1/attractions/${contentId}/likeCount`
    );

    console.log("getLikes 응답 데이터 : ", data);

    numberOfLikes.value = data.result;

    // totalPageCount.value = data.totalPageCount;
  };

  /* ======상세 페이지 좋아요 개수 End============== */

  /* ======내가 누른 좋아요 개수 체크 Start============== */
  const isLiked = ref(false);
  const getMyLike = async (contentId) => {
    const { data } = await axios.get(`/api/v1/attractions/${contentId}/likes`);

    console.log("getMyLike 응답 데이터 : ", data);

    isLiked.value = data.result;
  };
  /* ======내가 누른 좋아요 개수 체크 End============== */

  /* ======사용자가 좋아요 누르기 Start============== */
  const clickLike = async (contentId) => {
    return await axios.post(`/api/v1/attractions/${contentId}/likes`);
  };
  /* ======사용자가 좋아요 누르기 End============== */

  /* ======내가 누른 좋아요 목록 Start============== */
  const myLikesInfo = ref({});
  const myLikes = ref([]);
  const getMyLikeList = async () => {
    const { data } = await axios.get(`/api/v1/attractions/my`);

    console.log("getMyLikeList 응답 데이터 : ", data);

    myLikesInfo.value = data.result;
    myLikes.value = data.result.content;
  };
  /* ======내가 누른 좋아요 목록 End============== */

  /* ======내가 누른 좋아요 목록 Start============== */

  return {
    sidos,
    guguns,
    getGugun,
    contents,
    totalContents,
    pageable,
    totalPages,
    getContents,
    posts,
    getPosts,
    viewPost,
    getViewPost,
    content,
    getContent,
    description,
    getDescription,
    comments,
    numberOfComments,
    getComments,
    numberOfLikes,
    getLikes,
    isLiked,
    getMyLike,
    clickLike,
    writeReview,
    modifyReview,
    deleteReview,
    myLikesInfo,
    myLikes,
    getMyLikeList,
  };
});
