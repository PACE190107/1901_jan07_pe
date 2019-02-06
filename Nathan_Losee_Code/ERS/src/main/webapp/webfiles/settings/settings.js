if (!loadSettings)
	loadSettings = function() {
		fetch('http://localhost:8080/ERS/settings/')
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				return null;
			})
			.then(data => {
				if (data) {
					document.getElementById("idField").innerHTML = data.eID;
					document.getElementById("usernameField2").innerHTML = data.eUsername;
					document.getElementById("firstNameField").innerHTML = data.eFirstName;
					document.getElementById("lastNameField").innerHTML = data.eLastName;
					document.getElementById("emailField").innerHTML = data.eEmail;
				}
			});
	}

if (!changeSetting)
	changeSetting = function(credential) {
		let change = {
				credential: credential,
				newValue: document.getElementById(credential + "Input").value,
			};
		
		fetch('http://localhost:8080/ERS/settings/', {
			method: "PUT",
			body: JSON.stringify(change),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					loadSettings();
					loadUsername();
				}
			});
	}

if (!closePopup)
	closePopup = function() {
		let popup = document.getElementById("popup");
		popup.parentNode.removeChild(popup);
		cssAltTarget.setAttribute("href", "");
	};

document.getElementById("usernameSubmit").addEventListener("click", function() { changeSetting("username"); });
document.getElementById("passwordSubmit").addEventListener("click", function() { changeSetting("password"); });
document.getElementById("firstNameSubmit").addEventListener("click", function() { changeSetting("firstName"); });
document.getElementById("lastNameSubmit").addEventListener("click", function() { changeSetting("lastName"); });
document.getElementById("emailSubmit").addEventListener("click", function() { changeSetting("email"); });
document.getElementById("cancel").addEventListener("click", closePopup);

loadSettings();