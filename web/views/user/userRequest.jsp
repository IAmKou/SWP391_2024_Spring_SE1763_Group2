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
                                                    <option value="${status.status_id}">${status.status_name}</option>
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
                                <div class="row">
                                    <c:forEach items="${bookings}" var="booking">
                                        <div class="col-md-5 m-2 card">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <p class="custom-card-text">Booking ID: ${booking.booking_id}</p>
                                                            <p class="custom-card-text">Booking Date: ${booking.fommatted_booking_date}</p>
                                                        </div>
                                                        <div class="col-md-4 mt-1">
                                                            <a href="${pageContext.request.contextPath}/view?house_id=${booking.house.house_id}" class="btn btn-secondary">View detail</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:forEach items="${statuses}" var="status">
                                                    <c:if test="${booking.status_id == status.status_id}">
                                                        <c:if test="${booking.status_id == 1}">
                                                            <div class="col-md-12 btn mb-1" style="background-color: #a7b9b1">
                                                                ${status.status_name}
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${booking.status_id == 2}">
                                                            <div class="col-md-12 btn mb-1" style="background-color: #146c43">
                                                                ${status.status_name}
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${booking.status_id == 3}">
                                                            <div class="col-md-12 btn mb-1" style="background-color: #DB4A39">
                                                                ${status.status_name}
                                                            </div>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
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
