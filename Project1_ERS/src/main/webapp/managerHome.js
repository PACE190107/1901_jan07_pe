window.onload = function(){
	document.getElementById("logoutBtn").addEventListener("click",function(){
		
		window.location = "http://localhost:8080/Project1_ERS/login.html";
		
	});
	updateUserInfo();
	updateReimbursements();
	document.getElementById("approveRequest").addEventListener("click", approveRequest);
	document.getElementById("denyRequest").addEventListener("click", denyRequest);
	document.getElementById("viewEmployees").addEventListener("click", function(){
		
		window.location = "http://localhost:8080/Project1_ERS/manageEmployees.html";
		
	});
}

function approveRequest(){
	
	var x = new XMLHttpRequest();
	var request = {
		status: "APPROVED",
		requestId: document.getElementById("requestId").value
	};
	
	var jsonRequest = JSON.stringify(request);
	x.onreadystatechange = () => {
		//comment the if condition and try to print all different readyStates
		if((x.readyState == 4) && (x.status ==200)){
			updateReimbursements();
		}
	};
x.open("post","http://localhost:8080/Project1_ERS/rest/reimbursement/approved");
x.setRequestHeader("Content-Type", "application/json");
x.send(jsonRequest);
}

function denyRequest(){
	
	var x = new XMLHttpRequest();
	var request = {
		requestId: document.getElementById("requestId").value
	};
	
	var jsonRequest = JSON.stringify(request);
	x.onreadystatechange = () => {
		//comment the if condition and try to print all different readyStates
		if((x.readyState == 4) && (x.status ==200)){
			updateReimbursements();
		}
	};
x.open("post","http://localhost:8080/Project1_ERS/rest/reimbursement/denied");
x.setRequestHeader("Content-Type", "application/json");
x.send(jsonRequest);
}

function updateUserInfo(){
	var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				let eId = document.getElementById("eId");
				let fName = document.getElementById("fName");
				let lName = document.getElementById("lName");
				let email = document.getElementById("email");
			
				let userObj = JSON.parse(x.responseText); 
				eId.innerHTML = "Id: " + userObj.employeeId;
				fName.innerHTML = "First Name: " + userObj.firstName;
				lName.innerHTML = "Last Name: " + userObj.lastName;
				email.innerHTML = "Email: " + userObj.email;
			
				console.log(x.responseText + " - " + x.readyState + " - " + x.status );
				console.log("user Object: " + userObj);
			}
		};
	x.open("post","http://localhost:8080/Project1_ERS/rest/employee/-2");
	x.send();
}

function updateReimbursements(){
	
	var x = new XMLHttpRequest();
	
	x.onreadystatechange = () => {
		//comment the if condition and try to print all different readyStates
		if((x.readyState == 4) && (x.status ==200)){
			let rList = document.getElementById("reimbursements");
			
			while (rList.hasChildNodes()) {
			    rList.removeChild(rList.lastChild);
			}
			let userObj = JSON.parse(x.responseText); 
			console.log(userObj[0].requestId);
			var i;
			for(i = 0; i < userObj.length; i++){
				let node = document.createElement("LI");                 // Create a <li> node
				let textnode = document.createTextNode(userObj[i].requestId + "  --  " + userObj[i].subject + "  --$ " + userObj[i].amount + " ---- " + userObj[i].status + " ----- " + userObj[i].employeeId + "------------------" + userObj[i].approverId);         // Create a text node
				node.appendChild(textnode);                              // Append the text to <li>
				rList.appendChild(node); 
			}
				
			//console.log(x.responseText + " - " + x.readyState + " - " + x.status );
			//console.log("user Object: " + userObj);
		}
	};
x.open("get","http://localhost:8080/Project1_ERS/rest/reimbursement");
x.send();
	
	
}