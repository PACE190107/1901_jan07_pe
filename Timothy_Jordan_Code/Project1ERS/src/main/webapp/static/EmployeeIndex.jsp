<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Employee Homepage</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" media="screen"
	href="static/styles.css" />
<link href="https://fonts.googleapis.com/css?family=Slabo+27px"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />
</head>
<body class="bg-dark">
	<!--Navbar-->
	<nav class="navbar navbar-expand-xl bg-dark  navbar-dark fixed-top"
		id="mobile-nav">
		<div class="container">
			<i class="fas fa-user-tie fa-2x pr-2 text-light" id="user_icon_nav">
			</i>

			<h5 class=" text-light mr-auto">
				<%
					Employee currentEmployee = (Employee) session.getAttribute("currentEmployee");
				%>
				<%
					out.println(currentEmployee.getEmp_userName());
				%>
			</h5>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<i class="fas fa-user fa-2x pr-2 text-light" id="user_icon"></i>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<form action="profileView" method="GET" class="btn_form">
							<button name="viewProfile" type="button"
								class="employee_link_item" data-toggle="modal"
								data-target=".bd-viewProfile-modal-lg">
								<i class="fas fa-address-card"></i> View Profile
							</button>
						</form>
					</li>
					<li class="nav-item">
						<form action="" method="" class="btn_form">
							<button name="editProfile" type="button"
								class="employee_link_item" data-toggle="modal"
								data-target=".bd-editProfile-modal-lg">
								<i class="fas fa-user-cog"></i> Edit Profile
							</button>
						</form>
					</li>
					<li class="nav-item">
						<button name="addRequest" type="button" class="employee_link_item"
							data-toggle="modal" data-target=".bd-newRequest-modal-lg">
							<i class="fas fa-money-check-alt"></i> New Request
						</button>
					</li>



					<li class="nav-item">
						<form action="logout" method="GET" class="btn_form">
							<button name="mysubmitbutton" type="submit"
								class="employee_link_item">
								<i class="fas fa-sign-out-alt"></i> Log Out
							</button>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!--End Nav-->



	<%@ page import="com.reavature.tjordan.models.Employee"%>
	<div class="outerContainer container mb-5">
		<div class="sidebar_Cont">
			<div class="employee_info_cont">
				<div class="user_icon_container">
					<i class="fas fa-user fa-6x" id="user_icon"></i>
				</div>
				<div class="user_name_container">

					<%
						out.println(currentEmployee.getEmp_userName());
					%>
				</div>
			</div>
			<div class="employee_links_cont">
				<form action="profileView" method="GET" class="btn_form">
					<button name="viewProfile" type="button" class="employee_link_item"
						data-toggle="modal" data-target=".bd-viewProfile-modal-lg">
						<i class="fas fa-address-card"></i> View Profile
					</button>
				</form>

				<form action="" method="" class="btn_form">
					<button name="editProfile" type="button" class="employee_link_item"
						data-toggle="modal" data-target=".bd-editProfile-modal-lg">
						<i class="fas fa-user-cog"></i> Edit Profile
					</button>
				</form>


				<button name="addRequest" type="button" class="employee_link_item"
					data-toggle="modal" data-target=".bd-newRequest-modal-lg">
					<i class="fas fa-money-check-alt"></i> New Request
				</button>


				<form action="logout" method="GET" class="btn_form">
					<button name="mysubmitbutton" type="submit"
						class="employee_link_item">
						<i class="fas fa-sign-out-alt"></i> Log Out
					</button>
				</form>
			</div>
		</div>
		<div class="main_content_container">
			<div class="custom_heading">
				<h4>Pending Request</h4>
			</div>
			<div class=" container pending_req_cont">

				<div class="table_cont"></div>
				<table class="table table-light  table-hover custom_table"
					id="pendingReqTable">
					<thead class="custom_thead">
						<tr>
							<th scope="col">Employee ID</th>
							<th scope="col">Request ID</th>
							<th scope="col">Date</th>
							<th scope="col">Type</th>
							<th scope="col">Amount</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>

			<div class="custom_heading">
				<h4>Resolved Request</h4>
			</div>
			<div class=" container resolved_req_cont">

				<div class="table_cont">
					<table class="table table-light  table-hover custom_table"
						id="resolvedReqTable">
						<thead class="custom_thead">
							<tr>
								<th scope="col">Employee ID</th>
								<th scope="col">Request ID</th>
								<th scope="col">Date</th>
								<th scope="col">Type</th>
								<th scope="col">Amount</th>
								<th scope="col">Status</th>
								<th scope="col">Manager ID</th>
								<th scope="col">Resolved Date</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>








	<!-- ___________________________________________MODALS_____________________________________________ -->

	<!-- -------------------------------New Request Modal------------------------------------------- -->

	<div class="modal fade bd-newRequest-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<i class="fas fa-times ml-auto text-light"></i>
			<div class="modal-content">
				<div class="modal_form_cont">
					<form role="form" action="addRequest" method="POST"
						id="newReq-modal-form">

						<h5 class="text-center">New Request Form</h5>
						<h6>Type</h6>
						<select class="custom-select" name="type" required>
							<option value="Other" selected disabled>Choose Expense Type</option>
							<option value="Relocation">Relocation</option>
							<option value="Certifcation">Certification</option>
							<option value="Travel">Travel</option>
							<option value="Other">Other</option>
						</select>


						<div class="form-group">
							<label for="amount" class="mt-3">Amount</label>
							 <input
								type="number" class="form-control modal_input_item"
								placeholder="Expense Amount" id="expense-input" name="amount"
								step="0.01" required/>
						</div>




						<button type="submit"
							class="btn text-light btn-block employee_link_item"
							id="newReq-modal-submit-btn">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>













	<!-- -----------------------------------End New Request ------------------------------------------->



















	<!-- MODAL FOR VIEW PROFILE------------------------------------------------------------------------ -->

	<div class="modal fade bd-viewProfile-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<i class="fas fa-times ml-auto text-light"></i>
			<div class="modal-content">
				<div class="modal_form_cont">
					<div class="main_content_container">
						<div class="employee_profile_info_container">
							<div class="employee_profile_info_inner">
								<div class="employee_profile_info_inner_item">


									Username:
									<%
									out.println(currentEmployee.getEmp_userName());
								%>
								</div>
								<div class="employee_profile_info_inner_item">
									First name:
									<%
									out.println(currentEmployee.getEmp_firstName());
								%>
								</div>
								<div class="employee_profile_info_inner_item">
									Last name:
									<%
									out.println(currentEmployee.getEmp_lastName());
								%>
								</div>
								<div class="employee_profile_info_inner_item">
									<i class="fas fa-envelope form_fa_icon"> </i>
									<p></p>
									<%
										out.println(currentEmployee.getEmp_email());
									%>
								</div>
								<div class="employee_profile_info_inner_item">
									User ID:
									<%
									out.println(currentEmployee.getEmp_ID());
								%>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>





















	<!------------------------------------------------------------------------- END MODAL FOR VIEW PROFILE -->
























	<!---------------------------------MODAL FORM FOR Edit Profile REQUEST-------->

	<div class="modal fade bd-editProfile-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<i class="fas fa-times ml-auto text-light"></i>
			<div class="modal-content">
				<div class="modal_form_cont">
					<form role="form" action="updateProfile" method="post"
						id="profile_modal_form">
						<div class="form-group">
							<label for="First Name">FirstName</label> <input type="text"
								class="form-control modal_input_item"
								placeholder="Change First Name" id="firstname-input"
								name="firstName"
								value="<%out.println(currentEmployee.getEmp_firstName());%>" />
						</div>
						<div class="form-group">
							<label for="Last Name">LastName</label> <input type="text"
								class="form-control" placeholder="Change Last Name"
								id="lastname-input" name="lastName"
								value="<%out.println(currentEmployee.getEmp_lastName());%>" />
						</div>
						<div class="form-group">
							<label for="email-input">Email</label> <input type="email"
								class="form-control" placeholder="Change Email Address"
								id="email-input" name="email"
								value="<%out.println(currentEmployee.getEmp_email());%>" />
						</div>
						<div class="form-group">
							<label for="message-input">Password</label> <input
								type="password" class="form-control"
								placeholder="Change Password" id="changedpass-input"
								name="password"
								value="<%out.println(currentEmployee.getEmp_password());%>" />
						</div>
						<h5>Leave any field blank to keep the same.</h5>
						<button type="submit"
							class="btn text-light btn-block employee_link_item"
							id="profile-modal-submit-btn">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!----------------------------------------END MODAL FOR Edit Profile REQUEST-->















	<!-- ___________________________________________________________________________________________________ -->






































	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="static/employeeIndex.js"></script>
</body>
</html>
