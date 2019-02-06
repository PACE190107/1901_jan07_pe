<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Create Request</title>
</head>

<body>
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
        <button id="reqButton" type="submit" onclick="submit()">Submit</button>
    </div>
    <script type="text/javascript" src="../JS/employee.js"></script>
</body>
<script>
    submit = function () {
        var reqtype = document.getElementById("reqType");
        var reqamount = document.getElementById("reqAmount");
        var reqdescription = document.getElementById("description");
        if (reqamount.value > 0) {
            console.log("valid inputs \n" 
            + "Type " + reqtype.value + 
            "\nAmount " + reqamount.value + 
             "\nDescription " + reqdescription.value +
             "\nUserID " + <%= session.getAttribute("id") %>);
        }
    };
</script>

</html>