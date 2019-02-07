if (!loadEmail)
	loadEmail = function() {
		fetch('http://' + location.host + '/ERS/email/')
			.then(response => {
				if (response.ok) {
					return response.text();
				}
				return null;
			})
			.then(data => {
				if (data) {
					document.getElementById("emailField").value = data;
				}
			});
	}

if (!changeEmail)
	changeEmail = function() {
		if (document.getElementsByTagName("form")[0].checkValidity() === false) {
	        return;
	    }
		
		let change = {
				credential: "email",
				newValue: document.getElementById("emailInput").value,
			};
		
		fetch('http://' + location.host + '/ERS/settings/', {
			method: "PUT",
			body: JSON.stringify(change),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					loadEmail();
				}
			});
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

document.getElementById("cancel").addEventListener("click", logout);
document.getElementById("submit").addEventListener("click", changeEmail);

loadEmail();