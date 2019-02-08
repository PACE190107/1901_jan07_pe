window.onload = function () {
	document.getElementById("loginBtn").addEventListener("click", function(){
		
		
		var credentials = {
			username: document.getElementById("username").value,
			password: document.getElementById("password").value
		};
		
		var jsonCredentials = JSON.stringify(credentials);
		
		var req = new XMLHttpRequest();
		 req.onreadystatechange =  function () {
		        if ((req.status == 200) && (req.readyState == 4)){
		            if (req.getResponseHeader("nextPage") !== null){
		                window.location=req.getResponseHeader("nextPage");
		            }
		                
		        }else if(req.readyState == 4 && req.status == 404){
		        	console.log("invalid username password for login");
		        	document.getElementById("username").value = "";
		        	document.getElementById("password").value = "";
		        	document.getElementById("loginHeader").innerHTML = "Invalid Credentials Try Again";
		        }else if(req.readyState == 4){
		        	console.log("Issue with response in Login!")
		        }
		    };
		    console.log(credentials.username);
		    console.log(credentials.password);
		    req.open("POST", "http://localhost:8080/Project1_ERS/rest/login");
		    req.setRequestHeader("Content-Type", "application/json");
		    req.send(jsonCredentials);
		
		
	});
}

