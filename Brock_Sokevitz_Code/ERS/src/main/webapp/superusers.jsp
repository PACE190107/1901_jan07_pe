<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Welcome home!</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=.9">
	<style>
body {
	background-color: #6C5B7B;
}
</style>
</head>
	<div class="mt-5"></div>	
	<div class="jumbotron container">
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
	
		<div class="row">
			<div class="col-8">
				<h2>
					Welcome,
					<%=session.getAttribute("username")%></h2>
			</div>
		</div>
		<table class="table table-bordered table-striped">
			<tbody id="myTable">
				<th>username</th>
				<th>email</th>
			</tbody>
		</table>

		<form action="/ERS/login" method="POST">

			<label for="select user">username:</label> <input
				type="text" name="username"> <br>
			<input type="submit" value="view reimbursements" name="button"
				class="btn btn-primary">

		</form>

	</div>

	<script>

	window.onload = function() {
		
		var data;
		var x = new XMLHttpRequest();
		x.onreadystatechange = () => {
			//comment the if condition and try to print all different readyStates
			if((x.readyState == 4) && (x.status ==200)){
				console.log(JSON.parse(x.responseText));
				data = JSON.parse(x.responseText);
				for (i = 0; i < data.length; i++) {
			
					row = document.createElement("tr");
					usercol = document.createElement("td");
					emailcol = document.createElement("td");
					
					usercol.textContent = data[i].username;
					emailcol.textContent = data[i].email

					row.appendChild(usercol);
					row.appendChild(emailcol);
					document.getElementById("myTable").appendChild(row);
				} 
				
			}
		};
		x.open("get","http://"+location.host+"/ERS/login/users");
		x.send();
	}
	</script>
</body>

</html>