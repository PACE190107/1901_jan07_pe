window.onload = function(){
	document.getElementById("loginBtn").addEventListener("click",login);
}

function login(){
	const loginCred = {
			e_username: document.getElementById("username").value,
			e_password: document.getElementById("password").value
	};
	const jsonLoginCred = JSON.stringify(loginCred);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.getResponseHeader("location") !== null){
				window.location = xhr.getResponseHeader("location");
			}else{
				console.log("Invalid Login!");
				document.getElementById("loginAttempt").innerHTML = "Invalid Login!";
				document.getElementById("username").value = "";
				document.getElementById("password").value = "";
			}
				
		}
	};
	
	xhr.open("POST", "http://localhost:8080/ExpenseReimbursement/rest/employee");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonLoginCred);
}
