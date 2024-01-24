<%-- 
    Document   : userProfile
    Created on : Jan 12, 2024, 4:18:34 PM
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
        <h1>User Profile</h1>
        <form action="updateUser" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" name="fullName" id="fullName" value="<%= request.getParameter("fullName") %>"><br>
            
            <label for="userName">User Name:</label>
            <input type="text" name="userName" id="userName" value="<%= request.getParameter("userName") %>"><br>
            
            <label for="passWord">Password:</label>
            <input type="password" name="passWord" id="passWord" value="<%= request.getParameter("passWord") %>"><br>
            
            <label for="location">Location:</label>
            <input type="text" name="location" id="location" value="<%= request.getParameter("location") %>"><br>
            
            <label for="phone">Phone:</label>
            <input type="text" name="phone" id="phone" value="<%= request.getParameter("phone") %>"><br>
            
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" value="<%= request.getParameter("email") %>"><br>
            
            <input type="submit" value="Update">
        </form>
        
        <a href="changePassword.jsp">Change Password</a>
    </body>
</html>