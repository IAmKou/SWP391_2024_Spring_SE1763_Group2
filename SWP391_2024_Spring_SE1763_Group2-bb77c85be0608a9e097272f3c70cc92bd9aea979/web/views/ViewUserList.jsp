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
        <title>Profile</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href=" ${pageContext.request.contextPath}/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
        <link href="${pageContext.request.contextPath}/layout/styles/manh.css" rel="stylesheet" type="text/css" media="all">
        <link href="${pageContext.request.contextPath}/layout/styles/framework.css" rel="stylesheet" type="text/css" media="all">
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="extra code/css/style.css">

        <style>
            #mainav img {
                height: 70px;
                width: auto;
            }

            .grid1_4 {
                grid-column: 1;
                grid-row: 1;
                /* width: 87.3684%; */
                width: 19.8421%;
                float: left;
                /* border: 1px solid red; */
            }

            .gridmain {
                grid-template-columns: 1fr 3fr;
                grid-template-rows: 1fr;
                /* border: 1px solid yellow; */
            }

            .grid3_4 {
                grid-column: 1/2;
                grid-row: 1;
                float: right;
                width: 77.3684%;
            }

            .borderEntity {
                border: 1px solid #D7D7D7;
                padding: 10px;
                border-radius: 5px;
            }

            .ftco-section {
                overflow: hidden;
            }
        </style>
    </head>

    <body id="top">
        <div class="bgded overlay" style="background-color: black;">
            <!-- ################################################################################################ -->
            <jsp:include page="header.jsp"/>
            <!-- End Top Background Image Wrapper -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <div class="wrapper row3" style="top: -45px;">
                <main class="hoc container clear gridmain">
                    <!-- main body -->
                    <!-- ################################################################################################ -->
                    <div class="sidebar grid1_4">
                        <!-- ################################################################################################ -->
                        <!--<h6>Youz</h6>-->
                        <nav class="sdb_holder">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="profile.html" style="font-weight: bold;">Account Information</a></li>
                                <li><a href="vieworder.html">View Orders</a>
                                    <ul>
                                        <li><a href="#">Waiting</a></li>
                                        <li><a href="#">Accepted</a></li>
                                        <li><a href="#">Rejected</a></li>
                                        <li><a href="#">Successful</a></li>
                                    </ul>
                                </li>

                                <li><a href="#">View Rentals</a>
                                    <ul>
                                        <li><a href="#">Your Post</a></li>
                                        <li><a href="#">Customer's Requests</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>

                        <!-- ################################################################################################ -->
                    </div>
                    <!-- ################################################################################################ -->
                    <!-- ################################################################################################ -->
                    <div class="content grid3_4 borderEntity " style="height: 450px;">
                        <!-- <div class="clear"> -->
                        <section class="ftco-section">
                            <!-- <div class="container" >
                                    </div> -->
                            <div class="row" style="">
                                <div class="col-md-12">
                                    <div class="table-wrap">
                                        <table class="table table-responsive-xl" style="word-wrap: break-word">
                                            <thead>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <th>ID</th>
                                                    <th>Username</th>
                                                    <th>Phone</th>
                                                    <th>Email</th>

                                                </tr>
                                            </thead>
                                            <tbody style="white-space: nowrap; overflow: hidden;">
                                                <c:forEach var="user" items="${uList}">
                                                    <tr onclick="viewProfile('${user.user_id}')">
                                                        <td>${user.user_id}</td>
                                                        <td>${user.full_name}</td>
                                                        <td>${user.address}</td>
                                                        <td>${user.phone_number}</td>
                                                        <td>${user.email}</td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- </div> -->
                            </div>
                        </section>
                    </div>
                </main>
            </div>
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <jsp:include page="footer.jsp"/>
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
            <!-- JAVASCRIPTS -->
            <script src="layout/scripts/jquery.min.js"></script>
            <script src="layout/scripts/jquery.backtotop.js"></script>
            <script src="layout/scripts/jquery.mobilemenu.js"></script>
            <script>
                                                        function viewProfile(userId) {
                                                            window.location.href = 'userProfile?id=' + userId;
                                                        }
            </script>
    </body>


</html>
