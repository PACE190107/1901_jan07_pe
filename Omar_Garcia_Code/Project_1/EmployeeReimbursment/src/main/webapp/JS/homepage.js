window.onload = () => {
    document.getElementById('displayArea').innerHTML = '';
    
    if(sessionStorage.getItem('id') == -1){
        window.location.href = 'loginPage.html';
    }

    employee = {
        user: sessionStorage.getItem("username"),
        u_id: sessionStorage.getItem('id'),
        firstname: sessionStorage.getItem('firstName'),
        lastname: sessionStorage.getItem('lastName'),
        manager: sessionStorage.getItem('manager'),
        email: sessionStorage.getItem('email')
    }

    document.getElementById('welcome').innerHTML = 'Welcome, ' + sessionStorage.getItem('firstName');

    console.log(employee.user);
    console.log(employee.u_id);
    console.log(employee.firstname);
    console.log(employee.lastname);
    console.log(employee.manager);
    console.log(employee.email);

    if (employee.manager == 'true') {
        createManPage(employee);
    } else {
        createEmpPage(employee);
    }
}

createEmpPage = (employee) => {
    document.getElementById('optionArea').innerHTML = `
    <button type='button' onclick='viewRequest(${employee.u_id})'>View all Requests</button>
    <button type='button' onclick='viewRequest(-1)'>View resolved Requests</button>
    <button type='button' onclick='viewRequest(-2)'>View pending Requests</button>
    <button  type='button' onclick='submitPage()'>Submit new request</button>
    <button  type='button' onclick='viewInfo()'>View Information</button>
    <button  type='button' onclick='changeInfo()'>Change Information</button>
    <button type='button' onclick='logoff()'>Logoff</button>`;
}

viewInfo = () => {
    document.getElementById('displayArea').innerHTML = `
    <h5>username, ${ employee.user } </h5>
<h5>first name, ${ employee.firstname }</h5>
<h5>last name, ${ employee.lastname }</h5>
<h5>email, ${ employee.email }</h5>
<h5>id, ${ employee.u_id }</h5>
<h5>manager, ${ employee.manager }</h5>
<button type='button' onclick='changeInfo()'>Update Info</button>`;
}

changeInfo = () => {
    document.getElementById('displayArea').innerHTML = `
    <label for='username'>Current username, ${ employee.user } </label>
    <input id='username' type='text' name='username'>
    <br>
<labelfor='firstname'>Current first name, ${ employee.firstname }</label>
<input id='first' type='text' name='firstname'>
<br>
<labelfor='lastname'>Current last name, ${ employee.lastname }</label>
<input id='last' type='text' name='lastname'>
<br>
<labelfor='email'>Current email, ${ employee.email }</label>
<input id='email' type='text' name='email'>
<br>

<labelfor='password'>New Password</label>
<input id='psswrd' type='password' name='password'>
<br>

<button type='button' onclick='upDate()'>Update</button>
`;
}

upDate = () => {
    let usernm = document.getElementById('username').value;
    let first = document.getElementById('first').value;
    let last = document.getElementById('last').value;
    let mail = document.getElementById('email').value;
    let psswrd = document.getElementById('psswrd').value;

    if (usernm == null || usernm == '') {
        usernm = employee.user;
    }
    if (first == null || first == '') {
        first = employee.firstname;
    }
    if (last == null || last == '') {
        last = employee.lastname;
    }
    if (mail == null || mail == '') {
        mail = employee.email;
    }
    if (psswrd == null || psswrd == '') {
        psswrd = null;
    }
    const body = {
        id: employee.u_id,
        firstName: first,
        lastName: last,
        username: usernm,
        password: psswrd,
        manager: employee.manager,
        email: mail
    }
    console.log(body);
    fetch('http://localhost:8080/EmployeeReimbursment/rest/updateEmployee', {
            method: 'post',
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Methods': 'POST'
            },
        })
        .then(res => success(body))
        .then();
}

success = (json) => {
    sessionStorage.setItem("id", json.id);
    sessionStorage.setItem("firstName", json.firstName);
    sessionStorage.setItem("lastName", json.lastName);
    sessionStorage.setItem("username", json.username);
    sessionStorage.setItem("manager", json.manager);
    sessionStorage.setItem("email", json.email);
    
    window.location.href = 'homepage.html';
}

logoff = () => {
    sessionStorage.setItem("id", -1);
    sessionStorage.setItem("firstName", null);
    sessionStorage.setItem("lastName", null);
    sessionStorage.setItem("username", null);
    sessionStorage.setItem("manager", null);
    sessionStorage.setItem("email", null);

    console.log(sessionStorage.getItem('id'));

    window.location.href = 'loginPage.html';
}