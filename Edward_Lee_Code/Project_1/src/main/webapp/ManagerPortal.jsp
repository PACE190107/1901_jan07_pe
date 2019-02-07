<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Manager Portal</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<input type="hidden" value="${employee.id}" id="empId">
<nav class="navbar sticky-top navbar-light bg-light">
  <a class="navbar-brand" href="#">Welcome ${employee.firstName}</a> <!-- {name} is currently a placeholder. when switched to JSP it will hold the users first name -->
 	<form class="form-inline" onsubmit="getUserReimbursements(); return false">
      <input class="form-control mr-sm-2" placeholder="Search" aria-label="Search" id="unameBar">
      <button type="button" class="btn btn-outline-success my-2 my-sm-0" onclick="getUserReimbursements()">Search</button>
	</form>

<div class="nav-item dropdown">
 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Menu
 </button>
  <div class="dropdown-menu dropdown-menu-right">
    <button class="dropdown-item" data-toggle="modal" data-target="#editUserModal" >Edit Info</button>
        <div class="dropdown-divider"></div>   
	<button class="dropdown-item" data-toggle="modal" onclick="getAllEmployees()">View Employees</button>
        <div class="dropdown-divider"></div>   
    <button class="dropdown-item" data-toggle="modal" data-target="#logoutModal">Log Out</button>
  </div>
</div>
</nav>

<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit User</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="firstName" class="col-form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${employee.firstName }">
          </div>
          <div class="form-group">
          	<label for="lastName">Last Name</label>
          	<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${employee.lastName }">
          </div>
          <div class="form-group">
            <label for="email" class="col-form-label">Email</label>
            <input type="text" class="form-control" id="email" placeholder="Email" name="email" value="${ employee.email}">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editUser()">Save changes</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Logout</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          	<label for="logout">Are you sure you want to log out?</label>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
    <form method="POST" action="logout"><button class="dropdown-item btn-danger" href="logout">Log Out</button></form>
      </div>
    </div>
  </div>
</div>


<div class="container"><!-- need to address the fact it is under the nav bar -->
<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Waiting For Approval
        </button>
      </h2>
    </div>
    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body"> 
<!-- I want this to turn into a list of all the un-approved Reimbursements 
	I might wish to create a table instead.
	Another note is this might get long. A max height and scroll bar may be required
	-->
		<table class="table table-striped table-hover table-responsive" id="pendingTable">
			<thead>
			<tr>
				<th>Submitted By</th>
				<th>Amount</th>
				<th>Submitted On</th>
				<th>Approve</th>
				<th>Deny</th>
			</tr>
			</thead>
			<tbody id="pendingBody">
<!-- Use a where clause in the loop to remove buttons from a managers reimbursements -->
				<c:forEach var="Reimbursement" items="${pending}">
				<c:choose>
				<c:when test="${Reimbursement.employeeId == employee.id }">
					<tr id="${Reimbursement.reimbursementId }">
						<td><c:out value="${Reimbursement.employeeId}" /></td>						
						<td><c:out value="${Reimbursement.amount}" /></td>
						<td><c:out value="${Reimbursement.submitDate}" /></td>
						<td></td>
						<td></td>

					</tr>
				</c:when>
				<c:otherwise>
					<tr id="${Reimbursement.reimbursementId }">
						<td><c:out value="${Reimbursement.employeeId}" /></td>						
						<td><c:out value="${Reimbursement.amount}" /></td>
						<td><c:out value="${Reimbursement.submitDate}" /></td>
						<td><form action ="" onsubmit="approve(${Reimbursement.reimbursementId}); return false"><button type="submit" class="btn btn-success">Approve</button>
							<input type="hidden" value="${Reimbursement.reimbursementId}"></form></td>
						<td><form action ="" onsubmit="deny(${Reimbursement.reimbursementId}); return false"><button type="submit" class="btn btn-danger">Deny</button>
							<input type="hidden" value="${Reimbursement.reimbursementId}"></form></td>
					</tr>
				</c:otherwise>
				</c:choose>
				</c:forEach>
			</tbody>
		</table>

      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Approved Reimbursements
        </button>
      </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
      <div class="card-body">
