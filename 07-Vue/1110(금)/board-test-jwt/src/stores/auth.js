import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export const useAuthStore = defineStore(
  "auth",
  () => {
    //login User의 정보
    const user = ref({
      userId: "",
      userName: "",
      role: "",
    });
    const token = ref(""); //jwt 토큰 정보

    const login = async (loginForm) => {
      const { data } = await axios.post(`/api/member`, loginForm);
      // console.log("로그인 요청 후 응답 데이터:", data);

      token.value = data.token; //토큰 정보 저장

      const decoded = jwtDecode(data.token); //토큰에서 유저정보 추출하여 유저정보 저장
      console.log("디코딩된 토큰 정보 :", decoded);
      user.value.userId = decoded.userId;
      user.value.userName = decoded.userName;
      user.value.role = decoded.role;
    };

    const logout = () => {
      clearUser();
    };

    const clearUser = () => {
      user.value.userId = "";
      user.value.userName = "";
      user.value.role = "";
      token.value = "";
    };

    return { user, token, login, logout, clearUser };
  },

  //새로고침시 데이터 유지를 위한 설정(localStorage에 저장해서 불러오는 방식)
  {
    persist: true,
  }
  //sessionStorage 사용하고 싶은 경우
  // {
  //   persist: {
  //     storage: sessionStorage,
  //   },
  // }
);
