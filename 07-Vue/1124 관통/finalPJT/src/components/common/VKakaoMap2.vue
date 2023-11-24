<script setup>
import { ref, onMounted, watch } from 'vue';

const container = ref(null);
const map = ref(null);
const props = defineProps({ dayItems: Array });
const positions = ref([]);
const markers = ref([]);

const VITE_KAKAO_MAP_SERVICE_KEY = '226ed59f89b142583e1aa35c14dce496';

watch(
  () => props.dayItems.value,
  () => {
    // positions.value = [];
    // for (let item of props.dayItems) {
    //   const latLng = new kakao.maps.LatLng(
    //     item.attractionInfo.latitude,
    //     item.attractionInfo.longitude
    //   );

    //   positions.value.push(latLng);
    // }
    // console.log('positions', positions.value);
    loadMarkers();
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) initMap();
  else loadScript();
});
const loadScript = () => {
  const script = document.createElement('script');
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
  /* global kakao */
  script.onload = () => kakao.maps.load(() => initMap());
  document.head.appendChild(script);
};

const initMap = () => {
  const options = {
    center: new kakao.maps.LatLng(37.566535, 126.9779692),
    level: 3,
  };
  map.value = new kakao.maps.Map(container.value, options);

  loadMarkers();
};

const loadMarkers = () => {
  //마커 초기화
  for (let marker of markers.value) marker.setMap(null);
  markers.value = [];

  // positions.value.forEach((position) => {
  //   const marker = new kakao.maps.Marker({
  //     map: map.value, // 마커를 표시할 지도
  //     position: position, // 마커를 표시할 위치
  //   });
  //   markers.value.push(marker);
  // });

  const bounds = new kakao.maps.LatLngBounds();
  // 마커를 생성합니다

  for (let item of props.dayItems) {
    const latLng = new kakao.maps.LatLng(
      item.attractionInfo.latitude,
      item.attractionInfo.longitude
    );
    const marker = new kakao.maps.Marker({
      map: map.value, // 마커를 표시할 지도
      position: latLng, // 마커를 표시할 위치
      // image: markerImage, // 마커의 이미지
    });
    markers.value.push(marker);

    bounds.extend(latLng);
  }

  map.value.setBounds(bounds);

  // 마커에 클릭이벤트를 등록합니다
  // kakao.maps.event.addListener(marker, "click", () => {
  //   // 마커 위에 인포윈도우를 표시합니다
  //   infowindow.open(map.value, marker);
  //   console.log("클릭!!");
  // });
};
</script>

<template>
  <div ref="container" id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 700px;
  z-index: 3;
}
</style>
