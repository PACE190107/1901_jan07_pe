window.onload = () => {
	let firstName = document.getElementById("firstName");
	let lastName = document.getElementById("lastName");
	let email = document.getElementById("email");
	let username = document.getElementById("username");
	var personalInfo = new XMLHttpRequest();
	
	personalInfo.onreadystatechange = function() {
		if ((personalInfo.readyState == 4) && (personalInfo.status == 200)) {
			let rt = JSON.parse(personalInfo.responseText);
			firstName.value = rt.firstName;
			lastName.value = rt.lastName;
			email.value = rt.email;
			username.value = rt.username;
		}
	};
	personalInfo.open("POST", "http://localhost:8080/ERS_Updated/getEmployee"); 
	personalInfo.send();
}