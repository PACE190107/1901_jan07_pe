<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title id="title">Employee HomePage</title>
</head>

<body>
    <h1 id='welcome'>Welcome Employee,
        <%= session.getAttribute("username") %>
    </h1>
    <form action="createRequest.jsp">
        <button>Submit new request</button>
    </form>
    <form action="viewRequest.jsp">
        <button type="submit">View all request</button>
    </form>
    <form action="HTML/managerPage.html">
        <h5>username,
            <%= session.getAttribute("username") %>
        </h5>
        <h5>first name,
            <%= session.getAttribute("firstname") %>
        </h5>
        <h5>last name,
            <%= session.getAttribute("lastname") %>
        </h5>
        <h5>email,
            <%= session.getAttribute("email") %>
        </h5>
        <h5>id,
            <%= session.getAttribute("id") %>
        </h5>
        <h5>manager,
            <%= session.getAttribute("manager") %>
        </h5>
        <button type="submit">Click</button>
    </form>
    <script>
        window.onload = function () {
            <% session.setAttribute("rType", "0"); %> 
            console.log(<%= session.getAttribute("rType") %>);
        }
    </script>
</body>

</html>