<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERS Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css" />
    <script src="main.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head><body>
    
    
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
                <div>
                        <h1>ItsMyMoneyAndIWantItNow!</h1>
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
                        <button type="submit" class="btn btn-outline-primary">Login</button>
                        </div>     
                    
                    
                    </form>
        </div>
    </div>
</div>
    


   
</body>
</html>
