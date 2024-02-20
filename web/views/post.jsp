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
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Thông tin bài đăng</h2>
                </div>
                <div class="card-body">
                    <p><strong>ID:</strong> ${post.post_id}</p>
                    <p><strong>Giá:</strong> ${post.price}</p>
                    <p><strong>Trạng thái nhà:</strong> ${post.house_status.status_name}</p>
                    <p><strong>Trạng thái bài đăng:</strong> ${post.post_status.status_name}</p>
                    <p><strong>Mục đích:</strong> ${post.purpose.purpose_name}</p>
                </div>
            </div>
            <div class="card mt-4">
                <div class="card-header">
                    <h2>Thông tin nhà</h2>
                </div>
                <div class="card-body">
                    <p><strong>ID:</strong> ${post.house.house_id}</p>
                    <p><strong>Địa chỉ:</strong> ${post.house.location}</p>
                    <p><strong>Diện tích:</strong> ${post.house.area} m<sup>2</sup></p>
                    <p><strong>Mô tả:</strong> ${post.house.description}</p>
                    <p><strong>Số phòng:</strong> ${post.house.number_of_room}</p>
                </div>
            </div>
            <div class="card mt-4">
                <div class="card-header">
                    <h2>Thông tin người đăng</h2>
                </div>
                <div class="card-body">
                    <p><strong>ID:</strong> ${post.house.house_owner.user_id}</p>
                    <p><strong>Họ và tên:</strong> ${post.house.house_owner.full_name}</p>
                    <p><strong>Ngày sinh:</strong> ${post.house.house_owner.date_of_birth}</p>
                    <p><strong>Địa chỉ:</strong> ${post.house.house_owner.address}</p>
                    <p><strong>Số điện thoại:</strong> ${post.house.house_owner.phone_number}</p>
                    <p><strong>Email:</strong> ${post.house.house_owner.email}</p>
                </div>
            </div>
                    <div class="profile">
                        <img src="${pageContext.request.contextPath}/images/person_1.jpg" alt="Avatar" class="avatar">
                        <p class="username">Tên Tài Khoản</p>
                    </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
