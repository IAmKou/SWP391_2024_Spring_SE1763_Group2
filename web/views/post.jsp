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
            .logo-container {
                flex: 1; /* Phần tử chứa logo sẽ mở rộng để lấp đầy không gian trống */
                text-align: center; /* Canh giữa logo trong phần tử cha */
            }
            .modal-header {
                background-color: black;
                display: flex; /* Sử dụng flexbox để căn chỉnh phần tử con */
                justify-content: center; /* Canh giữa theo chiều ngang */
                align-items: center; /* Canh giữa theo chiều dọc */
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
                                    <td><strong>House type: </strong>${post.house.type_of_house.type_of_house_name}</td>
                                </tr>
                            </tbody>
                        </table>
                        <p><strong>Description: </strong><br>${post.house.description}</p>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body d-flex flex-column">
                                <c:if test="${post.purpose.purpose_id eq 2}">
                                    <button id="bookHouseBtn" class="btn btn-primary flex-grow-1 mb-2" data-bs-toggle="modal" data-bs-target="#bookingModal">Book house</button>
                                </c:if>
                                <c:if test="${post.purpose.purpose_id eq 1}">
                                    <button id="bookHouseBtn" class="btn btn-primary flex-grow-1 mb-2" data-bs-toggle="modal" data-bs-target="#meetingModal">Get a tour house</button>
                                </c:if>
                                <button class="btn btn-primary flex-grow-1 mb-2" data-bs-toggle="modal" data-bs-target="#contactModal">Contact information</button>
                            </div>

                            <!-- Modal for meeting -->
                            <div id="meetingModal" class="modal fade modal-lg" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <div class="logo-container">
                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                            </div>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${pageContext.request.contextPath}/post/visit">
                                                <input type="hidden" name="post_id" value="${post.post_id}">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="row mb-3">
                                                            <div class="col-md-6">
                                                                <label for="fullname" class="form-label">Full name:</label>    
                                                                <input type="text" id="fullname" name="fullname" value="${sessionScope.account.full_name}" readonly class="form-control">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label for="email" class="form-label">Email:</label>
                                                                <input type="email" id="email" name="email" value="${sessionScope.account.email}" readonly class="form-control">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label for="customerPhone">Phone Number:</label>
                                                    <input type="tel" class="form-control" id="customerPhone" value="${account.phone_number}" readonly>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label for="meetingDate">Meeting Date:</label>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <input type="date" class="form-control" id="meetingDate" name="meeting_date" required>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <input type="time" class="form-control" name="meeting_time" required>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="note">Note:</label>
                                                    <textarea class="form-control" id="note" rows="5" name="note"></textarea>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Submit request</button>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <!-- Modal for booking -->
                            <div class="modal fade modal-lg" id="bookingModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <div class="logo-container">
                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                            </div>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${pageContext.request.contextPath}/post/booking" method="post">
                                                <input type="hidden" name="post_id" value="${post.post_id}">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="row mb-3">
                                                            <div class="col-md-6">
                                                                <label for="fullname" class="form-label">Full name:</label>    
                                                                <input type="text" id="fullname" name="fullname" value="${sessionScope.account.full_name}" readonly class="form-control">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label for="email" class="form-label">Email:</label>
                                                                <input type="email" id="email" name="email" value="${sessionScope.account.email}" readonly class="form-control">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label for="phone" class="form-label">Phone number:</label>
                                                <input type="text" id="phone" name="phone" value="${sessionScope.account.phone_number}" readonly class="form-control mb-3">    

                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label for="checkin_date" class="form-label">Check-in date:</label>
                                                        <input type="date" id="checkin_date" name="check_in_date" required class="form-control">
                                                        <input type="time" id="checkin_time" name="check_in_time" required class="form-control">
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label for="checkout_date" class="form-label">Checkout date:</label>
                                                        <input type="date" id="checkout_date" name="check_out_date" required class="form-control">
                                                        <input type="time" id="checkout_time" name="check_out_time" required class="form-control">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label for="adults" class="form-label">Quantity of people:</label>
                                                        <input type="number" id="adults" name="peoples" min="1" required class="form-control mb-3">
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label for="payment_method" class="form-label">Payment:</label>
                                                        <select id="payment_method" name="payment_method" required class="form-select mb-3">
                                                            <c:forEach items="${methods}" var="method">
                                                                <option value="${method.method_id}">${method.method_name}</option>
                                                            </c:forEach>

                                                        </select>
                                                    </div>
                                                </div>
                                                <label for="special_requests" class="form-label">Note:</label>
                                                <textarea id="special_requests" name="note" rows="4" cols="50" class="form-control mb-3"></textarea>



                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Submit request</button>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Modal for contact -->
                            <div class="modal fade" id="contactModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <div class="logo-container">
                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                            </div>
                                        </div>
                                        <div class="modal-body">
                                            <p><i class="fas fa-phone" style="color: blue;"></i> ${post.house.house_owner.phone_number}</p>
                                            <p><i class="fas fa-envelope" style="color: red;"></i> ${post.house.house_owner.email}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <p style="color: red">${requestScope.msg}</p>
        <script>
            function checkSession() {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.request.contextPath}/checkSession", true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var response = xhr.responseText;
                        if (response === "false") {
                            window.location.href = '${pageContext.request.contextPath}/logIn.jsp';
                        }
                    }
                };
                xhr.send();
            }

            document.getElementById('bookHouseBtn').addEventListener('click', function () {
                checkSession();
            });
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
