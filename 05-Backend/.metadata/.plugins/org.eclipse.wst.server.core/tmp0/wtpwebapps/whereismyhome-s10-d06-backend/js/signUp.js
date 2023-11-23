let root = "http://localhost:8080/whereismyhome-s10-d06-backend";

document.getElementsByClassName("submit-form")[0].addEventListener("submit",function(e){
	e.preventDefault();
	
	let name = document.getElementById("name").value;
	let id = document.getElementById("ID").value;
	let pw = document.getElementById("pw").value;
	let address = document.getElementById("address").value;
	let phone = document.getElementById("phone").value;
	
	axios.post(root+"/member?action=signup",{"name":name,"memberId":id,"password":pw,"address":address,"tel":phone})
	.then(function(resp){
		if(resp.data.signup) window.location.href=root+"/member?action=mvLogin";
		else alert(resp.data.log);
	})
	.catch(function(error){
		alert("error"+error);
	});
	
});
