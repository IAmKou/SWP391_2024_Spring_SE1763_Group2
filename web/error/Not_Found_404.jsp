<%-- 
    Document   : error
    Created on : Feb 27, 2024, 5:32:40 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404: Not Found</title>
        
    </head>
    <body>
        
        
        <h1>${h1}</h1>
        <p>${p}</p>
        
        
        <a href="${pageContext.request.contextPath}${oldLink}"><button>Go Back</button></a>
        <a href="${pageContext.request.contextPath}\views/home.jsp"><button>Home</button></a>
    </body>
</html>
