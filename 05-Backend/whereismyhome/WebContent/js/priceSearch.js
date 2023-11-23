const projectPath = "http://localhost:8080/";

const signIn = document.querySelector("#signIn");
const signUp = document.querySelector("#signUp");
const logOut = document.querySelector("#logOut");
const myPage = document.querySelector("#myPage");
const bulletinBoard = document.querySelector("#bulletin-board");
let isLogin = localStorage.getItem("isLogin");
// console.log(isLogin);
// if (isLogin != null) {
//   logOut.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
//   myPage.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
//   bulletinBoard.setAttribute(
//     "class",
//     "me-3 py-2 text-dark text-decoration-none"
//   );
//   signIn.setAttribute(
//     "class",
//     "me-3 py-2 text-dark text-decoration-none d-none"
//   );
//   signUp.setAttribute(
//     "class",
//     "me-3 py-2 text-dark text-decoration-none d-none"
//   );
// }

// logOut.addEventListener("click", function () {
//   alert("로그아웃 성공!!");
//   localStorage.removeItem("isLogin");
//   window.location.reload();
// });

let date = new Date();

function sendRequest(selid, regcode) {
  const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
  fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function initOption(selid) {
  let = options = document.querySelector(`#${selid}`);
  options.length = 0;
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  switch (selid) {
    case "sido":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `
          <option value="${regcode.code}">${regcode.name}</option>
          `;
      });
      break;
    case "gugun":
      opt += `<option value="">구군선택</option>`;
      for (let i = 0; i < data.regcodes.length; i++) {
        if (i != data.regcodes.length - 1) {
          if (
            data.regcodes[i].name.split(" ")[1] ==
              data.regcodes[i + 1].name.split(" ")[1] &&
            data.regcodes[i].name.split(" ").length !=
              data.regcodes[i + 1].name.split(" ").length
          ) {
            data.regcodes.splice(i, 1);
            i--;
          }
        }
      }
      let name = "";
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length == 2)
          name = regcode.name.split(" ")[1];
        else
          name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
        opt += `
          <option value="${regcode.code}">${name}</option>
          `;
      });
      break;
    case "dong":
      opt += `<option value="">동선택</option>`;
      let idx = 2;
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length != 3) idx = 3;
        opt += `
          <option value="${regcode.code}">${
          regcode.name.split(" ")[idx]
        }</option>
          `;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugun", regcode);
  } else {
    initOption("gugun");
    initOption("dong");
  }
});

document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dong", regcode);
  } else {
    initOption("dong");
  }
});

window.onload = function () {
  let yearEl = document.querySelector("#year");
  let yearOpt = `<option value="">매매년도선택</option>`;
  let year = date.getFullYear();
  for (let i = year; i > year - 20; i--) {
    yearOpt += `<option value="${i}">${i}년</option>`;
  }
  yearEl.innerHTML = yearOpt;

  sendRequest("sido", "*00000000");

  let searchKey = localStorage.getItem("search");
  let searchObject = JSON.parse(searchKey);

  if (searchObject["isSearch"]) {
    localStorage.removeItem("search");
    console.log("hello");
    startmap();
  }
};

document.querySelector("#year").addEventListener("change", function () {
  let month = date.getMonth() + 1;
  let monthEl = document.querySelector("#month");
  let monthOpt = `<option value="">매매월선택</option>`;
  let yearSel = document.querySelector("#year");
  let m =
    yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
  for (let i = 1; i < m; i++) {
    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
  }
  monthEl.innerHTML = monthOpt;
});

const aptBtn = document.querySelector("#aptBtn");
aptBtn.addEventListener("click", function () {
  startmap();
  // window.location.reload();
});

let searchResult = [];
let ps;
let btnShow = document.getElementById("list-btn");

let positions = [];
// 마커를 담을 배열입니다
let markers = [];
let infowindow;

async function startmap() {
  searchResult = [];


  let sidoName = document.querySelector("#sido > option:checked").innerText;
  let gugunName = document.querySelector("#gugun > option:checked").innerText;
  let dongName = document.querySelector("#dong > option:checked").innerText;

  
  // console.log("dong: "+dong);
  // console.log(apts);
  
  let year = document.querySelector("#year > option:checked").innerText;
  year = year.substring(0, year.length - 1);
  let month = document.querySelector("#month > option:checked").innerText;
  month = month.substring(0, month.length - 1);

  let url = projectPath +
    `home?action=search&sido=${sidoName}&dong=${dongName}&year=${year}&month=${month}`;
  console.log(url);
  let response = await fetch(url);

  // let response = await fetch(`${url}?${queryParams}`);
  let data = await response.text();
  // console.log(data);
  await makeList(data); //동정보 기반으로
  // console.log(searchResult);
  positions = await getPosition(searchResult);
}

