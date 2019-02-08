<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home - Good Bank</title>
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
<%@ page import="com.revature.model.Login, com.revature.model.Employee" %>
<% 
Login login = (Login) session.getAttribute("user"); 
Employee employeeDetails = (Employee) session.getAttribute("currentUser");


HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
if (session.getAttribute("user") == null) {                               
                 response.sendRedirect("/ERSProject1/");
                 return;
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
                <nav id="navBar" class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand makeLinkWhite" href="#"><span id="companyTitle">Good Bank</span>&trade;</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                        <li class="nav-item active">
                            <form method="POST" action="home" class="nav-link makeLinkWhite linkHoverColorChange"><input id="navMainBtn" type="submit" name="homeBtn" value="Home"/> <span class="sr-only">(current)</span></form>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle makeLinkWhite" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span id="loggedInUsername"><% out.println("Welcome, " + employeeDetails.getFirstname() + " " + employeeDetails.getLastname()); %></span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button class="dropdown-item navBtnPadding" id="navBtn" data-toggle="modal" data-target="#exampleModal">Edit Profile</button>
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
            <div id="headerImgContainer" class="col-md-12 removePadding">
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
            <div id="pendingRequestsContainer" class="col-md-12 removePadding addBorderRadius">

                <div class="accordion" id="accordionExample">
                    <div class="card">
                        <div class="card-header accordionHeader removeBorderRadius removePadding" id="headingOne">
                        <h2 class="mb-0">
                            <button id="viewMyRequests" class="btn btn-link accordionHeaderBtn expandClickArea" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            Resolved Expense Requests
                            </button>
                        </h2>
                        </div>
                        <!--PENDING REQUESTS TABLE-->
                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body acordionContent removePadding">
                                <div class="table-responsive removeMargin">
                                    <table id="resolvedRequestsTable" class="table table-hover tableBg">
                                        <thead>
                                            <tr>
                                                <th class="tableHeaderRowBorder">ID#</th>
                                                <th class="tableHeaderRowBorder">Type</th>
                                                <th class="tableHeaderRowBorder">Description</th>
                                                <th class="tableHeaderRowBorder">Amount</th>
                                                <th class="tableHeaderRowBorder">Status</th>
                                                <th class="tableHeaderRowBorder">Resolve Date</th>  
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
                            Pending Expense Requests
                            </button>
                        </h2>
                        </div>
                        <!--RESOLVED REQUESTS TABLE-->
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body acordionContent removePadding">

                            <div class="table-responsive removeMargin">
                                <table id="unresolvedRequestsTable" class="table table-hover tableBg">
                                    <thead>
                                        <tr>
                                            <th class="tableHeaderRowBorder">ID#</th>
                                            <th class="tableHeaderRowBorder">Type</th>
                                            <th class="tableHeaderRowBorder">Description</th>
                                            <th class="tableHeaderRowBorder">Amount</th>
                                            <th class="tableHeaderRowBorder">Status</th>
                                            <th class="tableHeaderRowBorder">Submit Date</th>  
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
                            Submit A Request
                            </button>
                        </h2>
                        </div>
                        <!--SUBMIT REQUEST FORM-->
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                        <div class="card-body acordionContent">

                            <form name="expensesForm" method="POST" action="addexpense">
                                <div class="form-group">
                                    <select name="type" class="custom-select addInputBorder" required>
                                        <option value="Undefined" selected>Type of Expense</option>
                                        <option value="Travel">Travel</option>
                                        <option value="Relocation">Relocation</option>
                                        <option value="Sales">Sales</option>
                                        <option value="Comapny">Company</option>
                                        <option value="Certification">Certification</option>
                                    </select>
                                    <small id="typeHelp" class="form-text text-muted">Please select the type of your expense.</small>
                                </div>

                                <div class="form-group">
                                    <input name="description" type="text" class="form-control addInputBorder" placeholder="Enter short description of the nature of your expense..." maxlength="100" required>
                                	<small id="descHelp" class="form-text text-muted">Provide a brief description of the nature of your expense.</small>
                                </div>

                                <div class="form-group">
                                    <input type="number" class="form-control addInputBorder" name="amount" min="1.00" max="500.00" step="0.01" placeholder="1.00" required>
                                    <small id="amountHelp" class="form-text text-muted">Enter the amount of your expense. Please adhere to the 00.00 format. You may not enter an amount higher than 500.00 USD.</small>
                                </div>
                                <button type="submit" id="expenseRequestBtn" class="btn btn-primary">Submit</button>
                            </form>

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
            <footer id="footerContainer" class="col-md-12">
                &copy; All rights reserved - Good Bank&trade;
            </footer>
            <!--FOOTER CONTAINER END-->
        </div>
        <!--FOOTER ROW END-->
        
        <!-- MODAL BOOTSTRAP FOR EDIT PROFILE -->
        <!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form name="editprofileForm" method="POST" action="editprofile">
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="employeeId" value="<% out.println(employeeDetails.getEmpId()); %>" disabled>
	  					<small id="idHelp" class="form-text text-muted">Employee ID</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="username" value="<% out.println(employeeDetails.getUsername()); %>" required>
	  					<small id="usernameHelp" class="form-text text-muted">Username</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="password" name="password" value="<% out.println(login.getPassword());%>" placeholder="Enter new password">
	  					<small id="passwordHelp" class="form-text text-muted">Password</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="firstname" value="<% out.println(employeeDetails.getFirstname()); %>" required>
	  					<small id="firstnameHelp" class="form-text text-muted">First Name</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="lastname" value="<% out.println(employeeDetails.getLastname()); %>" required>
	  					<small id="lastnameHelp" class="form-text text-muted">Last Name</small>
	  				</div>
	  				<div class="form-group">
	  					<input class="form-control addInputBorder" type="text" name="email" value="<% out.println(employeeDetails.getEmail()); %>" required>
	  					<small id="emailHelp" class="form-text text-muted">Email</small>
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

window.onload = () => {
	 
		//AJAX request for resolved requests
		
		var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				let js = JSON.parse(x.responseText);
				console.log(js);
				for(var i = 0; i < js.length; i++){
					$("#resolvedRequestsTable").find('tbody').append( "<tr><td class=\"tableRowBorder\">" + js[i].requestId + "</td><td class=\"tableRowBorder\">" +
							js[i].requestType + "</td><td class=\"tableRowBorder\">" + js[i].desc + "</td><td class=\"tableRowBorder\"> $" +
							js[i].amount + "</td> <td class=\"tableRowBorder\"> " + js[i].status + "</td><td class=\"tableRowBorder\">" +
							js[i].dateResolved + "</td></tr>" );
				}
				for(var p in js){
					console.log(js[p].requestId + " " + js[p].requestType + " " + js[p].desc + " " + js[p].amount + " " + js[p].status + " " + js[p].dateResolved);
				}
			}
		};
		x.open("get","http://localhost:8080/ERSProject1/getemployeeresolved");
		x.send();
	
		//AJAX request for unresolved requests
		
 		var y = new XMLHttpRequest(); 
		y.onreadystatechange = () => { 
			//comment the if condition and try to print all different readyStates
			if((y.readyState == 4) && (y.status ==200)){
				let jsY = JSON.parse(y.responseText);
				console.log(jsY);
				for(var i = 0; i < jsY.length; i++){
					$("#unresolvedRequestsTable").find('tbody').append( "<tr><td class=\"tableRowBorder\">" + jsY[i].requestId + "</td><td class=\"tableRowBorder\">" +
							jsY[i].requestType + "</td><td class=\"tableRowBorder\">" + jsY[i].desc + "</td><td class=\"tableRowBorder\"> $" +
							jsY[i].amount + "</td> <td class=\"tableRowBorder\"> " + jsY[i].status + "</td><td class=\"tableRowBorder\">" +
							jsY[i].dateSubmitted + "</td></tr>" );
				}
			}
		};
		y.open("get","http://localhost:8080/ERSProject1/getemployeeunresolved");
		y.send(); 
		
}

</script>

</body>
</html>
