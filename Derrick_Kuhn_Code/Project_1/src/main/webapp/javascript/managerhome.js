window.onload = () => {
    document.getElementById("logout").addEventListener("click", logout);
}

function logout(){
    let logoutReq = new XMLHttpRequest();
    logoutReq.onreadystatechange = () => {
        if((logoutReq.readyState == 4) && (logoutReq.status == 200)){
            if(logoutReq.responseText == 'true'){
                window.location.href="http://localhost:8080/Project_1_war/static/login.html";
            }
        }
    };
    logoutReq.open("get", "http://localhost:8080/Project_1_war/login");
    logoutReq.send();
}