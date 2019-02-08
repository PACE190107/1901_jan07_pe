submitPage = function () {
    document.getElementById('displayArea').innerHTML = `
    <form>
    <label for="reqType">Reinbursment Type</label>
    <select id="reqType" name="reqType">
        <option value="Relocation">Relocation</option>
        <option value="Study">Study Expenses</option>
        <option value="Other">Other</option>
    </select>
    </form>

<label for="reqAmount">Amount</label>
<input id="reqAmount" type="number" min=0 name="reqAmount">
</div>
<div>
    <label for="descritpion">Description</label>
    <input id="description" type="text" name="description">
</div>
<div>
    <button type="button" onclick="submitRequest()">Submit</button>
</div>`;
}

submitRequest = function () {
    var reqtype = document.getElementById("reqType");
    var reqamount = document.getElementById("reqAmount");
    var reqdescription = document.getElementById("description");
    if (reqamount.value > 0) {
        console.log("valid inputs \n" +
            "Type " + reqtype.value +
            "\nAmount " + reqamount.value +
            "\nDescription " + reqdescription.value +
            "\nUserID " + sessionStorage.getItem("id"));
    }

    const body = {
        type: reqtype.value,
        amount: reqamount.value,
        description: reqdescription.value,
        userId: sessionStorage.getItem("id")
    };

    fetch('http://localhost:8080/EmployeeReimbursment/rest/addRequest', {
            method: 'post',
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Methods': 'POST'
            },
        })
        .then(response => viewRequest(sessionStorage.getItem("id")));
}



viewRequest = function (num) {
    document.getElementById('displayArea').innerHTML = `
    <div>
    <h1>Requests</h1>
    </h1>
    </div>
    <div>
    <form id='selectForm'>
        
        </form>
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
    if (employee.manager == 'true' && num == 0) {
        document.getElementById('selectForm').innerHTML = `
                    <label for="empReq">Filter</label>
        <select id="empReq" name="empReq" onchange="filter()">
        </select>`;
    }
    populate(num);
}

populate = function (num) {
    let xhr = new XMLHttpRequest();

    console.log(num);
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var jsonData = JSON.parse(xhr.responseText);
            tableIsPop = false;

            var idset = new Set();
            idset.add('all');

            for (i = 0; i < jsonData.length; i++) {
                addReq = false;
                if ((jsonData[i].userId == num) || employee.manager == 'true' || (jsonData[i].userId == employee.u_id)) {
                    if (num == 0) {
                        addReq = true;
                    } else if (num == -1) {
                        if (jsonData[i].approved > 0) {
                            addReq = true;
                        }
                    } else if (num == -2) {
                        if (jsonData[i].approved == 0) {
                            addReq = true;
                        }
                    } else if (jsonData[i].userId == num) {
                        addReq = true;
                    }
                }
                if (addReq == true) {
                    if (employee.manager == 'true') {
                        idset.add(jsonData[i].userId);
                    }
                    tableIsPop = true;
                    console.log(tableIsPop);

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
                        apcol.textContent = "Pending";
                        mcol.textContent = "N/A";
                        row.appendChild(mcol);
                        row.appendChild(apcol);
                    } else if (jsonData[i].approved == 1) {
                        apcol.textContent = "Approved";
                        mcol.textContent = "N/A";
                        row.appendChild(mcol);
                        row.appendChild(apcol);
                    } else if (jsonData[i].approved == 2) {
                        apcol.textContent = "Denied";
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
                if (employee.manager == 'true' && num == 0) {
                    document.getElementById('empReq').innerHTML = "";

                    idset.forEach(function (val) {
                        let opt = document.createElement('option');
                        opt.innerHTML = val;
                        opt.value = val;
                        document.getElementById('empReq').append(opt);
                    });
                }
            }
            if (tableIsPop == false) {
                console.log(tableIsPop);
                let err = document.createElement('h3');
                err.innerHTML = 'no available request';
                document.getElementById("displayArea").appendChild(err);
            }
        }
    }

    xhr
        .open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllRequests');
    xhr.send();
}

filter = () => {
    document.getElementById("myTable").innerHTML = `
    <th>ID</th>
    <th>Type</th>
    <th>Amount</th>
    <th>Description</th>
    <th>User</th>
    <th>Manager</th>
    <th>Status</th>`;
    let fil = document.getElementById("empReq").value;
    if (fil == 'all') {
        populate(0);
    } else {
        populate(fil);
    }
}