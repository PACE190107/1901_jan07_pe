<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Register</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<style>
body {
	background-color: #6C5B7B;
}
</style>
<body>

	<div class="mt-5"></div>

	<div class="container jumbotron">

	<div class="btn-toolbar d-flex" style="width:100%">
	<form action="/ERS/login" method="POST">
						<input type="submit" value="Home" name="button"
							class="list-group-item">
					</form>
					
									<form action="/ERS/login" method="POST">
						<input type="submit" value="Profile" name="button"
							class="list-group-item">
					</form>
					
					<form action="/ERS/login" method="POST">
						<input type="submit" value="resolved" name="button"
							class="list-group-item">
					</form>
					
														<form action="/ERS/login" method="POST">
						<input type="submit" value="users" name="button"
							class="list-group-item">
					</form>	
										<form action="/ERS/login" method="POST">
						<input type="submit" value="register user" name="button"
							class="list-group-item">
					</form>	
					
															<form action="/ERS/login" method="POST">
						<input type="submit" value="logout" name="button"
							class="list-group-item">
					</form>
</div>
		
		<form action="/ERS/login" method="POST">
			<div class="form-group centered">
				<label for="email">Email </label> <input type="email" name="email"
					class="form-control">
			</div>
			<div class="form-group centered">
				<label for="username">Username</label> <input type="text"
					name="username" class="form-control">
			</div>
			<div class="form-group centered">
				<label for="passwords">Password</label> <input type="password"
					name="password" class="form-control">
			</div>
			<p id="info" style="color:red"></p>
			<div>
				<input type="submit" value="register submit" name="button"
					class="btn btn-primary"> <input type="submit"
					value="cancel" name="button" class="btn btn-primary">
			</div>
		</form>
	</div>

	<script>
	
 		window.onload = function() {

 			var info ='<%=session.getAttribute("info")%>';
			if (info != "none") {
				document.getElementById("info").innerHTML = info;
			}else{
				document.getElementById("info").innerHTML = "";
			}
		}
	</script>
</body>
</html>