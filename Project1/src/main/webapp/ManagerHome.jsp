<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Homepage</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>
<body>
<form action="http://localhost:8080/Project1/rest/ManagerHome" method="POST">
<h1>MANAGER HOME PAGE</h1>
<h2>Welcome, <%= session.getAttribute("firstname") %></h2>
<label for="selection">Select what you would like to do</label>
<br>
<button type="button" onclick="loadAllPend()">View All Pending Requests</button>

<button type="button" onclick="loadAllRes()">view All Resolved Requests</button>

<button type="button" onclick="loadAllEmp()">View All Employees</button>


<button type="button" onclick="loadSubReim()">view Single User Requests</button>
 
<br>
</form>

<form action = "http://localhost:8080/Project1/rest/users" method="POST">
<button>Logout</button>
</form>
<div id="demo"></div>

<script>

function loadSubReimb() {
	 console.log("number=" + document.getElementById("bn").value)
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
	     let userreimid = document.createElement("th");
	     let managerassigned = document.createElement("th");
	     let processed = document.createElement("th");
	     let type = document.createElement("th");
	     let description = document.createElement("th");
	     let amnt = document.createElement("th");
	     
	     userid.innerHTML = "Request ID";
	     userreimid.innerHTML = "User ID";
	     managerassigned.innerHTML = "Manager ID";
	     processed.innerHTML = "Processed 0:NO 1:YES";
	     type.innerHTML = "Type";
	     description.innerHTML = "Description";
	     amnt.innerHTML = "Amount"
	     
	     x.append(userid);
	     x.append(userreimid);
	     x.append(managerassigned);
	     x.append(processed);
	     x.append(amnt);
	     x.append(type);
	     x.append(description);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        let userReim = myObj[i].userReimId;
			let id = myObj[i].userId;
			let managerA = myObj[i].managerAssigned;
			let proc = myObj[i].processed;
			let amt = myObj[i].amount;
			let typE = myObj[i].type;
			let descr = myObj[i].description;
			
			
			if ( id  && userReim && amt && typE && descr){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					userReimcol.textContent = userReim;
					idcol.textContent = id;
					managerAcol.textContent = managerA;
					proccol.textContent = proc;
					amtcol.textContent = amt;
					typeEcol.textContent = typE;
					descrcol.textContent = descr;
					
					console.log(id);
					
					row.appendChild(userReimcol);
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
	  xhttp.open("POST", "http://localhost:8080/Project1/rest/singleempRequest?number=" + document.getElementById("bn").value ,true);
	  xhttp.send("number=" + document.getElementById("bn").value);
	}

function loadSubReim() {
	
	
	     
	     document.getElementById("demo").innerHTML =`
	     <label>Enter User Id</label>
	     <input id="bn" type="text" name="number">
	     <button type="button" onclick="loadSubReimb()">submit</button>`;
	     
	    
	}

function loadAllPend() {
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
	     let userreimid = document.createElement("th");
	     let managerassigned = document.createElement("th");
	     let processed = document.createElement("th");
	     let type = document.createElement("th");
	     let description = document.createElement("th");
	     let amnt = document.createElement("th");
	     
	     userid.innerHTML = "User ID";
	     userreimid.innerHTML = "Request ID";
	     managerassigned.innerHTML = "Manager ID";
	     processed.innerHTML = "Processed 0:NO 1:YES";
	     type.innerHTML = "Type";
	     description.innerHTML = "Description";
	     amnt.innerHTML = "Amount"
	     
	     x.append(userid);
	     x.append(userreimid);
	     x.append(managerassigned);
	     x.append(processed);
	     x.append(amnt);
	     x.append(type);
	     x.append(description);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        let userReim = myObj[i].userReimId;
			let id = myObj[i].userId;
			let managerA = myObj[i].managerAssigned;
			let proc = myObj[i].processed;
			let amt = myObj[i].amount;
			let typE = myObj[i].type;
			let descr = myObj[i].description;
			
			
			if ( id  && userReim && amt && typE && descr){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					userReimcol.textContent = userReim;
					idcol.textContent = id;
					managerAcol.textContent = managerA;
					proccol.textContent = proc;
					amtcol.textContent = amt;
					typeEcol.textContent = typE;
					descrcol.textContent = descr;
					
					console.log(id);
					
					row.appendChild(userReimcol);
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
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/allPendRequest", true);
	  xhttp.send();
	}
	
function loadAllRes() {
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
	     let userreimid = document.createElement("th");
	     let managerassigned = document.createElement("th");
	     let processed = document.createElement("th");
	     let type = document.createElement("th");
	     let description = document.createElement("th");
	     let amnt = document.createElement("th");
	     
	     userid.innerHTML = "User ID";
	     userreimid.innerHTML = "Request ID";
	     managerassigned.innerHTML = "Manager ID";
	     processed.innerHTML = "Processed 0:NO 1:YES";
	     type.innerHTML = "Type";
	     description.innerHTML = "Description";
	     amnt.innerHTML = "Amount"
	     
	     x.append(userid);
	     x.append(userreimid);
	     x.append(managerassigned);
	     x.append(processed);
	     x.append(amnt);
	     x.append(type);
	     x.append(description);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        let userReim = myObj[i].userReimId;
			let id = myObj[i].userId;
			let managerA = myObj[i].managerAssigned;
			let proc = myObj[i].processed;
			let amt = myObj[i].amount;
			let typE = myObj[i].type;
			let descr = myObj[i].description;
			
			
			if ( id  && userReim && amt && typE && descr){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					userReimcol.textContent = userReim;
					idcol.textContent = id;
					managerAcol.textContent = managerA;
					proccol.textContent = proc;
					amtcol.textContent = amt;
					typeEcol.textContent = typE;
					descrcol.textContent = descr;
					
					console.log(id);
					
					row.appendChild(userReimcol);
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
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/allResolvedRequest", true);
	  xhttp.send();
	}
	
function loadAllEmp() {
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
	     let username = document.createElement("th");
	     let firstname = document.createElement("th");
	     let lastname = document.createElement("th");
	     let email = document.createElement("th");
	     let is_manager = document.createElement("th");
	     let userReim = document.createElement("th");
	     
	     userid.innerHTML = "User ID";
	     username.innerHTML = "Username";
	     firstname.innerHTML = "Firstname";
	     lastname.innerHTML = "Lastname";
	     email.innerHTML = "Email";
	     is_manager.innerHTML = "Manager 0:No 1:Yes";
	     userReim.innerHTML = "Reimbursment Id"
	     
	     x.append(userid);
	     x.append(username);
	     x.append(firstname);
	     x.append(lastname);
	     x.append(email);
	     x.append(is_manager);
	     x.append(userReim);
	     	     
	     for(i = 0; i < myObj.length; i++){
	        let userReim = myObj[i].userReim;
			let id = myObj[i].id;
			let firstName = myObj[i].firstname;
			let lastName = myObj[i].lastname;
			let ema= myObj[i].email;
			let isMan = myObj[i].isManager;
			let userName = myObj[i].username;
			
			
			if ( id  && userReim && firstName && lastName && userName){
				if(id.trim != "" || firstname.trim != "" || lastname.trim != ""){
					let row = document.createElement("tr");
					let userReimcol = document.createElement("td");
					let idcol = document.createElement("td");
					let managerAcol = document.createElement("td");
					let proccol = document.createElement("td");
					let amtcol = document.createElement("td");
					let typeEcol = document.createElement("td");
					let descrcol = document.createElement("td");
					
					userReimcol.textContent = id;
					idcol.textContent = userName;
					managerAcol.textContent = firstName;
					proccol.textContent = lastName;
					amtcol.textContent = ema;
					typeEcol.textContent = isMan;
					descrcol.textContent = userReim;
					
					console.log(id);
					
					row.appendChild(userReimcol);
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
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/allEmployees", true);
	  xhttp.send();
}

</script>
</body>
</html>