<%-- 
    Document   : generalPostList
    Created on : Feb 23, 2024, 10:25:25 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>${title}</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href="${pageContext.request.contextPath}/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
        <link href="${pageContext.request.contextPath}/layout/styles/manh.css" rel="stylesheet" type="text/css" media="all">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png">
    </head>
    <body>
        <jsp:include page="header.jsp"/>



        <div class="wrapper row1">
            <div
                style="height: 100px"></div>
        </div>

        <div class="wrapper row3">






            <section class="hoc container clear">
                <div class="sectiontitle">
                    <h4 class="heading">Most requested</h4>
                    <p>House has most number of order</p>
                </div>
                <div id="latest" class="group">

                    <article class="one_third borderEntity topproheight"><a href="#">
                            <img class="imgAuth" src="${pageContext.request.contextPath}/images/house3.jpg" style="border-radius: 4px;" alt=""></a>
                        <div class="excerpt">

                            <time>
                                <h6 class="heading" style="white-space: nowrap; overflow: hidden;"
                                    title=" Hà Nôi City, Tran Duy Hung Street,1234567890">
                                    <!-- phần inner html sẽ bị ẩn 1 phần nếu quá dài, cần cập nhật vào title để hiển thị đầy đủ -->
                                    Hà Nôi City, Tran Duy Hung Street, 1234567890
                                </h6>
                                <ul class="meta">
                                    <li style="line-height: 1.8;">
                                        <i class="fas fa-tags rgtspace-5"></i>
                                        <a href="#">4 <span> rooms</span> </a>,
                                        <a href="#">120 <span>m<sup>2</sup></span></a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="#">1.500.000.000 <span>VND</span> </a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="#">Type</a>
                                    </li>

                                    <li style="line-height: 1.8;">
                                        <a href="#">For Sale</a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="#">Available</a>
                                    </li>
                                </ul>
                            </time>
                            <div class="truncate-text">
                                <span> dolor phasellus ornare dui vel euismod ultrices orci libero pulvinar justo quis condimentum
                                    quam.</span>
                                <div class="view-more"><a class="" href="housedetail.html">View more</a></div>
                            </div>
                        </div>

                    </article>

                    <c:forEach var="card" items="${cardList}">
                        <article class="one_third borderEntity topproheight"><a href="${pageContext.request.contextPath}">
                                <img class="imgAuth" src="${pageContext.request.contextPath}/images/house3.jpg" style="border-radius: 4px;" alt=""></a>
                            <div class="excerpt">

                                <time>
                                    <h6 class="heading" style="white-space: nowrap; overflow: hidden;"
                                        title="">
                                        <!-- phần inner html sẽ bị ẩn 1 phần nếu quá dài, cần cập nhật vào title để hiển thị đầy đủ -->
                                        ${card.value.location}
                                    </h6>
                                    <ul class="meta">
                                        <li style="line-height: 1.8;">
                                            <i class="fas fa-tags rgtspace-5"></i>
                                            <a href="#">${card.value.numer_of_room} <span> rooms</span> </a>,
                                            <a href="#">${card.value.area}  <span>m<sup>2</sup></span></a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">${card.key.price}  <span>VND</span> </a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">${card.value.TypeOfHouse.type_of_house_name}</a>
                                        </li>

                                        <li style="line-height: 1.8;">
                                            <a href="#">For Sale</a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">Available</a>
                                        </li>
                                    </ul>
                                </time>
                                <div class="truncate-text">
                                    <span> dolor phasellus ornare dui vel euismod ultrices orci libero pulvinar justo quis condimentum
                                        quam.</span>
                                    <div class="view-more"><a class="" href="housedetail.html">View more</a></div>
                                </div>
                            </div>

                        </article>
                    </c:forEach>


                </div>
            </section>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
