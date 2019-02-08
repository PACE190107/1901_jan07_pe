<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Homepage</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>

<form action="http://localhost:8080/Project1/rest/EmployeeHome" method="POST">
<h1>EMPLOYEE HOME PAGE</h1>
<h2>Welcome, <%= session.getAttribute("firstname") %></h2>
<label for="selection">Select what you would like to do</label>
<br>

<button type="button" onclick="loadDoc()">View Your Information</button>

<button type="button" onclick="loadComp()">Completed Request</button>

<button type="button" onclick="loadPend()">Pending Requests</button>

<button type="button" onclick="loadSubReim()">Submit New Request</button>
 
<br>

</form>
<form action = "http://localhost:8080/Project1/rest/users" method="POST">
<button>Logout</button>
</form>
<div id="demo">
</div>

<script>

function loadDoc() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("demo").innerHTML = this.responseText;
	     var myObj = JSON.parse(this.responseText);
	     let employee = `First Name: ${myObj.firstname} Last Name: ${myObj.lastname} Username: ${myObj.username} Email: ${myObj.email} ID: ${myObj.id}`
	   		
	     document.getElementById("demo").innerHTML = employee;
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/info", true);
	  xhttp.send();
	}
	
function loadSubReim() {
	
	     document.getElementById("demo").innerHTML =`
	     <label>Submit Request</label>
	     <label for="amount">Amount of Money</label>
	     <input id ="amount" type="text" name="amount">
	     <br>
	     <label for="description">A Description</label>
	     <input id="description" type="text" name="description">
	     <br>
	     <button type="button" onclick="loadSubReimNext()" >Submit</button>
	     `;
	     
	}
	
function loadSubReimNext() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("demo").innerHTML = this.responseText;
	     var myObj = JSON.parse(this.responseText);
	     document.getElementById("demo").innerHTML =`
		<label>Submitted Request</label>`
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/subReim?amount=" + document.getElementById("amount").value + "&description=" + document.getElementById("description").value, true);
	  xhttp.send();
	}
	
function loadPend() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("demo").innerHTML = "";
	     var myObj = JSON.parse(this.responseText);
	     
	     var x = document.createElement("table");
	     
	     x.setAttribute("id","myTable");
	     
	     document.getElementById("demo").append(x);
	      x.setAttribute("class", "table table-bordered table-striped");
	     let userid = document.createElement("th");
	     //let userreimid = document.createElement("th");
	     let managerassigned = document.createElement("th");
	     let processed = document.createElement("th");
	     let type = document.createElement("th");
	     let description = document.createElement("th");
	     let amnt = document.createElement("th");
	     
	     userid.innerHTML = "Request ID";
	    // userreimid.innerHTML = "User ID";
	     managerassigned.innerHTML = "Manager ID";
	     processed.innerHTML = "Processed 0:NO 1:YES";
	     type.innerHTML = "Type";
	     description.innerHTML = "Description";
	     amnt.innerHTML = "Amount"
	     
	     x.append(userid);
	     //x.append(userreimid);
	     x.append(managerassigned);
	     x.append(processed);
	     x.append(amnt);
	     x.append(type);
	     x.append(description);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        //let userReim = myObj[i].userReimId;
			let id = myObj[i].userId;
			let managerA = myObj[i].managerAssigned;
			let proc = myObj[i].processed;
			let amt = myObj[i].amount;
			let typE = myObj[i].type;
			let descr = myObj[i].description;
			
			
			if ( id  && amt && typE && descr){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					//let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					//userReimcol.textContent = userReim;
					idcol.textContent = id;
					managerAcol.textContent = managerA;
					proccol.textContent = proc;
					amtcol.textContent = amt;
					typeEcol.textContent = typE;
					descrcol.textContent = descr;
					
					console.log(id);
					
					//row.appendChild(userReimcol);
					row.appendChild(idcol);
					row.appendChild(managerAcol);
					row.appendChild(proccol);
					row.appendChild(amtcol);
					row.appendChild(typeEcol);
					row.appendChild(descrcol);
					
					document.getElementById("myTable").appendChild(row);	
					
				} else {
					console.log("no values provided in the form");
				}		
				
			}
	    	 
	     }
	   		
	     
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/pendRequest", true);
	  xhttp.send();
	}
	
function loadComp() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("demo").innerHTML = "";
	     var myObj = JSON.parse(this.responseText);
	     
	     var x = document.createElement("table");
	     
	     x.setAttribute("id","myTable");
	     
	     document.getElementById("demo").append(x);
	      x.setAttribute("class", "table table-bordered table-striped");
	     let userid = document.createElement("th");
	     //let userreimid = document.createElement("th");
	     let managerassigned = document.createElement("th");
	     let processed = document.createElement("th");
	     let type = document.createElement("th");
	     let description = document.createElement("th");
	     let amnt = document.createElement("th");
	     
	     userid.innerHTML = "Request ID";
	     //userreimid.innerHTML = "User ID";
	     managerassigned.innerHTML = "Manager ID";
	     processed.innerHTML = "Processed 0:NO 1:YES";
	     type.innerHTML = "Type";
	     description.innerHTML = "Description";
	     amnt.innerHTML = "Amount"
	     
	     x.append(userid);
	     //x.append(userreimid);
	     x.append(managerassigned);
	     x.append(processed);
	     x.append(amnt);
	     x.append(type);
	     x.append(description);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        //let userReim = myObj[i].userReimId;
			let id = myObj[i].userId;
			let managerA = myObj[i].managerAssigned;
			let proc = myObj[i].processed;
			let amt = myObj[i].amount;
			let typE = myObj[i].type;
			let descr = myObj[i].description;
			
			
			if ( id && amt && typE && descr){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					//let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					//userReimcol.textContent = userReim;
					idcol.textContent = id;
					managerAcol.textContent = managerA;
					proccol.textContent = proc;
					amtcol.textContent = amt;
					typeEcol.textContent = typE;
					descrcol.textContent = descr;
					
					console.log(id);
					
					//row.appendChild(userReimcol);
					row.appendChild(idcol);
					row.appendChild(managerAcol);
					row.appendChild(proccol);
					row.appendChild(amtcol);
					row.appendChild(typeEcol);
					row.appendChild(descrcol);
					
					document.getElementById("myTable").appendChild(row);	
					
				} else {
					console.log("no values provided in the form");
				}		
				
			}
	    	 
	     }
	   		
	     
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/compRequest", true);
	  xhttp.send();
	}
	
</script>
</body>
</html>