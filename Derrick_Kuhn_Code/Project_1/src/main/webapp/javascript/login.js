window.onload = () => {
    let loginBtn = document.getElementById("loginBtn");
    loginBtn.addEventListener("click", () => {
        console.log("login button pressed");
        let loginRequest = new XMLHttpRequest();
        let passinput = document.getElementById("password").value;
        let userinput = document.getElementById("username").value;
        let inputEmployee = {
            "username" : userinput,
            "password" : passinput
        }
        const jsonMsg = JSON.stringify(inputEmployee);
        loginRequest.onreadystatechange = () => {
            console.log(" " + loginRequest.readyState);
            if((loginRequest.readyState == 4) && (loginRequest.status == 200)){
                console.log("response: " + loginRequest.responseText);
                if(loginRequest.responseText == 'true'){
                    console.log("Login successful");
                    window.location.href="http://localhost:8080/Project_1_war/static/home.html";
                } else {
                    console.log("Login failed.");
                    window.location.href="http://localhost:8080/Project_1_war/static/login.html";
                }
            }
        };
        loginRequest.open("post", "http://localhost:8080/Project_1_war/login");
        loginRequest.setRequestHeader("Content-Type", "application/json");
        loginRequest.send(jsonMsg);
    });
}

function loginButton() {
    console.log("login button pressed");
    let loginRequest = new XMLHttpRequest();
    let passinput = document.getElementById("password").value;
    let userinput = document.getElementById("username").value;
    let inputEmployee = {
        "username": userinput,
        "password": passinput
    }
    const jsonMsg = JSON.stringify(inputEmployee);
    loginRequest.onreadystatechange = () => {
        console.log(" " + loginRequest.readyState);
        if ((loginRequest.readyState == 4) && (loginRequest.status == 200)) {
            console.log("response: " + loginRequest.responseText);
            if (loginRequest.responseText == 'true') {
                console.log("Login successful");
                window.location.href = "http://localhost:8080/Project_1_war/static/home.html";
            } else {
                console.log("Login failed.");
                window.location.href = "http://localhost:8080/Project_1_war/static/login.html";
            }
        }
    };
    loginRequest.open("post", "http://localhost:8080/Project_1_war/login");
    loginRequest.setRequestHeader("Content-Type", "application/json");
    loginRequest.send(jsonMsg);
}
    
