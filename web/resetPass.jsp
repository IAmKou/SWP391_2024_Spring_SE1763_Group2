<%-- 
    Document   : resetPass
    Created on : Jan 14, 2024, 6:40:13 PM
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
        <form action="resetPassController" method="post">
        New Password : <input type="text" name="newpass"><br/><!-- comment -->
        Confirm Password : <input type="text" name="cfpass"><br/>
        <button type="submit">Submit</button>
        </form>
    </body>
</html>