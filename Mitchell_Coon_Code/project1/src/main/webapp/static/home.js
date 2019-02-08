window.onload = () => {
	let reqBtn = document.getElementById("viewRequestsBtn");
	reqBtn.addEventListener("click",() => {
		var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){

				var reimbursements = JSON.parse(x.responseText);

				console.log(reimbursements);

				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let purposecol = document.createElement("td");
				let amountcol = document.createElement("td");
				let statuscol = document.createElement("td");
				
				idcol.textContent = "Reimbursement ID";
				purposecol.textContent = "Purpose";
				amountcol.textContent = "Amount";
				statuscol.textContent = "Status";
				
				row.appendChild(idcol);
				row.appendChild(purposecol);
				row.appendChild(amountcol);
				row.appendChild(statuscol);

				document.getElementById("requestTable").appendChild(row);
				
				for (let i = 0; i < reimbursements.length; i++){
					
					let row = document.createElement("tr");
					let idcol = document.createElement("td");
					let purposecol = document.createElement("td");
					let amountcol = document.createElement("td");
					let statuscol = document.createElement("td");

					idcol.textContent = reimbursements[i].reimbursement_id;
					purposecol.textContent = reimbursements[i].purpose;
					amountcol.textContent = reimbursements[i].amount;
					statuscol.textContent = reimbursements[i].approved_status;

					row.appendChild(idcol);
					row.appendChild(purposecol);
					row.appendChild(amountcol);
					row.appendChild(statuscol);

					document.getElementById("requestTable").appendChild(row);
					
				}
			}
		};
		x.open("get","http://localhost:8080/project1/viewRequests");
		x.send();
	});
	
	
	let infoBtn = document.getElementById("viewInfoBtn");
	infoBtn.addEventListener("click",() => {
		var y = new XMLHttpRequest();
		y.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((y.readyState == 4) && (y.status ==200)){

				var user = JSON.parse(y.responseText);

				console.log(user);

				let topRow = document.createElement("tr");
				let topidcol = document.createElement("td");
				let topfnamecol = document.createElement("td");
				let toplnamecol = document.createElement("td");
				let topunamecol = document.createElement("td");
				
				topidcol.textContent = "Employee ID";
				topfnamecol.textContent = "First Name";
				toplnamecol.textContent = "Last Name";
				topunamecol.textContent = "Username";
				
				topRow.appendChild(topidcol);
				topRow.appendChild(topfnamecol);
				topRow.appendChild(toplnamecol);
				topRow.appendChild(topunamecol);
				
				document.getElementById("infoTable").appendChild(topRow);

				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let fnamecol = document.createElement("td");
				let lnamecol = document.createElement("td");
				let unamecol = document.createElement("td");
				
				idcol.textContent = user.employee_id;
				fnamecol.textContent = user.firstname;
				lnamecol.textContent = user.lastname;
				unamecol.textContent = user.username;

				row.appendChild(idcol);
				row.appendChild(fnamecol);
				row.appendChild(lnamecol);
				row.appendChild(unamecol);

				document.getElementById("infoTable").appendChild(row);
					
			}
		};
		y.open("get","http://localhost:8080/project1/viewInformation");
		y.send();
	});
	
	document.getElementById("viewRequestsBtn").addEventListener("click",clearRows1);
	document.getElementById("viewInfoBtn").addEventListener("click",clearRows2);
}

function clearRows1(){
	
	let table = document.getElementById("requestTable");
	
	let count = table.rows.length - 1;

	while (count >= 0){
		table.deleteRow(count);
		count = count - 1;

	}
	
}

function clearRows2(){
	
	let table = document.getElementById("infoTable");
	
	let count = table.rows.length - 1;
	
	while (count >= 0){
		table.deleteRow(count);
		count = count - 1;
		
	}
	
}