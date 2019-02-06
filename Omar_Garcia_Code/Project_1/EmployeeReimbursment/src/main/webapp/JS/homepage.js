window.onload = function () {
    document.getElementById('displayArea').innerHTML = '';
    employee = {
        user: sessionStorage.getItem("username"),
        u_id: sessionStorage.getItem('id'),
        firstname: sessionStorage.getItem('firstName'),
        lastname: sessionStorage.getItem('lastName'),
        manager: sessionStorage.getItem('manager'),
        email: sessionStorage.getItem('email')
    }

    document.getElementById('welcome').innerHTML = 'Welcome, ' + sessionStorage.getItem('firstName');

    /*console.log(employee.user);
    console.log(employee.u_id);
    console.log(employee.firstname);
    console.log(employee.lastname);
    console.log(employee.manager);
    console.log(employee.email);*/

    if (employee.manager == 'true') {
        createManPage(employee);
    } else {
        createEmpPage(employee);
    }
}

createEmpPage = function (employee) {
    document.getElementById('optionArea').innerHTML = `
    <button type='button' onclick='viewRequest(1)'>View current Requests</button>
    <button type='button' onclick='viewRequest(2)'>View resolved Requests</button>
    <button type='button' onclick='viewRequest(3)'>View pending Requests</button>
    <button  type='button' onclick='submitRequest()'>Submit new request</button>
    <button  type='button' onclick='viewInfo()'>View Information</button>
    <button  type='button' onclick='changeInfo()'>Change Information</button>
    <button type='submit' onclick='logoff'>Logoff</button>`;
}

createManPage = function (employee) {
    document.getElementById('optionArea').innerHTML = `
    <button type='button' onclick='viewRequest(1)'>View all Requests</button>
    <button type='button' onclick='viewRequest(3)'>View pending Requests</button>
    <button type='button' onclick='viewEmployees()'>View all employees</button>
    <button type='submit' onclick='logoff'>Logoff</button>
    `;
}

viewRequest = function (num) {
    document.getElementById('displayArea').innerHTML = `<div>
<h1>Requests</h1>
</h1>
</div>
<div>
<table class="table table-bordered table-striped ">
    <tbody id="myTable">
        <th>ID</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Description</th>
        <th>User</th>
        <th>Manager</th>
        <th>Status</th>
    </tbody>
</table>
</div>`;
    populate(num);
}

populate = function (num) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var jsonData = JSON.parse(xhr.responseText);
            for (i = 0; i < jsonData.length; i++) {
                addReq = false;
                if ((jsonData.userId == sessionStorage.u_id) || sessionStorage.manager == 'true') {
                    if (num == 1) {
                        addReq = true;
                    } else if (num == 2) {
                        if (jsonData[i].approved > 0) {
                            addReq = true;
                        }
                    } else if (num == 3) {
                        if (jsonData[i].approved == 0) {
                            addReq = true;
                        }
                    }
                }
                if (addReq == true) {
                    let row = document.createElement("tr");
                    let idcol = document.createElement("td");
                    let tcol = document.createElement("td");
                    let amcol = document.createElement("td");
                    let dcol = document.createElement("td");
                    let ucol = document.createElement("td");
                    let mcol = document.createElement("td");
                    let apcol = document.createElement("td");

                    idcol.textContent = jsonData[i].id;
                    tcol.textContent = jsonData[i].type;
                    amcol.textContent = jsonData[i].amount;
                    dcol.textContent = jsonData[i].description;
                    ucol.textContent = jsonData[i].userId;


                    row.appendChild(idcol);
                    row.appendChild(tcol);
                    row.appendChild(amcol);
                    row.appendChild(dcol);
                    row.appendChild(ucol);
                    if (jsonData[i].approved == 0 && employee.manager == 'true') {
                        let resolve = document.createElement("div");
                        resolve.innerHTML = `<form>
                        <button type="button" onclick='resol(${jsonData[i].id}, \"${jsonData[i].type}\", \"${jsonData[i].amount}\", \"${jsonData[i].description}\", ${jsonData[i].userId})'>
                        Resolve
                        </button>
                        </form>`;
                        mcol.textContent = "N/A";
                        row.appendChild(mcol);
                        row.appendChild(resolve);

                    } else if (jsonData[i].approved == 0) {
                        apcol.textContent = "N/A"
                        mcol.textContent = "N/A";
                        row.appendChild(mcol);
                        row.appendChild(apcol);
                    } else {
                        apcol.textContent = jsonData[i].approved;
                        mcol.textContent = jsonData[i].manager;
                        row.appendChild(mcol);
                        row.appendChild(apcol);
                    }
                    document.getElementById("myTable").appendChild(row);
                }
            }
        }
    }

    xhr
        .open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllRequests');
    xhr.send();
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

approve = function (id) {
    console.log("Request " + id + " has been approved");
    viewRequest(1);
}

deny = function (id) {
    console.log("Request " + id + " has been denied");
    viewRequest(1);
}