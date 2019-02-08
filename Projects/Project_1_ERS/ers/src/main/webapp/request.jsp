<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<title>Submit a Request</title>
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
		<form class=" z-depth-4 col s12 container" action="SubmitRequest"
			method="POST"
			style="width: 55%; margin-top: 30px; padding: 30px; display: inline-block;">

			<h4 class="row">Submit a Request</h4>
			<div class="row">
				<div class="input-field col m5">
					<input name ="amount" type="number" min="0.01" step="0.01" max="2500"
						placeholder="Amount" required />
				</div>
				<div class="input-field col m5">
					<select name="category" class="browser-default" required>
						<option value="" disabled selected>Category...</option>
						<option value="Certification">Certification</option>
						<option value="Relocation">Relocation</option>
						<option value="Other">Other</option>
					</select>

				</div>
			</div>
			
			<div class="row">
				<div class="input-field col s12">
					<textarea id="textarea2" class="materialize-textarea"
						data-length="120" name = "comments"></textarea>
					<label for="textarea2">Addtional Comments</label>
				</div>
			</div>


			<input class="btn" type="submit" name="submit" value="Submit"
				style="background-color: #4075b9;">
		</form>
	</div>
	<div class = "row">
		<a class="button" href="http://localhost:8080/ers">
  			<div class="logout">home</div>
		</a>
		</div>

</body>
</html>