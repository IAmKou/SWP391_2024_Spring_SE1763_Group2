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
                            <li><a href="#">View Rentals</a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/status?status=1" >Pending Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/status?status=3" >Declined Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/post/view">Your Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/booking/view">My Order</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Customer's Order</a></li>
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
                                                    <div class="btn-group">
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${post.post_id}">Overview</button>
                                                        <c:if test="${post.post_status.status_id eq 3}">
                                                            <a href="${pageContext.request.contextPath}/post/update?post_id=${post.post_id}" class="btn btn-outline-info">Update</a>
                                                            <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#messageStaticBackdrop_${post.post_id}">
                                                                Message
                                                            </button>                                                            
                                                        </c:if>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="messageStaticBackdrop_${post.post_id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body" style="color: black">
                                                                        <label class="form-control">Admin response: ${post.admin_message}</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-3 right">
                                                <div>
                                                    <span class="btn status" style="background-color:
                                                          <c:choose>
                                                              <c:when test="${post.post_status.status_id eq 1}">silver</c:when>
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
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
