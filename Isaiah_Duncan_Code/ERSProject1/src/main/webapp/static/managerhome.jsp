<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manager Home - Good Bank</title>
<!--GOOGLE FONT IMPORTS-->
<link href="https://fonts.googleapis.com/css?family=Baloo+Thambi|Major+Mono+Display|Roboto" rel="stylesheet">
<!--IMPORT CSS STYLES-->
<link rel="stylesheet" type="text/css" href="static/style.css"/>
<!--BOOTSTRAP IMPORT-->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<%@ page import="com.revature.model.Login, com.revature.model.Employee, com.revature.delegate.LoginDelegate" %>
<% 
Login login = (Login) session.getAttribute("user"); 
Employee employeeDetails = (Employee) session.getAttribute("currentUser");
LoginDelegate ld = new LoginDelegate();

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("user")==null){
    response.sendRedirect("/ERSProject1/");
 }

%>

<!--MAIN WEB CONTAINER-->
<div id="wrapper" class="container-fluid">
    <!--WEB CONTENT CONTAINER-->
    <div id="content-container" class="container">
        <!--NAV DIV CONTAINER-->
        <div class="row">
            <div id="navContainer" class="col-md-12 margin-top-space">
                <!--NAV BAR-->
                <nav id="navBar" class="navbar navbar-expand-lg navbar-light bg-light myShadow">
                    <a class="navbar-brand makeLinkWhite" href="#"><span id="companyTitle">Good Bank</span>&trade;</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                        <li class="nav-item active">
                            <form method="POST" action="managerhome" class="nav-link makeLinkWhite linkHoverColorChange"><input id="navMainBtn" type="submit" name="homeBtn" value="Manager Home"/> <span class="sr-only">(current)</span></form>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle makeLinkWhite" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span id="loggedInUsername"><% out.println("Welcome, " + employeeDetails.getFirstname() + " " + employeeDetails.getLastname()); %></span>
                            </a>
                            <div class="dropdown-menu manager-dropdown" aria-labelledby="navbarDropdownMenuLink">
                            <button class="dropdown-item navBtnPadding" id="navBtn" data-toggle="modal" data-target="#exampleModal">Resolution Form</button>
                            <button class="dropdown-item navBtnPadding" id="navBtn" data-toggle="modal" data-target="#exampleModal2">View Employee Requests</button>
                            <button class="dropdown-item navBtnPadding" id="navBtn" data-toggle="modal" data-target="#exampleModal3">Create Employee Account</button>
                            <form class="dropdown-item" method="POST" name="logout" action="logout"><input id="navBtn" type="submit" name="logout" value="Logout"/></form>
                            </div>
                        </li>
                        </ul>
                    </div>
                </nav>  
            </div>
            <!--NAV CONTAINER END-->
        </div>
        <!--NAV ROW END-->

        <!--IMAGE HEADER ROW-->
        <div class="row">
            <!--IMAGE HEADER CONTAINER-->
            <div id="headerImgContainer" class="col-md-12 removePadding myShadow">
                    <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="static/happy-people.jpg" class="d-block w-100" alt="..." style="height: 100%">
                            </div>
                            <div class="carousel-item">
                                <img src="static/happy-people-2.jpg" class="d-block w-100" alt="..." style="height: 100%">
                            </div>
                            <div class="carousel-item">
                                <img src="static/happy-people-3.jpg" class="d-block w-100" alt="..." style="height: 100%">
                            </div>
                        </div>
                    </div>
            </div>
            <!--IMAGE HEADER CONTAINER END-->
        </div>
        <!--IMAGE HEADER ROW END-->

        <!--PENDING REQUESTS ROW-->
        <div id="" class="row">
            <!--PENDING REQUESTS CONTAINER-->
            <div id="pendingRequestsContainer" class="col-md-12 removePadding addBorderRadius myShadow">

                <div class="accordion" id="accordionExample">
                    <div class="card">
                        <div class="card-header accordionHeader removeBorderRadius removePadding" id="headingOne">
                        <h2 class="mb-0">
                            <button id="viewMyRequests" class="btn btn-link accordionHeaderBtn expandClickArea" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            View All Employees
                            </button>
                        </h2>
                        </div>
                        <!--PENDING REQUESTS TABLE-->
                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body acordionContent removePadding">
                                <div class="table-responsive removeMargin">
                                    <table id="viewAllEmployees" class="table table-hover tableBg">
                                        <thead>
                                            <tr>
                                                <th class="tableHeaderRowBorder">ID#</th>
                                                <th class="tableHeaderRowBorder">First Name</th>
                                                <th class="tableHeaderRowBorder">Last Name</th>
                                                <th class="tableHeaderRowBorder">User Name</th>
                                                <th class="tableHeaderRowBorder">Email</th>
                                            </tr>
                                        </thead>
                                        <tbody id="resolvedRequestsTbody">
                                         <!-- TABLE POPULATED BY JS -->   
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header accordionHeader removeBorderRadius removePadding" id="headingTwo">
                        <h2 class="mb-0">
                            <button class="btn btn-link collapsed accordionHeaderBtn expandClickArea" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            View All Pending Requests
                            </button>
                        </h2>
                        </div>
                        <!--RESOLVED REQUESTS TABLE-->
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body acordionContent removePadding">

                            <div class="table-responsive removeMargin">
                                <table id="viewAllPendingRequests" class="table table-hover tableBg">
                                    <thead>
                                        <tr>
                                            <th class="tableHeaderRowBorder">Request ID#</th>
                                            <th class="tableHeaderRowBorder">Employee ID#</th>
                                            <th class="tableHeaderRowBorder">Type</th>
                                            <th class="tableHeaderRowBorder">Description</th>
                                            <th class="tableHeaderRowBorder">Amount</th>
                                            <th class="tableHeaderRowBorder">Status</th>
                                            <th class="tableHeaderRowBorder">Submitted</th>  
                                        </tr>
                                    </thead>
                                    <tbody>
                                     <!-- TABLE POPULATED BY JS -->    
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header accordionHeader removeBorderRadius removePadding" id="headingThree">
                        <h2 class="mb-0">
                            <button class="btn btn-link collapsed accordionHeaderBtn expandClickArea" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            View All Resolved Requests
                            </button>
                        </h2>
                        </div>
                        <!--SUBMIT REQUEST FORM-->
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                        <div class="card-body acordionContent removePadding">

                            <div class="table-responsive removeMargin">
                               <table id="viewAllResolvedRequests" class="table table-hover tableBg">
                                   <thead>
                                       <tr>
                                           <th class="tableHeaderRowBorder">ID#</th>
                                           <th class="tableHeaderRowBorder">Type</th>
                                           <th class="tableHeaderRowBorder">Description</th>
                                           <th class="tableHeaderRowBorder">Amount</th>
                                           <th class="tableHeaderRowBorder">Status</th>
                                           <th class="tableHeaderRowBorder">Resolved</th>
                                           <th class="tableHeaderRowBorder">Manager ID#</th>
                                       </tr>
                                   </thead>
                                   <tbody id="resolvedRequestsTbody">
                                    <!-- TABLE POPULATED BY JS -->   
                                   </tbody>
                               </table>
                           </div>

                        </div>
                        </div>
                    </div>
                </div>

            </div>
            <!--PENDING REQUESTS CONTAINER END-->
        </div>
        <!--PENDING REQUESTS ROW END-->

        <!--FOOTER ROW-->
        <div class="row">
            <!--FOOTER CONTAINER-->
            <footer id="footerContainer" class="col-md-12 myShadow">
                &copy; All rights reserved - Good Bank&trade;
            </footer>
            <!--FOOTER CONTAINER END-->
        </div>
        <!--FOOTER ROW END-->
        
        <!-- MODAL BOOTSTRAP FOR RESOLUTION FORM -->
        <!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-xl" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Resolution Form</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form name="resolverequest" method="POST" action="resolverequest">
		        
		   
		        	<div class="form-group">
                        <select id="selectRequest" name="requestid" class="custom-select addInputBorder" size="5" required>
                            <option value="Undefined" disabled selected>Select A Request</option>
                        </select>
                        <small id="requestidHelp" class="form-text text-muted">Please select the request ID you want to resolve.</small>
                    </div>
              
                
	  				<div class="form-group">
                        <select name="decision" class="custom-select addInputBorder" required>
                            <option value="PENDING" disabled selected>Select A Decision</option>
                            <option value="APPROVED">Approve</option>
                            <option value="DECLINED">Decline</option>
                        </select>
                        <small id="typeHelp" class="form-text text-muted">Please select the type of your expense.</small>
                    </div>
                  
                  
                    <button type="submit" id="editProfileBtn" class="btn btn-primary">Submit</button>
                </form>
		      </div>
		    </div>
		  </div>
		</div>

		<!-- MODAL BOOTSTRAP FOR VIEW A EMPLOYEE REQUEST -->
        <!-- Modal -->
		<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-xl" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Find Employee Requests</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
				   <div class="table-responsive removeMargin">
					<table id="myTable" class="table table-hover tableBg">
					  <tr class="header">
					    <th>Req ID</th>
					    <th>Emp ID</th>
					    <th>Type</th>
					    <th>Amount</th>
					    <th>Status</th>
					    <th>Submitted</th>
					    <th>Resolved</th>
					    <th>Manager ID</th>
					  </tr>
					</table>
					</div>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- MODAL BOOTSTRAP FOR VIEW A EMPLOYEE REQUEST -->
        <!-- Modal -->
		<div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Create Employee Account</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form name="createemployee" method="POST" action="createemployee">
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="username" placeholder="Enter a username" required>
	  					<small id="usernameHelp" class="form-text text-muted">Username</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="password" name="password" placeholder="Enter a password">
	  					<small id="passwordHelp" class="form-text text-muted">Password</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="firstname" placeholder="First Name" required>
	  					<small id="firstnameHelp" class="form-text text-muted">First Name</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="lastname" placeholder="Last Name" required>
	  					<small id="lastnameHelp" class="form-text text-muted">Last Name</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="email" name="email" placeholder="Enter an email" required>
	  					<small id="emailHelp" class="form-text text-muted">Email</small>
	  				</div>
	  				<div class="form-group">
                        <select name="manager" class="custom-select addInputBorder" required>
                            <option value="N" selected>NO</option>
                            <option value="Y">YES</option>
                        </select>
                        <small id="managerHelp" class="form-text text-muted">Is this employee a manager?</small>
                    </div>
                    <button type="submit" id="editProfileBtn" class="btn btn-primary">Submit</button>
                </form>
		      </div>
		    </div>
		  </div>
		</div>

    </div>
    <!--WEB CONTENT CONTAINER END-->
