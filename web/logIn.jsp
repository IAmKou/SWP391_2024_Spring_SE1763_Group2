<%-- 
    Document   : logIn
    Created on : Jan 10, 2024, 12:47:07 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="logInController" method="post">
        Username : <input type="text" name="username"><br/><!-- comment -->
        Password : <input type="password" name="password"><br/><!-- comment -->
        <button type="submit">Submit</button>
        </form>
    </body>
</html>
