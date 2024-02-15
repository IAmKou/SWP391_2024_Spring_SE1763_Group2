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
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/profile.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="container">
            <div class=" row">
                <div class="col-md-3 sidebar">
                    <nav class="sdb_holder">
                        <ul>
                            <li><a href="profile.html" style="font-weight: bold;">Account Information</a></li>
                            <li><a href="${pageContext.request.contextPath}/order/view">View Orders</a>
                                <ul>
                                    <c:forEach items="${statuses}" var="status">
                                        <li><a href="${pageContext.request.contextPath}/status?status=${status.status_id}">${status.status_name}</a></li>
                                        </c:forEach>
                                </ul>
                            </li>
                            <li><a href="#">View Rentals</a>
                                <ul>
                                    <li><a href="#">Your Post</a></li>
                                    <li><a href="#">Customer's Requests</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-9">
                    <c:if test="${not empty posts}">
                        <c:forEach items="${posts}" var="post">
                            <div class="card m-2">
                                <div class="row no-gutters">
                                    <div class="col-md-9">
                                        <div class="card-body">
                                            <p class="fs-4 text-primary mb-3">${post.house.location}</p>
                                            <p class="card-text"style="color: black">
                                                Price: ${post.price} $<br>
                                                <span class=" status">${post.post_status.status_name}</span>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <img src="${pageContext.request.contextPath}/images/house1.jpg" alt="hinh anh" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-9">

            <a href="userProfileUpdate.jsp" class="btn btn-primary">Go to Update Profile</a>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>