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
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .table-no-border {
                border: none;
            }
            .table-no-border td {
                border: none;
            }
            .poster-avatar {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                margin: 10px 0;
                border: 1px #000 solid;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="card">
                <div class="row">
                    <div class="col-md-8">
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

                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <c:forEach items="${images}" var="image" varStatus="status">
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0 ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                                </c:forEach>
                            </div>

                            <div class="carousel-inner" style="margin-bottom: 20px">
                                <c:forEach items="${images}" var="image" varStatus="status">
                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                        <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Slide ${status.index + 1}" preserveAspectRatio="xMidYMid slice" focusable="false">
                                        <rect width="100%" height="100%" fill="#3071BC" rx="10" ry="10"></rect>
                                        <image xlink:href="data:image/jpeg;base64,${image.getImageDataAsBase64()}" width="100%" height="100%"  alt="hinh anh"/>
                                        </svg>
                                    </div>
                                </c:forEach>
                            </div>

                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            </button>
                        </div>
                        <h3 class="fs-4 text-primary mb-3 custom-card-title">
                            <i class="fas fa-map-marker-alt mr-2"></i> ${post.house.location}
                        </h3>
                        <div class="profile-container">
                            <div class="avatar-container">
                                <img src="${pageContext.request.contextPath}/images/person_1.jpg" alt="Avatar" class="poster-avatar">
                            </div>
                            <div class="user-info-container">
                                <p class="poster-username">${post.house.house_owner.full_name}</p>
                            </div>
                        </div>
                        <h5 class="main-heading">House Information</h5>
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
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body d-flex flex-column">
                                <button class="btn btn-primary flex-grow-1 mb-2" data-bs-toggle="modal" data-bs-target="#messageModal">Book a tour house</button>
                                <button class="btn btn-primary flex-grow-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Contact information</button>
                            </div>
                            <!-- Modal for message -->
                            <div class="modal fade" id="messageModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="messageModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="messageModalLabel">Send a Message</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <!-- Message form -->
                                            <form action="${pageContext.request.contextPath}/post/booking" method="post">
                                                <div class="mb-3">
                                                    <label for="message" class="form-label">Appointment date:</label>
                                                    <input type="date" name="date" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="message" class="form-label">Message:</label>
                                                    <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
                                                </div>
                                                <input type="hidden" name="house_id" value="${post.house.house_id}">
                                                <button type="submit" class="btn btn-outline-success">Send</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Modal -->
                            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Contact information</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p><i class="fas fa-phone" style="color: blue;"></i> ${post.house.house_owner.phone_number}</p>
                                            <p><i class="fas fa-envelope" style="color: red;"></i> ${post.house.house_owner.email}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <p style="color: red">${requestScope.msg}</p>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
