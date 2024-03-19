<%-- 
    Document   : signUp
    Created on : Jan 9, 2024, 8:32:40 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
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
            .required-field::after {
                content: "*";
                color: red;
                margin-left:2px
            }


        </style>
    </head>
    <body>
        <form action="addUserController" method="post">
            <section class="ftco-section">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-7 col-lg-5">
                            <div class="login-wrap p-4 p-md-5">
                                <div class="icon d-flex align-items-center justify-content-center">
                                    <span class="fa fa-user-o"></span>
                                </div>
                                <h3 class="text-center mb-4">Sign Up</h3>
                                <form action="#" class="login-form">
                                    <div class="form-group">
                                        <label for="uname">Username: <span class="required-field"></span></label>
                                        <input name="uname" type="text" class="form-control rounded-left" placeholder="Username: " required id="uname" maxlength="10">
                                    </div>
                                    <div class="form-group">
                                        <label for="pass">Password: <span class="required-field"></span></label>
                                        <input name="pass" type="password" class="form-control rounded-left" placeholder="Password: " required id="pass" maxlength="20" minlength="6">
                                    </div>
                                    <div class="form-group">
                                        <label for="cfpass">Re-input password: <span class="required-field"></span></label>
                                        <input name="cfpass" type="password" class="form-control rounded-left" placeholder="Re-input password: " required id="cfpass" maxlength="20" minlength="6">
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Fullname:  <span class="required-field"></span></label>
                                        <input name="fullname" type="text" class="form-control rounded-left" placeholder="Fullname: " required id="fullname" maxlength="20">
                                    </div>
                                    <div class="form-group">
                                        <label for="dob">DoB: </label>
                                        <input type="date" name="dob"class="form-control rounded-left" required id="dob">
                                    </div>
                                    <div class="form-group">
                                        <label for="address">Address: <span class="required-field"></span></label>
                                        <input type="text" name="address"class="form-control rounded-left" placeholder="Ex: Ha Noi " required id="address" maxlength="100">
                                    </div> 
                                    <div class="form-group">
                                        <label for="phone">Phone Number: <span class="required-field"></span></label>
                                        <input type="number" pattern="0\d{9}" name="phone"class="form-control rounded-left" placeholder="Phone: " required id="phone">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email: <span class="required-field"></span></label>
                                        <input type="text" name="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" class="form-control rounded-left" placeholder="Email: " required id="email" maxlength="30">
                                    </div>
                                    <p style="color: red">${requestScope.msg}</p>
                                    <div class="form-group">
                                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">Submit</button>
                                    </div>

                                    <div class="form-group" style="align-content: center">
                                        <p>Already had an account? Login <a style="color: blue" href="${pageContext.request.contextPath}/logIn.jsp">here</a></p>
                                    </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                </div>
            </section>
        </form>
        <script>
            document.getElementById('phone').addEventListener('input', function (e) {
                if (!/0\d{9}/.test(e.target.value)) {
                    e.target.setCustomValidity('Please enter a valid phone number starting with 0 followed by 9 digits');
                } else {
                    e.target.setCustomValidity('');
                }
            });
            document.getElementById('email').addEventListener('input', function (e) {
                if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(e.target.value)) {
                    e.target.setCustomValidity('Please enter a valid email address');
                } else {
                    e.target.setCustomValidity('');
                }
            });
        </script>                     
    </body>
</html>
