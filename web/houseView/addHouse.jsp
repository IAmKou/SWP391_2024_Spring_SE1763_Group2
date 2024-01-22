<%-- 
    Document   : addHouse
    Created on : Jan 14, 2024, 6:12:30 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            Price: <input type="number" name="price" required><br>
            Picture: <input type="file" name="image" multiple><br>
            <br>
            <input type="submit" value="Add House">
            <h1>${alert}</h1>
        </form>
    </body>
</html>
