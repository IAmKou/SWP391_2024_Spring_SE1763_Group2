<%-- 
    Document   : account
    Created on : Jan 23, 2024, 9:09:59 AM
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
        <form action="addAccountController" method="post">
            <input type="hidden" name="email" value=${requestScope.email}>
            Username: <input type="text" name="username"><br/><!-- comment -->
            Password: <input type="password" name="pass"><br/>
            Confirm password: <input type="password" name="cfpass"><br/>         
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
