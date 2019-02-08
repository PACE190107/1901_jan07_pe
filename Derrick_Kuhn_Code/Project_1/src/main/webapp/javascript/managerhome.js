window.onload = () => {
    console.log("js loaded");
    //Hide all functionality on load
    document.getElementById("profileInfo").style.display = "none";
    document.getElementById("createRequest").style.display = "none";
    document.getElementById("requests").style.display = "none";
    document.getElementById("employeeList").style.display = "none";
    document.getElementById("employeeRequests").style.display = "none";

    //Assign event listeners to page buttons
    //Navbar buttons
    document.getElementById("logout").addEventListener("click", logout);
    document.getElementById("employee").addEventListener("click", employeeButton);
    document.getElementById("request").addEventListener("click", requestButton);
    document.getElementById("profile").addEventListener("click", profileButton);
}
let d = document;


/* NAVBAR LINKS */
let employee = d.getElementById("employee");
let request = d.getElementById("request");
let profile = d.getElementById("profile");
let logout = d.getElementById("logout");
let welcome = d.getElementById("welcome");

/* ---------------------------- */

/* PROFILE INFORMATION VARIABLES */

// DIV
let profileInfo = d.getElementById("profileInfo");

// TABLE VALUES
let currentUsername = d.getElementById("currentUsername");
let newUsername = d.getElementById("newUsername");
let currentFname = d.getElementById("currentFname");
let newFname = d.getElementById("newFname");
let currentLname = d.getElementById("currentLname");
let newLname = d.getElementById("newLname");
let currentEmail = d.getElementById("currentEmail");
let newEmail = d.getElementById("newEmail");

// BUTTON VALUES
let updateProfile = d.getElementById("updateProfile");
/* ---------------------------- */

/* CREATE NEW REQUEST */

// DIV
let createRequest = d.getElementById("createRequest");

// TABLE VALUES
let subject = d.getElementById("subject");
let description = d.getElementById("description");
let amount = d.getElementById("amount");

// BUTTON
let submitNewRequest = d.getElementById("submitNewRequest");
let cancelNewRequest = d.getElementById("cancelNewRequest");
/* ---------------------------- */

/* REQUESTS TABLE */

// DIV
let requests = d.getElementById("requests");

// TABLE VALUES
let requestTableBody = d.getElementById("requestTableBody");

// BUTTON
let newRequestBtn = d.getElementById("newRequestBtn");
let pendingRequestBtn = d.getElementById("pendingRequestBtn");
let resolvedRequestBtn = d.getElementById("resolvedRequestBtn");
/* ---------------------------- */

/* EMPLOYEES LIST */

// DIV
let employeeList = d.getElementById("employeeList");

// TABLE VALUES
let employeeTableBody = d.getElementById("employeeTableBody");
/* ---------------------------- */

/* EMPLOYEE REQUESTS LIST */

// DIV
let employeeRequests = d.getElementById("employeeRequests");

// TABLE VALUES
let employeeRequestBody = d.getElementById("employeeRequestBody");

// BUTTON
let exitEmployeeRequests = d.getElementById("exitEmployeeRequests");

function logoutButton(){
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

function employeeButton(){
    profileInfo.style.display = "none";
    createRequest.style.display = "none";
    requests.style.display = "none";
    employeeRequests.style.display = "none";
    welcome.style.display = "none";

    employeeList.style.display = "block";
}

function requestButton(){
    profileInfo.style.display = "none";
    createRequest.style.display = "none";
    employeeList.style.display = "none";
    employeeRequests.style.display = "none";
    welcome.style.display = "none";

    requests.style.display = "block";
}

function profileButton(){
    requests.style.display = "none";
    createRequest.style.display = "none";
    employeeList.style.display = "none";
    employeeRequests.style.display = "none";
    welcome.style.display = "none";

    profileInfo.style.display = "block";

    let profileRequest = new XMLHttpRequest();
    profileRequest.onreadystatechange = () => {
        if ((profileRequest.readyState == 4) && (profileRequest.status == 200)){
            let profileResponse = JSON.parse(profileRequest.responseText);
            currentUsername.innerText = profileResponse.username;
            newUsername.innerText = profileResponse.username;
            currentFname.innerText = profileResponse.first_name;
            newFname.innerText = profileResponse.first_name;
            currentLname = profileResponse.last_name;
            newLname = profileResponse.last_name;
            currentEmail = profileResponse.email;
            newEmail = profileResponse.email;
        }
    };
    profileRequest.open("get", "\"http://localhost:8080/Project_1_war/employee/profile");
    profileRequest.send();
}