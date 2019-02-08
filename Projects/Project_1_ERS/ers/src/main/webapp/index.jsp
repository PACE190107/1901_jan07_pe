<%
try
	{
		if(session.getAttribute("id").toString() != null);
			{
				response.sendRedirect("employeePage.jsp");
			}
	} catch (NullPointerException e)
	{
		
	}
	
%>

<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>BrownZeus Inc.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  
    <script type="text/javascript"> window.onload = () => {
    	console.log("We're loaded!");
    }</script>
</head>

<body style="text-align: center; margin: 0;">
    <div class="navbar-fixed">
        <nav style="background-color: #4075b9" class="z-depth-4">
            <div class="nav-wrapper">

                <ul name="nav-mobile" class="left hide-on-med-and-down">
                    <li>BrownZeus Inc. Employee Portal</li>
                </ul>
            </div>
        </nav>
    </div>

    <div style="margin-bottom: 50px; margin-top: 30px;padding: 0;">
        <form class=" z-depth-4 col s12 container" action="LoginService" method="POST" style="width: 55% ; margin-top:30px;padding: 30px; display: inline-block; ">

            <h4 class="row">Login</h4>
            <div class="row">
                <div class="input-field"><input type="text" name="username" placeholder="Username" class="validate"
                        required></div>
            </div>
            <div class="row">
                <div class="input-field"><input type="password" name="password" placeholder="Password" class="validate"
                        required></div>
            </div>

    </div>

    <input class="btn" type="submit" name="Review Order" value="Login" style="background-color:#4075b9;">
    </form>
    </div>

</body>

</html>