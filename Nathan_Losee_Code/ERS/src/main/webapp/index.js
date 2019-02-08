"use strict"
let contentTarget, cssMainTarget, cssAltTarget;

let loadContent = function(htmlContent, isPopup = false) {
	if (isPopup) {
		if (document.getElementById("popup"))
			return;
		let popup = document.createElement("div");
		popup.setAttribute("id", "popup");
		popup.innerHTML = htmlContent;
		contentTarget.appendChild(popup);
	}
	else
		contentTarget.innerHTML = htmlContent;
	
	let loadTarget = document.getElementById("loadTarget");
	let path = "/ERS/components/" + loadTarget.innerHTML + "/" + loadTarget.innerHTML;
	
	let jsTarget = document.createElement("script");
	jsTarget.setAttribute("type", "text/javascript");
	jsTarget.setAttribute("src", path + ".js");
	contentTarget.appendChild(jsTarget);
	
	if (isPopup)
		cssAltTarget.setAttribute("href",
				path + ".css");
	else
		cssMainTarget.setAttribute("href",
				path + ".css");
	
	loadTarget.parentNode.removeChild(loadTarget);
}

let login, logout, closePopup,
	loadUsername, loadEmail, confirm, changeEmail,
	alter, loadSettings, changeSetting, loadReceipt, fetchReceipt, saveReceipt, submitReceipt,
	loadRequests, loadRequestsSingle, makeRequest, submitRequest, stampRequest,
	loadEmployees, loadEmployee, registerEmployee, submitRegistration;

window.onload = () => {
	contentTarget = document.getElementById("contentTarget");
	cssMainTarget = document.getElementById("cssMainTarget");
	cssAltTarget = document.getElementById("cssAltTarget");
	
	fetch('http://' + location.host + '/ERS/session/')
		.then(response => {
			if (response.ok) {
				return response.text();
			}
			return null;
		})
		.then(data => {
			if (data) {
				loadContent(data);
			}
		});
};
