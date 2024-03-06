<%-- 
    Document   : header
    Created on : Jan 24, 2024, 11:54:48 PM
    Author     : FPTSHOP
--%>

<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>This is header</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/framework.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/layout.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/manh.css"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png">
        <style>
            .avatar {
                width: 100px; /* Adjust the width and height as needed */
                height: 100px;
                border-radius: 50%; /* Make it circular */
                overflow: hidden; /* Hide overflowing content */
                object-fit: cover;
            }
        </style>
    </head>
    <body id="top">
        <div class="bgded overlay ">
            <div class="wrapper row1">
                <header id="header" class="hoc clear" style="display: grid; grid-template-columns: 3fr 6fr 3fr;
                        grid-template-rows: 1fr;">
                    <nav id="mainav" class="hnc1">
                        <ul class="clear">
                            <li class="active"><a href="${pageContext.request.contextPath}/SearchPostController?type=1&content=1">Buy</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/SearchPostController?type=2&content=2">Rent</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/post/add">Sell</a></li>
                            <li class="active"><a href="#">Help</a></li>
                        </ul>
                    </nav>
                    <nav id="mainav" class="hnc2">
                        <a href="${pageContext.request.contextPath}/views/home.jsp"> <img src="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png" alt="image" class="logo"></a>
                    </nav>
                    <nav id="mainav" class="hnc3" >
                        <ul class="clear" style="
                            width: 450px;
                            /*align-content: flex-end;*/
                            ">

                            <li class="active"><a href="${pageContext.request.contextPath}/views/home.jsp">Home</a></li>

                            <c:if test="${sessionScope.account ne null}">
                                <c:if test="${sessionScope.user.role_id eq 1}">
                                    <li><a href="${pageContext.request.contextPath}/viewUserList">Manage</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.user.role_id eq 2}">
                                    <li class="active"><a href="#">My Rentals</a></li>
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.account eq null}">
                                <li class="active"><a href="${pageContext.request.contextPath}/logIn.jsp">Login</a></li>
                                </c:if>



                            <li> ${sessionScope.user.getUser_name()}
                                <img src="${sessionScope.account.getAvatar()}" class="avatar">
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/viewProfile">My Account</a></li>
                                    <li><a href="${pageContext.request.contextPath}/post/view">My Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/request/view">My Requests</a></li>
                                    <li><a href="${pageContext.request.contextPath}/logOutController">Log out</a></li>                                  
                                </ul>
                            </li>
                            <c:if test="${sessionScope.account ne null}">
                                <li> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bell" viewBox="0 0 16 16">
                                    <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2M8 1.918l-.797.161A4 4 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4 4 0 0 0-3.203-3.92zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5 5 0 0 1 13 6c0 .88.32 4.2 1.22 6"/>
                                    </svg>                                  
                                    <ul>
                                        <c:forEach var="noti" items="${sessionScope.notifications}">                                 
                                            <li><a href="#">${noti.message} ${noti.created_at}</a></li>                                     
                                            </c:forEach>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </header>
            </div>
            <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
        </div>
    </body>

</html>
