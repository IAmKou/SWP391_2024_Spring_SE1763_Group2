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
        <title>Report Detail</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href="${pageContext.request.contextPath}/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
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
        <jsp:include page="/views/header.jsp"></jsp:include>
            <div class="wrapper row3" style="top: 15px;">
                <main class="hoc container clear gridmain">
                    <!-- main body -->
                <jsp:include page="/sample/left_side_bar_for_admin_account.jsp"></jsp:include>
                    <!-- ################################################################################################ -->
                    <!-- ################################################################################################ -->
                    <div class="content grid3_4 borderEntity " style="height: 450px;">
                        <!-- <div class="clear"> -->

                        <section class="ftco-section">
                        <c:forEach var="report" items="${requestScope.list}">
                            <table>
                                <tr>
                                    <th>Report ID:</th>
                                    <td>${report.report_id}</td>
                                </tr>
                                <tr>
                                    <th>Feedback Content:</th>
                                    <td>${report.fcontent}</td>
                                </tr>
                                <tr>
                                    <th>Reporter:</th>
                                    <td>${report.user.full_name}</td>
                                </tr>
                                <tr>
                                    <th>Report Time: </th>
                                    <td>${report.report_time}</td>
                                </tr>
                            </table>
                            <p style="color: red">${requestScope.msg}</p>
　　　　　　　　　　　　　　　<form action="${pageContext.request.contextPath}/ReportResponseController" method="post">
                                <input type="hidden" value="${report.fb.user_id}" name="fuid">
                                Send Caution: <input type="text" name="message" required/>
                                <button class="btn" style="float: right; margin-right: 45px;" type="submit">Submit</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/DeleteFeedbackAdmin" method="get">
                                <input type="hidden" value="${report.fb.user_id}" name="fuid">
                                <input type="hidden" value="${report.fb.feedback_id}" name="fid">
                                <input type="hidden" value="${report.report_id}" name="rid">
                                <button class="btn" style="float: right; margin-right: 45px;" type="submit">Delete</button>
                            </form>
                        </c:forEach>
                    </section>
                </div>
        </div>
    </div>
</main>
</div>


<jsp:include page="/views/footer.jsp"></jsp:include>
</body>
</html>
