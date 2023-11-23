const signIn = document.querySelector("#signIn");
const signUp = document.querySelector("#signUp");
const logOut = document.querySelector("#logOut");
const myPage = document.querySelector("#myPage");
const bulletinBoard = document.querySelector("#bulletin-board");
let isLogin = localStorage.getItem("isLogin");
if (isLogin != null) {
    logOut.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
    myPage.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
    bulletinBoard.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none"
    );
    signIn.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none d-none"
    );
    signUp.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none d-none"
    );
  }

const submitForm = document.querySelector(".submit-form");

submitForm.addEventListener("submit", function(){
    alert("회원가입 성공!")
    let memberInfo = {
        name : "김싸피",
        id : "ssafy",
        pw : "ssafy",
        address : "대전광역시 유성구",
        전화번호 : "010-0000-0000"
    }
    localStorage.setItem("memberInfo", JSON.stringify(memberInfo));
})