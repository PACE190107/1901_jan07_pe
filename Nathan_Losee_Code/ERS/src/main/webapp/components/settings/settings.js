if (!loadSettings)
	loadSettings = function() {
		fetch('http://' + location.host + '/ERS/settings/')
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				return null;
			})
			.then(data => {
				if (data) {
					document.getElementById("idField").value = data.eID;
					document.getElementById("usernameField2").value = data.eUsername;
					document.getElementById("firstNameField").value = data.eFirstName;
					document.getElementById("lastNameField").value = data.eLastName;
					document.getElementById("emailField").value = data.eEmail;
				}
			});
	}

if (!changeSetting)
	changeSetting = function(credential) {
		if (document.getElementById(credential + "Input").checkValidity() === false) {
	        return;
	    }
		
		let change = {
				credential: credential,
				newValue: document.getElementById(credential + "Input").value,
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