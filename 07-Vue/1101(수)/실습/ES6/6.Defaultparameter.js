console.log("Defaultparameter");

//ES6 이전
function print1(msg) {
  if (!msg) msg = "hello";
  console.log(msg);
}

print1("hellow ssafy");
print1();

//ES6 이후
function print2(msg = "안녕") {
  //디폴트 값 설정
  console.log(msg);
}
print2("hello ssafy2");
print2();

function getUserId(userId = "ssafy9") {
  return userId;
}
console.log(getUserId()); //default 파라미터
console.log(getUserId(undefined)); //default 파라미터
console.log(getUserId(null)); //null

function appendArr(val, array = []) {
  array.push(val);
  return array;
}
appendArr(10); //[10]
appendArr(20); //[20]

const array = appendArr(10);
console.log(appendArr(20, array)); //[10, 20]
console.log(array);
