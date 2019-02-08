createManPage = function (employee) {
    document.getElementById('optionArea').innerHTML = `
    <button type='button' onclick='viewRequest(0)'>View all Requests</button>
    <button type='button' onclick='viewRequest(-2)'>View pending Requests</button>
    <button type='button' onclick='viewEmployees()'>View all employees</button>
    <button type='submit' onclick='logoff()'>Logoff</button>
    `;
}

resol = function (id, type, amount, description) {
    document.getElementById('displayArea').innerHTML = `
    <h5>Request ID : ${id}</h5> 
    <h5>Request Type : ${type}</h5>
    <h5>Request Amount : ${amount}</h5>
    <h5>Description</h5>
    <h5>${description}</h5>
    <button onclick='approve(${id})'>Approve</button>
    <button onclick='deny(${id})'>Deny</button>
    `;
}

approve = function (reqid) {

    const body = {
        id: reqid,
        manager: employee.u_id,
        approved: 1
    };


    fetch('http://localhost:8080/EmployeeReimbursment/rest/updateRequests', {
            method: 'post',
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Methods': 'POST'
            },
        })
        .then(response => viewRequest(-2));
}


deny = function (reqid) {
    
    const body = {
        id: reqid,
        manager: employee.u_id,
        approved: 2
    };


    fetch('http://localhost:8080/EmployeeReimbursment/rest/updateRequests', {
            method: 'post',
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Methods': 'POST'
            },
        })
        .then(response => viewRequest(-2));
}


viewEmployees = function () {
    document.getElementById('displayArea').innerHTML = ` <table class="table table-bordered table-striped">
    <tbody id="myTable">
        <th>ID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Username</th>
        <th>Manager</th>
        <th>Email</th>
    </tbody>
</table>`;

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var jsonData = JSON.parse(xhr.responseText);
            for (i = 0; i < jsonData.length; i++) {
                let row = document.createElement("tr");
                let idcol = document.createElement("td");
                let fcol = document.createElement("td");
                let lcol = document.createElement("td");
                let ucol = document.createElement("td");
                let mcol = document.createElement("td");
                let ecol = document.createElement("td");

                idcol.textContent = jsonData[i].id;
                fcol.textContent = jsonData[i].firstName;
                lcol.textContent = jsonData[i].lastName;
                ucol.textContent = jsonData[i].username;
                mcol.textContent = jsonData[i].manager;
                ecol.textContent = jsonData[i].email;

                row.appendChild(idcol);
                row.appendChild(fcol);
                row.appendChild(lcol);
                row.appendChild(ucol);
                row.appendChild(mcol);
                row.appendChild(ecol);

                document.getElementById("myTable").appendChild(row);
            }
        }
    }

    xhr.open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllUsers');
    xhr.send();
}