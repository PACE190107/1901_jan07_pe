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
body {
	background-color: #6C5B7B;
}
</style>



</head>

<div class="mt-5"></div>

<div class="jumbotron container">

<div class="btn-toolbar  d-flex" style="width:100%">
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
			<th>ID</th>
			<th>amount</th>
			<th>status</th>
		</tbody>
	</table>
<div class="form-group">
	<form action="/ERS/login" method="POST" id="myForm">
	<div>
<label for="amount">Reimbursement Amount</label> <input type="number"
			name="amount" min="1" step="0.01" class="form-control">  
			</div> 
			<p id="invalid" style="color:red"></p>    
	<div>
<input
			type="submit" value="submit reimbursement" name="button"
			class="btn btn-primary" id="submit">
	</div>
	</form>
	</div>
</div>
<script>
	
		window.onload = function() {				

			var myButton = document.getElementById('submit');
			var myForm = document.getElementById('myForm');
			myButton.addEventListener('click', function (event) {
			    var allInputs = myForm.getElementsByTagName('input');

			    for (var i = 0; i < allInputs.length; i++) {
			        var input = allInputs[i];

			        if (input.name && !input.value) {
			        	event.preventDefault();
			        	document.getElementById('invalid').innerHTML = "Empty input.";
			        }
			    }
			});
			
			console.log('<%=session.getAttribute("username")%>');
			
			var x = new XMLHttpRequest();
			x.onreadystatechange = () => {
				//comment the if condition and try to print all different readyStates
				if((x.readyState == 4) && (x.status ==200)){
					//console.log(JSON.parse(x.responseText));
					data = JSON.parse(x.responseText);
					for (i = 0; i < data.length; i++) {

						row = document.createElement("tr");
						idcol = document.createElement("td");
						amountcol = document.createElement("td");
						statuscol = document.createElement("td");

						//console.log(data);
						console.log(data[0].amount);
						idcol.textContent = data[i].reimbursement_id;
						amountcol.textContent = Number(data[i].amount).toFixed(2);
						statuscol.textContent = data[i].status;

						row.appendChild(idcol);
						row.appendChild(amountcol);
						row.appendChild(statuscol);
						document.getElementById("myTable")
								.appendChild(row);
					} 
					
				}
			};
			x.open("get","http://"+location.host+"/ERS/login/reimbursements/username");
			x.send();
		}
	</script>
</body>

</html>