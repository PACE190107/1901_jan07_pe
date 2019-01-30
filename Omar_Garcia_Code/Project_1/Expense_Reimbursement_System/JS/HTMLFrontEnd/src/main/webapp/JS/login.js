let button = document.getElementById("login");
let username = document.getElementById("username");
let pasword = document.getElementById("password");

username.addEventListener("click",() => {
	username.value = "";
})

password.addEventListener("click",() => {
	password.type = "password";
	password.value = "";
})

button.addEventListener("click", () => {
    
})