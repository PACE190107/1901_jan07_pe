<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	try
					{
						int manager = Integer.parseInt(session.getAttribute("manager").toString());
						if (manager < 1)
							{
								response.sendRedirect("managerPage.jsp");
							} else if (session.getAttribute("manager") == null)
							{
								response.sendRedirect("index.jsp");
							}
					} catch (NullPointerException e)
					{
						response.sendRedirect("index.jsp");
					}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<title>Manager Portal</title>
</head>
<body>

	<div class="navbar-fixed">
		<nav style="background-color: #4075b9" class="z-depth-4">
			<div class="nav-wrapper">
				<ul name="nav-mobile" class="left hide-on-med-and-down">
					<li>BrownZeus Inc. Employee Portal</li>
				</ul>
			</div>
		</nav>
	</div>
	<br>
	
	<br>
	<br>
	<br>
	
	<div style="margin:10%;" class="row">
	<h4>Welcome <% out.print(session.getAttribute("name") + " (" + session.getAttribute("id") +")"); %></h4>
		<div class="col s12 ">
			<div class="row">
				<div class="col s12 m3 ">
					<div class="card darken-1 z-depth-4">
						<div class="card-content">
							<span class="card-title">Register a new Employee</span>
						</div>
						<div class="card-action">
							<a href="register.jsp">Register</a>
						</div>
					</div>
				</div>
				<div class="col s12 m3 ">
					<div class="card darken-1 z-depth-4">
						<div class="card-content">
							<span class="card-title">View Pending Requests</span>
						</div>
						<div class="card-action">
							<a href="ad.jsp">Pending</a>
						</div>
					</div>
				</div>
				<div class="col s12 m3 ">
					<div class="card darken-1 z-depth-4">
						<div class="card-content">
							<span class="card-title">View Resolved Requests</span>
						</div>
						<div class="card-action">
							<a href="allresolved.jsp">Pending</a>
						</div>
					</div>
				</div>
				<div class="col s12 m3 ">
					<div class="card darken-1 z-depth-4">
						<div class="card-content">
							<span class="card-title">Make a request</span>
						</div>
						<div class="card-action">
							<a href="request.jsp">Create!</a>
						</div>
					</div>
				</div>
			</div>

		</div>
		
		
		<div class = "row">
		<a class="button" href="LogoutService">
  			<div class="logout">LOGOUT</div>
		</a>
		</div>
		
		
		
	</div>
	
</body>
</html>