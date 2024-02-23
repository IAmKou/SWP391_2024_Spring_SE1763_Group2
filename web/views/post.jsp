<%-- 
    Document   : post
    Created on : Feb 18, 2024, 6:05:44 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/post.css"/>
        <title>Post</title>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.min.js"></script>
        <style>
            .table-no-border {
                border: none;
            }
            .table-no-border td {
                border: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h2 class="text-center">Post Information</h2>
            <div class="card">
                <div id="carouselExampleIndicators" class="carousel slide">

                    <div class="carousel-inner" style="margin-bottom: 20px">
                        <div class="carousel-item active">
                            <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: First slide" preserveAspectRatio="xMidYMid slice" focusable="false">
                            <rect width="100%" height="100%" fill="#A0A0A0"></rect>
                            <image xlink:href="images/house1.jpg" width="100%" height="100%"  alt="hinh anh"/>
                            </svg>
                        </div>
                        <div class="carousel-item">
                            <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Second slide" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#666"></rect><text x="50%" y="50%" fill="#444" dy=".3em">Second slide</text></svg>
                        </div>
                        <div class="carousel-item">
                            <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Third slide" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#555"></rect><text x="50%" y="50%" fill="#333" dy=".3em">Third slide</text></svg>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>
                <h5 class=" fs-4 text-primary mb-3 custom-card-title">
                    <i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}
                </h5>
                <div class="profile-container">
                    <div class="avatar-container">
                        <img src="${pageContext.request.contextPath}/images/person_1.jpg" alt="Avatar" class="avatar">
                    </div>
                    <div class="user-info-container">
                        <p class="username">${sessionScope.account.getFull_name()}</p>
                        <details>
                            <summary>Contact Information</summary><br>
                            <p><i class="fas fa-phone" style="color: blue;"></i> ${post.house.house_owner.phone_number}</p>
                            <p><i class="fas fa-envelope" style="color: red;"></i> ${post.house.house_owner.email}</p>
                        </details>
                    </div>
                </div>
                <table class="table-no-border">
                    <tbody>
                        <tr>
                            <td><strong>Price: </strong>${post.price}</td>
                            <td><strong>Area: </strong>${post.house.area} m<sup>2</sup></td>
                        </tr>
                        <tr>
                            <td><strong>Rooms: </strong>${post.house.number_of_room}</td>
                            <td><strong>Purpose: </strong>${post.purpose.purpose_name}</td>
                        </tr>
                        <tr>
                            <td><strong>Status: </strong>${post.house_status.status_name}</td> 
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <p><strong>Description: </strong><br>${post.house.description}</p>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
