<%-- 
    Document   : mainPage
    Created on : Jan 9, 2024, 8:57:59 AM
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
        ${requestScope.acc.passWord}
        <a href="signUp.jsp">Sign Up</a><br/><!-- comment -->
        <a href="logIn.jsp">Log In</a><br/><!-- comment -->
        <a href="userProfile.jsp">Profile</a>
        <form action="logOutController">
            <button type="submit">Log Out</button>
        </form>
    </body>
</html>
