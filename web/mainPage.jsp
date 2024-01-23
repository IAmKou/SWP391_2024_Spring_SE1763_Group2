<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        
        <form action="search-house" method="post">
            search hourse
            <input type="text" name="search">
            <input type="submit" value="search" />
        </form>
    </body>
</html>
