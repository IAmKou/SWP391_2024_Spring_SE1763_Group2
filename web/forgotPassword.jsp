<%-- 
    Document   : forgotPassword
    Created on : Jan 11, 2024, 2:47:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password Page</title>
    </head>
    <body>
        <form action="forgotPasswordController" method="post">
        Email: <input type="text" name="mail"><br/><!-- comment -->
        <button type="submit">Submit</button>
        </form>
    </body>
</html>