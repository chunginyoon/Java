console.log("ArrowFunction");

/**
 * function name(param){
 *
 * }
 *
 * const name = () => {
 *
 * }
 *
 */

//매개변수 X
function func1() {
  return 100;
}

const func2 = () => {
  return 100;
};
//ArrowFunction의 중괄호가 생략된 경우 화살표의 뒤의 값이 return
const func3 = () => 100;
let result = func3();
console.log(result);

//매개변수 1개
function func4(x) {
  return x + 100;
}
const func5 = (x) => {
  return x + 100;
};
//const func6 = x => x + 100; //매개변수 하나이면 소괄호 생략 가능
//result = func6(10) //110 반환

//매개변수 2개
function func7(x, y) {
  return x + y;
}
result = func7(50, 80);
console.log(result);
const func8 = (x, y) => x + y;
result = func8(50, 80);
console.log(result);

//객체 생성하여 반환하는 메소드
function func9() {
  return {
    id: "ssafy",
  };
}
result = func9();
console.log(result);

//새로운 객체 생성해서 리턴
const func10 = () => ({
  id: "ssafy",
});
result = func10();
console.log(result);

//ArrowFunction에서 this가 바인딩 되지 않음
console.log(this); //this=window
console.log(window);

const user = {
  id: "ssafy",
  name: "연민호",
  age: 20,
  info: function () {
    console.log("객체의 메소드에서 this:", this);
  },
};
user.info();

const user2 = {
  id: "ssafy",
  name: "연민호",
  age: 20,
  info: () => {
    console.log("객체의 .arrow 메소드에서 this:", this);
  },
};
user2.info();
