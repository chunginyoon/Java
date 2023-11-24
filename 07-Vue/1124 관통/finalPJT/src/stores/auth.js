import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

export const useAuthStore = defineStore(
  "auth",
  () => {
    //login User의 정보
    const user = ref({
      userName: "", //아이디
      nickname: "", //닉네임
      role: "", //직위
    });
    const token = ref(""); //jwt 토큰 정보

    const login = async (loginForm) => {
      const { data } = await axios.post(`/api/v1/users/login`, loginForm);
      console.log("로그인 요청 후 응답 데이터:", data);

      token.value = data.result.token; //토큰 정보 저장

      const decoded = jwtDecode(data.result.token); //토큰에서 유저정보 추출하여 유저정보 저장
      console.log("디코딩된 토큰 정보 :", decoded);
      user.value.userName = decoded.userName;
      user.value.nickname = decoded.nickname;
      user.value.role = decoded.role;
    };

    //회원가입
    const signIn = async (signInForm) => {
      const { data } = await axios.post(`/api/v1/users/join`, signInForm);
      console.log("회원가입 요청 후 응답 데이터:", data);
    };

    const logout = () => {
      clearUser();
    };

    const clearUser = () => {
      user.value.userName = "";
      user.value.userName = "";
      user.value.role = "";
      token.value = "";
    };

    return { user, token, login, signIn, logout, clearUser };
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
