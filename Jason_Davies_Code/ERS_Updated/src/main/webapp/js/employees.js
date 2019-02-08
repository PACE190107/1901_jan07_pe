window.onload = () => {
	fetch("http://localhost:8080/ERS_Updated/getAllEmployees")
	.then(function(response){
		return response.json();
	})
	.then(function(employees){
		for (let i=0; i<employees.length; i++) {
			let row = document.createElement("tr");
			let username = document.createElement("td");
			let email = document.createElement("td");
			let firstName = document.createElement("td");
			let lastName = document.createElement("td");
			let manager = document.createElement("td");
			
			username.textContent = employees[i].username;
			email.textContent = employees[i].email;
			firstName.textContent = employees[i].firstName;
			lastName.textContent = employees[i].lastName;
			manager.textContent = employees[i].isManager;
			
			row.appendChild(username);
			row.appendChild(email);
			row.appendChild(firstName);
			row.appendChild(lastName);
			row.appendChild(manager);
			
			document.getElementById("tableBody").appendChild(row);		
		}
	})
// var x = new XMLHttpRequest();
// console.log("here");
// x.onreadystatechange = function() {
// console.log("x.readyState" + x.readyState + "x.status" + x.status);
// if ((x.readyState == 4) && (x.status == 200)) {
// console.log("x.responseText: " + x.responseText);
// var employees = JSON.parse(x.responseText);
// console.log("employees: " + employees);
// for (let i=0; i<employees.length; i++) {
// let row = document.createElement("tr");
// let username = document.createElement("td");
// let email = document.createElement("td");
// let firstName = document.createElement("td");
// let lastName = document.createElement("td");
// let manager = document.createElement("td");
//				
// username.textContent = employees[i].username;
// email.textContent = employees[i].email;
// firstName.textContent = employees[i].firstName;
// lastName.textContent = employees[i].lastName;
// manager.textContent = employees[i].isManager;
//				
// row.appendChild(username);
// row.appendChild(email);
// row.appendChild(firstName);
// row.appendChild(lastName);
// row.appendChild(manager);
//				
// document.getElementById("tableBody").appendChild(row);
// }
// }
// };
// x.open("POST", "http://localhost:8080/ERS_Updated/getAllEmployees");
// x.send();
}