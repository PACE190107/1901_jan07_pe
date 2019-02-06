<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Manager HomePage</title>
</head>

<body>
    <h1 id='welcome'>Welcome Manager,
        <%= session.getAttribute("username") %>
    </h1>
    <form action="viewEmployees.jsp">
        <button>View Employees</button>
    </form>
    <form action='viewRequest.jsp'>
        <button id='vp'>View Pending Request</button>
    </form>
    <form>
        <button id='vr'>View Resolved Request</button>
    </form>
    <form>
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
    </form>
    <script>
            window.onload = function () {
                <% 
                session.setAttribute("rType", "1");
                %>
                console.log(<%= session.getAttribute("rType") %>);
                
            }
            document.getElementById('vp').addEventListener('click', function () {
                <%
                session.setAttribute("rType", "2"); 
                %>
                console.log(<%= session.getAttribute("rType")%>); 
            })
            document.getElementById('vr').addEventListener('click', function () {
                <%
                session.setAttribute("rType", "3"); 
                %>
                console.log(<%= session.getAttribute("rType")%>); 
            })
        </script>
</body>

</html>