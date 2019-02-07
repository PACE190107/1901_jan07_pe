if (!confirm)
	confirm = function() {
		if (document.getElementsByTagName("form")[0].checkValidity() === false) {
	        return;
	    }
		
		let passwordJSON = {
			credential: "confirmation",
			newValue: document.getElementById("pass").value
		};
		if (passwordJSON.pass == "")
			return null;
		
		let confirmRequest = new XMLHttpRequest();
		confirmRequest.onreadystatechange = () => {
			if (confirmRequest.readyState == 4 && confirmRequest.status == 200 &&
					confirmRequest.responseText != "") {
				loadContent(confirmRequest.responseText);
			}
		};
		confirmRequest.open('PUT', 'http://' + location.host + '/ERS/settings/');
		confirmRequest.setRequestHeader("Content-Type", "application/json");
		confirmRequest.send(JSON.stringify(passwordJSON));
	}

if (!logout)
	logout = function () {
		let logoutRequest = new XMLHttpRequest();
		logoutRequest.onreadystatechange = () => {
			if (logoutRequest.readyState == 4 && logoutRequest.status == 200 &&
					logoutRequest.responseText != "") {
				loadContent(logoutRequest.responseText);
			}
		};
		logoutRequest.open('GET', 'http://' + location.host + '/ERS/logout/');
		logoutRequest.send();
	}

document.getElementById("confirm").addEventListener("click", confirm);
document.getElementById("cancel").addEventListener("click", logout);