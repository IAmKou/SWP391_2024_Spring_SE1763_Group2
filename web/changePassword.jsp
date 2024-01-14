<%-- 
    Document   : changePassword
    Created on : Jan 11, 2024, 2:47:30 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Page</title>
    </head>
    <body>
        ${requestScope.mess1}
        <form action="changePasswordController" method="post">
        Old Password : <input type="text" name="oldpass"><br/><!-- comment -->
        New Password : <input type="text" name="newpass"><br/><!-- comment -->
        Confirm Password : <input type="text" name="cfpass"><br/>
        <button type="submit">Submit</button>
        </form>
    </body>
</html>
