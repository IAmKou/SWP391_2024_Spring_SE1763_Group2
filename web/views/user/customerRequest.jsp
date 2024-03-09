<%-- 
    Document   : customerRequest
    Created on : Mar 1, 2024, 3:11:17 AM
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
        <title>Customer Request</title>
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
            <div class="row">
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
                            <form id="filterForm" action="${pageContext.request.contextPath}/request/view/fillter" method="get">
                                <div class="row m-4">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="date">Booking Date:</label>
                                            <input type="date" class="form-control" id="date" name="date">
                                        </div>

                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="customer">Customer Name:</label>
                                            <select class="form-control" id="customer" name="customer_name">
                                                <option value="">Select Customer</option>
                                                <c:set var="seenNames" value="" />
                                                <c:forEach items="${requests}" var="order">
                                                    <c:choose>
                                                        <c:when test="${order.booking != null && order.booking.user != null}">
                                                            <c:if test="${not seenNames.contains(order.booking.user.full_name)}">
                                                                <c:set var="seenNames" value="${seenNames},${order.booking.user.full_name}" />
                                                                <option value="${order.booking.user.full_name}">${order.booking.user.full_name}</option>
                                                            </c:if>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:if test="${not seenNames.contains(order.meeting.customer.full_name)}">
                                                                <c:set var="seenNames" value="${seenNames},${order.meeting.customer.full_name}" />
                                                                <option value="${order.meeting.customer.full_name}">${order.meeting.customer.full_name}</option>
                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>
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
                                            </svg> ${totalRequest}${fillterTotalRequest}
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <c:if test="${not empty requests}">
                                <div class="row">
                                    <div class="table-responsive">
                                        <table class="table table-bordered ">
                                            <thead>
                                                <tr class="center">
                                                    <th>Customer Name</th>
                                                    <th>Booking Date</th>
                                                    <th>Type</th>
                                                    <th>View</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requests}" var="request">
                                                    <tr >
                                                        <td class="center">${request.booking.user.getFull_name()}${request.meeting.customer.getFull_name()}</td>
                                                        <td class="center">${request.booking.getFommatted_booking_date()}${request.meeting.getFommattedBookingDate()}</td>
                                                        <td class="center">
                                                            <c:choose>
                                                                <c:when test="${request.booking.getFommatted_booking_date() eq null}">
                                                                    Visiting
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Booking
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <div class="d-flex justify-content-center">
                                                                <div class="btn-group">
                                                                    <button class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${request.booking.getBooking_id()}">View Detail</button>
                                                                    <a href="${pageContext.request.contextPath}/view?post_id=<c:choose><c:when test="${request.booking.getFommatted_booking_date() eq null}">${request.meeting.getPostId()}</c:when><c:otherwise>${request.booking.getPost_id()}</c:otherwise></c:choose>" class="btn btn-outline-info">View House</a>
                                                                        </div>
                                                                    </div>        
                                                                    <!-- Modal -->
                                                                        <div class="modal fade" id="staticBackdrop_${request.booking.getBooking_id()}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <div class="logo-container">
                                                                                <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <div class="bg-dark text-white p-1 rounded center h4">Customer Information</div>
                                                                            <div class="form-group mb-3">
                                                                                <label for="customerName">Customer name:</label>
                                                                                <input type="text" class="form-control" id="customerName" value="${request.booking.getUser().getFull_name()}${request.meeting.getCustomer().getFull_name()}" readonly>
                                                                            </div>
                                                                            <div class="form-group mb-3">
                                                                                <label for="phoneNumber"><i class="fas fa-phone" style="color: blue;"></i> Phone number:</label>
                                                                                <input type="text" class="form-control" id="phoneNumber" value="${request.booking.getUser().getPhone_number()}${request.meeting.getCustomer().getPhone_number()}" readonly>
                                                                            </div>
                                                                            <div class="form-group mb-3">
                                                                                <label for="email"><i class="fas fa-envelope" style="color: red;"></i> Email:</label>
                                                                                <input type="text" class="form-control" id="email" value="${request.booking.getUser().getEmail()}${request.meeting.getCustomer().getEmail()}" readonly>
                                                                            </div>
                                                                            <div class="bg-dark text-white p-1 rounded center h4">Order Information</div>
                                                                            <c:choose>
                                                                                <c:when test="${request.meeting.customer.getFull_name() ne null}">
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="bookingDate">Booking date:</label>
                                                                                        <input type="text" class="form-control" id="bookingDate" value="${request.meeting.getFommattedBookingDate()}" readonly>
                                                                                    </div>
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="meetingDate">Meeting date:</label>
                                                                                        <input type="text" class="form-control" id="meetingDate" value="${request.meeting.getFommattedMeetingDate()}" readonly>
                                                                                    </div>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="bookingDate">Booking date:</label>
                                                                                        <input type="text" class="form-control" id="bookingDate" value="${request.booking.getFommatted_booking_date()}" readonly>
                                                                                    </div>
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="checkInDate">Check-in date:</label>
                                                                                        <input type="text" class="form-control" id="checkInDate" value="${request.booking.getFomatted_check_in_date()}" readonly>
                                                                                    </div>
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="checkOutDate">Check-out date:</label>
                                                                                        <input type="text" class="form-control" id="checkOutDate" value="${request.booking.getFomatted_check_out_date()}" readonly>
                                                                                    </div>
                                                                                    <div class="form-group mb-3">
                                                                                        <label for="quantityOfPeople">Quantity of people:</label>
                                                                                        <input type="text" class="form-control" id="quantityOfPeople" value="${request.booking.getQuantityOfpeople()}" readonly>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <div class="form-group mb-3">
                                                                                <label for="message">Note:</label>
                                                                                <textarea class="form-control" rows="5" id="message"readonly>${request.booking.getNote()}${request.meeting.getNote()}</textarea>
                                                                            </div>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="d-flex justify-content-center">
                                                                <div class="btn-group">
                                                                    <button class="btn btn-success">Accept</button>
                                                                    <button class="btn btn-danger">Cancel</button>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- Hiển thị các nút điều hướng phân trang -->
                                <c:if test="${not empty fillterTotalPages}">
                                    <nav aria-label="Page navigation example" class="m-3">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="../request/view?page=${currentPage - 1}" aria-label="Previous">
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
                                <c:if test="${not empty totalPages}">
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
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </body>
</html>
