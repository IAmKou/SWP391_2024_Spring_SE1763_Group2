<%-- 
    Document   : viewPost
    Created on : Jan 23, 2024, 9:32:00 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${posts}" var="post">
            <p>${post.post_id}</p>
            <p>${post.price}</p>
            <p>${post.purpose.purpose_name}</p>
            <p>${post.house_status.status_name}</p>
            <p>${post.post_status.status_name}</p>
            <p>${post.house.house_id}</p>
            <p>${post.house.location}</p>
            <p>${post.house.type_of_house.type_of_house_name}</p>
            <p>${post.house.description}</p>
            <p>${post.house.area}</p>
            <p>${post.house.number_of_room}</p>
            <p>${post.house.house_owner.full_name}</p>
        </c:forEach>
            <h1>this is request</h1>
            <c:forEach items="${statuses}" var="status">
                <p>${status.status_name}</p>
            </c:forEach>
    </body>
</html>
