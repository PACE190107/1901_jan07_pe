window.onload = function(){
	document.getElementById("backBtn").addEventListener("click",function(){
		
		window.location = "http://localhost:8080/Project1_ERS/managerHome.html";
		
	});
	
	loadEmployees();
	document.getElementById("loadEmployee").addEventListener("click", loadSingleEmployee);
}

function loadSingleEmployee(){
	var x = new XMLHttpRequest();
	var idString = "" + document.getElementById("eId").value;
	x.onreadystatechange = () => {
		//comment the if condition and try to print all different readyStates
		if((x.readyState == 4) && (x.status ==200)){
			let rList = document.getElementById("employeeView");
			
			let userObj = JSON.parse(x.responseText); 
			
			rList.innerHTML = "First Name: " + userObj.firstName + "<br>" +
								"Last Name: " + userObj.lastName + "<br>" +
								"Username: " + userObj.username + "<br>" +
								"Email: " + userObj.email + "<br><br>"+
								"<strong>Employee Reimbursement Requests: </strong>";
				
			//console.log(x.responseText + " - " + x.readyState + " - " + x.status );
			//console.log("user Object: " + userObj);
		}
	
		
	};
	
x.open("get","http://localhost:8080/Project1_ERS/rest/employee/" + idString);
x.send();

}

function loadEmployees(){
	
var x = new XMLHttpRequest();
	
	x.onreadystatechange = () => {
		//comment the if condition and try to print all different readyStates
		if((x.readyState == 4) && (x.status ==200)){
			let rList = document.getElementById("employeeList");
			
			while (rList.hasChildNodes()) {
			    rList.removeChild(rList.lastChild);
			}
			let userObj = JSON.parse(x.responseText); 
			
			var i;
			for(i = 0; i < userObj.length; i++){
				let node = document.createElement("LI");                 // Create a <li> node
				let textnode = document.createTextNode(userObj[i].employeeId+ "  --  " + userObj[i].firstName + "  --------- " + userObj[i].lastName + " ------- " + userObj[i].username + " ----- " + userObj[i].email );         // Create a text node
				node.appendChild(textnode);                              // Append the text to <li>
				rList.appendChild(node); 
			}
				
			//console.log(x.responseText + " - " + x.readyState + " - " + x.status );
			//console.log("user Object: " + userObj);
		}
	};
x.open("get","http://localhost:8080/Project1_ERS/rest/employee");
x.send();
	
	
}