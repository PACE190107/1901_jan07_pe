<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Employee Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="static/ERS_CSScss.css">
</head>
<body class="normal">
<%@ page import="com.revature.models.User" %>
<% User currentUser = (User) session.getAttribute("currentUser");%>

	<div >
	
	<!-- top navbar -->
    <div class="container-fluid bg-primary">
            <ul class="nav nav-pills">
                   
                
                
                    <li class="nav-item">
                    <form action="logout" method="get">
                    <button  type = "submit" class="btn btn-primary" name = "mylogoutbutton">
                                      
                    </button>
                    </form>
                    
                    </li>
                  </ul>
    </div>





    <!--Card with Reimbursement Form-->
    <div class="container py-5">
    
        <div class = "card border-success">
                <div class="card-header">New Request</div>

            <div class="card-body">
                <h5>Make a Request</h5>
             
               

                <form method="POST" action="newRequest">
                        <div class="form-group">
                          <label for="DateInput">Date</label>
                          <input type="date" class="form-control" id="date_received"  placeholder="Date" name="date_received">
                    
                        </div>
                       
                        <div class="form-group">
                          <label for="Type">Type</label>
                         
                          <select class="form-control" name ="type">

                            <option selected disabled >Select Reimbursement Type</option>

                            
 
                                <option value="travel">Travel</option>
                                 
                                <option value="food">Food</option>
                                 
                                <option value="relocation">Relocation</option>
                                 
                                <option value="education">Education</option>
                                 
                                <option value="client">Client Expense</option>
                                 
                                <option value="misc">Misc</option>
                                 
                          </select>
                        </div>
                       
                        <div class="form-group">
                                <label for="Type">Amount</label>
                                <input type="number" class="form-control" id="amount" placeholder="$" name="amount">
                                
                              </div>
                    <div class="form-group">
                          <label for="Type">Description</label>
                          <input type="text" class="form-control" id="description" placeholder="Add a brief description" name="description">
                        </div> 
                      
                        <button type="submit" class="btn btn-outline-success">Submit</button>
                      </form>
            </div>

        </div>


    </div>

<!-- works! -->
<!--Card for employee reimbursements table-->
<div class="container py-5">
        <div class = "card border-success">
                <div class="card-header">View Requests</div>

            		<div class="card-body">

                    <table class="table table-striped" id="viewEmployeeRequests">
                            <thead>
                              <tr>
                             	<th scope="col">Request Id</th>
                                <th scope="col">Employee Id</th>
                                <th scope="col">Date Received</th>
                                <th scope="col">Request Type</th>
                                <th scope="col">Amount</th>                        
                                <th scope="col">Description</th>
                                <th scope="col">Status</th>
                                <th scope="col">Reviewed by Manager</th>
                              </tr>
                            </thead>
                       		<tbody>
                            </tbody>
                          </table>



<!-- Display User Info -->
<div class="container py-5">
        <div class = "card border-success">
                <div class="card-header">Employee Information</div>

            		<div class="card-body">
            		<h1>Hi
            		<%
            		out.println(currentUser.getFirstName());
            		%>
            		</h1>
            		<h4>Employee ID:
            		<%
            		out.println(currentUser.getE_id());
            		%>
            		</h4>
            		<h4>First Name:
            		<%
            		out.println(currentUser.getFirstName());
            		%>
            		</h4>
            		<h4>Last Name:
            		<%
            		out.println(currentUser.getLastName());
            		%>
            		
            		</h4>
            		<h4>Email:
            		<%
            		out.println(currentUser.getEmail());
            		%>
            		
            		</h4>
            		
            		
            		
            		
            		
            		
            		</div>
            	</div>	
            </div>
            
            
            
<!-- Update User Info -->            	
<div class="container py-5">
        <div class = "card border-success">
                <div class="card-header">Update Employee Information</div>
            		<div class="card-body">
            			 <form method="get" action="updateUser">
            			 
            			 
            			 <div class="form-group">
                                <label for="Type">First Name</label>
                                <input type="text" class="form-control" id="firstName" placeholder="Update Your First Name" name="firstName" required>
                                
                         </div>
                   		 
                   		 
                   		 <div class="form-group">
                          <label for="Type">Last Name</label>
                          <input type="text" class="form-control" id="lastName" placeholder="Update Your Last Name" name="lastName" required>
                        </div> 
                        
                        <div class="form-group">
                          <label for="Type">Email</label>
                          <input type="email" class="form-control" id="email" placeholder="Update Your Email" name="email" required>
                        </div> 
                      
                      
                      
                        <button type="submit" class="btn btn-outline-success">Submit</button>
                      </form>
            			
            		
            		
            		</div>
				</div>
		</div>
</div>












		
</div>
</div>
</div>

 </div>               


<script type ="text/javascript" src= "static/viewrequestsajax.js"  ></script>
</body>
</html>