<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Reimbursements</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar sticky-top navbar-light bg-light">
  <a class="navbar-brand" href="#">Welcome ${employee.firstName} </a>
  
<div class="dropdown">
 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Menu
 </button>
  <div class="dropdown-menu">
    <button class="dropdown-item" data-toggle="modal" data-target="#editUserModal" >Edit Info</button>
        <div class="dropdown-divider"></div>    
    <form method="POST" action="logout"><button class="dropdown-item" href="logout">Log Out</button></form>
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


<div class="container">
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
		<table class="table table-striped table-hover table-responsive" id="pendingTable">
			<thead>
			<tr>
				<th>Submitted By</th>
				<th>Amount</th>
				<th>Submitted On</th>
			</tr>
			</thead>
			<tbody id="pendingBody">
				<c:forEach var="Reimbursement" items="${pending}">
					<tr>
						<td><c:out value="${Reimbursement.employeeId}" /></td>						
						<td><c:out value="${Reimbursement.amount}" /></td>
						<td><c:out value="${Reimbursement.submitDate}" /></td>
					</tr>
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
<!-- same notes as above -->
		<table class="table table-striped table-hover table-responsive" id = "resolvedTable">
		<thead>
			<tr>
				<th>Amount</th>
				<th>Submitted On</th>
				<th>Approved On</th>
				<th>Approved By</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="resolved" items="${resolved}">
			<tr>
				<td><c:out value="${resolved.amount}" /></td>
				<td><c:out value="${resolved.submitDate }"/></td>					
				<td><c:out value="${resolved.approvedDate}"/></td>
				<td><c:out value="${resolved.approvedId }"/></td>
			</tr>
		</c:forEach>		
		</tbody>
		</table>
      </div>
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
      <form id="newReimbursement" enctype="multipart/form-data" method="post" action="newReimbursements">
      
      <div class="modal-body">
          <div class="form-group">
          	<label for="occuredDate">Date Of Expenditure</label>
          	<input type="date" class="form-control" id="occuredDate" name="occuredDate"	>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Amount:</label>
            <input type="number" class="form-control" id="amount" name = amount min="0.00" step="0.01"></input>
          </div>
              </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="newReimbursementBtn" onclick="sendReimbursement()">Save changes</button>
      </div>
      </form>
    </div>
  </div> 
</div>
</body>
<script type="text/javascript">
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
		var occurDate = document.getElementById("occuredDate").value;
		var amount = document.getElementById("amount").value;
		console.log("email.value" + email)
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
			      row.innerHTML="<td>"+r.employeeId+"</td><td>"+r.amount+"</td><td>"+r.expenditureDate+"</td>"
			      table.appendChild(row);
			}
		};
		var occurDate = document.getElementById("occuredDate").value;
		var amount = document.getElementById("amount").value;
		newRequest.open("POST", "newReimbursement?occurDate=" + occurDate+"&amount="+amount, true);
		newRequest.send();
		console.log("After data sent");
	}
</script>
</html>