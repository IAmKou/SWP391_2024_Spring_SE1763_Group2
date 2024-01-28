<%-- 
    Document   : header
    Created on : Jan 24, 2024, 11:54:48 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>This is header</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/framework.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/layout.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/manh.css"/>
    </head>
    <body id="top">
        <div class="bgded overlay ">
            <div class="wrapper row1">
                <header id="header" class="hoc clear" style="display: grid; grid-template-columns: 3fr 6fr 3fr;
                        grid-template-rows: 1fr;">
                    <nav id="mainav" class="hnc1">
                        <ul class="clear">
                            <li class="active"><a href="#">Buy</a></li>
                            <li class="active"><a href="#">Rent</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/post/add">Sell</a></li>
                            <li class="active"><a href="#">Help</a></li>
                        </ul>
                    </nav>

                    <nav id="mainav" class="hnc2">
                        <a href="${pageContext.request.contextPath}/views/home.jsp"> <img src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image" class="logo"></a>

                    </nav>

                    <nav id="mainav" class="hnc3">
                        <ul class="clear">
                            <li class="active"><a href="#">Home</a></li>
                            <li class="active"><a href="#">My Rentals</a></li>
                            <li><a class="drop" href="${pageContext.request.contextPath}/order/view">Profile</a>
                                <!-- ghi ten user -->
                                <ul>
                                    <li><a href="profile.html">My Account</a></li>
                                    <li><a href="profile.html">My Rentals</a></li>
                                    <li><a href="profile.html">My Requests</a></li>
                                    <li><a href="non_logined.html">Log out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </header>
            </div>
            <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
        </div>
    </body>

</html>
