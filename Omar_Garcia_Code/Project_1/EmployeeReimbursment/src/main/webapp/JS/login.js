window.onload = function () {
    console.log("js works");
}

document.getElementById("test").addEventListener('click', function () {
    let usernm = document.getElementById('user').value;
    let pssword = document.getElementById('pass').value;
    
    console.log(usernm + " " + pssword);
    const body = {
        username: usernm,
        password: pssword
    };

    
    fetch('http://localhost:8080/EmployeeReimbursment/rest/login', {
            method: 'post',
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Methods': 'POST'
            },
        })
        .then(res => res.json())
        .then(json => addData(json));

});

addData = function (json) {
    if(json != null){
    console.log(json);
    console.log('username : ' + json.username);
    console.log('id : ' + json.id);
    console.log('first name : ' + json.firstName);
    console.log('last name : ' + json.lastName);
    console.log('manager : ' + json.manager);
    console.log('email : ' + json.email);

    sessionStorage.setItem("id", json.id);
    sessionStorage.setItem("firstName", json.firstName);
    sessionStorage.setItem("lastName", json.lastName);
    sessionStorage.setItem("username", json.username);
    sessionStorage.setItem("manager", json.manager);
    sessionStorage.setItem("email", json.email);

    document.getElementById('test');
    window.location.href = 'homepage.html';
    }
    else {
        console.log('error');
        document.getElementById('errornotify').innerHTML = 'Invalid credentials';
    }
}