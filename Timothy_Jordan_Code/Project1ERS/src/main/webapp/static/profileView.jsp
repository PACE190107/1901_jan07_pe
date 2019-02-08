<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Profile View</title>
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
<%@ page import="com.reavature.tjordan.models.Employee" %>
<div class="outerContainer container mb-5">
      <div class="sidebar_Cont">
        <div class="employee_info_cont">
          <div class="user_icon_container">
            <i class="fas fa-user fa-6x" id="user_icon"></i>
          </div>
            <% Employee currentEmployee = (Employee)session.getAttribute("currentEmployee"); %>
                  
          <div class="user_name_container"><%out.println(currentEmployee.getEmp_userName()); %></div>
        </div>
        <div class="employee_links_cont">
          <form action="returnHome" method="GET" class="btn_form">
            <button
              name="mysubmitbutton"
              id=""
              type="submit"
              class="employee_link_item"
            >
              <i class="fas fa-home"></i> Home
            </button>
          </form>
          <form action="editProfile" method="GET" class="btn_form">
            <button
              name="mysubmitbutton"
              id=""
              type="submit"
              class="employee_link_item"
            >
              <i class="fas fa-user-cog"></i> Edit Profile
            </button>
          </form>

          <form action="logout" method="GET" class="btn_form">
            <button
              name="mysubmitbutton"
              id=""
              type="submit"
              class="employee_link_item"
            >
            
              <i class="fas fa-sign-out-alt"></i> Log Out
            </button>
          </form>
          
        </div>
      </div>
      <div class="main_content_container">
        <div class="employee_profile_info_container">
          <div class="employee_profile_info_inner">
            <div class="employee_profile_info_inner_item">
            
                  
              Username: <%out.println(currentEmployee.getEmp_userName()); %>
            </div>
            <div class="employee_profile_info_inner_item">
              First name: <%out.println(currentEmployee.getEmp_firstName()); %>
            </div>
            <div class="employee_profile_info_inner_item">
              Last name: <%out.println(currentEmployee.getEmp_lastName()); %>
            </div>
            <div class="employee_profile_info_inner_item">
              Email: <%out.println(currentEmployee.getEmp_email()); %>
            </div>
            <div class="employee_profile_info_inner_item">
              User ID: <%out.println(currentEmployee.getEmp_ID()); %>
            </div>
          </div>
        </div>
      </div>
    </div>
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
</body>
</html>