window.onload = () => {
	let reqBtn = document.getElementById("viewRequestsBtn");
	reqBtn.addEventListener("click",() => {
		var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
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
			// comment the if condition and try to print all different
			// readyStates
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
	
	let EmpBtn = document.getElementById("viewEmployeesBtn");
	EmpBtn.addEventListener("click",() => {
		var z = new XMLHttpRequest();
		z.onreadystatechange = () => {
			// comment the if condition and try to print all different
			// readyStates
			if((z.readyState == 4) && (z.status ==200)){

				var employees = JSON.parse(z.responseText);

				console.log(employees);

				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let fnamecol = document.createElement("td");
				let lnamecol = document.createElement("td");
				let unamecol = document.createElement("td");
				let ismanagercol = document.createElement("td");
				
				idcol.textContent = "Employee ID";
				fnamecol.textContent = "First Name";
				lnamecol.textContent = "Last Name";
				unamecol.textContent = "Username";
				ismanagercol.textContent = "Manager";
				
				row.appendChild(idcol);
				row.appendChild(fnamecol);
				row.appendChild(lnamecol);
				row.appendChild(unamecol);
				row.appendChild(ismanagercol);
				document.getElementById("employeesTable").appendChild(row);
				
				for (let i = 0; i < employees.length; i++){
					
					let row = document.createElement("tr");
					let idcol = document.createElement("td");
					let fnamecol = document.createElement("td");
					let lnamecol = document.createElement("td");
					let unamecol = document.createElement("td");
					let ismanagercol = document.createElement("td");
					
					idcol.textContent = employees[i].employee_id;
					fnamecol.textContent = employees[i].firstname;
					lnamecol.textContent = employees[i].lastname;
					unamecol.textContent = employees[i].username;
					ismanagercol.textContent = employees[i].manager ? "Yes":"No";

					row.appendChild(idcol);
					row.appendChild(fnamecol);
					row.appendChild(lnamecol);
					row.appendChild(unamecol);
					row.appendChild(ismanagercol);

					document.getElementById("employeesTable").appendChild(row);
					
				}
			}
		};
		z.open("get","http://localhost:8080/project1/viewEmployees");
		z.send();
	});
	
	
	
	let allReqBtn = document.getElementById("viewAllRequestsBtn");
	allReqBtn.addEventListener("click",() => {
		var w = new XMLHttpRequest();
		w.onreadystatechange = () => {
			// comment the if condition and try to print all different
			// readyStates
			if((w.readyState == 4) && (w.status ==200)){

				var reimbursements = JSON.parse(w.responseText);

				console.log(reimbursements);

				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let empidcol = document.createElement("td");
				let purposecol = document.createElement("td");
				let amountcol = document.createElement("td");
				let statuscol = document.createElement("td");
				let approvedbycol = document.createElement("td");
				
				idcol.textContent = "Reimbursement ID";
				empidcol.textContent = "Employee ID"
				purposecol.textContent = "Purpose";
				amountcol.textContent = "Amount";
				statuscol.textContent = "Status";
				approvedbycol.textContent = "Approved By"
				
				row.appendChild(idcol);
				row.appendChild(empidcol);
				row.appendChild(purposecol);
				row.appendChild(amountcol);
				row.appendChild(statuscol);
				row.appendChild(approvedbycol);

				document.getElementById("allRequestsTable").appendChild(row);
				
				for (let i = 0; i < reimbursements.length; i++){
					
					let row = document.createElement("tr");
					let idcol = document.createElement("td");
					let empidcol = document.createElement("td");
					let purposecol = document.createElement("td");
					let amountcol = document.createElement("td");
					let statuscol = document.createElement("td");
					let approvedbycol = document.createElement("td");

					idcol.textContent = reimbursements[i].reimbursement_id;
					empidcol.textContent = reimbursements[i].employee_id;
					purposecol.textContent = reimbursements[i].purpose;
					amountcol.textContent = reimbursements[i].amount;
					statuscol.textContent = reimbursements[i].approved_status;
					approvedbycol.textContent = reimbursements[i].approved_by;

					row.appendChild(idcol);
					row.appendChild(empidcol);
					row.appendChild(purposecol);
					row.appendChild(amountcol);
					row.appendChild(statuscol);
					if (approvedbycol != null){
						row.appendChild(approvedbycol);
					}

					document.getElementById("allRequestsTable").appendChild(row);
					
				}
			}
		};
		w.open("get","http://localhost:8080/project1/viewAllRequests");
		w.send();
	});
	
	allReqBtn.addEventListener("click",() => {
		var t = new XMLHttpRequest();
		t.onreadystatechange = () => {
			// comment the if condition and try to print all different
			// readyStates
			if((t.readyState == 4) && (t.status ==200)){

				var employees = JSON.parse(t.responseText);

				console.log(employees);
				
				for (let i = 0; i < employees.length; i++){
					
					$("#filter").append( "<option value=\"" + employees[i].employee_id + "\">Employee ID: "+ employees[i].employee_id+", Username: " + employees[i].username+ "</option>" );
					
				}
			}
		};
		t.open("get","http://localhost:8080/project1/viewEmployees");
		t.send();
	});
	
	
	let approveDenyBtn = document.getElementById("approveDenyBtn");
	approveDenyBtn.addEventListener("click",() => {
		var s = new XMLHttpRequest();
		s.onreadystatechange = () => {
			// comment the if condition and try to print all different
			// readyStates
			if((s.readyState == 4) && (s.status ==200)){

				var reimbursements = JSON.parse(s.responseText);

				console.log(reimbursements);
				
				for (let i = 0; i < reimbursements.length; i++){
					
					if (reimbursements[i].approved_status == "pending"){
						$("#reimbursementSelector").append( "<option value=\"" + reimbursements[i].reimbursement_id + "\">Reimbursement ID: "+ reimbursements[i].reimbursement_id + ", Purpose: " + reimbursements[i].purpose + ", Amount: $" + reimbursements[i].amount+ "</option>" );
					}
				}
			}
		};
		s.open("get","http://localhost:8080/project1/viewAllRequests");
		s.send();
	});
	
	
	let filterReqBtn = document.getElementById('filterRequestsBtn');
	filterReqBtn.addEventListener("click",() => {
		var u = new XMLHttpRequest();
		u.onreadystatechange = () => {
			// comment the if condition and try to print all different
			// readyStates
			if((u.readyState == 4) && (u.status ==200)){

				var reimbursements = JSON.parse(u.responseText);

				console.log(reimbursements);

				let row = document.createElement("tr");
				let idcol = document.createElement("td");
				let empidcol = document.createElement("td");
				let purposecol = document.createElement("td");
				let amountcol = document.createElement("td");
				let statuscol = document.createElement("td");
				let approvedbycol = document.createElement("td");
				
				idcol.textContent = "Reimbursement ID";
				empidcol.textContent = "Employee ID"
				purposecol.textContent = "Purpose";
				amountcol.textContent = "Amount";
				statuscol.textContent = "Status";
				approvedbycol.textContent = "Approved By"
				
				row.appendChild(idcol);
				row.appendChild(empidcol);
				row.appendChild(purposecol);
				row.appendChild(amountcol);
				row.appendChild(statuscol);
				row.appendChild(approvedbycol);

				document.getElementById("allRequestsTable").appendChild(row);
				
				for (let i = 0; i < reimbursements.length; i++){
					
					if (reimbursements[i].employee_id == $("#filter").val()){

						let row = document.createElement("tr");
						let idcol = document.createElement("td");
						let empidcol = document.createElement("td");
						let purposecol = document.createElement("td");
						let amountcol = document.createElement("td");
						let statuscol = document.createElement("td");
						let approvedbycol = document.createElement("td");

						idcol.textContent = reimbursements[i].reimbursement_id;
						empidcol.textContent = reimbursements[i].employee_id;
						purposecol.textContent = reimbursements[i].purpose;
						amountcol.textContent = reimbursements[i].amount;
						statuscol.textContent = reimbursements[i].approved_status;
						approvedbycol.textContent = reimbursements[i].approved_by;

						row.appendChild(idcol);
						row.appendChild(empidcol);
						row.appendChild(purposecol);
						row.appendChild(amountcol);
						row.appendChild(statuscol);
						if (approvedbycol != null){
							row.appendChild(approvedbycol);
						}

						document.getElementById("allRequestsTable").appendChild(row);
					}
					
				}
			}
		};
		u.open("get","http://localhost:8080/project1/viewAllRequests");
		u.send();
	});
	
	
	
	
	document.getElementById("viewRequestsBtn").addEventListener("click",clearRows1);
	document.getElementById("viewInfoBtn").addEventListener("click",clearRows2);
	document.getElementById("viewEmployeesBtn").addEventListener("click",clearRows3);
	document.getElementById("viewAllRequestsBtn").addEventListener("click",clearRows4);
	document.getElementById("filterRequestsBtn").addEventListener("click",clearRows4);
	document.getElementById("approveDenyBtn").addEventListener("click",clearSelect);
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

function clearRows3(){
	
	let table = document.getElementById("employeesTable");
	
	let count = table.rows.length - 1;
	
	while (count >= 0){
		table.deleteRow(count);
		count = count - 1;
		
	}	
}

function clearRows4(){
	
	let table = document.getElementById("allRequestsTable");
	
	let count = table.rows.length - 1;
	
	while (count >= 0){
		table.deleteRow(count);
		count = count - 1;
		
	}
	
	let selector = document.getElementById("filter");

	count = selector.length - 1;
	while (count >= 0){
		selector.remove(count);
		count = count - 1;

	}	
	
}

function clearSelect(){
	
	let selector = document.getElementById("reimbursementSelector");
	
	let count = selector.length - 1;
	while (count >= 0){
		selector.remove(count);
		count = count - 1;
		
	}	
}