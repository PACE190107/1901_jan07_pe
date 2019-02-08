window.onload = () => {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function() {
		if ((x.readyState == 4) && (x.status == 200)) {
			var reimbursements = JSON.parse(x.responseText);
			for (let i=0; i<reimbursements.length; i++) {
				let row = document.createElement("tr");
				let reimbursementId = document.createElement("td");
				let requesterId = document.createElement("td");
				let authorizerId = document.createElement("td");
				let type = document.createElement("td");
				let status = document.createElement("td");
				let amount = document.createElement("td");
				let date = document.createElement("td");
				
				reimbursementId.textContent = reimbursements[i].id;
				requesterId.textContent = reimbursements[i].requesterId;
				authorizerId.textContent = (reimbursements[i].authorizerId >= 0) ? reimbursements[i].authorizerId : "-";
				type.textContent = reimbursements[i].type;
				status.textContent = reimbursements[i].status;
				amount.textContent = "$" + reimbursements[i].amount.toFixed(2);
				date.textContent = reimbursements[i].date;
				
				row.appendChild(reimbursementId);
				row.appendChild(requesterId);
				row.appendChild(authorizerId);
				row.appendChild(type);
				row.appendChild(status);
				row.appendChild(amount); 
				row.appendChild(date);
				
				document.getElementById("tableBody").appendChild(row);		
			}
		}
	};
	x.open("POST", "http://localhost:8080/ERS_Updated/getAllReimbursements"); 
	x.send();
}