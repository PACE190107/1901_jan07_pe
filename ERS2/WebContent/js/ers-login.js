/**
 * 
 */

 window.onload = function(){

    document.getElementById("loginBtn").addEventListener("click", login);
    document.getElementById("reset").addEventListener("click", print);
    
    function login() {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/ERS2/login", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("username",username);
        xhr.setRequestHeader("password",password);
        xhr.send();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let verifyLogin = JSON.parse(xhr.responseText);
                //emp - redirect to window.location.href = "ersemp.html";
                //mgr - redirect to window.location.href = "ersmgr.html";
            }
            

            
        }
    }
}

