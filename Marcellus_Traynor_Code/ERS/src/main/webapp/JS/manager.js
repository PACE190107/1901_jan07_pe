window.onload = function()
{
	document.getElementById("manSingleBtn").addEventListener("click", getSingleEmployeeReim);
	document.getElementById("allEmp").addEventListener("click", getAllEmployeeReim);
	document.getElementById("manPendingBtn").addEventListener("click", getPendingReimbursements);
	document.getElementById("manResolvedBtn").addEventListener("click", getResolvedReimbursements);
	
	var employee = new XMLHttpRequest();
	employee.open("get", "http://localhost:8080/ERS/home/getAllEmployeeInfo");
	
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
		
				document.getElementById("manEmpTable").appendChild(row);
			}
		}
	};
	employee.send();
}

function getSingleEmployeeReim()
{
	fetch('http://localhost:8080/ERS/home/singleEmployeeReim')
	.then(function(response)
		{
			return response.json();
		})
	.then(function(data)
		{
			console.log(data); 
			
			let Table = document.getElementById("manReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				let approved_by = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				approved_by.textContent = data[i].approved_by;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				row.appendChild(approved_by);
				
				document.getElementById("manReTable").appendChild(row);
			}
		});
}

function getAllEmployeeReim()
{
	fetch('http://localhost:8080/ERS/home/allEmployeeReim')
	.then(function(response)
		{
			return response.json();
		})
	.then(function(data)
		{
			console.log(data); 
			
			let Table = document.getElementById("manReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				let approved_by = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				approved_by.textContent = data[i].approved_by;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				row.appendChild(approved_by);
				
				document.getElementById("manReTable").appendChild(row);
			}
		});
}

function getPendingReimbursements()
{
	fetch('http://localhost:8080/ERS/home/allPendingReim')
	.then(function(response)
		{
			return response.json();
		})
	.then(function(data)
		{
			console.log(data);
			
			let Table = document.getElementById("manReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				let approved_by = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				approved_by.textContent = data[i].approved_by;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				row.appendChild(approved_by);
				
				document.getElementById("manReTable").appendChild(row);
			}
		});
}

function getResolvedReimbursements()
{
	fetch('http://localhost:8080/ERS/home/allResolvedReim')
	.then(function(response)
		{
			return response.json();
		})
	.then(function(data)
		{
			console.log(data); 
			
			let Table = document.getElementById("manReTable");
			Table.innerHTML = "";
			
			for(i = 0; i < data.length; i++)
			{
				let row = document.createElement("tr");
				let username = document.createElement("td");
				let rId = document.createElement("td");
				let reason = document.createElement("td");
				let amount = document.createElement("td");
				let status = document.createElement("td");
				let approved_by = document.createElement("td");
				
				username.textContent = data[i].username;
				rId.textContent = data[i].rId;
				reason.textContent = data[i].reason;
				amount.textContent = data[i].amount;
				status.textContent = data[i].status;
				approved_by.textContent = data[i].approved_by;
				
				row.appendChild(username);
				row.appendChild(rId);
				row.appendChild(reason);
				row.appendChild(amount);
				row.appendChild(status);
				row.appendChild(approved_by);
				
				document.getElementById("manReTable").appendChild(row);
			}
		});
}