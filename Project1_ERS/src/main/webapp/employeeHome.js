window.onload = function(){
	document.getElementById("eHomeLogout").addEventListener("click",function(){
		
		window.location = "http://localhost:8080/Project1_ERS/login.html";
		
	});
	updateUserInfo();
	updateReimbursements();
	document.getElementById("firstNameButton").addEventListener("click", updateFirstName);
	document.getElementById("lastNameButton").addEventListener("click", updateLastName);
	document.getElementById("emailButton").addEventListener("click", updateEmail);
	document.getElementById("createRequest").addEventListener("click", createRequest);
}

function createRequest(){
	var x = new XMLHttpRequest();
		var request = {
			subject: document.getElementById("rSubject").value,
			amount: document.getElementById("rAmount").value,
			description: document.getElementById("rDescription").value
		};
		
		var jsonRequest = JSON.stringify(request);
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				updateReimbursements();
			}
		};
	x.open("post","http://localhost:8080/Project1_ERS/rest/reimbursement");
	x.setRequestHeader("Content-Type", "application/json");
	x.send(jsonRequest);
}

function updateFirstName(){
	var x = new XMLHttpRequest();
		var firstName = {
			firstName: document.getElementById("updateFirstName").value
		};
		
		var jsonFirstName = JSON.stringify(firstName);
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				updateUserInfo();
			}
		};
	x.open("post","http://localhost:8080/Project1_ERS/rest/employee/firstName");
	x.setRequestHeader("Content-Type", "application/json");
	x.send(jsonFirstName);
}

function updateLastName(){
	var x = new XMLHttpRequest();
	var lastName = {
			lastName: document.getElementById("updateLastName").value
		};
		
		var jsonLastName = JSON.stringify(lastName);
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				updateUserInfo();
			}
		};
	x.open("post","http://localhost:8080/Project1_ERS/rest/employee/lastName");
	x.setRequestHeader("Content-Type", "application/json");
	x.send(jsonLastName);
}

function updateEmail(){
	var x = new XMLHttpRequest();
	var email = {
			email: document.getElementById("updateEmail").value
		};
		
		var jsonEmail = JSON.stringify(email)
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				
				updateUserInfo();
				
			}
		};
	x.open("post","http://localhost:8080/Project1_ERS/rest/employee/email");
	x.setRequestHeader("Content-Type", "application/json");
	x.send(jsonEmail);
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
				let textnode = document.createTextNode(userObj[i].requestId + "  --  " + userObj[i].subject + "  --$ " + userObj[i].amount + " ---- " + userObj[i].status);         // Create a text node
				node.appendChild(textnode);                              // Append the text to <li>
				rList.appendChild(node); 
			}
				
			//console.log(x.responseText + " - " + x.readyState + " - " + x.status );
			//console.log("user Object: " + userObj);
		}
	};
x.open("get","http://localhost:8080/Project1_ERS/rest/employee/reimbursements");
x.send();
	
	
}
	
