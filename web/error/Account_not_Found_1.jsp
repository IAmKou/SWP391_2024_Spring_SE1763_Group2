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
        <title>Account not Found</title>
    </head>
    <body>
        <h1>Account not Found</h1>
        <p>You need to Login to continue</p>
        <a href="${pageContext.request.contextPath}\logIn.jsp"><button>Login</button></a>
        <a href="${pageContext.request.contextPath}\views/home.jsp"><button>Home</button></a>
    </body>
</html>
