<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Welcome home!</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style>
body {
	background-color: #6C5B7B;
}
</style>



</head>

<div class="mt-5"></div>
<div class="jumbotron container">
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


	<div class="row">
		<div class="col-12">
			<h2>
				Welcome,
				<%=session.getAttribute("username")%></h2>
		</div>
	</div>

	<table class="table table-bordered table-striped">
		<tbody id="myTable">

		</tbody>
	</table>

	<form action="/ERS/login" method="POST">

		<input
			type="submit" value="edit profile" name="button"
			class="btn btn-primary">

	</form>
</div>
<script>
	
		window.onload = function() {

			var x = new XMLHttpRequest();
			x.onreadystatechange = () => {
				//comment the if condition and try to print all different readyStates
				if((x.readyState == 4) && (x.status ==200)){
					//console.log(JSON.parse(x.responseText));
					data = JSON.parse(x.responseText);
					console.log(data)
					row = document.createElement("tr");
					idcol = document.createElement("td");
					infocol = document.createElement("td");
					
					idcol.textContent = "Username:";
					infocol.textContent = data.username;
					
					row.appendChild(idcol);
					row.appendChild(infocol);
					document.getElementById("myTable").appendChild(row);
					
					row = document.createElement("tr");
					idcol = document.createElement("td");
					infocol = document.createElement("td");
					
					idcol.textContent = "Email:";
					infocol.textContent = data.email;
					
					row.appendChild(idcol);
					row.appendChild(infocol);
					document.getElementById("myTable").appendChild(row);
					
					if(data.superuser == 0){
						row = document.createElement("tr");
						idcol = document.createElement("td");
						infocol = document.createElement("td");
						
						idcol.textContent = "Position:";
						infocol.textContent = "employee";
						
						row.appendChild(idcol);
						row.appendChild(infocol);
						document.getElementById("myTable").appendChild(row);
						
					}else{
						row = document.createElement("tr");
						idcol = document.createElement("td");
						infocol = document.createElement("td");
						
						idcol.textContent = "Position:";
						infocol.textContent = "manager";
						
						row.appendChild(idcol);
						row.appendChild(infocol);
						document.getElementById("myTable").appendChild(row);
					}
					
				}
			};
			var session ='<%=session.getAttribute("username")%>';
			x.open("get","http://"+location.host+"/ERS/login/userinfo/");
			x.send();
		}
	</script>
</body>

</html>