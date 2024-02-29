<%-- 
    Document   : userPostList
    Created on : Feb 29, 2024, 9:08:59 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/profile.css"/>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
        <title>User Post</title>
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
        <jsp:include page="../header.jsp"/>

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
                                    <li><a href="${pageContext.request.contextPath}/booking/view">My Requests</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Customer's Requests</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">Request history</a></li>
                                </ul>
                            </li>   
                        </ul>
                    </nav>
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 mb-4">
                            <form id="filterForm" action="${pageContext.request.contextPath}/post/view/fillter" method="get">
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
                                    <div class="col-md-3 align-self-end btn-group">
                                        <button type="submit" class="btn btn-primary">Apply Filters</button>
                                        <div class="btn btn-info">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-backpack4" viewBox="0 0 16 16">
                                            <path d="M4 9.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5zm1 .5v3h6v-3h-1v.5a.5.5 0 0 1-1 0V10z"/>
                                            <path d="M8 0a2 2 0 0 0-2 2H3.5a2 2 0 0 0-2 2v1c0 .52.198.993.523 1.349A.5.5 0 0 0 2 6.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V6.5a.5.5 0 0 0-.023-.151c.325-.356.523-.83.523-1.349V4a2 2 0 0 0-2-2H10a2 2 0 0 0-2-2m0 1a1 1 0 0 0-1 1h2a1 1 0 0 0-1-1M3 14V6.937q.24.062.5.063h4v.5a.5.5 0 0 0 1 0V7h4q.26 0 .5-.063V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1m9.5-11a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-9a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1z"/>
                                            </svg> ${totalPost}${fillterTotalPost}
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>
                        <c:if test="${not empty ownerPost}">
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
                                                            Time created: ${ownerPost.fommated_create_time}
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
                    </div>
                    <div>
                        <c:if test="${fillterTotalPages ne null}">
                            <nav aria-label="Page navigation example" class="m-3">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link" href="../post/view/fillter?page=${currentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="1" end="${fillterTotalPages}" var="page">
                                        <c:set var="activeClass" value="" />
                                        <c:if test="${currentPage eq page}">
                                            <c:set var="activeClass" value="active" />
                                        </c:if>
                                        <li class="page-item ${activeClass}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/post/view/fillter?page=${page}">${page}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link" href="../post/view/fillter?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>

                        <c:if test="${totalPages ne null}">
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
                    </div>    
                </c:if>
            </div>
        </div>

        <jsp:include page="../footer.jsp"/>
    </body>
</html>
