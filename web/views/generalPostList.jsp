<%-- 
    Document   : generalPostList
    Created on : Feb 23, 2024, 10:25:25 PM
    Author     : Acer
--%>

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





        <div class="wrapper row3 " >

            <section class="hoc container clear " >
                <div class="sectiontitle">
                    <h4 class="heading">Result:</h4>
                    <p>There are ${num}/${numcheck}/${hnum} houses found</p>
                </div>
                <div id="latest" class="group" >

                    <c:forEach var="card" items="${cardList}" varStatus="loop">
                        <article class="one_third borderEntity topproheight ${loop.index%3 ==0 ? 'first' : ''}" style=" margin-bottom: 30px; padding: auto">
                            <a href="${pageContext.request.contextPath}">
                                <img class="imgAuth" src="${pageContext.request.contextPath}/images/house3.jpg" style="border-radius: 4px;" alt="">
                            </a>
                            <div class="excerpt">
                                <time>
                                    <h6 class="heading" style="white-space: nowrap; overflow: hidden;" title="${card.value.location}">
                                        ${card.value.location}
                                    </h6>
                                    <ul class="meta">
                                        <li style="line-height: 1.8;">
                                            <i class="fas fa-tags rgtspace-5"></i>
                                            <a href="#">${card.value.number_of_room} <span> rooms</span> </a>,
                                            <a href="#">${card.value.area}  <span>m<sup>2</sup></span></a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">${card.key.price}  <span>VND</span> </a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">${card.value.type_of_house.type_of_house_name}</a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">${card.key.purpose.purpose_name}</a>
                                        </li>
                                        <li style="line-height: 1.8;">
                                            <a href="#">Available</a>
                                        </li>
                                    </ul>
                                </time>
                                <div class="truncate-text">
                                    <span>${card.value.description}</span>
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
