<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	try
					{
						int manager = Integer.parseInt(session.getAttribute("manager").toString());
						if (manager < 1)
							{
								response.sendRedirect("employeePage.jsp");
							} else if (session.getAttribute("manager") == null)
							{
								//response.sendRedirect("index.jsp");
							}
					} catch (NullPointerException e)
					{
						//response.sendRedirect("index.jsp");
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
<title>Register New Employee</title>
</head>
<body style="text-align: center; margin: 0;">
	<div class="navbar-fixed">
		<nav style="background-color: #4075b9" class="z-depth-4">
			<div class="nav-wrapper">

				<ul name="nav-mobile" class="left hide-on-med-and-down">
					<li>BrownZeus Inc. Employee Portal</li>
				</ul>
			</div>
		</nav>
	</div>

	<div style="margin-bottom: 50px; margin-top: 30px; padding: 0;">
		<form class=" z-depth-4 col s12 container" action="RegistrationService"
			method="POST"
			style="width: 55%; margin-top: 30px; padding: 30px; display: inline-block;">

			<h4 class="row">Register New Employee</h4>
			<div class="row">
				<div class="input-field col m5">
					<input type="text" name="fname" placeholder="First Name"
						class="validate" required>
				</div>
				<div class="input-field col m5">
					<input type="text" name="lname" placeholder="Last Name"
						class="validate" required>
				</div>
			</div>
			<div class="row">

				<div class="input-field col m5">
					<input type="text" name="username" placeholder="Username"
						class="validate" required>
				</div>
				<div class="input-field col m5">
					<input type="password" name="password" placeholder="Password"
						class="validate" required>
				</div>

			</div>
			<div class="row">
				<p>
					<label> <input type="checkbox" name = "isman" value = "1"/> <span>Is this person a manager?</span>
					</label>
				</p>
			</div>
	</div>

	<input class="btn" type="submit" name="Review Order" value="Register"
		style="background-color: #4075b9;">
	</form>
	<div class = "row">
		<a class="button" href="http://localhost:8080/ers">
  			<div class="logout">home</div>
		</a>
		</div>
	</div>

</body>
</html>