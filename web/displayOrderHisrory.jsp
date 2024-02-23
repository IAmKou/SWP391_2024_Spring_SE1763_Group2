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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/profile.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="./views/header.jsp"/>


        <div class="d-flex align-items-center">
            <!-- Avatar -->
            <div class="me-3">
                <img src="your-avatar-image.jpg" alt="Avatar" class="rounded-circle" style="width: 50px; height: 50px;">
            </div>

            <!-- User Information -->
            <div>
                <p class="mb-auto"><strong>Name:</strong> John Doe</p>
                <p class="mb-auto"><strong>Balance:</strong> $1000</p>
                <a href="#" class="btn btn-outline-primary">Edit Profile</a>
            </div>

            <!-- Navigation Links -->
            <div class="container mt-4">
                <div class="ms-5">
                    <ul class="nav nav-pills nav-fill">
                        <li class="nav-item mb-3">
                            <button class="btn btn-outline-primary" type="submit">All</button>

                        </li>
                        <li class="nav-item mb-3">
                            <button class=" btn btn-outline-primary" type="submit">Requested</button>

                        </li>
                        <li class="nav-item mb-3">
                            <button class=" btn btn-outline-primary" type="submit">Completed</button>

                        </li>
                        <li class="nav-item mb-3">
                            <button class=" btn btn-outline-primary" type="submit">Canceled</button>

                        </li>
                    </ul>
                </div>
            </div>

        </div>

        <div class="mt-auto">
            <div class="row">
                <div class="col-3">
                    <div class="d-blockz align-items-center">
                        <div>Home</div>
                        <div>My Account</div>
                        <div>My Account</div>
                        <div>View Your Rental</div>
                    </div>
                </div>
                <div class="col-7">

                    <c:forEach items="${listRequest}" var="listRequest">
                        <div class="row">
                            <div class="col-3 d-flex justify-content-between align-items-center">
                                <div class="">
                                    House Name: ${listRequest.getHouse_id().getHouse_id()}
                                    <br><!-- comment -->
                                    <img src="./images/demo/gallery/01.png" alt="alt" style="height: 90px; width: 90px"/>
                                </div>
                            </div>
                            <div class="col-6 d-flex justify-content-between align-items-center">
                                <div class="">
                                    View Post
                                    <br>House Owner: ${listRequest.getSeller_id().getFull_name()}
                                    <br>Address: ${listRequest.getHouse_id().getLocation()}
                                </div>
                            </div>
                            <div class="col-3 d-flex justify-content-between align-items-center">
                                <div class="">Status:${listRequest.getRequest_status().getStatus_name()}</div>
                            </div>
                        </div>

                    </c:forEach>



                </div>
            </div>
        </div>





        <jsp:include page="./views/footer.jsp"/>
    </body>
</html>
