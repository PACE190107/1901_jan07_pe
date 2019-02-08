if (!login)
	login = function() {
		if (document.getElementsByTagName("form")[0].checkValidity() === false) {
	        return;
	    }
		
		let employeeJSON = {
			user: document.getElementById("user").value,
			pass: document.getElementById("pass").value
		};
		
		let loginRequest = new XMLHttpRequest();
		loginRequest.onreadystatechange = () => {
			if (loginRequest.readyState == 4 && loginRequest.status == 200 &&
					loginRequest.responseText != "") {
				loadContent(loginRequest.responseText);
			}
		};
		loginRequest.open('POST', 'http://' + location.host + '/ERS/login/');
		loginRequest.setRequestHeader("Content-Type", "application/json");
		loginRequest.send(JSON.stringify(employeeJSON));
	}

document.getElementById("login").addEventListener("click", login);