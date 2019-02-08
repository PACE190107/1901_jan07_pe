<html>
<body>

<form action="http://localhost:8080/Project1/rest/users" method="POST">

<!-- <script>

function loadDoc() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("demo").innerHTML = this.responseText;
	     var myObj = JSON.parse(this.responseText);
	     if(m)
	     document.getElementById("demo").innerHTML = myObj.name;
	     
	    }
	  };
	  xhttp.open("GET", "http://localhost:8080/Project1/rest/users", true);
	  xhttp.send();
	}
	
</script>

<div id="demo">
  <h2>Login Page</h2>
</div> -->

<label for="username">Username</label>
<input type="text" name="username">
<br>

<label for="passwords">Password</label>
<input type="password" name="password">
<br>

<input type="submit" value="Log In">

</form>
</body>
</html>
