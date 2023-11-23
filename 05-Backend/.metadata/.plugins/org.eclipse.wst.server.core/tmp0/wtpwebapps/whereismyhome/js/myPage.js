
const signIn = document.querySelector("#signIn");
const signUp = document.querySelector("#signUp");
const logOut = document.querySelector("#logOut");
const myPage = document.querySelector("#myPage");
const bulletinBoard = document.querySelector("#bulletin-board");
let isLogin = localStorage.getItem("isLogin");
if (isLogin != null) {
    logOut.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
    myPage.setAttribute("class", "me-3 py-2 text-dark text-decoration-none");
    bulletinBoard.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none"
    );
    signIn.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none d-none"
    );
    signUp.setAttribute(
      "class",
      "me-3 py-2 text-dark text-decoration-none d-none"
    );
}

logOut.addEventListener("click", function () {
    alert("로그아웃 성공!!");
    localStorage.removeItem("isLogin");
    window.location.reload();
  });

let memberInfo = localStorage.getItem("memberInfo");

if(memberInfo!= null){
    let info = document.querySelector(".info");
    memberInfoObject = JSON.parse(memberInfo);

    let str = ``
    
    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
                아이디 &nbsp : &nbsp ${memberInfoObject["id"]}
            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
                비밀번호 &nbsp : &nbsp *****
            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            이름 &nbsp : &nbsp ${memberInfoObject["name"]}

            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            주소 &nbsp : &nbsp ${memberInfoObject["address"]}

            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            전화번호 &nbsp : &nbsp ${memberInfoObject["전화번호"]}

            </div>
        </div>
    `

    

    str += `
    <div class="buttons">
        <button id="modify-btn" class="btn btn-primary btn-lg">수정</button>
        <button id="leave-btn" class="btn btn-primary btn-lg">탈퇴</button>
        
    </div>    
    `

    info.innerHTML = str;

}




const leaveBtn = document.querySelector("#leave-btn");


leaveBtn.addEventListener("click", function(){
    localStorage.removeItem("memberInfo");
    localStorage.removeItem("isLogin")
    alert("탈퇴 성공!");
    open("./index.html","_self")
})

const modifyBtn = document.querySelector("#modify-btn");

modifyBtn.addEventListener("click", function(){
    let info = document.querySelector(".info");
    info.innerHTML = '';

    let str = ``;

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
                아이디 &nbsp : &nbsp ${memberInfoObject["id"]}
            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            비밀번호 &nbsp : &nbsp 
            <input id="modified-password" type="text" style="width:80%;"/>
            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            이름 &nbsp : &nbsp ${memberInfoObject["name"]}

            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            주소 &nbsp :
            <input id="modified-address"type="text" style="width:80%;"/>

            </div>
        </div>
    `

    str += `
        <div class="list-group">
            <div class="list-group-item me-4">
            전화번호 &nbsp :
            <input id="modified-phoneNum" type="text" style="width:80%;"/>

            </div>
        </div>
    `
    str += `
    <div class="buttons">
        <button id="confirm-btn" class="btn btn-primary btn-lg" onclick="confirm()">확인</button>        
    </div>    
    `
    
    info.innerHTML = str;

})

function confirm(){
    let memberInfoObject = JSON.parse(memberInfo);
    const modifiedPassword = document.getElementById("modified-password");
    const modifiedAddress = document.getElementById("modified-address");
    const modifiedPhoneNum = document.getElementById("modified-phoneNum")
    let newMember = {
        name : memberInfoObject["name"],
        id : memberInfoObject["id"],
        pw : modifiedPassword.value,
        address : modifiedAddress.value,
        전화번호 : modifiedPhoneNum.value
    }

    localStorage.setItem("memberInfo", JSON.stringify(newMember));
    alert("회원정보 수정되었습니다");
    open("./index.html","_self");
}


