console.log("SpreadSyntax");

const user1 = {
  id: "ssafy",
};
const user2 = {
  id: "ssafy2",
};

const arr = [user1, user2];
console.log(arr);

//배열 복사
const copyArr = [...arr];
console.log(copyArr);
console.log(arr == copyArr);

//같은 값을 갖는지 확인하고싶은 경우
console.log(JSON.stringify(arr));
console.log(JSON.stringify(copyArr));
console.log(JSON.stringify(arr) == JSON.stringify(copyArr));

//0번째 요소는 같은 객체
console.log(arr[0] == copyArr[0]);

const team1 = ["서울", "광주"];
const team2 = ["대전", "부울경", "구미"];

const team3 = [...team1];
console.log(team3);

//기존 배열에 새로운 요소 추가한 배열
const team4 = ["대전", ...team1, "구미"];
console.log(team4);

//배열 이어붙이기
const team = [...team1, ...team2];
console.log(team);

//객체 복사
const copyUser = { ...user1 };
/*
{...user1}
{
    id:"ssafy1",
}
*/

//기존 객체에 속성 추가한 객체 생성
const copyUser2 = {
  ...user1,
  name: "윤정인",
};

//객체 합치기
const u1 = { id1: "ssafy1" };
const u2 = { id2: "ssafy2" };
const u = { ...u1, ...u2 };
console.log(u);

//같은 key 값을 가진 속성이 있는 경우(뒤 쪽의 value 값으로 할당 됨)
const user = { ...user1, ...user2 };
console.log(user);

//
const num = [1, 3, 5, 7];

function plus(x, y, z) {
  return x + y + z;
}

const result = plus(...num);
//plus(1,3,5,7)와 같음
console.log(result);
