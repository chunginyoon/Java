let root = "http://localhost:8080/whereismyhome-s10-d06-backend";

document.getElementsByClassName("submit-form")[0].addEventListener("submit",function(e){
	e.preventDefault();
});


document.getElementById("updateBtn").addEventListener("click", function() {
  // 버튼이 클릭되었을 때 실행할 코드
	let name = document.getElementById("name").value;
	let id = document.getElementById("ID").value;
	let pw = document.getElementById("pw").value;
	let address = document.getElementById("address").value;
	let phone = document.getElementById("phone").value;
	
	axios.post(root+"/member?action=updateMypage",{"name":name,"memberId":id,"password":pw,"address":address,"tel":phone})
	.then(function(resp){
		if(resp.data.update) {
			alert("회원정보가 수정되었습니다.");
			window.location.href=root+"/member?action=myPage";
		}
		else alert("실패");
	})
	.catch(function(error){
		alert("error"+error);
	});
	
});

document.getElementById("deleteBtn").addEventListener("click", function() {
    // 버튼이 클릭되었을 때 실행할 코드
	if(!confirm("정말로 탈퇴하시겠습니까?")) return;
	else axios.get(root+"/member?action=deleteUser").then(function(resp){
		window.location.href=root+"/";
	});
});




