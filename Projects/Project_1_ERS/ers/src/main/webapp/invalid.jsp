<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" http-equiv="refresh" content="5; URL= index.jsp">
<title>Insert title here</title>
</head>
<body>
<h1>Invalid Login, Redirecting to login...</h1>

<p><% out.println(session.getAttribute("name")); %>

</body>
</html>
