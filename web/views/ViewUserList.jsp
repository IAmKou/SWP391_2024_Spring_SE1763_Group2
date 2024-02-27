<%-- 
    Document   : viewUserList
    Created on : Jan 26, 2024, 12:05:05 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
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
        </style>
    </head>

    <body id="top">
        <div class="bgded overlay" style="background-color: black;">
            <!-- ################################################################################################ -->
            <jsp:include page="header.jsp"/>
            <!-- End Top Background Image Wrapper -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <div class="wrapper row3" style="top: -45px;">
                <main class="hoc container clear gridmain">
                    <!-- main body -->
                    <!-- ################################################################################################ -->
                    <div class="sidebar grid1_4">
                        <!-- ################################################################################################ -->
                        <!--<h6>Youz</h6>-->
                        <nav class="sdb_holder">
                            <ul>
                                <li><a href="views/home.jsp">Home</a></li>
                                <li><a href="profile.html" style="font-weight: bold;">Account Information</a></li>
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
                                        <li><a href="#">Your Post</a></li>
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
                            <!-- <div class="container" >
                                    </div> -->
                            <div class="row" style="">
                                <div class="col-md-12">
                                    <div class="table-wrap">

                                        <table class="table table-responsive-xl" style="word-wrap: break-word ;font-family: Arial, sans-serif;
                                               border-collapse: collapse;
                                               width: 100%;
                                               max-width: 600px; /* Chiều rộng tối đa của bảng */" >
                                            <tr>
                                                <td><input type="text" id="col1Input" onkeyup="filterTable()" placeholder="id"></th>
                                                <td><input type="text" id="col2Input" onkeyup="filterTable()" placeholder="username"></th>
                                                <td><input type="text" id="col3Input" onkeyup="filterTable()" placeholder="fullname"></th>
                                                <td><input type="text" id="col4Input" onkeyup="filterTable()" placeholder="email"></th>
                                                <td><input type="text" id="col5Input" onkeyup="filterTable()" placeholder="phone"></th>
                                            </tr>
                                        </table>
                                        <p>${msg}</p>
                                        <table id="ulist" class="table table-responsive-xl" style="word-wrap: break-word">

                                            <thead>
                                                <tr>
                                                    <th onclick="sortTable(0)">User ID<i class="fas fa-sort"></i></th>
                                                    <th onclick="sortTable(1)">User Name<i class="fas fa-sort"></i></th>
                                                    <th onclick="sortTable(2)">Full Name<i class="fas fa-sort"></i></th>
                                                    <th onclick="sortTable(3)">Email<i class="fas fa-sort"></i></th>
                                                    <th onclick="sortTable(4)">Phone<i class="fas fa-sort"></i></th>
                                                    <th onclick="sortTable(5)">Active<i class="fas fa-sort"></i></th>
                                                </tr>
                                            </thead>
                                            <tbody style="white-space: nowrap; overflow: hidden;">
                                                <c:forEach var="user" items="${userList}">
                                                    <tr onclick="viewProfile('${user.key.user_id}')">
                                                        <td>${user.key.user_id}</td>
                                                        <td>${user.value.user_name}</td>
                                                        <td>${user.key.full_name}</td>

                                                        <td>${user.key.email}</td>
                                                        <td>${user.key.phone_number}</td>
                                                        <td>${user.value.isActive()==true?'Active':'Inactive'}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- </div> -->
                            </div>
                        </section>
                        <nav aria-label="Page navigation example" class="m-3">
                            <ul class="pagination">
                                
                                <c:set var="status" value="${param.status}" />
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${totalPages}" var="page">
                                    <c:set var="activeClass" value="" />
                                    <c:if test="${currentPage eq page}">
                                        <c:set var="activeClass" value="active" />
                                    </c:if>
                                    <li class="page-item ${activeClass}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${page}">${page}</a>
                                    </li>
                                </c:forEach>
                                    
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/viewUserList?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </main>
            </div>
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <jsp:include page="footer.jsp"/>
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <!-- ################################################################################################ -->
            <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
            <!-- JAVASCRIPTS -->
            <script src="layout/scripts/jquery.min.js"></script>
            <script src="layout/scripts/jquery.backtotop.js"></script>
            <script src="layout/scripts/jquery.mobilemenu.js"></script>
            <script>
                                                        function viewProfile(userId) {
                                                            window.location.href = 'userProfile?id=' + userId;
                                                        }
                                                        function sortTable(colIndex) {
                                                            var table = document.getElementById("ulist");
                                                            var rows = table.rows;
                                                            var switching = true;

                                                            while (switching) {
                                                                switching = false;
                                                                for (var i = 1; i < rows.length - 1; i++) {
                                                                    var row1 = rows[i].getElementsByTagName("td")[colIndex];
                                                                    var row2 = rows[i + 1].getElementsByTagName("td")[colIndex];
                                                                    if (row1.innerHTML.toLowerCase() > row2.innerHTML.toLowerCase()) {
                                                                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                                                                        switching = true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        //                                                        function filterTable(colIndex) {
                                                        //                                                            var input, filter, table, tr, td, txtValue;
                                                        //                                                            input = document.getElementById("input1");
                                                        //                                                            filter = input.value.toUpperCase();
                                                        //                                                            table = document.getElementById("ulist");
                                                        //                                                            tr = table.getElementsByTagName("tr");
                                                        //
                                                        //                                                            for (var i = 1; i < tr.length; i++) {
                                                        //                                                                td = tr[i].getElementsByTagName("td")[colIndex];
                                                        //                                                                if (td) {
                                                        //                                                                    txtValue = td.textContent || td.innerText;
                                                        //                                                                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                        //                                                                        tr[i].style.display = "";
                                                        //                                                                    } else {
                                                        //                                                                        tr[i].style.display = "none";
                                                        //                                                                    }
                                                        //                                                                }
                                                        //                                                            }
                                                        function filterTable() {
                                                            var inputs, filter, table, tr, td, i, j, txtValue;
                                                            inputs = document.querySelectorAll('input[type=text]');
                                                            table = document.getElementById("ulist");
                                                            tr = table.getElementsByTagName("tr");
                                                            for (i = 0; i < tr.length; i++) {
                                                                tr[i].style.display = "";
                                                                for (j = 0; j < inputs.length; j++) {
                                                                    filter = inputs[j].value.toUpperCase();
                                                                    td = tr[i].getElementsByTagName("td")[j];
                                                                    if (td) {
                                                                        txtValue = td.textContent || td.innerText;
                                                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                                            continue;
                                                                        } else {
                                                                            tr[i].style.display = "none";
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
            </script>
    </body>
</html>
