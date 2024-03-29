<%-- 
    Document   : postList
    Created on : Mar 11, 2024, 4:13:55 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/profile.css"/>
        <title>JSP Page</title>
        <style>
            .modal-header {
                background-color: black;
                display: flex; /* Sử dụng flexbox để căn chỉnh phần tử con */
                justify-content: center; /* Canh giữa theo chiều ngang */
                align-items: center; /* Canh giữa theo chiều dọc */
            }

            .logo-container {
                flex: 1; /* Phần tử chứa logo sẽ mở rộng để lấp đầy không gian trống */
                text-align: center; /* Canh giữa logo trong phần tử cha */
            }
            .table-no-border {
                border: none;
            }
            .table-no-border td {
                border: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div class="container">
            <div class="container">
                <form action="${pageContext.request.contextPath}/post/list" method="post">
                    <div class="row">
                        <div class="col-md-2">
                            <label for="postID">Post ID:</label>
                            <input type="text" id="postID" class="form-control" name="post_id">
                        </div>
                        <div class="col-md-3">
                            <label for="poster">Poster:</label>
                            <select id="poster" class="form-control" name="poster">
                                <option value="">-- Select Poster --</option>
                                <c:forEach items="${accounts}" var="account">
                                    <option value="${account.getUser_id()}">${account.getUser_name()}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="postStatus">Post Status:</label>
                            <select id="postStatus" class="form-control" name="status">
                                <option value="">-- Select Post Status --</option>
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status.status_id}">${status.status_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <label for="location">Location:</label>
                            <input type="text" id="location" class="form-control" name="location">
                        </div>
                        <div class="col-md-2 btn-group align-self-end">
                            <button type="submit" class="btn btn-outline-primary">Apply</button>
                            <button  class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-backpack4" viewBox="0 0 16 16">
                                <path d="M4 9.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5zm1 .5v3h6v-3h-1v.5a.5.5 0 0 1-1 0V10z"/>
                                <path d="M8 0a2 2 0 0 0-2 2H3.5a2 2 0 0 0-2 2v1c0 .52.198.993.523 1.349A.5.5 0 0 0 2 6.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V6.5a.5.5 0 0 0-.023-.151c.325-.356.523-.83.523-1.349V4a2 2 0 0 0-2-2H10a2 2 0 0 0-2-2m0 1a1 1 0 0 0-1 1h2a1 1 0 0 0-1-1M3 14V6.937q.24.062.5.063h4v.5a.5.5 0 0 0 1 0V7h4q.26 0 .5-.063V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1m9.5-11a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-9a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1z"/>
                                </svg> ${totalPost}</button>
                        </div>
                    </div>
                </form>
            </div>
            <c:if test="${alert ne null}">
                <div class="alert alert-warning h5">                                   
                    ${alert}
                </div>
            </c:if>
            <c:if test="${success ne null}">
                <div class="alert alert-success h5">
                    ${success}
                </div>
            </c:if>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr class="center">
                            <th>Post ID</th>
                            <th>Location</th>
                            <th>Poster</th>
                            <th>House status</th>
                            <th>Post Status</th>
                            <th>Create Time</th>
                            <th>View</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${posts}" var="post">
                            <tr >
                                <td class="center">${post.getPost_id()}</td>
                                <td>${post.house.getLocation()}</td>
                                <td class="center">
                                    <c:forEach items="${accounts}" var="account">
                                        <c:if test="${account.getUser_id() == post.house.getHouse_owner().getUser_id()}">
                                            ${account.getUser_name()}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="center">${post.house_status.getStatus_name()}</td>
                                <td class="center">${post.post_status.getStatus_name()}</td>
                                <td class="center">${post.getFommated_create_time()}</td>
                                <td >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#UserModal${post.getPost_id()}">
                                            Detail
                                        </button>
                                    </div>
                                    <!--User Modal -->
                                    <div class="modal fade modal-xl" id="UserModal${post.getPost_id()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="logo-container">
                                                        <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                    </div>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-md-6 border-end">
                                                            <div id="carouselExampleIndicators_${post.post_id}" class="carousel slide" data-bs-ride="carousel">
                                                                <!-- Carousel indicators -->
                                                                <div class="carousel-indicators">
                                                                    <c:forEach items="${post.house.image}" var="image" varStatus="status">
                                                                        <button type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0 ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                                                                    </c:forEach>
                                                                </div>
                                                                <!-- Carousel items -->
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
                                                                <!-- Carousel controls -->
                                                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide="prev">
                                                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                                </button>
                                                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_${post.post_id}" data-bs-slide="next">
                                                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                                </button>
                                                            </div>
                                                            <!-- Location and House Information -->
                                                            <h5 class="card-title fs-4 text-primary mb-3 custom-card-title" ><i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}</h5>
                                                            <div>
                                                                <h5 class="main-heading">House Information</h5>
                                                                <table class="table-no-border">
                                                                    <tr>
                                                                        <td>Price: ${post.price} $</td>
                                                                        <td>Area: ${post.house.area} m<sup>2</sup></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Type: ${post.house.type_of_house.type_of_house_name}</td>
                                                                        <td>Number of Rooms: ${post.house.number_of_room}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Purpose: ${post.purpose.purpose_name}</td>
                                                                        <td>House status: ${post.house_status.status_name}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            Time create: ${post.fommated_create_time}
                                                                        </td>
                                                                        <td>

                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="description-section">
                                                                <p><span class="info-label">Description:</span> ${post.house.description}</p>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <!-- User Information -->
                                                            <div class="mb-3 text-center">
                                                                <img src="${post.house.getHouse_owner().getAvatar()}" alt="avatar" style="width: 200px; height: 200px; border-radius: 50px"/>
                                                                <c:forEach items="${accounts}" var="account">
                                                                    <c:if test="${account.getUser_id() == post.house.getHouse_owner().getUser_id()}">
                                                                        <h5>${account.getUser_name()}</h5>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                            <div class="bg-dark text-white p-1 rounded center h4">User Information</div>
                                                            <div class="mb-3">
                                                                <label for="user-id">User ID:</label>
                                                                <input type="text" class="form-control" id="user-id" value="${post.house.house_owner.getUser_id()}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="full-name">Full Name:</label>
                                                                <input type="text" class="form-control" id="full-name" value="${post.house.house_owner.getFull_name()}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="date-of-birth">Date of Birth:</label>
                                                                <input type="text" class="form-control" id="date-of-birth" value="${post.house.house_owner.getDate_of_birth()}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="email">Email:</label>
                                                                <input type="email" class="form-control" id="email" value="${post.house.house_owner.getEmail()}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="phone-number">Phone Number:</label>
                                                                <input type="text" class="form-control" id="phone-number" value="${post.house.house_owner.getPhone_number()}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="address">Address:</label>
                                                                <textarea class="form-control" id="address" rows="3" readonly>${post.house.house_owner.getAddress()}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td class="center">
                                    <c:choose>
                                        <c:when test="${post.post_status.getStatus_id() eq 1}">
                                            <div class="btn-group"> 
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#acceptBackdrop${post.getPost_id()}">Accept</button>
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cancelBackdrop${post.getPost_id()}">Cancel</button>

                                            </div>                                            
                                            <!-- accept Modal -->
                                            <div class="modal fade" id="acceptBackdrop${post.getPost_id()}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <div class="logo-container">
                                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                            </div>
                                                        </div>
                                                        <div class="modal-body center">
                                                            <form action="${pageContext.request.contextPath}/post/status" method="post">
                                                                <p>Are your sure for accept this post?</p>
                                                                <input type="hidden" name="postId" value="${post.getPost_id()}">
                                                                <input type="hidden" name="statusId" value="2">
                                                                <div class="modal-footer">
                                                                    <button class="btn btn-outline-danger">Submit</button>
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- cancel Modal -->
                                            <div class="modal fade" id="cancelBackdrop${post.getPost_id()}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <div class="logo-container">
                                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                            </div>                                                            
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="${pageContext.request.contextPath}/post/status" method="post">
                                                                <p>Are you sure for cancel this post?</p>
                                                                <input type="hidden" name="postId" value="${post.getPost_id()}">
                                                                <input type="hidden" name="statusId" value="3">
                                                                <textarea class="form-control" name="message" rows="3" cols="10" placeholder="Please enter your message" required></textarea>  
                                                                <div class="modal-footer">
                                                                    <button class="btn btn-outline-danger">Submit</button>
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                </div>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                        </c:when>
                                    </c:choose>                            
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${ not empty totalPages}">
                <nav aria-label="Page navigation example" class="m-3">
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="../post/list?page=${currentPage - 1}" aria-label="Previous">
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
                                <a class="page-link" href="../post/list?page=${page}">${page}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <a class="page-link" href="../post/list?page=${currentPage + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
        <jsp:include page="../footer.jsp"/>
    </body>
</html>