</div>

<script>

function myFunction() {
	  // Declare variables 
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}

window.onload = () => {
	
		//AJAX request for viewing all employees
		
		var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				let jsX = JSON.parse(x.responseText);
				console.log(jsX);
				for(var i = 0; i < jsX.length; i++){
					$("#viewAllEmployees").find('tbody').append( "<tr><td class=\"tableRowBorder\">" + jsX[i].empId + "</td><td class=\"tableRowBorder\">" +
							jsX[i].firstname + "</td><td class=\"tableRowBorder\">" + jsX[i].lastname + "</td><td class=\"tableRowBorder\">" +
							jsX[i].username + "</td> <td class=\"tableRowBorder\"> " + jsX[i].email + "</td></tr>" );
				}
	
			}
		};
		x.open("get","http://localhost:8080/ERSProject1/viewallemployees");
		x.send();
	
		//AJAX request for viewing all pending requests
		
 		var y = new XMLHttpRequest(); 
		y.onreadystatechange = () => { 
			//comment the if condition and try to print all different readyStates
			if((y.readyState == 4) && (y.status ==200)){
				let jsY = JSON.parse(y.responseText);
				console.log(jsY);
				for(var i = 0; i < jsY.length; i++){
					$("#viewAllPendingRequests").find('tbody').append( "<tr><td class=\"tableRowBorder\">" + jsY[i].requestId + "</td><td class=\"tableRowBorder\">" + jsY[i].employeeId + "</td><td class=\"tableRowBorder\">" +
							jsY[i].requestType + "</td><td class=\"tableRowBorder\">" + jsY[i].desc + "</td><td class=\"tableRowBorder\"> $" +
							jsY[i].amount + "</td> <td class=\"tableRowBorder\"> " + jsY[i].status + "</td><td class=\"tableRowBorder\">" +
							jsY[i].dateSubmitted + "</td></tr>" );
					
					$("#selectRequest").append( "<option value=\""+ jsY[i].requestId +"\"> REQ ID: " + jsY[i].requestId + " --- EMP ID: " + jsY[i].employeeId + " --- TYPE: " +
							jsY[i].requestType + " --- AMOUNT: $" + jsY[i].amount + " --- STATUS: " + jsY[i].status + " --- SUBMITTED: " +
							jsY[i].dateSubmitted + "</option>" );
				}
			}
		};
		y.open("get","http://localhost:8080/ERSProject1/viewallpendingrequests");
		y.send(); 
		
		//AJAX request for view all resolved requests
		
 		var z = new XMLHttpRequest(); 
		z.onreadystatechange = () => { 
			//comment the if condition and try to print all different readyStates
			if((z.readyState == 4) && (z.status ==200)){
				let jsZ = JSON.parse(z.responseText);
				console.log(jsZ);
				for(var i = 0; i < jsZ.length; i++){
					$("#viewAllResolvedRequests").find('tbody').append( "<tr><td class=\"tableRowBorder\">" + jsZ[i].requestId + "</td><td class=\"tableRowBorder\">" +
							jsZ[i].requestType + "</td><td class=\"tableRowBorder\">" + jsZ[i].desc + "</td><td class=\"tableRowBorder\"> $" +
							jsZ[i].amount + "</td> <td class=\"tableRowBorder\"> " + jsZ[i].status + "</td><td class=\"tableRowBorder\">" +
							jsZ[i].dateResolved + "</td><td class=\"tableRowBorder\">" + jsZ[i].resolvedBy + "</td></tr>" );
	
				}
			}
		};
		z.open("get","http://localhost:8080/ERSProject1/viewallresolvedrequests");
		z.send();
		
		
		var w = new XMLHttpRequest(); 
		w.onreadystatechange = () => { 
			//comment the if condition and try to print all different readyStates
			if((w.readyState == 4) && (w.status ==200)){
				let jsW = JSON.parse(w.responseText);
				console.log(jsW);
				for(var i = 0; i < jsW.length; i++){
					var emplId;
					if(jsW[i].employeeId<10){
						emplId = "0" + jsW[i].employeeId;
					}else{
						emplId = jsW[i].employeeId;
					}
					
					$("#myTable").append( "<tr><td class=\"tableRowBorder\">" + jsW[i].requestId + "</td><td class=\"tableRowBorder\">" + emplId + "</td><td class=\"tableRowBorder\">" +
							jsW[i].requestType + "</td><td class=\"tableRowBorder\"> $" +
							jsW[i].amount + "</td><td class=\"tableRowBorder\"> " + jsW[i].status + "</td><td class=\"tableRowBorder\">" + jsW[i].dateSubmitted + "</td><td class=\"tableRowBorder\">" +
							jsW[i].dateResolved + "</td><td class=\"tableRowBorder\">" + jsW[i].resolvedBy + "</td></tr>" );
				}
			}
		};
		w.open("get","http://localhost:8080/ERSProject1/viewallrequests");
		w.send();

		
}

</script>

</body>
</html>
