<%-- 
    Document   : updateHouse
    Created on : Jan 15, 2024, 1:14:29 PM
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
        <form method="post" action="../house/update">
            Location: <input type="text" name="location" required><br>
            <input type="hidden" value="1" name="purpose">
            <input type="hidden" value="4" name="user">
            Type: <input type="number" name="type" required><br>
            Description: <textarea name="description" required></textarea><br>
            Price: <input type="number" name="price" required><br>
            Picture: <input type="file" name="image[]" multiple><br> <!-- Thêm [] cho việc chấp nhận nhiều tệp -->
            <br>
            Area: <input type="number" name="area"><br>
            nubmer of room: <input type="number" name="number_of_room">
            <input type="submit" value="Update post">
            <h1>${alert}</h1>
        </form>
    </body>
</html>
