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
}</style>
<body>

	<div class="mt-5"></div>

	<div class="container col-lg-4 offset-lg-4 jumbotron">
		<form action="/ERS/login" method="POST">
			<div class="form-group centered">
				<label for="email">Email </label> <input type="email" name="email" class="form-control" required>
			</div>
			<div class="form-group centered">
				<label for="username">Username</label> <input type="text"
					name="username" class="form-control" required>
			</div>
			<div class="form-group centered">
				<label for="passwords">Password</label> <input type="password"
					name="password" class="form-control" required>
			</div>
			<p id="info" style="color:red">username or email taken.</p>
			<div>
				<input type="submit" value="registerSubmit" name="button"
					class="btn btn-primary"> <input type="submit"
					value="exit" name="button" class="btn btn-primary">
			</div>
		</form>
				<form action="/ERS/login" method="POST">
		<input type="submit"
					value="exit" name="button" class="btn btn-primary">
					</form>
	</div>
	
</body>
</html>