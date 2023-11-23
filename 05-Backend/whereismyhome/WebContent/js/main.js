$(function () {
  $("#scroller").simplyScroll({
    pauseOnHover: false,
  });
});

//돔이 로드되면 명함에 대한 애니메이션을 실행한 뒤(1초)
// -> 명함의 부가설명 애니메이션을 실행한 뒤(2초)
// -> 하위요소(대검색창, 슬라이드) 애니메이션을 실행
document.addEventListener("DOMContentLoaded", function () {
  const animateElements = document.querySelectorAll(".text-muted");

  setTimeout(() => {
    animateElements[0].classList.add("animation-trigger");
  }, 1000);

  setTimeout(() => {
    document.querySelector("section:nth-child(2)").classList.add("appear");
    document.querySelector("section:nth-child(3)").classList.add("appear");
  }, 2000);
});

new Swiper(".swiper", {
  autoplay: {
    delay: 1500,
  },
  loop: true,
  slidesPerView: 4,
  spaceBetween: 30,
  centeredSlides: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
});



const signIn = document.querySelector("#signIn");
const signUp = document.querySelector("#signUp");
const logOut = document.querySelector("#logOut");
const myPage = document.querySelector("#myPage");
const bulletinBoard = document.querySelector("#bulletin-board");
let isLogin = localStorage.getItem("isLogin");
// console.log(isLogin);

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

logOut.addEventListener("click", function () {
  alert("로그아웃 성공!!");
  localStorage.removeItem("isLogin");
  window.location.reload();
});
