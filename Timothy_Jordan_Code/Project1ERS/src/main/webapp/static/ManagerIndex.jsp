<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Manager Homepage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      type="text/css"
      media="screen"
      href="static/styles.css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Slabo+27px"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
      integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
      integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
      crossorigin="anonymous"
    />
  </head>
  <body class="bg-dark">
  <%@ page import="com.reavature.tjordan.models.Employee"%>
    <!--Navbar-->
    <nav
      class="navbar navbar-expand-xl bg-dark  navbar-dark fixed-top"
      id="mobile-nav"
    >
      <div class="container">
        
<i class="fas fa-user-tie fa-2x pr-2 text-light" id="user_icon-nav"></i>
        <h5 class=" text-light mr-auto"><%
						Employee currentEmployee = (Employee) session.getAttribute("currentEmployee");
					%>
					<%
						out.println(currentEmployee.getEmp_userName());
					%></h5>
        <button
          class="navbar-toggler"
          data-toggle="collapse"
          data-target="#navbarCollapse"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
	
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <form action="profileView" method="GET" class="btn_form">
                <button
                  name="viewProfile"
                  type="button"
                  class="employee_link_item"
                  data-toggle="modal"
                  data-target=".bd-viewProfile-modal-lg"
                >
                  <i class="fas fa-address-card"></i> View Profile
                </button>
              </form>
            </li>
            <li class="nav-item">
              <form action="" method="" class="btn_form">
                <button
                  name="editProfile"
                  type="button"
                  class="employee_link_item"
                  data-toggle="modal"
                  data-target=".bd-editProfile-modal-lg"
                >
                  <i class="fas fa-user-cog"></i> Edit Profile
                </button>
              </form>
            </li>
            <li class="nav-item">
              <button
                name="allUsers"
                id="get-all-users-btn"
                type="button"
                class="employee_link_item"
                data-toggle="modal"
                data-target=".bd-allUsers-modal-lg"
              >
                <i class="fas fa-users"></i> View All Users
              </button>
            </li>
            <li class="nav-item">
              <button
                name="addEmployee"
                id="add-employee-btn"
                type="submit"
                class="employee_link_item"
                data-toggle="modal"
                data-target=".bd-newEmployee-modal-lg"
              >
                <i class="fas fa-user-plus"></i> Add User
              </button>
            </li>
            <li class="nav-item">
              <button
                name="addEmployee"
                id="all-resolved-btn"
                type="submit"
                class="employee_link_item"
                data-toggle="modal"
                data-target=".bd-allResolved-modal-lg"
              >
                <i class="fas fa-money-check-alt"></i> View Resolved Request
              </button>
            </li>
            <li class="nav-item">
              <form action="logout" method="GET" class="btn_form">
                <button
                  name="mysubmitbutton"
                  type="submit"
                  class="employee_link_item"
                >
                  <i class="fas fa-sign-out-alt"></i> Log Out
                </button>
              </form>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!--End Nav-->

    <div class="outerContainer container mb-5">
      <div class="sidebar_Cont" id="manager-sidebar">
        <div class="employee_info_cont">
          <div class="user_icon_container">
            <i class="fas fa-user-tie fa-6x" id="user_icon"></i>
          </div>
          <div class="user_name_container">
           
					<%
						out.println(currentEmployee.getEmp_userName());
					%>
          </div>
        </div>
        <div class="employee_links_cont">
          <form action="profileView" method="GET" class="btn_form">
            <button
              name="viewProfile"
              type="button"
              class="employee_link_item"
              data-toggle="modal"
              data-target=".bd-viewProfile-modal-lg"
            >
              <i class="fas fa-address-card"></i> View Profile
            </button>
          </form>

          <form action="" method="" class="btn_form">
            <button
              name="editProfile"
              type="button"
              class="employee_link_item"
              data-toggle="modal"
              data-target=".bd-editProfile-modal-lg"
            >
              <i class="fas fa-user-cog"></i> Edit Profile
            </button>
          </form>

          <button
            name="allUsers"
            id="get-all-users-btn"
            type="button"
            class="employee_link_item"
            data-toggle="modal"
            data-target=".bd-allUsers-modal-lg"
          >
            <i class="fas fa-users"></i> View All Users
          </button>

          <button
            name="addEmployee"
            id="add-employee-btn"
            type="submit"
            class="employee_link_item"
            data-toggle="modal"
            data-target=".bd-newEmployee-modal-lg"
          >
            <i class="fas fa-user-plus"></i> Add User
          </button>

          <button
            name="addEmployee"
            id="all-resolved-btn"
            type="submit"
            class="employee_link_item"
            data-toggle="modal"
            data-target=".bd-allResolved-modal-lg"
          >
            <i class="fas fa-money-check-alt"></i> View Resolved Request
          </button>

          <form action="logout" method="GET" class="btn_form">
            <button
              name="mysubmitbutton"
              type="submit"
              class="employee_link_item"
            >
              <i class="fas fa-sign-out-alt"></i> Log Out
            </button>
          </form>
        </div>
      </div>
      <div class="main_content_container">
        <div class=" container man-pending_req_cont">
          <div class="table_cont"></div>
          <table
            class="table table-light   table-hover custom_table"
            id="man-pendingReqTable"
          >
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
            <tbody id="temp-tbody"></tbody>
          </table>
        </div>
        <div id="man-main-bottom-container">
          <div class=" manager-main-form-cont">
            <h5>Filter By Employee ID</h5>
            <div id="idfilter-cont" class="">
              <label class="mr-sm-2 sr-only" for="if-Filter"
                >Select an ID</label
              >

              <select class="custom-select mr-sm-2" id="id-Filter">
                <option selected disabled>Choose...</option>
              </select>

              <button  class="btn manager_link_item" id="id-filter-btn">
                Submit
              </button>
            </div>
          </div>
          <div class=" manager-main-form-cont">
            <h5>View All Pending</h5>
            <button class=" mt-3 btn  manager_link_item manager-main-bottom-btn" id="view-all-pending-btn">
              View
            </button>
          </div>
        </div>
        <div
          class="manager-main-resolve-container text-center justify-content-center bg-light"
        >
          <h4 class="pt-5">Request Processing</h4>
          <form
            class=" text-center approveForm"
            action="updateReq"
            method="Post"
          >
            <label class="mr-sm-2 sr-only" for="req-Filter">Select an ID</label>

            <select
              class="custom-select m-auto"
              id="req-filter"
              name="requestID"
            >
              <option selected disabled>Choose...</option>
            </select>

            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="updateRadios"
                id="approveRadio"
                value="Approved"
                checked
              />
              <label class="form-check-label" for="approveRadio"
                >Approve
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="updateRadios"
                id="denyRadio"
                value="Declined"
              />
              <label class="form-check-label" for="denyRadio">Decline</label>
            </div>

            <button type="submit" class="manager_link_item manager-main-bottom-btn ">Submit</button>
          </form>
        </div>
      </div>
    </div>

    <!-- ------------------------MODALS------------------------------------------------------------------ -->

    <!-- -------------------------------New Employee Modal------------------------------------------- -->

    <div
      class="modal fade bd-newEmployee-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <i class="fas fa-times ml-auto text-light"></i>
        <div class="modal-content">
          <div class="modal_form_cont">
            <form
              role="form"
              action="addEmployee"
              method="POST"
              id="newReq-modal-form"
            >
              <h5 class="text-center">New Employee Form</h5>

              <div class="form-group">
                <label for="userName" class="mt-3">Username</label>
                <input
                  type="text"
                  class="form-control modal_input_item"
                  placeholder="Enter Username"
                  id="userName-input"
                  name="userName"
                  required
                />
              </div>
              <div class="form-group">
                <label for="firstName" class="mt-3">First Name</label>
                <input
                  type="text"
                  class="form-control modal_input_item"
                  placeholder="Enter First Name"
                  id="firstName-input"
                  name="firstName"
                  required
                />
              </div>
              <div class="form-group">
                <label for="lastName" class="mt-3">Last Name</label>
                <input
                  type="text"
                  class="form-control modal_input_item"
                  placeholder="Enter Last Name"
                  id="lastName-input"
                  name="lastName"
                  required
                />
              </div>

              <div class="form-group">
                <label for="password" class="mt-3">Password</label>
                <input
                  type="password"
                  class="form-control modal_input_item"
                  placeholder="Enter Password"
                  id="password-input"
                  name="password"
                  required
                />
              </div>

              <div class="form-group">
                <label for="email" class="mt-3">Email</label>
                <input
                  type="text"
                  class="form-control modal_input_item"
                  placeholder="Enter Email"
                  id="new-user-email-input"
                  name="email"
                  required
                />
              </div>

              <h6>Manager</h6>
              <select class="custom-select" name="type">
                <option value="N" selected disabled>Is Manager?</option>
                <option value="Y">Yes</option>
                <option value="N">No</option>
              </select>

              <button
                type="submit"
                class="btn text-light btn-block employee_link_item"
                id="newReq-modal-submit-btn"
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- -----------------------------------End New Employee ------------------------------------------->

    <!-- MODAL FOR VIEW PROFILE------------------------------------------------------------------------ -->

    <div
      class="modal fade bd-viewProfile-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <i class="fas fa-times ml-auto text-light"></i>
        <div class="modal-content">
          <div class="modal_form_cont">
            <div class="main_content_container">
              <div class="employee_profile_info_container">
                <div class="employee_profile_info_inner">
                  <div class="employee_profile_info_inner_item">
                    Username: <%
						out.println(currentEmployee.getEmp_userName());
					%>
                  </div>
                  <div class="employee_profile_info_inner_item">
                    First name: <%
						out.println(currentEmployee.getEmp_firstName());
					%>
                  </div>
                  <div class="employee_profile_info_inner_item">
                    Last name:<%
						out.println(currentEmployee.getEmp_lastName());
					%>
                  </div>
                  <div class="employee_profile_info_inner_item">
                    <i class="fas fa-envelope form_fa_icon"> </i>
                    <p><%
						out.println(currentEmployee.getEmp_email());
					%></p>
                  </div>
                  <div class="employee_profile_info_inner_item">
                    User ID: <%
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

    <div
      class="modal fade bd-editProfile-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <i class="fas fa-times ml-auto text-light"></i>
        <div class="modal-content">
          <div class="modal_form_cont">
            <form
              role="form"
              action="updateProfile"
              method="post"
              id="profile_modal_form"
            >
              <div class="form-group">
                <label for="First Name">FirstName</label>
                <input
                  type="text"
                  class="form-control modal_input_item"
                  placeholder="Change First Name"
                  id="firstname-input"
                  name="firstName"
                  value="<%out.println(currentEmployee.getEmp_firstName());%>"
                />
              </div>
              <div class="form-group">
                <label for="Last Name">LastName</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Change Last Name"
                  id="lastname-input"
                  name="lastName"
                  value="<%out.println(currentEmployee.getEmp_lastName());%>"
                />
              </div>
              <div class="form-group">
                <label for="email-input">Email</label>
                <input
                  type="email"
                  class="form-control"
                  placeholder="Change Email Address"
                  id="email-input"
                  name="email"
                  value="<%out.println(currentEmployee.getEmp_email());%>"
                />
              </div>
              <div class="form-group">
                <label for="message-input">Password</label>
                <input
                  type="password"
                  class="form-control"
                  placeholder="Change Password"
                  id="changedpass-input"
                  name="password"
                  value="<%out.println(currentEmployee.getEmp_password());%>"
                />
              </div>
              <h5>Leave any field blank to keep the same.</h5>
              <button
                type="submit"
                class="btn text-light btn-block employee_link_item"
                id="profile-modal-submit-btn"
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!----------------------------------------END MODAL FOR Edit Profile REQUEST-->

    <!---------------------------------MODAL FORM FOR View All Users-------->

    <div
      id="all-user-modal"
      class="modal fade bd-allUsers-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div id="all-user-modal-dialog" class="modal-dialog modal-lg">
        <i class="fas fa-times ml-auto text-light"></i>
        <div id="all-user-modal-content" class="modal-content">
          <div id="all-user-modal-cont" class="modal_form_cont">
            <table
              class="table table-light  table-hover custom_table"
              id="all-Table"
            >
              <thead class="custom_thead">
                <tr>
                  <th scope="col">Employee ID</th>
                  <th scope="col">User Name</th>
                  <th scope="col">First Name</th>
                  <th scope="col">Last Name</th>
                  <th scope="col">Email</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!----------------------------------------END MODAL FOR View All Users-->

    <!---------------------------------MODAL FORM FOR View All Resolved-------->

    <div
      id="all-resolved-modal"
      class="modal fade bd-allResolved-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div id="all-user-modal-dialog" class="modal-dialog modal-lg">
        <i class="fas fa-times ml-auto text-light"></i>
        <div id="all-resolved-modal-content" class="modal-content">
          <div id="all-user-modal-cont" class="modal_form_cont">
            <table
              class="table table-dark text-light  table-hover custom_table"
              id="allResolved-Table"
            >
              <thead class="custom_thead">
                <tr>
                  <th scope="col">Employee ID</th>
                  <th scope="col">Request ID</th>
                  <th scope="col">Date Received</th>
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

    <!----------------------------------------END MODAL FOR View AllResolved-->

    <!-- ------------------------------------END MODALS---------------------------------------------- -->

    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
      integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
      integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
      crossorigin="anonymous"
    ></script>

    <script type="text/javascript" src="static/managerIndex.js"></script>
  </body>
</html>
