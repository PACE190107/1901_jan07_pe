if (!loadUsername)
	loadUsername = function() {
		fetch('http://localhost:8080/ERS/username/')
		.then(response => {
			if (response.ok) {
				return response.text();
			}
			return null;
		})
		.then(data => {
			if (data) {
				document.getElementById("usernameField").innerHTML = data;
			}
		});
	}

if (!alter)
	alter = function() {
		fetch('http://localhost:8080/ERS/alter/')
			.then(response => {
				if (response.ok) {
					return response.text();
				}
				return null;
			})
			.then(data => {
				if (data) {
					loadContent(data, true);
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
		logoutRequest.open('GET', 'http://localhost:8080/ERS/logout/');
		logoutRequest.send();
	}

if (!makeRequest)
	makeRequest = function() {
		fetch('http://localhost:8080/ERS/request/')
			.then(response => {
				if (response.ok) {
					return response.text();
				}
				return null;
			})
			.then(data => {
				if (data) {
					loadContent(data, true);
				}
			});
	}

if (!loadRequests)
	loadRequests = function(isManager) {
		fetch('http://localhost:8080/ERS/requests/')
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				return null;
			})
			.then(data => {
				if (data) {
					let requestRecords_Pending = document.getElementById("requestRecords_Pending");
					let requestRecords_Approved = document.getElementById("requestRecords_Approved");
					let requestRecords_Denied = document.getElementById("requestRecords_Denied");
					while (requestRecords_Pending.firstChild)
						requestRecords_Pending.removeChild(requestRecords_Pending.firstChild);
					while (requestRecords_Approved.firstChild)
						requestRecords_Approved.removeChild(requestRecords_Approved.firstChild);
					while (requestRecords_Denied.firstChild)
						requestRecords_Denied.removeChild(requestRecords_Denied.firstChild);
					
					for (let i = 0; i < data.length; i++) {
						let record = document.createElement("tr");
						
						let id = document.createElement("td");
						id.innerHTML = data[i].rrID;
						record.appendChild(id);
						
						if (isManager) {
							let createdBy = document.createElement("td");
							createdBy.innerHTML = data[i].eID;
							record.appendChild(createdBy);
							
							let resolvedBy = document.createElement("td");
							if (data[i].mID)
								resolvedBy.innerHTML = data[i].mID;
							else
								resolvedBy.innerHTML = "Pending";
							record.appendChild(resolvedBy);
						}
						
						let description = document.createElement("td");
						description.innerHTML = data[i].rrDescription;
						record.appendChild(description);
						
						let amount = document.createElement("td");
						amount.innerHTML = "$&nbsp;" + data[i].rrAmount.toString();
						record.appendChild(amount);
	
						let approved = document.createElement("td");
						if (data[i].mID) {
							if (data[i].approved) {
								approved.innerHTML = "Approved";
								record.setAttribute("class", "table-success");
								requestRecords_Approved.appendChild(record);
							}
							else {
								approved.innerHTML = "Denied";
								record.setAttribute("class", "table-danger");
								requestRecords_Denied.appendChild(record);
							}
						} else if (isManager) {
							let approve = document.createElement("button");
							approve.setAttribute("type", "button");
							approve.setAttribute("class", "btn-success");
							approve.innerHTML = "X";
							approve.addEventListener("click", () => { stampRequest(data[i].rrID, true); });
							approved.appendChild(approve);
							
							let deny = document.createElement("button");
							deny.setAttribute("type", "button");
							deny.setAttribute("class", "btn-danger");
							deny.innerHTML = "X";
							deny.addEventListener("click", () => { stampRequest(data[i].rrID, false); });
							approved.appendChild(deny);

							record.setAttribute("class", "table-warning");
							requestRecords_Pending.appendChild(record);
						} else {
							approved.innerHTML = "Pending";
							record.setAttribute("class", "table-warning");
							requestRecords_Pending.appendChild(record);
						}
						record.appendChild(approved);
					}
				}
			});
	}

document.getElementById("logout").addEventListener("click", logout);
document.getElementById("alter").addEventListener("click", alter);
document.getElementById("makeRequest").addEventListener("click", makeRequest);

loadUsername();
loadRequests(false);