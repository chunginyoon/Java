console.log("Promise");

let p = new Promise(function (resolve, reject) {
  // resolve, reject는 모두 함수이다
  setTimeout(function () {
    const number = Math.round(Math.random() * 10);
    console.log("생성된 숫자", number);

    if (number >= 5) resolve(number);
    else reject(number);
  }, 3000);
});

//resolve 실행되면 then, reject 실행되면 catch 실행
//p, then, catch 모두 반환 타입은 Promise이다
p.then(function (data) {
  console.log("then의 메소드 호출 인자:", data);
});

p.catch(function (data) {
  console.log("catch의 메소드 호출 인자:", data);
});
