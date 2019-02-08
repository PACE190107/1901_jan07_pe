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

	<h3 style="text-align: center;">Approved</h3>

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
						<th>Manager</th>
					</tr>
				</thead>
				<tbody id="approved">


				</tbody>


			</table>

		</div>
	</form>
	
	<h3 style="text-align: center;">Denied</h3>

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
						<th>Manager</th>
					</tr>
				</thead>
				<tbody id="denied">


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
      loadTable1();
      loadTable2();
      };
      
      function loadTable1 ()
      {
    	  
            var x = new XMLHttpRequest();
            x.onreadystatechange = () => {
              if (x.readyState == 4 && x.status == 200) {
                let rt = document.getElementById("approved");
               
                var ex = JSON.parse(x.responseText); // JSON -> Object
                console.log(ex);

                for (var i = 0; i < ex.length; i++) {
                	
                
                  var row = document.createElement("tr");//create row
                  var cell1 = document.createElement("td");
                  var cell2 = document.createElement("td");
                  var cell3 = document.createElement("td");
                  var cell4 = document.createElement("td");//create cell
                  var cell5 = document.createElement("td");
                  var textNode1 = document.createTextNode(ex[i].reqID);//Create text element
                  var textNode2 = document.createTextNode(ex[i].empID);
                  var textNode3 = document.createTextNode(ex[i].amount);
                  var textNode4 = document.createTextNode(ex[i].category);
                  var textNode5 = document.createTextNode(ex[i].manID);
                  
                 
                  	
                  	cell1.appendChild(textNode1);
                  	cell2.appendChild(textNode2);
                  	cell3.appendChild(textNode3);
                  	cell4.appendChild(textNode4);
                  	cell5.appendChild(textNode5);
					row.appendChild(cell1);
					row.appendChild(cell2);
					row.appendChild(cell3);
					row.appendChild(cell4);
					row.appendChild(cell5);
					 
                  rt.appendChild(row);
                }
              }
            };
            x.open("post", "http://localhost:8080/ers/MyApproved");
            x.send();
      }
      
      function loadTable2 ()
      {
    	  
            var y = new XMLHttpRequest();
            y.onreadystatechange = () => {
              if (y.readyState == 4 && y.status == 200) {
                let rt = document.getElementById("denied");
               
                var ex = JSON.parse(y.responseText); // JSON -> Object
                console.log(ex);

                for (var i = 0; i < ex.length; i++) {
                	
                
                  var row = document.createElement("tr");//create row
                  var cell1 = document.createElement("td");
                  var cell2 = document.createElement("td");
                  var cell3 = document.createElement("td");
                  var cell4 = document.createElement("td");//create cell
                  var cell5 = document.createElement("td");
                  var textNode1 = document.createTextNode(ex[i].reqID);//Create text element
                  var textNode2 = document.createTextNode(ex[i].empID);
                  var textNode3 = document.createTextNode(ex[i].amount);
                  var textNode4 = document.createTextNode(ex[i].category);
                  var textNode5 = document.createTextNode(ex[i].manID);
                  
                 
                  	
                  	cell1.appendChild(textNode1);
                  	cell2.appendChild(textNode2);
                  	cell3.appendChild(textNode3);
                  	cell4.appendChild(textNode4);
                  	cell5.appendChild(textNode5);
					row.appendChild(cell1);
					row.appendChild(cell2);
					row.appendChild(cell3);
					row.appendChild(cell4);
					row.appendChild(cell5);
					 
                  rt.appendChild(row);
                }
              }
            };
            y.open("post", "http://localhost:8080/ers/MyDenied");
            y.send();
      }
    </script>
</body>
</html>