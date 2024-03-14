<%-- 
    Document   : sendNotification
    Created on : Mar 3, 2024, 10:46:52 PM
    Author     : luong
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report List Page</title>
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
            .avatar {
                width: 100px; /* Adjust the width and height as needed */
                height: 100px;
                border-radius: 50%; /* Make it circular */
                overflow: hidden; /* Hide overflowing content */
            }
        </style>
    </head>
    <body>
        <div class="bgded overlay" style="background-color: black;">
            <jsp:include page="/views/header.jsp"></jsp:include>
                <div class="wrapper row3" style="top: 15px;">
                    <main class="hoc container clear gridmain">
                    <jsp:include page="/sample/left_side_bar_for_admin_account.jsp"/>
                    <div class="content grid3_4 borderEntity " style="height: 450px;">
                        <section class="ftco-section">
                            <form action="ViewReportDetail" method="post"> 
                                <c:forEach var="report" items="${requestScope.list}">
                                    Report:<input type="text" name="rid" readonly required value="${report.report_id}">
                                    <button type=submit><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
                                    </svg>
                                    </button>
                                </c:forEach>
                                <p style="color: red">${requestScope.msg}</p>
                            </form>
                        </section>
                    </div>
                </main>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp"></jsp:include>
    </body>
</html>
