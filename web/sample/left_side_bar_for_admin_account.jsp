<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="username" value="${sessionScope.account.full_name}" />
        <c:set var="user" value="${sessionScope.account}" />
        <title>Profile Update ${username}</title>
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
                margin-top: 0px;
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
        <div class="sidebar grid1_4">
            <!-- ################################################################################################ -->
            <!--<h6>Youz</h6>-->
            <nav class="sdb_holder">
                <ul>
                    <li><a href="views/home.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/viewUserList" style="font-weight: bold;">User Management</a></li>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/viewUserList">User List</a></li>
                        <li><a href="#">Updating</a></li>
                        <li><a href="#">Updating</a></li>
                    </ul>
                    <li><a href="vieworder.html">Post Management</a>
                        <ul>
                            <li><a href="#">Updating</a></li>
                            <li><a href="#">Updating</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- ################################################################################################ -->
        </div>
    </body>
</html>
