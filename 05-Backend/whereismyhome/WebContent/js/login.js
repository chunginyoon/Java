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



const loginBtn = document.querySelector("#login-btn");


loginBtn.addEventListener("click", function(){
    let memberInfo = localStorage.getItem("memberInfo");
    if(memberInfo != null){

        alert("로그인 성공");
        let isLogin = {
            login:true
        }
        localStorage.setItem("isLogin", JSON.stringify(isLogin));
        window.location.href="./index.html";
    }else{
        alert("로그인 실패!! 회원가입부터 하세요")
        window.location.href="./index.html";
    }
});
