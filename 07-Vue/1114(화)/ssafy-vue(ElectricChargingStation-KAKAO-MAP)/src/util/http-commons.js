import axios from "axios";

// # OPEN API KEY
// VITE_OPEN_API_SERVICE_KEY=wwFBN2kgVIUiXe41JQxSc66r73iuuHm+nxLyBnW53dZYuIyTchkW5QOQZbXelGfQvxQ90qwGDL1ZMMlTh4MEgQ==
// VITE_KAKAO_MAP_SERVICE_KEY=226ed59f89b142583e1aa35c14dce496

// # API URL settings for PJT
// VITE_VUE_API_URL=http://localhost/vue
// VITE_ELECTRIC_CHARGING_STATION_URL=https://apis.data.go.kr/B552584/EvCharger/getChargerInfo

// # count per page
// VITE_ARTICLE_LIST_SIZE=20
// VITE_ARTICLE_NAVIGATION_SIZE=10

const { VITE_VUE_API_URL, VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta
  .env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// station vue api axios instance
function stationAxios() {
  const instance = axios.create({
    baseURL: VITE_ELECTRIC_CHARGING_STATION_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { localAxios, stationAxios };
