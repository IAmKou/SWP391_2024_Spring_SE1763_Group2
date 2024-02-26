<%-- 
    Document   : userProfile
    Created on : Jan 26, 2024, 6:06:18 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="username" value="${sessionScope.account.full_name}" />
        <c:set var="user" value="${sessionScope.account}" />
        <title>Edit + ${username}</title>
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
            table {
                margin-top: 50px;
                width: 100%;
                border-radius: 4px;
            }

            th {
                text-align: right;
                padding-right: 10px;
                background-color: lightgrey;
                width: 25%;
            }

            td {
                text-align: left;
                padding-left: 10px;
            }
            .avatar {
                width: 100px; /* Adjust the width and height as needed */
                height: 100px;
                border-radius: 50%; /* Make it circular */
                overflow: hidden; /* Hide overflowing content */
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="wrapper row3" style="top: -45px;">
                <main class="hoc container clear gridmain">
                    <!-- main body -->
                    <!-- ################################################################################################ -->
                    <div class="sidebar grid1_4">
                        <!-- ################################################################################################ -->
                        <!--<h6>Youz</h6>-->
                        <nav class="sdb_holder">
                            <ul>
                                <li><a href="home.jsp">Home</a></li>
                                <li><a href="myAccount" style="font-weight: bold;">Account Information</a></li>
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
                                        <li><a href="#">Post</a></li>
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
                            <form action="updateUser" method="post" enctype="multipart/form-data">
                                <table>
                                    <tr>
                                        <th>User ID:</th>
                                        <td>${user.user_id}</td>
                                </tr>
                                <tr>
                                    <th>Role:</th>
                                    <td>${role}</td>
                                </tr>
                                <tr>
                                    <th>Full Name:</th>
                                    <td> <input type="text" name="full_name" id="fullName" value="${param1.getFull_name()}" placeholder="${param1.getFull_name()}"></td>
                                </tr>
                                <tr>
                                    <th>Date Of Birth: </th>
                                    <td><input type="date" name="date_of_birth" id="phone" value="${param1.getDate_of_birth()}" placeholder="${param1.getDate_of_birth()}"></td>
                                </tr>
                                <tr>
                                    <th>Address:</th>
                                    <td><input type="text" name="address" id="address" value="${param1.getAddress()}"></td>
                                </tr>

                                <tr>
                                    <th>Phone Number:</th>
                                    <td><input type="text" name="phone_number" id="phone" value="${param1.getPhone_number()}"></td>
                                </tr>
                                <tr>
                                    <th>Email:</th>
                                    <td><input type="email" name="email" id="email" value="${param1.getEmail()}"></td>
                                </tr>
                                <tr>
                                    <th>Avatar: <input type="file" name="file"></th>
                                    <td><img src="${param1.getAvatar()}" class="avatar"></td>
                                </tr>

                            </table>
                            <p style="color: red">${requestScope.msg}</p>
                            <a href="${pageContext.request.contextPath}/views/myProfile.jsp"><button class="btn "  style="float: right 45px;">Cancel</button></a>
                            <button class="btn" style="float: right 45px;" type="submit">Submit</button>
                        </form>            
                    </section>

                </div>


        </div>
    </div>
</main>
</div>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>