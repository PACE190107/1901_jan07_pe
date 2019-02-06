if (!login)
	login = function() {
		let employeeJSON = {
			user: document.getElementById("user").value,
			pass: document.getElementById("pass").value
		};
		if (employeeJSON.user == "" || employeeJSON.pass == "")
			return null;
		
		let loginRequest = new XMLHttpRequest();
		loginRequest.onreadystatechange = () => {
			if (loginRequest.readyState == 4 && loginRequest.status == 200 &&
					loginRequest.responseText != "") {
				loadContent(loginRequest.responseText);
			}
		};
		loginRequest.open('POST', 'http://localhost:8080/ERS/login/');
		loginRequest.setRequestHeader("Content-Type", "application/json");
		loginRequest.send(JSON.stringify(employeeJSON));
	}

document.getElementById("login").addEventListener("click", login);