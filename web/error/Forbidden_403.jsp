<%-- 
    Document   : error
    Created on : Feb 27, 2024, 5:32:40 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>403 Forbidden: </title>
    </head>
    <body>
        <h1>403 Forbidden: </h1>
        <p>You do not have permission to access this page</p>
        <a href="${pageContext.request.contextPath}\logIn.jsp"><button>Login</button></a>
        <a href="${pageContext.request.contextPath}\views/home.jsp"><button>Home</button></a>
    </body>
</html>
