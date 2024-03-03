<%-- 
    Document   : sendNotification
    Created on : Mar 3, 2024, 10:46:52 PM
    Author     : luong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Send Notification Page</title>
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
                            <form action="sendNotificationController" method="post">
                                <table>
                                    <tr>
                                        <th>Notification:</th>
                                        <td><input type="text" name="mess" required placeholder="Notification here"></td>
                                    </tr>                                
                                </table>
                                <p style="color: red">${requestScope.msg}</p>
                                <button class="btn" style="float: right; margin-right: 45px;" type="submit">Submit</button>
                            </form>
                        </section>
                    </div>
                </main>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp"></jsp:include>
    </body>
</html>
