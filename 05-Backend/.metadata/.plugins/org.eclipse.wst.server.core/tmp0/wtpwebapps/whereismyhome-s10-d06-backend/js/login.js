

const loginBtn = document.querySelector("#login-btn");

let root = "http://localhost:8080/whereismyhome-s10-d06-backend";

document.getElementById("inputBox").addEventListener("submit", function (e) {
  e.preventDefault();
  let id = document.getElementById("uid").value;
  let pw = document.getElementById("upw").value;
  console.log("전송데이터 : " + id + ", " + pw);
  axios
    .post(root + "/member?action=login", { memberId: id, password: pw })
    .then(function (resp) {
      console.log(resp);
      if (resp.data == null) alert("존재하지 않는 회원입니다.");
      else window.location.href = root + "/";
    })
    .catch(function (error) {
      alert("error" + error);
    });
});
