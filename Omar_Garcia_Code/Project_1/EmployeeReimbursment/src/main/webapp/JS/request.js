window.onload = function () {
    document.getElementById("GoBack").addEventListener('click', function (){
        window.history.back();
    })
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            var jsonData = JSON.parse(xhr.responseText);
            for(i = 0; i < jsonData.length; i++){
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
                mcol.textContent = jsonData[i].manager;
                apcol.textContent = jsonData[i].approved;

                row.appendChild(idcol);
                row.appendChild(tcol);
                row.appendChild(amcol);
                row.appendChild(dcol);
                row.appendChild(ucol);
                row.appendChild(mcol);
                row.appendChild(apcol);

                document.getElementById("myTable").appendChild(row);
                console.log("work");
            }
        }
    }

    xhr.open('GET', 'http://localhost:8080/EmployeeReimbursment/rest/viewAllRequests');
    xhr.send();
}