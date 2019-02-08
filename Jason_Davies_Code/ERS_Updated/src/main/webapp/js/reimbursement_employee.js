window.onload = () => {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function() {
		if ((x.readyState == 4) && (x.status == 200)) {
			var reimbursements = JSON.parse(x.responseText);
			for (let i=0; i<reimbursements.length; i++) {
				let row = document.createElement("tr");
				let type = document.createElement("td");
				let status = document.createElement("td");
				let amount = document.createElement("td");
				let date = document.createElement("td");
				
				type.textContent = reimbursements[i].type;
				status.textContent = reimbursements[i].status;
				amount.textContent = "$" + reimbursements[i].amount.toFixed(2);;
				date.textContent = reimbursements[i].date;
				
				row.appendChild(type);
				row.appendChild(status);
				row.appendChild(amount);
				row.appendChild(date);
				
				document.getElementById("tableBody").appendChild(row);		
			}
		}
	};
	x.open("POST", "http://localhost:8080/ERS_Updated/getMyReimbursements"); 
	x.send();
}