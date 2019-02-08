<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<title>Insert title here</title>
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

	<h3 style="text-align: center;">Pending Requests</h3>

	<form class=" z-depth-4 col s12 container" action="ProcessRequest"
		method="POST"
		style="width: 55%; margin-top: 30px; padding: 30px; display: inline-block;">

		<div class="col s12 container">
			<table id="requests">
				<thead>
					<tr>
						<th>Request ID</th>
						<th>Employee ID</th>
						<th>Amount</th>
						<th>Category</th>
					</tr>
				</thead>
				<tbody id="requests">


				</tbody>


			</table>


		</div>
	</form>
	<div class = "row">
		<a class="button" href="http://localhost:8080/ers">
  			<div class="logout">home</div>
		</a>
		</div>

	<script type="text/javascript">
      window.onload = () => {
      loadTable();
      };
      
      function loadTable ()
      {
    	  
            var x = new XMLHttpRequest();
            x.onreadystatechange = () => {
              if (x.readyState == 4 && x.status == 200) {
                let rt = document.getElementById("requests");
               
                var ex = JSON.parse(x.responseText); // JSON -> Object
                console.log(ex);

                for (var i = 0; i < ex.length; i++) {
                	
                	var op = document.createElement("option");
                  var row = document.createElement("tr");//create row
                  var cell1 = document.createElement("td");
                  var cell2 = document.createElement("td");
                  var cell3 = document.createElement("td");
                  var cell4 = document.createElement("td");//create cell
                  var textNode1 = document.createTextNode(ex[i].reqID);//Create text element
                  var textNode2 = document.createTextNode(ex[i].empID);
                  var textNode3 = document.createTextNode(ex[i].amount);
                  var textNode4 = document.createTextNode(ex[i].category);
                  var textNode = document.createTextNode(ex[i].reqID);
                 
                  	
                  	cell1.appendChild(textNode1);
                  	cell2.appendChild(textNode2);
                  	cell3.appendChild(textNode3);
                  	cell4.appendChild(textNode4);
					row.appendChild(cell1);
					row.appendChild(cell2);
					row.appendChild(cell3);
					row.appendChild(cell4);
                  rt.appendChild(row);
                }
              }
            };
            x.open("get", "http://localhost:8080/ers/MyPending");
            x.send();
      }
    </script>
</body>
</html>