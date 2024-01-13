<%-- 
    Document   : changePassword
    Created on : Jan 11, 2024, 2:47:30 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Page</title>
    </head>
    <body>
        <form action="changePasswordController" method="post">
        Old Password : <input type="password" name="oldpass"><br/><!-- comment -->
        New Password : <input type="password" name="newpass"><br/><!-- comment -->
        Confirm Password : <input type="password" name="cfpass"><br/>
        <button type="submit">Submit</button>
        </form>
    </body>
</html>
