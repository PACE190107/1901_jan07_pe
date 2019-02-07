window.onload = function(){
	document.getElementById("getSingleUserBtn").addEventListener("click", getSingleUser);
	document.getElementById("changeUsernameBtn").addEventListener("click", changeUsername);
	document.getElementById("changePasswordBtn").addEventListener("click", changePassword);
	document.getElementById("changeEmailBtn").addEventListener("click", changeEmail);
	document.getElementById("viewResolvedBtn").addEventListener("click", viewResolved);
	document.getElementById("viewPendingBtn").addEventListener("click", viewPending);
	document.getElementById("submitReb").addEventListener("click", submitRequest);
	document.getElementById("logout").addEventListener("click", logout);
}

function getSingleUser(){
	for(var i = 1;i<document.getElementById("employeeDetails").rows.length;){
        document.getElementById("employeeDetails").deleteRow(i);
    }
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			var data = JSON.parse(this.responseText);
			console.log(xhr.responseText);
			console.log(data.e_id);
				let employeeRow = document.createElement("tr");
				let employeeId = document.createElement("td");
				let employeeUsername = document.createElement("td");
				let employeePassword = document.createElement("td");
				let employeeFirstName = document.createElement("td");
				let employeeLastName = document.createElement("td");
				let employeeEmail = document.createElement("td");
				employeeId.innerHTML = data.e_id;
				employeeUsername.innerHTML = data.e_username;
				employeePassword.innerHTML = data.e_password;
				employeeFirstName.innerHTML = data.e_firstName;
				employeeLastName.innerHTML = data.e_lastName;
				employeeEmail.innerHTML = data.e_email;
				employeeRow.appendChild(employeeId);
				employeeRow.appendChild(employeeUsername);
				employeeRow.appendChild(employeePassword);
				employeeRow.appendChild(employeeFirstName);
				employeeRow.appendChild(employeeLastName);
				employeeRow.appendChild(employeeEmail);
				document.getElementById("employeeDetails").append(employeeRow);
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/employee/Single");
	xhr.send();
}
function changeUsername(){
	const newUsername = {
			e_username: document.getElementById("newUsername").value,
	};
	const jsonNewUsername = JSON.stringify(newUsername);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("usernameStatus").innerHTML = "Username Changed!";
			}		
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/employee/Username");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonNewUsername);
}
function changePassword(){
	const newPassword = {
			e_password: document.getElementById("newPassword").value,
	};
	const jsonNewPassword = JSON.stringify(newPassword);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("passwordStatus").innerHTML = "Password Changed!";
			}		
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/employee/Password");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonNewPassword);
}
function changeEmail(){
	const newEmail = {
			e_email: document.getElementById("newEmail").value,
	};
	const jsonNewEmail = JSON.stringify(newEmail);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("emailStatus").innerHTML = "Email Changed!";
			}		
		}
	};
	xhr.open("PUT", "http://localhost:8080/ExpenseReimbursement/rest/employee/Email");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonNewEmail);
}

function viewResolved(){
	for(var i = 1;i<document.getElementById("resolvedRebTable").rows.length;){
        document.getElementById("resolvedRebTable").deleteRow(i);
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
				document.getElementById("resolvedRebTable").append(rebRow);
				i++;
			}
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Approved");
	xhr.send();
}
function viewPending(){
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
				document.getElementById("pendingRebTable").append(rebRow);
				i++;
			}
		}
	};
	xhr.open("GET", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Pending");
	xhr.send();
}
function submitRequest(){
	const rebDetails = {
			r_amount: document.getElementById("rebAmount").value,
			description: document.getElementById("rebDesc").value
	};
	const jsonRebDetails = JSON.stringify(rebDetails);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange  = function(){
		if ((xhr.status == 200) && (xhr.readyState == 4)){
			if (xhr.responseText == 'true'){
				document.getElementById("rebStatus").innerHTML = "Request Submitted";
			}	
				
		}
	};
	xhr.open("POST", "http://localhost:8080/ExpenseReimbursement/rest/reimbursement");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonRebDetails);
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

var header = document.getElementById("btn-group");
var btns = header.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function() {
  var current = document.getElementsByClassName("active");
  current[0].className = current[0].className.replace(" active", "");
  this.className += " active";
  });
}


function toggleSingleUser(){
	  var x = document.getElementById("singleUserDiv");
	  var btn = document.getElementById("getSingleUserBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}

function toggleResolvedReb() {
	  var x = document.getElementById("resolvedRebDIV");
	  var btn = document.getElementById("viewResolvedBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function togglePendingReb() {
	  var x = document.getElementById("pendingRebDIV");
	  var btn = document.getElementById("viewPendingBtn");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
function toggleSubmitForm() {
	  var x = document.getElementById("submitForm");
	  var btn = document.getElementById("toggleSubmitNew");
	  btn.classList.toggle("mystyle");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}

