<script setup>
import { ref, onMounted } from "vue";

const container = ref(null);
const map = ref(null);
const props = defineProps({ contentLocation: Object });

const VITE_KAKAO_MAP_SERVICE_KEY = "226ed59f89b142583e1aa35c14dce496";
onMounted(() => {
  if (window.kakao && window.kakao.maps) initMap();
  else loadScript();
});
const loadScript = () => {
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
  /* global kakao */
  script.onload = () => kakao.maps.load(() => initMap());
  document.head.appendChild(script);
};

const initMap = () => {
  const options = {
    center: new kakao.maps.LatLng(
      props.contentLocation.lat,
      props.contentLocation.lng
    ),
    level: 3,
  };
  map.value = new kakao.maps.Map(container.value, options);

  loadMarkers();
};

const loadMarkers = () => {
  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  const marker = new kakao.maps.Marker({
    map: map.value, // 마커를 표시할 지도
    position: new kakao.maps.LatLng(
      props.contentLocation.lat,
      props.contentLocation.lng
    ), // 마커를 표시할 위치
    // image: markerImage, // 마커의 이미지
  });

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "click", () => {
    // 마커 위에 인포윈도우를 표시합니다
    infowindow.open(map.value, marker);
    console.log("클릭!!");
  });
};
</script>

<template>
  <div ref="container" id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 700px;
}
</style>
