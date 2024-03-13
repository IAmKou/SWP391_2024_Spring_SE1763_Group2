<%-- 
    Document   : viewUserList
    Created on : Jan 26, 2024, 12:05:05 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>View User List</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png">
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
        <jsp:include page="header.jsp"/>

        <div class="container mt-5">
            <div class="row">
                <jsp:include page="/sample/left_side_bar_for_admin_account.jsp"/>
                <div class="col-md-9">
                    
                    <c:if test="${alert ne null}">
                        <div class="alert alert-warning">                                   
                            ${alert}
                        </div>
                    </c:if>
                    <c:if test="${success ne null}">
                        <div class="alert alert-success h5">
                            ${success}
                        </div>
                    </c:if>
                    <table class="table table-responsive">
                        <thead>
                            <tr class="center">
                                <th>User ID</th>
                                <th>User Name</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Active</th>
                                <th>Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr onclick="viewProfile(${user.key.user_id})">
                                    <td>${user.key.user_id}</td>
                                    <td>${user.value.user_name}</td>
                                    <td>${user.key.full_name}</td>
                                    <td>${user.key.email}</td>
                                    <td>${user.key.phone_number}</td>
                                    <td>${user.value.isActive()==true?'Active':'Deactivate'}</td>
                                    <td>
                                        <c:if test="${user.value.getRole_id() eq 2}">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmBackdrop${user.key.user_id}">Change Status</button>
                                        </c:if>
                                        <!-- confirm Modal -->
                                        <div class="modal fade" id="confirmBackdrop${user.key.user_id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <div class="logo-container">
                                                            <img class="logo" src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image"/>
                                                        </div>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>
                                                            Are you sure want to 
                                                            ${user.value.isActive() ? 'deactivate' : 'activate'} the account for ${user.value.user_name}?
                                                        </p>
                                                        <form  action="${pageContext.request.contextPath}/user/status" method="post">
                                                            <div class="form-group">
                                                                <input type="hidden" value="${user.key.user_id}" name="user_id">
                                                                <input type="password" class="form-control" name="admin_password" placeholder="Enter your password for confirm" required>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-warning">Accpet</button> 
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            </div>
                                                        </form>
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
            </div>

            <nav aria-label="Page navigation example" class="m-3">
                <ul class="pagination">
                    <c:set var="status" value="${param.status}" />
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${currentPage - 1}" aria-label="Previous">
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
                            <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${page}">${page}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <jsp:include page="footer.jsp"/>
        <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
    </body>
</html>
