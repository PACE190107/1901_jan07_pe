<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Edit User</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<style>
body {
	background-color: #6C5B7B;
}
p{
color: red;
}
</style>
</head>
<body>

	<div class="mt-5"></div>

	<div class="container col-lg-4 offset-lg-4 jumbotron">
	
	<div class="btn-toolbar" style="width:100%">
<form action="/ERS/login" method="POST">
						<input type="submit" value="Home" name="button"
							class="list-group-item">
					</form>
					
									<form action="/ERS/login" method="POST">
						<input type="submit" value="Profile" name="button"
							class="list-group-item">
					</form>	
					
										<form action="/ERS/login" method="POST">
						<input type="submit" value="logout" name="button"
							class="list-group-item">
					</form>
</div>
	
		<form action="/ERS/login" method="POST">
			<div class="form-group centered">
				<label for="email">Email </label> <input type="email" name="email" id="email"
					class="form-control">
			</div>
			<div class="form-group centered">
				<label for="username">Username</label> <input type="text"
					name="username" id="username" class="form-control">
			</div>
			<div class="form-group centered">
				<label for="passwords">New Password</label> <input type="password"
					name="new password" class="form-control">
			</div>
						<div class="form-group centered">
				<label for="passwords">Current Password</label> <input type="password"
					name="password" class="form-control">
					<p id="info" style="color:red"></p>
			</div>
			<div>
				<input type="submit" value="submit edit" name="button"
					class="btn btn-primary">
			</div>
		</form>
	</div>
	
	<script>
	
 		window.onload = function() {

 			var info ='<%=session.getAttribute("info")%>';
 			
 			document.getElementById("info").innerHTML = info;
 			
			var x = new XMLHttpRequest();
			x.onreadystatechange = () => {
				//comment the if condition and try to print all different readyStates
				if((x.readyState == 4) && (x.status ==200)){
					//console.log(JSON.parse(x.responseText));
					data = JSON.parse(x.responseText);
					console.log(data)

					document.getElementById("username").setAttribute('value', data.username);					
					document.getElementById("email").setAttribute('value', data.email);
				}
			};
			var session ='<%=session.getAttribute("username")%>';
			x.open("get","http://"+location.host+"/ERS/login/userinfo/"+session);
			x.send();
		} 
	</script>
</body>
</html>