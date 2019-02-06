if (!submitRegistration)
	submitRegistration = function() {
		let registration = {
				eUsername: document.getElementById("rUser").value,
				eFirstName: document.getElementById("rFirst").value,
				eLastName: document.getElementById("rLast").value,
				eEmail: document.getElementById("rEmail").value
			};
		
		fetch('http://localhost:8080/ERS/register/', {
			method: "POST",
			body: JSON.stringify(registration),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					loadEmployees();
					closePopup();
				}
			});
	}

if (!closePopup)
	closePopup = function() {
		let popup = document.getElementById("popup");
		popup.parentNode.removeChild(popup);
		cssAltTarget.setAttribute("href", "");
	};

document.getElementById("cancel").addEventListener("click", closePopup);
document.getElementById("register").addEventListener("click", submitRegistration);