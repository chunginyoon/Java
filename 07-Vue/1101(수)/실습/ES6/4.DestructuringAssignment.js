console.log("구조 분해 할당");

//배열: 순서 있음
const areas = ["서울", "광주", "구미", "부울경", "대전"];

//ES6 이전
{
  const a1 = areas[0];
  const a2 = areas[1];
  const a3 = areas[2];
  const a4 = areas[3];
  const a5 = areas[4];

  console.log(a1, a2, a3, a4, a5);
}

//ES6
{
  const [a1, a2, a3, a4, a5] = areas;
  console.log(a1, a2, a3, a4, a5);
}

//객체 : 순서 없음, 변수 이름 명 따라감(key 값이 중요)
const user = {
  id: "ssafy",
  name: "윤정인",
  age: 20,
};

//ES6 이전
{
  const id = user.id;
  const name = user.name;
  const age = user.age;

  console.log(id, name, age);
}

//ES6 이후
//객체의 key와 사용하려는 변수명 같은 경우
{
  const { id, name, age } = user;
  console.log(id, name, age);
}
//객체의 key와 사용하려는 변수명 다른 경우
{
  const { id: user_id, name: user_name, age: user_age } = user;
  console.log(user_id, user_name, user_age);
}

//객체 return 함수
function getUser() {
  return {
    //객체를 새로 생성하는 함수
    id: "ssafy",
    name: "윤정인",
    age: 20,
  };
}

//ES6
let { id, age } = getUser();
console.log(id, age);
