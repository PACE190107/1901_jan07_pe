window.onload = function()
{
	document.getElementById("empAllBtn").addEventListener("click", getAllReimbursements);
	document.getElementById("empPendingBtn").addEventListener("click", getPendingReimbursements);
	document.getElementById("empResolvedBtn").addEventListener("click", getResolvedReimbursements);
	
	var employee = new XMLHttpRequest();
	employee.open("get", "http://localhost:8080/ERS/home/getEmployeeInfo");
	
	employee.onreadystatechange = () =>
	{
		if((employee.readyState == 4) && (employee.status == 200))
		{
			
			let data = JSON.parse(employee.responseText);
			console.log(data);
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let fName = document.createElement("td");
				let lName = document.createElement("td");
				let job = document.createElement("td");
				let uName = document.createElement("td");
				
				fName.textContent = data[i].fName;
				lName.textContent = data[i].lName;
				job.textContent = data[i].job;
				uName.textContent = data[i].uName;
				
				row.appendChild(fName);
				row.appendChild(lName);
				row.appendChild(job);
				row.appendChild(uName);
		
				document.getElementById("empEmpTable").appendChild(row);
			}
		}
	};
	employee.send();
	
	var reim = new XMLHttpRequest();
	reim.open("get", "http://localhost:8080/ERS/home/empReimbursements");
	
	reim.onreadystatechange = () =>
	{
		if((reim.readyState == 4) && (reim.status == 200))
		{
			
			let data = JSON.parse(reim.responseText);
			console.log(data);
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				
				document.getElementById("empReTable").appendChild(row);
			}
		}
	};
	reim.send();
}

function getAllReimbursements()
{
	var empReim = new XMLHttpRequest();
	empReim.open("get", "http://localhost:8080/ERS/home/empReimbursements");
	
	empReim.onreadystatechange = () =>
	{
		if((empReim.readyState == 4) && (empReim.status == 200))
		{
			console.log("is it even getting here?")
			console.log(empReim.responseText);
			let data = JSON.parse(empReim.responseText);
			
			let Table = document.getElementById("empReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				
				document.getElementById("empReTable").appendChild(row);
			}
		}
	};
	empReim.send();
}

function getPendingReimbursements()
{
	var empPending = new XMLHttpRequest();
	empPending.open("get", "http://localhost:8080/ERS/home/empPending");
	
	empPending.onreadystatechange = () =>
	{
		if((empPending.readyState == 4) && (empPending.status == 200))
		{
			console.log("is it even getting here?")
			console.log(empPending.responseText);
			let data = JSON.parse(empPending.responseText);
			
			let Table = document.getElementById("empReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				
				document.getElementById("empReTable").appendChild(row);
			}
		}
	};
	empPending.send();
}

function getResolvedReimbursements()
{
	var empResolved = new XMLHttpRequest();
	empResolved.open("get", "http://localhost:8080/ERS/home/empResolved");
	
	empResolved.onreadystatechange = () =>
	{
		if((empResolved.readyState == 4) && (empResolved.status == 200))
		{
			console.log("is it even getting here?")
			console.log(empResolved.responseText);
			let data = JSON.parse(empResolved.responseText);
			
			let Table = document.getElementById("empReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				
				document.getElementById("empReTable").appendChild(row);
			}
		}
	};
	empResolved.send();
}