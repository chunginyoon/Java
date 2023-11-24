console.log("fetch");

//promise, then, catch
const request = () => {
  const url = "https://jsonplaceholder.typicode.com/todos/1";
  const config = {
    method: "get",
  };
  //   fetch(url, config)
  //     .then((res) => {
  //       console.log("첫 번째 응답: ", res);
  //       return res.json();
  //     })
  //     .then((data) => {
  //       console.log("res.json()의 결과:", data);
  //     });
  return fetch(url, config)
    .then((res) => res.json())
    .then((data) => {
      console.log("res.json()의 결과:", data);
    });
};

request().then(() => {
  console.log("request 응답 수신 완료");
});

//async await
//응답하는데 시간이 걸리므로 나중에 출력된다
const request2 = async () => {
  const url = "https://jsonplaceholder.typicode.com/todos/1";
  const response = await fetch(url);
  console.log("첫 번째 await 응답:", response);
  const data = await response.json();
  console.log("응답 데이터:", data);
};

//싱크 맞추기
// request2().then(() => {
//   console.log("request2에 대한 응답을 받았습니다");
// });
