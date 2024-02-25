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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
        </script>
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
            <div class=" row">
                <div class="col-md-2 sidebar">
                    <nav class="sdb_holder mb-4">
                        <ul>
                            <li><a href="#" style="font-weight: bold;">Account Information</a></li>
                            <li><a href="#">View Orders</a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/status?status=1">Pending</a></li>
                                    <li><a href="${pageContext.request.contextPath}/status?status=2">Accepted</a></li>
                                    <li><a href="${pageContext.request.contextPath}/status?status=3">Rejected</a></li>
                                    <li><a href="${pageContext.request.contextPath}/status?status=6">Successful</a></li>
                                </ul>
                            </li>
                            <li><a href="#">View Rentals</a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/post/view">Your Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Customer's Requests</a></li>
                                </ul>
                            </li>   
                        </ul>
                    </nav>
                </div>
                <div class="col-md-10">
                    <!--This is for view order-->
                    <c:if test="${not empty posts}">
                        <c:forEach items="${posts}" var="post">
                            <div class="m-2 custom-card">
                                <div class="row no-gutters">
                                    <div class="col-md-4">
                                        <img src="${pageContext.request.contextPath}/images/house1.jpg" alt="hinh anh" class="card-img img-fluid custom-img">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="custom-card-body">
                                            <h5 class="card-title fs-4 text-primary mb-3 custom-card-title">
                                                <i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}
                                            </h5>
                                            <p class="custom-card-text">
                                                Price: ${post.price} $ &#124; For ${post.purpose.purpose_name}
                                            </p>
                                            <div>
                                                <span class=" status" style="background-color:
                                                      <c:choose>
                                                          <c:when test="${post.post_status.status_id eq 1}">silver</c:when>
                                                          <c:when test="${post.post_status.status_id eq 2}">green</c:when>
                                                          <c:when test="${post.post_status.status_id eq 3}">lightcoral</c:when>
                                                          <c:otherwise>black</c:otherwise>
                                                      </c:choose>;">
                                                    ${post.post_status.status_name}
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <!--This is for view owner post-->
                    <c:if test="${not empty ownerPost}">
                        <div class="row">
                            <c:forEach items="${ownerPost}" var="ownerPost">
                                <div class="col-md-12 mb-4">
                                    <div class="card m-2 custom-card">
                                        <div class="row no-gutters">
                                            <div class="col-4">
                                                <img src="${pageContext.request.contextPath}/images/house1.jpg" alt="hinh anh" class="card-img img-fluid custom-img">
                                            </div>
                                            <div class="col-8">
                                                <div class="custom-card-body">
                                                    <h5 class="card-title fs-4 text-primary mb-3 custom-card-title">
                                                        <i class="fas fa-map-marker-alt mr-2"></i> ${ownerPost.house.location}
                                                    </h5>
                                                    <div>
                                                        <p class=" custom-card-text">
                                                            Price: ${ownerPost.price} $ &#124; For ${ownerPost.purpose.purpose_name}
                                                        </p>
                                                    </div>

                                                    <div class="btn-group">
                                                        <a href="${pageContext.request.contextPath}/post/update?post_id=${ownerPost.post_id}" class="btn btn-outline-info">Update</a>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Overview</button>
                                                    </div>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-xl">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <div class="logo-container">
                                                                        <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>

                                                                    </div>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="container">
                                                                        <div class="row">
                                                                            <div class="col-md-12 mb-4">
                                                                                <div class="card m-2">
                                                                                    <div class="card-body" style="color: black;" >
                                                                                        <div id="carouselExampleIndicators" class="carousel slide">

                                                                                            <div class="carousel-inner" style="margin-bottom: 20px">
                                                                                                <div class="carousel-item active">
                                                                                                    <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: First slide" preserveAspectRatio="xMidYMid slice" focusable="false">
                                                                                                    <rect width="100%" height="100%" fill="#A0A0A0"></rect>
                                                                                                    <image xlink:href="../images/house1.jpg" width="100%" height="100%"  alt="hinh anh"/>
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
                                                                                        <h5 class="card-title fs-4 text-primary mb-3 custom-card-title" ><i class="fas fa-map-marker-alt mr-2"></i> ${ownerPost.house.location}</h5>
                                                                                        <div>
                                                                                            <table class="table-no-border">
                                                                                                <tr>
                                                                                                    <td>Price: ${ownerPost.price} $</td>
                                                                                                    <td>Area: ${ownerPost.house.area} m<sup>2</sup></td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>House Type: ${ownerPost.house.type_of_house.type_of_house_name}</td>
                                                                                                    <td>Number of Rooms: ${ownerPost.house.number_of_room}</td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>Purpose: ${ownerPost.purpose.purpose_name}</td>
                                                                                                </tr>
                                                                                            </table>
                                                                                        </div>
                                                                                        <div class="description-section">
                                                                                            <p><span class="info-label">Description:</span> ${ownerPost.house.description}</p>
                                                                                        </div>
                                                                                        <details>
                                                                                            <summary>Contact Information</summary><br>
                                                                                            <p><i class="fas fa-phone" style="color: blue;"></i> ${ownerPost.house.house_owner.phone_number}</p>
                                                                                            <p><i class="fas fa-envelope" style="color: red;"></i> ${ownerPost.house.house_owner.email}</p>
                                                                                        </details>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <!--this is for view request of user-->
                    <c:if test="${not empty requests}">
                        <div class="row">
                            <c:forEach items="${requests}" var="request">
                                <div class="col-md-5 m-2 card">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <p>Booking ID: ${request.booking_id}</p>
                                                    <p>Booking Date: ${request.booking_date}</p>
                                                </div>
                                                <div class="col-md-4 mt-1">
                                                    <button class="btn btn-outline-info">View Detail</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class=" col-md-12 btn-group mb-1">
                                            <button class="btn btn-success">Accept</button>
                                            <button class="btn btn-danger">Cancel</button>
                                        </div>

                                    </div>

                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
