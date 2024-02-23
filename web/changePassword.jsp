<%-- 
    Document   : changePassword
    Created on : Jan 11, 2024, 2:47:30 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/style.css">
        <style>
            body {
                background-color: #f2f2f2; /* Màu xám cho nền */
            }
            .login-wrap {
                background-color: #e6e6e6; /* Màu xám cho phần wrap của form đăng nhập */
                border-radius: 10px; /* Bo góc */
            }
            .login-form {
                color: #333333; /* Màu chữ xám đậm */
            }
            .form-control {
                background-color: #cccccc; /* Màu xám cho ô nhập liệu */
                border: none; /* Không có viền */
                color: #333333; /* Màu chữ xám đậm */
            }
            .btn-primary {
                background-color: #666666; /* Màu xám đậm cho nút */
                color: #ffffff; /* Màu chữ trắng */
            }
            .btn-primary:hover {
                background-color: #999999; /* Màu xám nhạt khi hover vào nút */
            }
            a {
                color: #666666; /* Màu chữ xám đậm cho liên kết */
            }
            a:hover {
                color: #999999; /* Màu chữ xám nhạt khi hover vào liên kết */
            }
        </style>
    </head>
    <body>
        <form action="changePasswordController" method="post">
        <section class="ftco-section">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-7 col-lg-5">
                            <div class="login-wrap p-4 p-md-5">
                                <div class="icon d-flex align-items-center justify-content-center">
                                    <span class="fa fa-user-o"></span>
                                </div>
                                <h3 class="text-center mb-4">Change Password</h3>
                                    <div class="form-group">
                                        <input name="oldpass" type="password" class="form-control rounded-left" placeholder="Your old passwrod:  " required>
                                    </div>
                                    <div class="form-group">
                                        <input name="newpass" type="password" class="form-control rounded-left" placeholder="New Password: " required>
                                    </div>
                                    <div class="form-group">
                                        <input name="cfpass" type="password" class="form-control rounded-left" placeholder="Re-input password: " required>
                                    </div>
                                    <p style="color: red">${requestScope.message}</p>
                                    <div class="form-group">
                                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">Change</button>
                                    </div>
                                   
                                    </div>
        </form>
    </body>
</html>