<%-- 
    Document   : userRequest
    Created on : Mar 1, 2024, 2:42:29 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
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
        </style>
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

                <div class="col-md-10 ">
                    <div class="row">
                        <div class="col-md-12 mb-4">
                            <form id="filterForm" action="${pageContext.request.contextPath}/booking/view/fillter" method="get">
                                <div class="row m-4">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="date">Booking Date:</label>
                                            <input type="date" class="form-control" id="date" name="date">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="date">Status:</label>
                                            <select name="status" class="form-control">
                                                <option value="">Select Status</option>
                                                <c:forEach items="${statuses}" var="status">
                                                    <c:if test="${status.status_id ne 7 && status.status_id ne 4 && status.status_id ne 5}">
                                                        <option value="${status.status_id}">${status.status_name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                    <div class="col-md-3 align-self-end btn-group">
                                        <button type="submit" class="btn btn-primary">Apply Filters</button>
                                        <div class="btn btn-info">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-backpack4" viewBox="0 0 16 16">
                                            <path d="M4 9.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5zm1 .5v3h6v-3h-1v.5a.5.5 0 0 1-1 0V10z"/>
                                            <path d="M8 0a2 2 0 0 0-2 2H3.5a2 2 0 0 0-2 2v1c0 .52.198.993.523 1.349A.5.5 0 0 0 2 6.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V6.5a.5.5 0 0 0-.023-.151c.325-.356.523-.83.523-1.349V4a2 2 0 0 0-2-2H10a2 2 0 0 0-2-2m0 1a1 1 0 0 0-1 1h2a1 1 0 0 0-1-1M3 14V6.937q.24.062.5.063h4v.5a.5.5 0 0 0 1 0V7h4q.26 0 .5-.063V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1m9.5-11a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-9a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1z"/>
                                            </svg> ${totalBooking}${fillterTotalBooking}
                                        </div>

                                    </div>
                                </div>
                            </form>
                            <!--this is for view booking of user-->
                            <c:if test="${not empty bookings}">
                                <div class="container">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr class="center">
                                                <th>House Location</th>
                                                <th>Booking Date</th>
                                                <th>Type</th>
                                                <th>Status</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${bookings}" var="booking">
                                                <tr>
                                                    <td>
                                                        <c:set var="printed" value="false" />
                                                        <c:forEach items="${posts}" var="post">
                                                            <c:if test="${!printed && (post.post_id eq booking.booking.getPost_id() || post.post_id eq booking.meeting.getPostId())}">
                                                                ${post.house.location}
                                                                <c:set var="printed" value="true" />
                                                            </c:if>
                                                        </c:forEach>

                                                    </td>
                                                    <td class="center">${booking.booking.getFommatted_booking_date()}${booking.meeting.getFommattedBookingDate()}</td>
                                                    <td class="center">
                                                        <c:choose>
                                                            <c:when test="${booking.booking.getFommatted_booking_date() eq null}">
                                                                Visiting
                                                            </c:when>
                                                            <c:otherwise>
                                                                Booking
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="center" style="color: red">
                                                        <c:choose>
                                                            <c:when test="${booking.booking.getStatus().getStatus_name() eq null}">
                                                                <div class="center" style="color:
                                                                     ${booking.meeting.getMeetingStatus().getStatus_id() == 1 ? 'black' : 
                                                                       (booking.meeting.getMeetingStatus().getStatus_id() == 2 ? 'green' : 
                                                                       (booking.meeting.getMeetingStatus().getStatus_id() == 3 ? 'red' : ''))};">
                                                                         ${booking.meeting.getMeetingStatus().getStatus_name()}
                                                                     </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="center" style="color:
                                                                         ${booking.booking.getStatus().getStatus_id() == 1 ? 'black' : 
                                                                           (booking.booking.getStatus().getStatus_id() == 2 ? 'green' : 
                                                                           (booking.booking.getStatus().getStatus_id() == 3 ? 'red' : ''))};">
                                                                             ${booking.booking.getStatus().getStatus_name()}
                                                                         </div>
                                                                    </c:otherwise>
                                                                </c:choose>


                                                            </td>
                                                            <td>
                                                                <div class="btn-group center">
                                                                    <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${booking.booking.getBooking_id()}">View Detail</button>
                                                                </div>
                                                                <!-- Modal -->
                                                                <div class="modal fade" id="staticBackdrop_${booking.booking.getBooking_id()}" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="bg-dark text-white p-1 rounded center h4">Order Information</div>
                                                                                <c:choose>
                                                                                    <c:when test="${booking.meeting.getFommattedBookingDate() ne null}">
                                                                                        <div class="form-group mb-3 ">
                                                                                            <label for="location">House Location:</label>
                                                                                            <c:set var="printed" value="false" />
                                                                                            <c:forEach items="${posts}" var="post">
                                                                                                <c:if test="${!printed && (post.post_id eq booking.booking.getPost_id() || post.post_id eq booking.meeting.getPostId())}">
                                                                                                    <input type="text" class="form-control" id="location" value="${post.house.location}" readonly>
                                                                                                    <c:set var="printed" value="true" />
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="bookingDate">Booking date:</label>
                                                                                                    <input type="text" class="form-control" id="bookingDate" value="${booking.meeting.getFommattedBookingDate()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="meetingDate">Meeting date:</label>
                                                                                                    <input type="text" class="form-control" id="meetingDate" value="${booking.meeting.getFommattedMeetingDate()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="message">Note:</label>
                                                                                                    <textarea class="form-control" rows="5" id="message"readonly>${booking.meeting.getNote()}</textarea>
                                                                                                </div>
                                                                                                <div class="bg-dark text-white p-1 rounded center h4">Contact Information</div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="email">Email:</label>
                                                                                                    <input type="text" class="form-control" id="email" value="${post.house.house_owner.email}" readonly>
                                                                                                    <label for="phone_number">Phone Number:</label>
                                                                                                    <input type="text" class="form-control" id="phone_number" value="${post.house.house_owner.phone_number}" readonly>
                                                                                                </div>
                                                                                                <div class="bg-dark text-white p-1 rounded center h4">Response</div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <textarea class="form-control" rows="5"readonly> ${booking.meeting.getResponeMessage()}</textarea>
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <div class="form-group mb-3">
                                                                                            <label for="location">House Location:</label>
                                                                                            <c:set var="printed" value="false" />
                                                                                            <c:forEach items="${posts}" var="post">
                                                                                                <c:if test="${!printed && (post.post_id eq booking.booking.getPost_id() || post.post_id eq booking.meeting.getPostId())}">
                                                                                                    <input type="text" class="form-control" id="location" value="${post.house.location}" readonly>
                                                                                                    <c:set var="printed" value="true" />
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="bookingDate">Booking date:</label>
                                                                                                    <input type="text" class="form-control" id="bookingDate" value="${booking.booking.getFommatted_booking_date()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="checkInDate">Check-in date:</label>
                                                                                                    <input type="text" class="form-control" id="checkInDate" value="${booking.booking.getFomatted_check_in_date()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="checkOutDate">Check-out date:</label>
                                                                                                    <input type="text" class="form-control" id="checkOutDate" value="${booking.booking.getFomatted_check_out_date()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="quantityOfPeople">Quantity of people:</label>
                                                                                                    <input type="text" class="form-control" id="quantityOfPeople" value="${booking.booking.getQuantityOfpeople()}" readonly>
                                                                                                </div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="message">Note:</label>
                                                                                                    <textarea class="form-control" rows="5" id="message"readonly> ${booking.booking.getNote()}</textarea>
                                                                                                </div>
                                                                                                <div class="bg-dark text-white p-1 rounded center h4">Contact Information</div>
                                                                                                <div class="form-group mb-3">
                                                                                                    <label for="email">Email:</label>
                                                                                                    <input type="text" class="form-control" id="email" value="${post.house.house_owner.email}" readonly>
                                                                                                    <label for="phone_number">Phone Number:</label>
                                                                                                    <input type="text" class="form-control" id="phone_number" value="${post.house.house_owner.phone_number}" readonly>
                                                                                                </div>
                                                                                                    <div class="bg-dark text-white p-1 rounded center h4">Response</div>
                                                                                                    <div class="form-group mb-3">
                                                                                                        <textarea class="form-control" rows="5"readonly> ${booking.booking.getResponseMessage()}</textarea>
                                                                                                    </div>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!-- Hiển thị các nút điều hướng phân trang -->
                                        <c:if test="${not empty fillterTotalPages}">
                                            <nav aria-label="Page navigation example" class="m-3">
                                                <ul class="pagination">
                                                    <li class="page-item">
                                                        <a class="page-link" href="../booking/view/fillter?page=${currentPage - 1}" aria-label="Previous">
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
                                                            <a class="page-link" href="../booking/view/fillter?page=${page}">${page}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item">
                                                        <a class="page-link" href="../booking/view/fillter?page=${currentPage + 1}" aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </c:if>
                                        <c:if test="${not empty totalPages}">
                                            <nav aria-label="Page navigation example" class="m-3">
                                                <ul class="pagination">
                                                    <li class="page-item">
                                                        <a class="page-link" href="../booking/view?page=${currentPage - 1}" aria-label="Previous">
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
                                                            <a class="page-link" href="../booking/view?page=${page}">${page}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item">
                                                        <a class="page-link" href="../booking/view?page=${currentPage + 1}" aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <jsp:include page="../footer.jsp"/>
                </div>
            </body>
        </html>
