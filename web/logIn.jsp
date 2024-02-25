<%-- 
    Document   : logIn
    Created on : Jan 10, 2024, 12:47:07 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
        <form action="logInController" method="post">
            <section class="ftco-section">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-7 col-lg-5">
                            <div class="login-wrap p-4 p-md-5">
                                <div class="icon d-flex align-items-center justify-content-center">
                                    <span class="fa fa-user-o"></span>
                                </div>
                                <h3 class="text-center mb-4">Sign In</h3>
                                <form action="#" class="login-form">
                                    <div class="form-group">
                                        <input name="username" type="text" class="form-control rounded-left" placeholder="Username: " required value="${requestScope.username}">
                                    </div>
                                    <div class="form-group d-flex">
                                        <input type="password" name="password"class="form-control rounded-left" placeholder="Password: " required>
                                    </div>
                                    <p style="color: red">${requestScope.message}</p>
                                    <div class="form-group">
                                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">Login</button>
                                    </div>
                                    <div class="form-group d-md-flex">
                                        <div class="w-50 text-md-left">
                                            <a href="signUp.jsp">Sign Up</a>
                                        </div>
                                        <div class="w-50 text-md-right">
                                            <a href="forgotPassword.jsp">Forgot Password</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
    </body>
</html>