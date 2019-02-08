window.onload = function(){
	document.getElementById("getAllUsersBtn").addEventListener("click",getAllUsers);
	document.getElementById("getAllPendingBtn").addEventListener("click",getAllPending);
	document.getElementById("getAllApprovedBtn").addEventListener("click",getAllApproved);
	document.getElementById("getAllRebEmpBtn").addEventListener("click",getAllRebEmp);
	document.getElementById("approveRequest").addEventListener("click",approval);
	document.getElementById("denyRequest").addEventListener("click",denial);
	document.getElementById("logout").addEventListener("click", logout);
}

/*
 * the window.history code snippet is from
 * https://stackoverflow.com/questions/5806860/how-can-i-prevent-user-to-access-the-previous-jsp-page-using-browsers-back-butt
 */
window.history.forward();

function noBack() {
  window.history.forward();
}

function getAllUsers(){
	for(var i = 1;i<document.getElementById("employeeTable").rows.length;){
        document.getElementById("employeeTable").deleteRow(i);
    }
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			var i = 0;
			var data = JSON.parse(this.responseText);
			while (i <= (data.length -1)){
				let employeeRow = document.createElement("tr");
				let employeeId = document.createElement("td");
				let employeeUsername = document.createElement("td");
				let employeePassword = document.createElement("td");
				let employeeFirstName = document.createElement("td");
				let employeeLastName = document.createElement("td");
				let employeeEmail = document.createElement("td");
				employeeId.innerHTML = data[i].e_id;
				employeeUsername.innerHTML = data[i].e_username;
				employeePassword.innerHTML = data[i].e_password;
				employeeFirstName.innerHTML = data[i].e_firstName;
				employeeLastName.innerHTML = data[i].e_lastName;
				employeeEmail.innerHTML = data[i].e_email;
				employeeRow.appendChild(employeeId);
				employeeRow.appendChild(employeeUsername);
				employeeRow.appendChild(employeePassword);
				employeeRow.appendChild(employeeFirstName);
				employeeRow.appendChild(employeeLastName);
				employeeRow.appendChild(employeeEmail);
				document.getElementById("employeeTable").append(employeeRow);
				i++;
			}
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/employee/All");
	xhr.send();
}
function getAllPending(){
	for(var i = 1;i<document.getElementById("pendingRebTable").rows.length;){
        document.getElementById("pendingRebTable").deleteRow(i);
    }
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			var i = 0;
			var data = JSON.parse(this.responseText);
			while (i <= (data.length -1)){
				let rebRow = document.createElement("tr");
				let employeeId = document.createElement("td");
				let rebId = document.createElement("td");
				let rebAmount = document.createElement("td");
				let rebDesc= document.createElement("td");
				let rebStatus = document.createElement("td");
				employeeId.innerHTML = data[i].e_id;
				rebId.innerHTML = data[i].r_id;
				rebAmount.innerHTML = data[i].r_amount;
				rebDesc.innerHTML = data[i].description;
				rebStatus.innerHTML = data[i].status;
				rebRow.appendChild(employeeId);
				rebRow.appendChild(rebId);
				rebRow.appendChild(rebAmount);
				rebRow.appendChild(rebDesc);
				rebRow.appendChild(rebStatus);
				document.getElementById("pendingRebTable").append(rebRow);
				i++;
			}
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/AllPending");
	xhr.send();
}
function getAllApproved(){
	for(var i = 1;i<document.getElementById("approvedRebTable").rows.length;){
        document.getElementById("approvedRebTable").deleteRow(i);
    }
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			var i = 0;
			var data = JSON.parse(this.responseText);
			while (i <= (data.length -1)){
				let rebRow = document.createElement("tr");
				let employeeId = document.createElement("td");
				let rebId = document.createElement("td");
				let rebAmount = document.createElement("td");
				let rebDesc= document.createElement("td");
				let rebStatus = document.createElement("td");
				let rebResolver = document.createElement("td");
				employeeId.innerHTML = data[i].e_id;
				rebId.innerHTML = data[i].r_id;
				rebAmount.innerHTML = data[i].r_amount;
				rebDesc.innerHTML = data[i].description;
				rebStatus.innerHTML = data[i].status;
				rebResolver.innerHTML = data[i].resolverId;
				rebRow.appendChild(employeeId);
				rebRow.appendChild(rebId);
				rebRow.appendChild(rebAmount);
				rebRow.appendChild(rebDesc);
				rebRow.appendChild(rebStatus);
				rebRow.appendChild(rebResolver);
				document.getElementById("approvedRebTable").append(rebRow);
				i++;
			}
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/AllApproved");
	xhr.send();
}
function getAllRebEmp(){
	for(var i = 1;i<document.getElementById("allRebEmpTable").rows.length;){
        document.getElementById("allRebEmpTable").deleteRow(i);
    }
	const single = {e_id: document.getElementById("requestedId").value};
	const jsonSingle = JSON.stringify(single);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			var i = 0;
			var data = JSON.parse(this.responseText);
			if (xhr.responseText != 'null'){
				while (i <= (data.length -1)){
					let rebRow = document.createElement("tr");
					let employeeId = document.createElement("td");
					let rebId = document.createElement("td");
					let rebAmount = document.createElement("td");
					let rebDesc= document.createElement("td");
					let rebStatus = document.createElement("td");
					let rebResolver = document.createElement("td");
					employeeId.innerHTML = data[i].e_id;
					rebId.innerHTML = data[i].r_id;
					rebAmount.innerHTML = data[i].r_amount;
					rebDesc.innerHTML = data[i].description;
					rebStatus.innerHTML = data[i].status;
					rebResolver.innerHTML = data[i].resolverId;
					rebRow.appendChild(employeeId);
					rebRow.appendChild(rebId);
					rebRow.appendChild(rebAmount);
					rebRow.appendChild(rebDesc);
					rebRow.appendChild(rebStatus);
					rebRow.appendChild(rebResolver);
					document.getElementById("allRebEmpTable").append(rebRow);
					i++;
				}
			}
			
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Request");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonSingle);
}
function approval(){
	const rebID = {
			r_id: document.getElementById("rebId").value
	};
	const jsonRebID = JSON.stringify(rebID);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("decision").innerHTML = "Request Approved!";
			}	
				
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Approval");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonRebID);
}
function denial(){
	const rebID = {
			r_id: document.getElementById("rebId").value
	};
	const jsonRebID = JSON.stringify(rebID);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("decision").innerHTML = "Request Denied!";
			}	
				
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Deny");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonRebID);
}
function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.getResponseHeader("location") !== null){
				window.location = xhr.getResponseHeader("location");
			}
				
		}
	};
	xhr.open("POST", "http://localhost:8080/ExpenseReimbursement/rest/employee/logout");
	xhr.send();
}

function toggleAllUsers() {
	  var x = document.getElementById("allUsersDIV");
	  var btn = document.getElementById("getAllUsersBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function toggleAllPending() {
	  var x = document.getElementById("allPendingDIV");
	  var btn = document.getElementById("getAllPendingBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function toggleAllApproved() {
	  var x = document.getElementById("allApprovedDIV");
	  var btn = document.getElementById("getAllApprovedBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}

function toggleAllRebEmpForm() {
	toggleAllRebEmp();
	  var x = document.getElementById("allRebEmpForm");
	  var btn = document.getElementById("getSingle");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function toggleAllRebEmp() {
	  var x = document.getElementById("allRebEmp");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function toggleDecision() {
	  var x = document.getElementById("decisionForm");
	  var btn = document.getElementById("decsBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}