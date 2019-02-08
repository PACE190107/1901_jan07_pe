<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERS Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!--Fontawesome-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!--CSS-->
    <link rel="stylesheet" type="text/css" href="static/ERS_CSScss.css">
</head>



<body class="normal">

    
<div class="container py-4">
	<div class= "card border-success">
    	<div class="row justify-content-center">
       		 <div class="col-md-4">
                	<div class="card-body">
                            <span style="font-size: 48px; color: Dodgerblue;">
                                    
                                    <i class="fas fa-money-bill-wave"></i>
                                   <h1>Employee Portal</h1>
                                  </span>
                       
                       
                        </div>
                <form action="login" method="POST">
                        <div class="form-group">
                    
                            <label for="emailinput">Email Address</label>
                            <input type="text" class="form-control" id="emailinput" aria-describedby="emailHelp" placeholder="Enter email" name="email">
                            <small id="emailhelp" class="form-text text-muted">Login with a secured email</small>
                        </div>
                       
                        <div class="form-group">
                            <label for="inputpassword">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                        </div>
                    
                        <div>
                        <button type="submit" class="btn btn-outline-success">Login</button>
                        </div>     
                    
                    
                    </form>
        </div>
    </div>
</div>
    


</div>   
</body>
</html>