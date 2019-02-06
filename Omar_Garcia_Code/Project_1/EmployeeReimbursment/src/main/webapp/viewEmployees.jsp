<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
        crossorigin="anonymous">
    <title>View all Employees</title>
</head>

<body class="container">
    <div>
        <h1>Requests</h1>
        </h1>
    </div>
    <div>
        <table class="table table-bordered table-striped">
            <tbody id="myTable">
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Username</th>
                <th>Manager</th>
                <th>Email</th>
            </tbody>
        </table>
    </div>
    <script>
      
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(xhr.responseText);
                    var jsonData = JSON.parse(xhr.responseText);
                    for(i = 0; i < jsonData.length; i++){
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
                        console.log("work");
                    }
                }
            }

            xhr.open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllUsers');
            xhr.send();
            window.onload = function () {
        }
    </script>
</body>

</html>