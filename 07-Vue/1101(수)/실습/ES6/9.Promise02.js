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
p.then(function (data) {
  console.log("then의 메소드 호출 인자:", data);

  throw new Error("에러 발생");
  return "then1의 리턴값";
})
  .then(function (data) {
    console.log("then2의 메소드 호출 인자:", data);
  })
  .catch(function (data) {
    console.log("catch의 메소드 호출 인자:", data);
  });

fetch(url)
  .then(function (data) {
    return data.text();
  })
  .then(function (data) {
    console.log(data);
  });