<!-- same notes as above
approving manager submit and request dates with the photos as well -->
		<table class="table table-striped table-hover table-responsive" id = "resolvedTable">
		<thead>
			<tr>
				<th>Submitted By</th>
				<th>Amount</th>
				<th>Submitted On</th>
				<th>Approved On</th>
				<th>Approved By</th>
				<th>Approved/Denied</th>
			</tr>
		</thead>
		<tbody id="resolvedBody">
		<c:forEach var="resolved" items="${resolved}">
			<tr id="${resolved.reimbursementId }">
				<td><c:out value="${resolved.employeeId }"></c:out>
				<td><c:out value="${resolved.amount}" /></td>
				<td><c:out value="${resolved.submitDate }"/></td>					
				<td><c:out value="${resolved.approvedDate}"/></td>
				<td><c:out value="${resolved.approvedId }"/></td>
				<td><c:out value="${resolved.approved}"/></td>
			</tr>
		</c:forEach>		
		</tbody>
		</table>
    </div>
  </div>
</div>
</div>
<button type="button" data-toggle="modal" data-target="#exampleModal" style="margin-left:45%; margin-right:45%;" class="btn btn-primary">New Reimbursement</button>
	<!--  will create a modal  https://getbootstrap.com/docs/4.2/components/modal/ -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New Reimbursement</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
          	<label for="occuredDate">Date Of Expenditure</label>
          	<input type="date" class="form-control" id="occuredDate" required="required">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Amount:</label>
            <input type="number" class="form-control" id="amount" min="0.00" step="0.01" required="required"></input>
          </div>
        </form> 
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendReimbursement()">Save changes</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="employeeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="employeesModalTitle">Employees</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<ul id="employeesList">
		</ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
	<script type="text/javascript">
	function getUserReimbursements(){
		console.log("getUserReimbursements")
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if(this.readyState==4 && this.status == 200){
				console.log("Success");
				let arrays =JSON.parse(this.responseText);
				//set pending table
					//clear table
				let table = document.getElementById("pendingBody")
				while(table.firstChild) { //empty the list if it has children
		    		table.removeChild(table.firstChild);
				} //check for userId= to employeeId
				if(arrays.pending.length > 0 && document.getElementById("empId").value != arrays.pending[0].employeeId){
				for(i = 0;i<arrays.pending.length;i++){
				   	var row =document.createElement('tr');
				   	row.id=arrays.pending[i].reimbursementId;
				   	row.innerHTML="<td>"+arrays.pending[i].employeeId+"</td><td>"+arrays.pending[i].amount+"</td><td>"+arrays.pending[i].submitDate+"</td>";
		      		row.innerHTML+="<td><button type=\"button\" class=\"btn btn-success\">Approve</button></td>";
		      		row.innerHTML+="<td><button class=\"btn btn-danger\">Deny</button></td>";
		      		table.appendChild(row);
				}
				}else{
				for(i = 0;i<arrays.pending.length;i++){
				   	var row =document.createElement('tr');
				   	row.id=arrays.pending[i].reimbursementId;
		      		row.innerHTML="<td>"+arrays.pending[i].employeeId+"</td><td>"+arrays.pending[i].amount+"</td><td>"+arrays.pending[i].submitDate+"</td>";
		      		row.innerHTML+="<td></td>";
		      		row.innerHTML+="<td></td>";
		      		table.appendChild(row);
				}
				}
				//set resolved table
				let body = document.getElementById("resolvedBody")
				while(body.firstChild) { //empty the list if it has children
		    		body.removeChild(body.firstChild);
				}	
				for(i=0;i<arrays.resolved.length;i++){
				   	var resolvedRow =document.createElement('tr');
				   	resolvedRow.innerHTML="<td>"+arrays.resolved[i].employeeId+"</td><td>"+arrays.resolved[i].amount+"</td><td>"+arrays.resolved[i].submitDate+"</td><td>"+arrays.resolved[i].approvedDate+"</td><td>"+arrays.resolved[i].approvedId+"</td><td>"+arrays.resolved[i].approved+"</td>";
				   	body.appendChild(resolvedRow);
				}
			}
		}
		let uname = document.getElementById("unameBar").value;
		newRequest.open("POST","getEmployeeReimbursements?uname="+uname,true);
		newRequest.send();
	}
	function getAllEmployees(){
		console.log("getAllEmployees");
		var ul = document.getElementById("employeesList");
		while(ul.firstChild) { //empty the list if it has children
		    ul.removeChild(ul.firstChild);
		}
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				console.log("Success"); //update the modal here then display
				employees = JSON.parse(this.responseText);
				console.log(employees);
				for(i = 0; i<employees.length; i++){
					s = employees[i].userName;
					s += " "
					s += employees[i].firstName;
					s += " "
					s += employees[i].lastName;
					var li =document.createElement('li');
					li.innerHTML="<li>"+s+"</li>";
				    ul.appendChild(li);
				}
				$('#employeeModal').modal('show');
			}
		};				
		newRequest.open("POST", "getEmployees", true);
		newRequest.send();
		console.log("After data sent");
	}
	function editUser(){
		console.log("editing User");
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				console.log("Success");
			}
		};
		console.log("new request made")
		var email = document.getElementById("email").value;
		var fname = document.getElementById("firstName").value;
		var lname = document.getElementById("lastName").value;	
		newRequest.open("POST", "newstuff?fname="+fname+"&lname="+lname+"&email="+email, true);
		newRequest.send();
		console.log("After data sent");
	}
	function sendReimbursement(){
		console.log("sending reimbursement")
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200){
				var r =  JSON.parse(this.responseText);
				var table = document.getElementById("pendingBody");	
			      var row =document.createElement('tr');
			      row.innerHTML="<td>"+r.employeeId+"</td><td>"+r.amount+"</td><td>"+r.expenditureDate+"</td><td></td><td></td>"
			      table.appendChild(row);
			}
		};
		var occurDate = document.getElementById("occuredDate").value;
		var amount = document.getElementById("amount").value;
		newRequest.open("POST", "newReimbursement?occurDate=" + occurDate+"&amount="+amount, true);
		newRequest.send();
		console.log("After data sent");
	}
	function approve(reimbursementID){
	console.log("approved")
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if( this.readyState == 4 && this.status == 200){
				console.log(this.responseText);
				var r = JSON.parse(this.responseText);
				// remove the row with this ID
				let pendingRow = document.getElementById(r.reimbursementId);
				console.log(r.reimbursementId)
				pendingRow.remove();
				//add the row to the resolvedBody
				var table = document.getElementById("resolvedBody");	
			      var row =document.createElement('tr');
				  row.innerHTML="<td>"+r.employeeId+"</td><td>"+r.amount+"</td><td>"+r.submitDate+"</td><td>"+r.approvedDate+"</td><td>"+r.approvedId+"</td><td>"+r.approved+"</td>";
			      table.appendChild(row);
			}
		};
		let manId =document.getElementById("empId").value;
		newRequest.open("post","approve?rid=" + reimbursementID+"&manId="+manId,true);
		newRequest.send();
	}
	function deny(reimbursementID){
		console.log("DENIED!")
		let newRequest = new XMLHttpRequest();
		newRequest.onreadystatechange = function(){
			if( this.readyState == 4 && this.status == 200){
				console.log("success");
				console.log(this.responseText);
				var r = JSON.parse(this.responseText);
				//remove from pending
				let pendingRow = document.getElementById(r.reimbursementId);
				console.log(r.reimbursementId)
				pendingRow.remove();
				//resolved
				var table = document.getElementById("resolvedBody");	
			    var row =document.createElement('tr');
			    row.innerHTML="<td>"+r.employeeId+"</td><td>"+r.amount+"</td><td>"+r.submitDate+"</td><td>"+r.approvedDate+"</td><td>"+r.approvedId+"</td><td>"+r.approved+"</td>";
				table.appendChild(row);
			}
		};
		let manId =document.getElementById("empId").value;
		newRequest.open("post","deny?rid="+reimbursementID+"&manId="+manId,true);
		newRequest.send();
	}
	</script>
</html>