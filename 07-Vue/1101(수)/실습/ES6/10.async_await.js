console.log("async_await");

let p = new Promise(function (resolve, reject) {
  // resolve, reject는 모두 함수이다
  setTimeout(function () {
    const number = 3;
    console.log("생성된 숫자", number);

    if (number >= 5) resolve(number);
    else reject(number);
  }, 3000);
});

//await 쓸려면 함수가 async여야 한다
async function test() {
  try {
    const result = await p;
    console.log(result);
  } catch (error) {
    console.log("catch 실행 인자값:", error);
  }
}

console.log(test());
async function request() {
  const rawData = await fetch();
  const data = await rawData.json();
}
request();
