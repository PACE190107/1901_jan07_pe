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

if (!loadEmployees)
	loadEmployees = function() {
		fetch('http://localhost:8080/ERS/employees/')
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				return null;
			})
			.then(data => {
				if (data) {
					let employeeRecords = document.getElementById("employeeRecords");
					while (employeeRecords.firstChild)
						employeeRecords.removeChild(employeeRecords.firstChild);
					
					for (let i = 0; i < data.length; i++) {
						let record = document.createElement("tr");
						
						let id = document.createElement("td");
						id.innerHTML = data[i].eID;
						record.appendChild(id);
						
						let username = document.createElement("td");
						username.innerHTML = data[i].eUsername;
						record.appendChild(username);
						
						let firstName = document.createElement("td");
						firstName.innerHTML = data[i].eFirstName;
						record.appendChild(firstName);
						
						let lastName = document.createElement("td");
						lastName.innerHTML = data[i].eLastName;
						record.appendChild(lastName);
						
						let email = document.createElement("td");
						email.innerHTML = data[i].eEmail;
						record.appendChild(email);
						
						if (data[i].manager)
							record.setAttribute("class", "table-info");
						else
							record.addEventListener("click", function() {
								focus(data[i].eID);
							});
						
						employeeRecords.appendChild(record);
					}
				}
			});
	}
if (!loadEmployee)
	loadEmployee = function(eID) {
		let employeeJSON = {
			eID: eID	
		};
	
		fetch('http://localhost:8080/ERS/employee/', {
			method: "POST",
			body: JSON.stringify(employeeJSON),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				return null;
			})
			.then(data => {
				if (data) {
					let employeeRecords = document.getElementById("employeeRecords");
					while (employeeRecords.firstChild)
						employeeRecords.removeChild(employeeRecords.firstChild);
					
					let record = document.createElement("tr");
					
					let id = document.createElement("td");
					id.innerHTML = data.eID;
					record.appendChild(id);
					
					let username = document.createElement("td");
					username.innerHTML = data.eUsername;
					record.appendChild(username);
					
					let firstName = document.createElement("td");
					firstName.innerHTML = data.eFirstName;
					record.appendChild(firstName);
					
					let lastName = document.createElement("td");
					lastName.innerHTML = data.eLastName;
					record.appendChild(lastName);
					
					let email = document.createElement("td");
					email.innerHTML = data.eEmail;
					record.appendChild(email);
					
					if (data.manager)
						record.setAttribute("class", "table-info");
					else
						record.addEventListener("click", function() {
							loadEmployee(data.eID);
						});
					
					employeeRecords.appendChild(record);
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

if (!loadRequestsSingle)
	loadRequestsSingle = function(eID) {
		let employeeJSON = {
			eID: eID	
		};
	
		fetch('http://localhost:8080/ERS/requestsSingle/', {
			method: "POST",
			body: JSON.stringify(employeeJSON),
			headers: {
				"Content-Type": "application/json"
			}
		})
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
						
						let createdBy = document.createElement("td");
						createdBy.innerHTML = data[i].eID;
						record.appendChild(createdBy);
						
						let resolvedBy = document.createElement("td");
						if (data[i].mID)
							resolvedBy.innerHTML = data[i].mID;
						else
							resolvedBy.innerHTML = "Pending";
						record.appendChild(resolvedBy);
						
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
						} else {
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
						}
						record.appendChild(approved);
					}
				}
			});
	}

if (!registerEmployee)
	registerEmployee = function() {
		fetch('http://localhost:8080/ERS/registration/')
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

if (!stampRequest)
	stampRequest = function(rID, isApproved) {
		let stamp = {
				request: rID,
				isApproved: isApproved,
			};
		
		fetch('http://localhost:8080/ERS/request/', {
			method: "PUT",
			body: JSON.stringify(stamp),
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(response => {
				if (response.ok) {
					if (document.getElementById("focusID").innerHTML)
						loadRequestsSingle(document.getElementById("focusID").innerHTML);
					else
						loadRequests(true);
				}
			});
	}

if (!focus)
	focus = function(eID) {
		document.getElementById("unfocus").setAttribute("style", "display: inline");
		document.getElementById("spacer").setAttribute("style", "display: none");
		document.getElementById("focusID").innerHTML = eID;
		loadEmployee(eID);
		loadRequestsSingle(eID);
	}

if (!unfocus)
	unfocus = function() {
		document.getElementById("unfocus").setAttribute("style", "display: none");
		document.getElementById("spacer").setAttribute("style", "display: inline");
		document.getElementById("focusID").innerHTML = "";
		loadEmployees();
		loadRequests(true);
	}

document.getElementById("logout").addEventListener("click", logout);
document.getElementById("alter").addEventListener("click", alter);
document.getElementById("registration").addEventListener("click", registerEmployee);
document.getElementById("unfocus").addEventListener("click", unfocus);

loadUsername();
loadEmployees();
loadRequests(true);