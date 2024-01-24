<%-- 
    Document   : viewPost
    Created on : Jan 23, 2024, 9:32:00 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/layout.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/framework.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/manh.css"/>
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
    <body>
        <jsp:include page="header.jsp"/>
        <div class="wrapper row3" style="top: -45px;">
            <main class="hoc container clear gridmain">
                <div class="sidebar grid1_4">
                    <nav class="sdb_holder">
                        <ul>
                            <li><a href="profile.html" style="font-weight: bold;">Account Information</a></li>
                            <li><a href="${pageContext.request.contextPath}/order/view">View Orders</a>
                                <ul>
                                    <c:forEach items="${statuses}" var="status">
                                        <li><a href="${pageContext.request.contextPath}/status?status=${status.status_id}">${status.status_name}</a></li>
                                        </c:forEach>
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
                </div>
                <div>
                    <table><c:if test="${not empty posts}">
                            <tbody>
                                <c:forEach items="${posts}" var="post">
                                    <tr>
                                        <td>${post.house.location}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </c:if>
                    </table>

                    <c:if test="${empty posts}">
                        <!-- Hiển thị thông báo hoặc nội dung khác khi không có bài đăng -->
                        <p>No posts available.</p>
                    </c:if>
                </div>
            </main>

        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
