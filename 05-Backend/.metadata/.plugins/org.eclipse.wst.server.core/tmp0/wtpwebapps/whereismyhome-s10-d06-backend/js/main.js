$(function () {
  $("#scroller").simplyScroll({
    pauseOnHover: false,
  });
});
let root = "http://localhost:8080/whereismyhome-s10-d06-backend";
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

