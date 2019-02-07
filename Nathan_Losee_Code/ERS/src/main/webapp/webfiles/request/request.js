if (!submitRequest)
	submitRequest = function() {
		if (document.getElementsByTagName("form")[0].checkValidity() === false) {
	        return;
	    }
		
		let request = {
				rrDescription: document.getElementById("rDescription").value,
				rrAmount: document.getElementById("rAmount").value,
			};
		
		fetch('http://' + location.host + '/ERS/request/', {
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