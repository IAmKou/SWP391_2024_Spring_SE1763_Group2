<%-- 
    Document   : signUp
    Created on : Jan 9, 2024, 8:32:40 AM
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
        <form action="registerController" method="post">
            Username: <input type="text" name="username"><br/><!-- comment -->
            Password: <input type="password" name="password"><br/>
            Confirm Password: <input type="password" name="cfpassword"><br/>
            Fullname: <input type="text" name="fullname"><br/><!-- comment -->
            Email: <input type="text" name="email"><br/>
            Phone number: <input type="number" name="phone"><br/>
            Location: <input type="text" name="location"><br/>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
