/**
 * 
 */

 window.onload = function(){

    document.getElementById("LoginForm").addEventListener("submit", login);
    document.getElementById("reset").addEventListener("click", print);
    
    function login() {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let xhr = new XMLHttpRequest();
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let verifyLogin = JSON.parse(xhr.responseText);
                verifyLogin
                //emp - redirect to window.location.href = "ersemp.html";
                //mgr - redirect to window.location.href = "ersmgr.html";
            }
            xhr.open("POST", "http://localhost:8080/ERS/rest" + name + pass, true);
            xhr.send();
            
        }
    }
}

