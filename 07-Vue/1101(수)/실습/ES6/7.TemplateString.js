console.log("TemplateString");

const id = "ssafy";
const name = "윤정인";
const age = 20;

//ES6 이전
console.log("(" + id + ")" + name + "님의 나이는" + age + "살 입니다");

//ES6
console.log(`(${id})${name}님의 나이는 ${age}살 입니다.`);
