if (!submitRequest)
	submitRequest = function() {
		let request = {
				rrDescription: document.getElementById("rDescription").value,
				rrAmount: document.getElementById("rAmount").value,
			};
		
		fetch('http://localhost:8080/ERS/request/', {
			method: "POST",
			body: JSON.stringify(request),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					loadRequests(false);
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
document.getElementById("request").addEventListener("click", submitRequest);