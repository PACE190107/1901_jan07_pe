if (!fetchReceipt)
	fetchReceipt = function(requestID) {
		fetch('http://' + location.host + '/ERS/receiptPath?reqID=' + requestID.toString())
			.then(response => {
				if (response.ok) {
					return response.text();
				}
			})
			.then(data => {
				if (data) {
					document.getElementById("receipt").setAttribute("src", "data:image/png;base64," + data);
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
fetchReceipt(document.getElementById("receiptValue").innerHTML);