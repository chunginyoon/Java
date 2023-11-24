// 1. 선언 및 할당
var x = 10;
console.log(x);

var x = 20;
console.log(x);

//let:block{} scope
let y = 10;
console.log(10);
{
  //이 블록 안에서만 유효한 변수
  let y = 20;
  console.log(y);
}
console.log(y);
// let y=30; //이미 선언된 변수이므로 재선언 불가

//const: 상수, 재선언 불가
const z = 10;
// z = 20; //재할당 불가

//객체{}, 배열[]의 경우, 객체 속성, 요소 변경은 가능
const ssafy = {
  area: "대전",
  class: 6,
};
console.log(ssafy);
ssafy.area = "부울경";
console.log(ssafy.area);

//객체 주소값 변경 X
// ssafy = {
//   area: "부울경",
//   class: 5,
// };
