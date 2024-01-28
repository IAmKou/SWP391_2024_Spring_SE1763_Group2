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
        <form action="addUserController" method="post">
            Fullname: <input type="text" name="fullname"><br/><!-- comment -->
            DoB: <input type="date" name="dob"><br/>
            Address: <input type="text" name="address"><br/>
            Phone number: <input type="number" name="phone"><br/>
            Email: <input type="text" name="email"><br/>          
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
