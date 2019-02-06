document.getElementById("login").addEventListener("click" , function () {
    console.log("button clicked");
    Body = {
        "username": "first",
        "password": "password"
    }
    fetch('http://localhost:8080/EmployeeReimbursment/rest/login?username=first&password=password')
.then(response => response.json())
.then(response => console.log('Success:', document.getElementById("loginform").target = "employeePage.html" ))
.catch(error => console.error('Error:', error));
});