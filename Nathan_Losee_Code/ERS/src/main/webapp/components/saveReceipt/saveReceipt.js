if (!submitReceipt)
	submitReceipt = function(requestID) {
		if (document.getElementsByTagName("form")[0].checkValidity() === false) {
	        return;
	    }
		
		let formData = new FormData();
		let file = document.getElementById('receipt').files[0];
		formData.append('photos[]', file, requestID.toString() + ".png");
		
		fetch('http://' + location.host + '/ERS/receipt/', {
			method: "POST",
			body: formData
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
document.getElementById("submit").addEventListener("click",
		() => { submitReceipt(document.getElementById("receiptValue").innerHTML); });