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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">        

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>        
        <style>
            .table-no-border {
                border: none;
            }
            .table-no-border td {
                border: none;
            }
        </style>
        <script>
            
            function confirmDelete(houseId) {
                var confirmDelete = confirm("Are you sure for delete this post?");
                if (confirmDelete) {
                    deletePost(houseId);
                }
            }
            
            function deletePost(houseId) {
                var xhr = new XMLHttpRequest();
                var url = "../post/delete?house_id=" + houseId;
                xhr.open("GET", url, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            confirm("Delete successfully!");
                            location.reload();
                        } else {
                            confirm("An error occur went delete post!");
                        }
                    }
                };
                xhr.send();
            }

            function applyFilters() {
                var price = document.getElementById('price').value;
                var purpose = document.getElementById('purpose').value;
                var date = document.getElementById('date').value;

                var xhr = new XMLHttpRequest();
                var url = "../post/view?price=" + price + "&purpose=" + purpose + "&date=" + date;
                xhr.open("GET", url, true);
                xhr.send();
            }

        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="container">
            <div class=" row">
                <div class="col-md-2 sidebar">
                    <nav class="sdb_holder mb-4">
                        <ul>
                            <li><a href="#" style="font-weight: bold;">Account Information</a></li>
                            <li><a href="#">View Status</a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/status?status=1" >Pending</a></li>
                                    <li><a href="${pageContext.request.contextPath}/status?status=3" >Declined</a></li>
                                </ul>
                            </li>
                            <li><a href="#">View Rentals</a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/post/view">Your Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Customer's Requests</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Request history</a></li>
                                </ul>
                            </li>   
                        </ul>
                    </nav>
                </div>
                <div class="col-md-10 ">
                    <!--This is for view status-->
                    <c:if test="${not empty posts}">
                        <c:forEach items="${posts}" var="post">
                            <div class="m-2 custom-card">
                                <div class="row no-gutters">
                                    <div class="col-md-4">
                                        <div id="carouselExampleFade_${post.post_id}" class="carousel slide carousel-fade">
                                            <div class="carousel-inner">
                                                <c:forEach items="${post.house.image}" var="image" varStatus="loop">
                                                    <c:if test="${not empty image}">
                                                        <div class=" carousel-item ${loop.first ? 'active' : ''} img-container">
                                                            <img src="data:image/jpeg;base64,${image.getImageDataAsBase64()}" class="d-block w-100" alt="hinh anh" style="width: 350px; height: 200px; border-radius: 5px"/>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade_${post.post_id}" data-bs-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="visually-hidden">Previous</span>
                                            </button>
                                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade_${post.post_id}" data-bs-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="visually-hidden">Next</span>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="row">
                                            <div class="col-9">
                                                <div class="custom-card-body">
                                                    <h5 class="card-title fs-4 text-primary mb-3 custom-card-title">
                                                        <i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}
                                                    </h5>
                                                    <p class="custom-card-text">
                                                        Price: ${post.price} $ &#124; For ${post.purpose.purpose_name}
                                                    </p>
                                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${post.post_id}">Overview</button>
                                                </div>
                                            </div>
                                            <div class="col-3 right">
                                                <div>
                                                    <span class="btn status" style="background-color:
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
                                            <!-- Modal -->
                                            <div class="modal fade" id="staticBackdrop_${post.post_id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                                                                                <div id="carouselExampleIndicators_${post.post_id}" class="carousel slide" data-bs-ride="carousel">
                                                                                    <div class="carousel-indicators">
                                                                                        <c:forEach items="${post.house.image}" var="image" varStatus="status">
                                                                                            <button type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0 ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                                                                                        </c:forEach>
                                                                                    </div>

                                                                                    <div class="carousel-inner" style="margin-bottom: 20px">
                                                                                        <c:forEach items="${post.house.image}" var="image" varStatus="status">
                                                                                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                                                                                <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Slide ${status.index + 1}" preserveAspectRatio="xMidYMid slice" focusable="false">
                                                                                                <rect width="100%" height="100%" fill="#3071BC" rx="10" ry="10"></rect>
                                                                                                <image xlink:href="data:image/jpeg;base64,${image.getImageDataAsBase64()}" width="100%" height="100%"  alt="hinh anh"/>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </c:forEach>
                                                                                    </div>

                                                                                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide="prev">
                                                                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                                                    </button>
                                                                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide="next">
                                                                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                                                    </button>
                                                                                </div>
                                                                                <h5 class="card-title fs-4 text-primary mb-3 custom-card-title" ><i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}</h5>
                                                                                <div>
                                                                                    <h5 class="main-heading">House Information</h5>
                                                                                    <table class="table-no-border">
                                                                                        <tr>
                                                                                            <td>Price: ${post.price} $</td>
                                                                                            <td>Area: ${post.house.area} m<sup>2</sup></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>House Type: ${post.house.type_of_house.type_of_house_name}</td>
                                                                                            <td>Number of Rooms: ${post.house.number_of_room}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>Purpose: ${post.purpose.purpose_name}</td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </div>
                                                                                <div class="description-section">
                                                                                    <p><span class="info-label">Description:</span> ${post.house.description}</p>
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
                        <!-- Hiển thị các nút điều hướng phân trang -->
                        <nav aria-label="Page navigation example" class="m-3">
                            <ul class="pagination">
                                <c:set var="status" value="${param.status}" />
                                <li class="page-item">
                                    <a class="page-link" href="status?page=${currentPage - 1}&status=${status}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${totalPages}" var="page">
                                    <c:set var="activeClass" value="" />
                                    <c:if test="${currentPage eq page}">
                                        <c:set var="activeClass" value="active" />
                                    </c:if>
                                    <li class="page-item ${activeClass}">
                                        <a class="page-link" href="status?page=${page}&status=${status}">${page}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item">
                                    <a class="page-link" href="status?page=${currentPage + 1}&status=${status}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>

                    <!--This is for view owner post-->
                    <c:if test="${not empty ownerPost}">
                        <div class="row">
                            <!-- Filter -->
                            <div class="row m-4">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="price">Price:</label>
                                        <input type="number" class="form-control" id="price" name="price" placeholder="Enter your price">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="purpose">Purpose:</label>
                                        <select class="form-control" id="purpose" name="purpose">
                                            <option value="">Select purpose</option>
                                            <c:forEach items="${purposes}" var="purpose">
                                                <option value="${purpose.purpose_id}">${purpose.purpose_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="date">Date:</label>
                                        <input type="date" class="form-control" id="date" name="date">
                                    </div>
                                </div>
                                <div class="col-md-3 align-self-end">
                                    <button class="btn btn-primary" onclick="applyFilters()">Apply Filters</button>
                                </div>
                            </div>

                            <c:forEach items="${ownerPost}" var="ownerPost">
                                <div class="col-md-12 mb-4">
                                    <div class="card m-2 custom-card">
                                        <div class="row no-gutters">
                                            <div class="col-4">
                                                <div id="carouselExampleFade_${ownerPost.post_id}" class="carousel slide carousel-fade">
                                                    <div class="carousel-inner">
                                                        <c:forEach items="${ownerPost.house.image}" var="image" varStatus="loop">
                                                            <c:if test="${not empty image}">
                                                                <div class=" carousel-item ${loop.first ? 'active' : ''} img-container">
                                                                    <img src="data:image/jpeg;base64,${image.getImageDataAsBase64()}" class="d-block w-100" alt="hinh anh" style="width: 350px; height: 250px; border-radius: 5px"/>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade_${ownerPost.post_id}" data-bs-slide="prev">
                                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                        <span class="visually-hidden">Previous</span>
                                                    </button>
                                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade_${ownerPost.post_id}" data-bs-slide="next">
                                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                        <span class="visually-hidden">Next</span>
                                                    </button>
                                                </div>
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
                                                        <p class="custom-card-text">
                                                            Time created: ${ownerPost.create_time}
                                                        </p>
                                                    </div>

                                                    <div class="btn-group">
                                                        <a href="${pageContext.request.contextPath}/post/update?post_id=${ownerPost.post_id}" class="btn btn-outline-info">Update</a>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${ownerPost.post_id}">Overview</button>
                                                        <button id="deleteButton" class="btn btn-danger" onclick="confirmDelete(${ownerPost.house.house_id})">Delete</button>
                                                    </div>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="staticBackdrop_${ownerPost.post_id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                                                                                        <div id="carouselExampleIndicators_${ownerPost.post_id}" class="carousel slide" data-bs-ride="carousel">
                                                                                            <div class="carousel-indicators">
                                                                                                <c:forEach items="${ownerPost.house.image}" var="image" varStatus="status">
                                                                                                    <button type="button" data-bs-target="#carouselExampleIndicators_${ownerPost.post_id}" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0 ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                                                                                                </c:forEach>
                                                                                            </div>

                                                                                            <div class="carousel-inner" style="margin-bottom: 20px">
                                                                                                <c:forEach items="${ownerPost.house.image}" var="image" varStatus="status">
                                                                                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                                                                                        <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Slide ${status.index + 1}" preserveAspectRatio="xMidYMid slice" focusable="false">
                                                                                                        <rect width="100%" height="100%" fill="#3071BC" rx="10" ry="10"></rect>
                                                                                                        <image xlink:href="data:image/jpeg;base64,${image.getImageDataAsBase64()}" width="100%" height="100%"  alt="hinh anh"/>
                                                                                                        </svg>
                                                                                                    </div>
                                                                                                </c:forEach>
                                                                                            </div>

                                                                                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_${ownerPost.post_id}" data-bs-slide="prev">
                                                                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                                                            </button>
                                                                                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_${ownerPost.post_id}" data-bs-slide="next">
                                                                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                                                            </button>
                                                                                        </div>
                                                                                        <h5 class="card-title fs-4 text-primary mb-3 custom-card-title" ><i class="fas fa-map-marker-alt mr-2"></i> ${ownerPost.house.location}</h5>
                                                                                        <div>
                                                                                            <h5 class="main-heading">House Information</h5>
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
                        <!-- Hiển thị các nút điều hướng phân trang -->
                        <nav aria-label="Page navigation example" class="m-3">
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="../post/view?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${totalPages}" var="page">
                                    <c:set var="activeClass" value="" />
                                    <c:if test="${currentPage eq page}">
                                        <c:set var="activeClass" value="active" />
                                    </c:if>
                                    <li class="page-item ${activeClass}">
                                        <a class="page-link" href="../post/view?page=${page}">${page}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item">
                                    <a class="page-link" href="../post/view?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
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
                                                    <p class="custom-card-text">Booking ID: ${request.booking_id}</p>
                                                    <p class="custom-card-text">Booking Date: ${request.booking_date}</p>
                                                </div>
                                                <div class="col-md-4 mt-1">
                                                    <button class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${request.booking_id}">View Detail</button>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="staticBackdrop_${request.booking_id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <h5 class="card-title fs-4 text-primary mb-3 custom-card-title" ><i class="fas fa-map-marker-alt mr-2"></i> ${request.house.location}</h5>
                                                                    <p class="custom-card-text">Booking ID: ${request.booking_id}</p>
                                                                    <p class="custom-card-text">Booking date: ${request.booking_date}</p>
                                                                    <p class="custom-card-text">Customer name: ${request.user.full_name}</p>
                                                                    <details>
                                                                        <summary class="custom-card-text">Contact information</summary><br>
                                                                        <p class="custom-card-text"><i class="fas fa-phone" style="color: blue;"></i> ${request.user.phone_number}</p>
                                                                        <p class="custom-card-text"><i class="fas fa-envelope" style="color: red;"></i> ${request.user.email}</p>
                                                                    </details>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class=" col-md-12 btn-group mb-1">
                                            <button class="btn btn-success">Accept</button>
                                            <button class="btn btn-danger">Refuse</button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Hiển thị các nút điều hướng phân trang -->
                        <nav aria-label="Page navigation example" class="m-3">
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="../request/view?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${totalPages}" var="page">
                                    <c:set var="activeClass" value="" />
                                    <c:if test="${currentPage eq page}">
                                        <c:set var="activeClass" value="active" />
                                    </c:if>
                                    <li class="page-item ${activeClass}">
                                        <a class="page-link" href="../request/view?page=${page}">${page}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item">
                                    <a class="page-link" href="../request/view?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>

                    </c:if>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
