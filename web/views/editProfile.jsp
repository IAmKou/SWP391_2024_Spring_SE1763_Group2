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
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="wrapper row3" style="top: 15px;">
                <main class="hoc container clear gridmain">
                    <!-- main body -->
                <c:if test="${sessionScope.user.role_id eq 2}">
                    <jsp:include page="/sample/left_side_bar_for_user_account.jsp"></jsp:include>
                </c:if>
                <c:if test="${sessionScope.user.role_id eq 1}">
                    <jsp:include page="/sample/left_side_bar_for_admin_account.jsp"></jsp:include>
                </c:if>
                <!-- ################################################################################################ -->
                <!-- ################################################################################################ -->
                <div class="content grid3_4 borderEntity " style="height: 450px;">
                    <!-- <div class="clear"> -->

                    <section class="ftco-section">

                        <c:if test="${msg eq null}">
                            <form action="updateUser" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                <table>

                                    <tr>
                                        <th>User ID:</th>
                                        <td>${user.user_id}</td>
                                    </tr>
                                    <tr>
                                        <th>User Name:</th>
                                        <td>${account.user_name}</td>
                                    </tr>
                                    <tr>
                                        <th>Full Name:</th>
                                        <td> <input type="text" name="full_name" id="fullName" value="${sessionScope.account.getFull_name()}" placeholder="${sessionScope.account.getFull_name()}"></td>
                                    </tr>
                                    <tr>
                                        <th>Date Of Birth: </th>
                                        <td><input type="date" name="date_of_birth" id="phone" value="${sessionScope.account.getDate_of_birth()}" placeholder="${sessionScope.account.getDate_of_birth()}"></td>
                                    </tr>
                                    <tr>
                                        <th>Address:</th>
                                        <td><input type="text" name="address" id="address" value="${sessionScope.account.getAddress()}"></td>
                                    </tr>

                                    <tr>
                                        <th>Phone Number:</th>
                                        <td><input type="text" name="phone_number" id="phone" value="${sessionScope.account.getPhone_number()}"></td>
                                    </tr>
                                    <tr>
                                        <th>Email:</th>
                                        <td><input type="email" name="email" id="email" value="${sessionScope.account.getEmail()}"></td>
                                    </tr>
                                    <tr>
                                        <th>Avatar: </th>
                                        <td><input type="file" name="file"><img src="${sessionScope.account.getAvatar()}" class="avatar"></td>
                                    </tr>

                                </table>
                                <p style="color: red">${requestScope.msg}</p>

                                <a href="${pageContext.request.contextPath}/myProfile">
                                    <button class="btn" style="float: right; margin-right: 45px;">Cancel</button>
                                </a>

                                <button class="btn" style="float: right; margin-right: 45px;" type="submit">Submit</button>
                            </form>            


                        </c:if>
                        <c:if test="${msg ne null}">
                            <h4>${msg}</h4>
                            <a href="${pageContext.request.contextPath}/logIn.jsp"><button class="btn" style="float: right; margin-right: 45px;" type="submit">Login</button>

                        </c:if>

                    </section>

                </div>


        </div>
    </div>
</main>
</div>


<jsp:include page="footer.jsp"></jsp:include>
<script>
    function validateForm() {
        var fullName = document.getElementById("fullName").value;
        var dateOfBirth = document.getElementById("dateOfBirth").value;
        var address = document.getElementById("address").value;
        var phoneNumber = document.getElementById("phoneNumber").value;
        var email = document.getElementById("email").value;

        if (fullName == "" || dateOfBirth == "" || address == "" || phoneNumber == "" || email == "") {
            alert("Vui lòng điền đầy đủ thông tin.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
