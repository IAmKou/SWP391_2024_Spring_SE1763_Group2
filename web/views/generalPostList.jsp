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
    <style>
        .form-container {
            width: 300px;
            height: 200px; /* Set the desired height */
            overflow-y: scroll; /* Enable vertical scrollbar */
            border: 1px solid #ccc;
            padding: 10px;
        }
    </style>
    <body>
        <jsp:include page="header.jsp"/>
        <section class="hoc container clear " >
            <aside id="sidebar" class="hoc clear">
                <form action="AdvanceSearchController" method="get">
                    <input type="hidden" value="${requestScope.current}" name="content"/>
                    <p>Type Of House</p> <select name="type"> 
                        <option value="department">Department</option>
                        <option value="villa">Villa</option>
                        <option value="entire house">Entire House</option>
                        <option value="other">Other</option>
                    </select>
                    <p>Rooms</p><select name="room">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option><!-- comment -->
                        <option value="6">6</option>
                    </select>
                    <p>Price Range</p>
                    <input type="range" name="price" min="0" max="100000" step="1000" onchange="updatePrice(this.value)">
                    <output id="priceOutput" for="price">50000</output>
                    <input type="range" name="area" min="0" max="500" step="10" onchange="updateArea(this.value)">
                    <output id="areaOutput" for="area">250</output>
                    <br/>
                    <button type="submit">Submit</button>
                </form>
            </aside>
            <div class="sectiontitle">
                <h4 class="heading">Result:</h4>
                <p>There are ${num} houses found</p>
                <p>${requestScope.msg}</p>

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
        <script>
            function updatePrice(value) {
                document.getElementById('priceOutput').textContent = value;
            }
            function updateArea(value) {
                document.getElementById('areaOutput').textContent = value;
            }
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
