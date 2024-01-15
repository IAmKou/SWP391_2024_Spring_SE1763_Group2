<%-- 
    Document   : addHouse
    Created on : Jan 14, 2024, 6:12:30 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="../house/add">
            Location: <input type="text" name="location" required><br>
            Type: <input type="number" name="type" required><br>
            Description: <textarea name="description" required></textarea><br>
            <input type="hidden" name="ownerId" value="1"><br>
            Price: <input type="number" name="price" required><br>
            Picture: <input type="text" name="picture"><br>
            Available: <input type="checkbox" name="available" checked><br>
            <input type="submit" value="Add House">
        </form>
    </body>
</html>
