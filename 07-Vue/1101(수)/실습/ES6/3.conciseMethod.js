console.log("3.conciseMethod.js");

const id = "ssafy";
const name = "윤정인";
const age = 20;

//ES 6 이전
const user = {
  id,
  name,
  age,
  info: function () {
    console.log("사용자 정보: " + this.id + " " + this.name + " " + this.age);
  },
};
user.info();

//ES 6
const user2 = {
  id,
  name,
  age,
  info() {
    console.log("사용자 정보: " + this.id + " " + this.name + " " + this.age);
  },
};
user2.info();
