<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title id="title">Employee HomePage</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	 crossorigin="anonymous">
</head>

<body>
	<h1 class='col-sm-10 offset-sm-1 text-center' id='welcome'></h1>
	<form action='http://localhost:8080/EmployeeReimbursment/login' method='GET' id='optionArea'>
		<button>
			Logout
		</button>
	</form>
	<div id='displayArea'>

	</div>
	<script>
		window.onload = () => {
			document.getElementById('displayArea').innerHTML = '';

			if (sessionStorage.getItem('id') == -1) {
				window.location.href = 'login.jsp';
			}

			employee = {
				user: `<%= session.getAttribute("username") %>`,
				u_id: <%= session.getAttribute("id") %>,
				firstname: `<%= session.getAttribute("firstname") %>`,
				lastname: `<%= session.getAttribute("lastname") %>`,
				manager: <%= session.getAttribute("manager") %>,
				email: `<%= session.getAttribute("email") %>`
			}

			document.getElementById('welcome').innerHTML = 'Welcome, ' + employee.firstname + " " + employee.lastname;

			console.log(employee.user);
			console.log(employee.u_id);
			console.log(employee.firstname);
			console.log(employee.lastname);
			console.log(employee.manager);
			console.log(employee.email);

			if (<%= session.getAttribute("manager") %> == true) {
				console.log('is a manager');
				createManPage();
			} else {
				createEmpPage();
			}
		}

		createEmpPage = () => {
			console.log("id : " + employee.u_id);
			let id = <%= session.getAttribute("id") %>;
			document.getElementById('optionArea').innerHTML =
				`
				<div class='container'>
				<nav class="btn-group">
    <button class='btn-info' type='button' onclick='viewRequest(${id})'>View all Requests </button>
    <button class='btn-info' type='button' onclick='viewRequest(-1)'>View resolved Requests</button>
    <button class='btn-info' type='button' onclick='viewRequest(-2)'>View pending Requests</button>
    <button class='btn-info' type='button' onclick='submitPage()'>Submit new request</button>
    <button class='btn-info' type='button' onclick='viewInfo()'>View Information</button>
    <button class='btn-info' type='button' onclick='changeInfo()'>Change Information</button>
	<button class='btn-danger' type='submit' >Logoff</button>
	</nav>
	</div>`;
		}

		viewInfo = () => {
			user = `<%= session.getAttribute("username") %>`;
			u_id = <%= session.getAttribute("id") %>;
			firstname = `<%= session.getAttribute("firstname") %>`;
			lastname = `<%= session.getAttribute("lastname") %>`;
			manager = <%= session.getAttribute("manager") %>;
			email = `<%= session.getAttribute("email") %>`;

			document.getElementById('displayArea').innerHTML =
				`
				<br>
				<br>
					<div class='col-sm-10 offset-sm-4 text-center'>
    <h5 class='col-sm-10 text-left' name="username" id="username">username : ` + user +` </h5>
<h5 class='col-sm-10  text-left'>first name : ${ firstname }</h5>
<h5 class='col-sm-10  text-left'>last name : ${ lastname }</h5>
<h5 class='col-sm-10  text-left'>email : ${ email }</h5>
<h5 class='col-sm-10  text-left'>id : ` +
				u_id + `</h5>
<h5 class='col-sm-10  text-left'>manager, ${ manager }</h5>
</div>
<button class='offset-sm-4 text-left' type='button' onclick='changeInfo()'>Update Info</button>
`;
		}

		changeInfo = () => {
			user = `<%= session.getAttribute("username") %>`;
			u_id = <%= session.getAttribute("id") %>;
			firstname = `<%= session.getAttribute("firstname") %>`;
			lastname = `<%= session.getAttribute("lastname") %>`;
			manager = <%= session.getAttribute("manager") %>;
			email = `<%= session.getAttribute("email") %>`;

			console.log(user);

			document.getElementById('displayArea').innerHTML =
				`<label></label><br><br>
				<div class='col-sm-6 offset-sm-1 text-right'>
    <label for='username'>Current username, ` + user +
				` </label>
    <input id='username' type='text' name='username'>
    <br>
<labelfor='firstname'>Current first name, ${ firstname }</label>
<input id='first' type='text' name='firstname'>
<br>
<labelfor='lastname'>Current last name, ${ lastname }</label>
<input id='last' type='text' name='lastname'>
<br>
<labelfor='email'>Current email, ${ email }</label>
<input id='email' type='text' name='email'>
<br>

<labelfor='password'>New Password</label>
<input id='psswrd' type='password' name='password'>
<br>

<button type='button' onclick='upDate()'>Update</button>
</div>
`;
		}

		upDate = () => {
			let usernm = document.getElementById('username').value;
			let first = document.getElementById('first').value;
			let last = document.getElementById('last').value;
			let mail = document.getElementById('email').value;
			let psswrd = document.getElementById('psswrd').value;

			if (usernm == null || usernm == '') {
				usernm = employee.user;
			}
			if (first == null || first == '') {
				first = employee.firstname;
			}
			if (last == null || last == '') {
				last = employee.lastname;
			}
			if (mail == null || mail == '') {
				mail = employee.email;
			}
			if (psswrd == null || psswrd == '') {
				psswrd = null;
			}
			const body = {
				id: employee.u_id,
				firstName: first,
				lastName: last,
				username: usernm,
				password: psswrd,
				manager: employee.manager,
				email: mail
			}
			console.log(body);
			fetch('http://localhost:8080/EmployeeReimbursment/rest/updateEmployee', {
					method: 'post',
					body: JSON.stringify(body),
					headers: {
						'Content-Type': 'application/json',
						'Access-Control-Allow-Origin': 'http://localhost:8080',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Allow-Methods': 'POST'
					},
				})
				.then(res => success(body))
				.then();
		}

		success = (json) => {
			sessionStorage.setItem("id", json.id);
			sessionStorage.setItem("firstName", json.firstName);
			sessionStorage.setItem("lastName", json.lastName);
			sessionStorage.setItem("username", json.username);
			sessionStorage.setItem("manager", json.manager);
			sessionStorage.setItem("email", json.email);

			window.location.href = 'home.jsp';
		}

		createManPage = function () {
			document.getElementById('optionArea').innerHTML =
				`
				<div class='container'>
				<nav class=" offset-sm-3  btn-group">
    <button class='btn-info' type='button' onclick='viewRequest(0)'>View all Requests</button>
    <button class='btn-info' type='button' onclick='viewRequest(-2)'>View pending Requests</button>
    <button class='btn-info' type='button' onclick='viewEmployees()'>View all employees</button>
	<button class='btn-danger' type='submit' >Logoff</button>
	</nav>
	</div>
    `;
		}

		resol = function (id, type, amount, description) {
			console.log('Resolving error');
			document.getElementById('displayArea').innerHTML =
				`
    <h5>Request ID : ` + id + `</h5> 
    <h5>Request Type : ` + type + ` </h5>
    <h5>Request Amount : ` +
				amount + ` </h5>
    <h5>Description</h5>
    <h5> ` + description + ` </h5>
    <button class='btn-success' onclick='approve(` + id +
				`)'>Approve</button>
    <button class='btn-danger' onclick='deny(` + id + `)'>Deny</button>
    `;
		}

		approve = function (reqid) {

			const body = {
				id: reqid,
				manager: employee.u_id,
				approved: 1
			};


			fetch('http://localhost:8080/EmployeeReimbursment/rest/updateRequests', {
					method: 'post',
					body: JSON.stringify(body),
					headers: {
						'Content-Type': 'application/json',
						'Access-Control-Allow-Origin': 'http://localhost:8080',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Allow-Methods': 'POST'
					},
				})
				.then(response => viewRequest(-2));
		}


		deny = function (reqid) {

			const body = {
				id: reqid,
				manager: employee.u_id,
				approved: 2
			};


			fetch('http://localhost:8080/EmployeeReimbursment/rest/updateRequests', {
					method: 'post',
					body: JSON.stringify(body),
					headers: {
						'Content-Type': 'application/json',
						'Access-Control-Allow-Origin': 'http://localhost:8080',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Allow-Methods': 'POST'
					},
				})
				.then(response => viewRequest(-2));
		}


		viewEmployees = function () {
			document.getElementById('displayArea').innerHTML =
				` <table class="table table-bordered table-striped">
    <tbody id="myTable">
        <th>ID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Username</th>
        <th>Manager</th>
        <th>Email</th>
    </tbody>
</table>`;

			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(xhr.responseText);
					for (i = 0; i < jsonData.length; i++) {
						let row = document.createElement("tr");
						let idcol = document.createElement("td");
						let fcol = document.createElement("td");
						let lcol = document.createElement("td");
						let ucol = document.createElement("td");
						let mcol = document.createElement("td");
						let ecol = document.createElement("td");

						idcol.textContent = jsonData[i].id;
						fcol.textContent = jsonData[i].firstName;
						lcol.textContent = jsonData[i].lastName;
						ucol.textContent = jsonData[i].username;
						mcol.textContent = jsonData[i].manager;
						ecol.textContent = jsonData[i].email;

						row.appendChild(idcol);
						row.appendChild(fcol);
						row.appendChild(lcol);
						row.appendChild(ucol);
						row.appendChild(mcol);
						row.appendChild(ecol);

						document.getElementById("myTable").appendChild(row);
					}
				}
			}

			xhr.open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllUsers');
			xhr.send();
		}
		submitPage = function () {
			document.getElementById('displayArea').innerHTML =
				`
		<br>
		<br>
		<div class='col-sm-6 offset-sm-1 text-right'>
		<form class='offset-sm-2'>
    <label for="reqType">Reinbursment Type</label>
    <select id="reqType" name="reqType">
        <option value="Relocation">Relocation</option>
        <option value="Study">Study Expenses</option>
        <option value="Other">Other</option>
    </select>
    </form>

<label for="reqAmount">Amount</label>
<input id="reqAmount" type="number" min=0 name="reqAmount">
<div>
    <label for="description">Description</label>
    <input id="description" type="text" name="description">
</div>
<div>
    <button type="button" onclick="submitRequest()">Submit</button>
</div>
</div>`;
		}

		submitRequest = function () {
			var reqtype = document.getElementById("reqType");
			var reqamount = document.getElementById("reqAmount");
			var reqdescription = document.getElementById("description");
			if (reqamount.value > 0) {
				console.log("valid inputs \n" +
					"Type " + reqtype.value +
					"\nAmount " + reqamount.value +
					"\nDescription " + reqdescription.value +
					"\nUserID " + employee.u_id);
			}

			const body = {
				type: reqtype.value,
				amount: reqamount.value,
				description: reqdescription.value,
				userId: employee.u_id
			};

			fetch('http://localhost:8080/EmployeeReimbursment/rest/addRequest', {
					method: 'post',
					body: JSON.stringify(body),
					headers: {
						'Content-Type': 'application/json',
						'Access-Control-Allow-Origin': 'http://localhost:8080',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Allow-Methods': 'POST'
					},
				})
				.then(response => viewRequest(employee.u_id));
		}



		viewRequest = function (num) {
			document.getElementById('displayArea').innerHTML =
				`
	<div>
		<br>
		<br>
    <h1 class='col-sm-10 offset-sm-1 text-center' >Requests</h1>
    </h1>
    </div>
    <div>
    <form id='selectForm'>
        
        </form>
    </div>
    <div>
    <table class="table table-bordered table-striped ">
        <tbody id="myTable">
            <th>ID</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Description</th>
            <th>User</th>
            <th>Manager</th>
            <th>Status</th>
        </tbody>
    </table>
    </div>`;
			if (<%= session.getAttribute("manager") %> == true && num == 0) {
				document.getElementById('selectForm').innerHTML =
					`
                    <label for="empReq">Filter</label>
        <select id="empReq" name="empReq" onchange="filter()">
        </select>`;
			}
			populate(num);
		}

		populate = function (num) {
			let xhr = new XMLHttpRequest();

			console.log(num);
			xhr.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(xhr.responseText);
					tableIsPop = false;

					var idset = new Set();
					idset.add('all');

					for (i = 0; i < jsonData.length; i++) {
						addReq = false;
						if ((jsonData[i].userId == num) || <%= session.getAttribute("manager") %> == true || (jsonData[i].userId ==
								employee.u_id)) {
							if (num == 0) {
								addReq = true;
							} else if (num == -1) {
								if (jsonData[i].approved > 0) {
									addReq = true;
								}
							} else if (num == -2) {
								if (jsonData[i].approved == 0) {
									addReq = true;
								}
							} else if (jsonData[i].userId == num) {
								addReq = true;
							}
						}
						if (addReq == true) {
							if (<%= session.getAttribute("manager") %> == true) {
								idset.add(jsonData[i].userId);
							}
							tableIsPop = true;
							console.log(tableIsPop);

							let row = document.createElement("tr");
							let idcol = document.createElement("td");
							let tcol = document.createElement("td");
							let amcol = document.createElement("td");
							let dcol = document.createElement("td");
							let ucol = document.createElement("td");
							let mcol = document.createElement("td");
							let apcol = document.createElement("td");

							idcol.textContent = jsonData[i].id;
							tcol.textContent = jsonData[i].type;
							amcol.textContent = jsonData[i].amount;
							dcol.textContent = jsonData[i].description;
							ucol.textContent = jsonData[i].userId;


							row.appendChild(idcol);
							row.appendChild(tcol);
							row.appendChild(amcol);
							row.appendChild(dcol);
							row.appendChild(ucol);
							if (jsonData[i].approved == 0 && <%= session.getAttribute("manager") %> == true) {
								let resolve = document.createElement("div");
								resolve.innerHTML =
									`<form>
                        <button class='btn-primary' type="button" onclick='resol(` + jsonData[i].id + `, "` + jsonData[i]
									.type + `", "` + jsonData[i].amount + `", "` + jsonData[i].description + `", ` + jsonData[i].userId +
									`)'>
                        Resolve
                        </button>
                        </form>`;
								mcol.textContent = "N/A";
								row.appendChild(mcol);
								row.appendChild(resolve);

							} else if (jsonData[i].approved == 0) {
								apcol.textContent = "Pending";
								mcol.textContent = "N/A";
								row.appendChild(mcol);
								row.appendChild(apcol);
							} else if (jsonData[i].approved == 1) {
								apcol.textContent = "Approved";
								mcol.textContent = jsonData[i].manager;
								row.appendChild(mcol);
								row.appendChild(apcol);
							} else if (jsonData[i].approved == 2) {
								apcol.textContent = "Denied";
								mcol.textContent = jsonData[i].manager;

								row.appendChild(mcol);
								row.appendChild(apcol);
							} else {
								apcol.textContent = jsonData[i].approved;
								mcol.textContent = jsonData[i].manager;

								row.appendChild(mcol);
								row.appendChild(apcol);
							}
							document.getElementById("myTable").appendChild(row);
						}
						if (<%= session.getAttribute("manager") %> == true && num == 0) {
							document.getElementById('empReq').innerHTML = "";

							idset.forEach(function (val) {
								let opt = document.createElement('option');
								opt.innerHTML = val;
								opt.value = val;
								document.getElementById('empReq').append(opt);
							});
						}
					}
					if (tableIsPop == false) {
						console.log(tableIsPop);
						let err = document.createElement('h3');
						err.innerHTML = 'no available request';
						document.getElementById("displayArea").appendChild(err);
					}
				}
			}

			xhr
				.open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllRequests');
			xhr.send();
		}
		getEmp = (number) => {
			console.log("manager id " + number)
			const body = {
				id: number
			};


			fetch('http://localhost:8080/EmployeeReimbursment/rest/getEmp', {
					method: 'post',
					body: JSON.stringify(body),
					headers: {
						'Content-Type': 'application/json',
						'Access-Control-Allow-Origin': 'http://localhost:8080',
						'Access-Control-Allow-Credentials': 'true',
						'Access-Control-Allow-Methods': 'POST'
					},
				})
				.then((response) => {console.log("In response " + response.json().firstName); return response});
		}
		filter = () => {
			document.getElementById("myTable").innerHTML =
				`
    <th>ID</th>
    <th>Type</th>
    <th>Amount</th>
    <th>Description</th>
    <th>User</th>
    <th>Manager</th>
    <th>Status</th>`;
			let fil = document.getElementById("empReq").value;
			if (fil == 'all') {
				populate(0);
			} else {
				populate(fil);
			}
		}
	</script>
</body>

</html>