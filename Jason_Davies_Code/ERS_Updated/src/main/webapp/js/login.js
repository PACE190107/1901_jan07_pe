window.onload = () => {
	let btn = document.getElementById("loginBtn");
	let username = document.getElementById("username");
	let password = document.getElementById("password");
	let message = document.getElementById("message");
	btn.addEventListener("click",()=>{
		var employee = {
			username: document.getElementById("username").value,
			password: document.getElementById("password").value
		}
		var x = new XMLHttpRequest();
		x.onreadystatechange = function() {
			// Invalid Credentials
//			if ((x.readyState == 4) && (x.status == 400)) {
//				username.value = "";
//				password.value = "";
//				message.textContent = "Invalid Credentials";
//				message.style.color = "red";
//			}
		};
		x.open("POST", "http://localhost:8080/ERS_Updated/Request"); 
		x.setRequestHeader("Content-Type", "application/json");
		x.send(JSON.stringify(employee));
	});
}