function move(idx) {
  let apt = searchResult[idx];
  console.log(apt);
  var geocoder = new kakao.maps.services.Geocoder();
  let address = apt["address"];
  console.log(address);
  geocoder.addressSearch(address, function (result, status) {
    let position = new kakao.maps.LatLng(result[0].y, result[0].x);
    map.setCenter(position);
    map.setLevel(map.getLevel() - 2);
  });
}

async function getPosition(searchResult) {
  var imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  // console.log(searchResult);

  let descrption = document.querySelector("#description");
  if (searchResult.length == 0) {
    console.log("거래 정보가 없습니다");
    descrption.textContent = "거래정보가 없습니다.";
  } else {
    descrption.innerText = "결과조회";
    let str = `
      결과조회
      <hr class="line">
    `;

    for (let i = 0; i < searchResult.length; i++) {
      let cost = searchResult[i].cost;
      let aptName = searchResult[i].aptName;
      let area = searchResult[i].area;
      str += `
        <div class="apt border-white">
          <div class="list-group" onclick="move(${i})"> 
            <button class="list-group-item" ><strong>${cost.toLocaleString()}만원</strong></button>
            <button class="list-group-item" >${aptName}</button>
            <button class="list-group-item">${area}㎡</button>
          </div>
        </div>
      `;
    }
    searchResult.forEach(function (result) {});

    descrption.innerHTML = str;
  }
  searchResult.forEach(async (apt) => {
    var geocoder = await new kakao.maps.services.Geocoder();
    geocoder.addressSearch(apt["address"], function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        positions.push(new kakao.maps.LatLng(result[0].y, result[0].x));
        // 마커 이미지의 이미지 크기
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(result[0].y, result[0].x), // 마커를 표시할 위치
          // title : apt.querySelector("거래금액").textContent, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        let lastPosition = new kakao.maps.LatLng(result[0].y, result[0].x); //지도 센터 좌표값
        // console.log(result[0].y, result[0].x);
        map.setCenter(lastPosition);
      }
    });

    //////////////////////////////////////////////////////////////////////////////////
    // 장소 검색 객체를 생성합니다
    ps = new kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  });
  return positions;
}

async function makeList(data) {
  let parser = new DOMParser();
  const xml = parser.parseFromString(data, "application/xml");

  let apts = xml.querySelectorAll("item");

  apts.forEach((apt) => {
    // console.log(apt);
    let curDongCode = apt.querySelector("법정동읍면동코드").textContent;
    let curYear = apt.querySelector("년").textContent;
    // console.log("법정동읍면동코드: " +curDongCode);
    // console.log("dong: "+dong.substr(5));

    let aptObject = {
      cost: parseInt(apt.querySelector("거래금액").textContent) * 1000,
      area: apt.querySelector("전용면적").textContent,
      aptName: apt.querySelector("아파트").textContent,
      address:
        apt.querySelector("법정동").textContent +
        " " +
        apt.querySelector("지번").textContent,
    };
    // 1,555,000,000
    // if(curDongCode == dong.substr(5)){
    //   searchResult.push(apt);
    // }

    if (curDongCode == dong.substr(5)) {
      searchResult.push(aptObject);
    }
  });
}

var mapContainer = document.querySelector("#map"); // 지도를 표시할 div
mapOption = {
  center: new kakao.maps.LatLng(36.355116, 127.298372), // 지도의 중심좌표
  level: 5, // 지도의 확대 레벨s
};

let map = new kakao.maps.Map(mapContainer, mapOption);

///////////////////////// 조회 버튼 누르면 지도가 해당 동으로 이동 /////////////////////////>
document.querySelector("#aptBtn").addEventListener("click", function () {
  // 지도 중심좌표를 동으로 이동

  //시 , 구, 동 정보 추출
  let sido = document.querySelector("#sido > option:checked").innerText;
  let gugun = document.querySelector("#gugun > option:checked").innerText;
  let dong = document.querySelector("#dong > option:checked").innerText;

  let address = `${sido} ${gugun} ${dong}`;
  console.log("주소", address);
  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();
  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(address, function (result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
      console.log("해당 동의 위도, 경도 정보: ", result[0]);
      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

      // 지도 중심을 이동 시킵니다
      map.setCenter(coords);
    }
  });
});
