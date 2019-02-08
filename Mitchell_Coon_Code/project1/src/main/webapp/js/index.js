window.onload = function(){
	document.getElementById("subButton").addEventListener("click",function(){
		let name = document.getElementById("username").value;
		let pass = document.getElementById("pass").value;
		
		if (name && pass){
			if(name.trim != "" || pass.trim != ""){
				
				// Verify username and password:
				
				console.log("this worked");
				
			} else {
				console.log("no values provided in the form");
			}		
		}
	});
	document.getElementById("subButton").addEventListener("click",clearRows);
	document.getElementById("resButton").addEventListener("click",clearRows);
}



function clearRows(){
	document.getElementById("username").value = "";
	document.getElementById("pass").value = "";
}