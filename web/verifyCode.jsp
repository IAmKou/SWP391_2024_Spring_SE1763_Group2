<%-- 
    Document   : verifyCode
    Created on : Jan 13, 2024, 9:33:20 PM
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
        <form action="verifyCode" method="post">
            Code : <input type="text" name="cfcode"><!-- comment -->
            <button type="submit">Submit</button>
        </form>
    </body>
</html>