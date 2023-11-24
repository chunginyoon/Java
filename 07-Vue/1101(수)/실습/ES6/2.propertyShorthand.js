//PropertyShorthand(단축 속성명)

//ES6 이전

const id = "ssafy";
const name = "윤정인";
const age = 20;

const user = {
  id: id,
  name: name,
  age: age,
};

//ES6
const user2 = {
  id,
  name,
  age,
};
