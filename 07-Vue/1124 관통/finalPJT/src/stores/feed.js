import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/utils/boardAxios";

export const useFeedStore = defineStore("feed", () => {
  /* ======작성한 포스트 전체 조회 Start============== */
  const posts = ref([]);
  const pageable = ref({});
  const totalPages = ref(0);
  const getPosts = async () => {
    const { data } = await axios.get(`/api/v1/posts`);
    console.log("전체 포스트", data);
    posts.value = data.result.content;
    pageable.value = data.result.pageable;
    totalPages.value = data.result.totalPages;
  };
  /* ======작성한 포스트 전체 조회 End============== */

  /* ======포스트 생성하기 Start============== */
  const registFeed = async (formData) => {
    const { data } = await axios.post(`/api/v1/posts`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    console.log("등록할 피드", data);
  };
  /* ======포스트 생성하기 End============== */

  /* ======포스트 단건 조회 Start============== */
  const post = ref({});
  const images = ref([]);
  const getPost = async (postId) => {
    const { data } = await axios.get(`/api/v1/posts/${postId}`);
    console.log("포스트 단건 조회", data);
    post.value = data.result;
    images.value = data.result.images;
  };
  /* ======포스트 단건 조회 End============== */

  return {
    posts,
    pageable,
    totalPages,
    getPosts,
    registFeed,
    post,
    images,
    getPost,
  };
});
