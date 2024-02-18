<%-- 
    Document   : search
    Created on : Feb 15, 2024, 7:20:19 AM
    Author     : trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href="${pageContext.request.contextPath}/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
        <link href="${pageContext.request.contextPath}/layout/styles/manh.css" rel="stylesheet" type="text/css" media="all">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="">

            <div>
                <jsp:include page="./views/header.jsp"/>
            </div>


            <div class="wrapper row3">
                <main class="hoc container clear"> 
                    <!-- main body -->
                    <!-- ################################################################################################ -->
                    <div class="content"> 
                        <!-- ################################################################################################ -->
                        <div id="gallery">
                            <figure>
                                <header class="">
                                    <form action="search-result" method="post">
                                        <div class="sectiontitle" style="margin: 20px; padding: 15px; background-color: #f0f0f0;max-width: 100%;">
                                            <h4 class="heading" style="font-size: 1.5em; margin-bottom: 10px;">Search Location</h4> 
                                            <div style="display: flex; justify-content: space-between;">
                                                <div style="flex: 1; margin-right: 10px; padding: 10px; background-color: #eaeaea;">
                                                    Search Location
                                                </div>



                                                <div style="flex: 1; margin-right: 10px;">

                                                    <select name="selectedPurpose" style="width: 100%;">

                                                        <c:forEach items="${listPurposes}" var="listPu">
                                                            <option value="${listPu.getPurpose_id()}">${listPu.getPurpose_name()}</option>

                                                        </c:forEach>


                                                    </select>
                                                </div>
                                                <div style="flex: 1; margin-right: 10px;">
                                                    <select name="selectedPriceRange" style="width: 100%;">
                                                        <option value="0-1000000">Price</option>
                                                        <option value="0-200">0-200</option>
                                                        <option value="200-500">200-500</option>
                                                        <option value="500-1000">500-1k</option>
                                                        <option value="1000-1000000000">>1k</option>
                                                    </select>
                                                </div>
                                                <div style="flex: 1; margin-right: 10px;">
                                                    <select   name="selectedTypeOfHouse" style="width: 100%;">
                                                        <c:forEach items="${listType}" var="listType">
                                                            <option value="${listType.getType_of_house_id()}">${listType.getType_of_house_name()}</option>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                                <!--                                                <div style="flex: 1; margin-right: 10px;">
                                                                                                    <select style="width: 100%;">
                                                                                                        <option>1</option>
                                                                                                        <option>2</option>
                                                                                                        <option>3</option>
                                                                                                    </select>
                                                                                                </div>-->
                                                <div style="flex: 1;">
                                                    <button type="submit"  style="width: 100%; padding: 10px; background-color: #4caf50; color: white; border: none; cursor: pointer;">Save Search</button>
                                                </div>

                                            </div>
                                        </div>
                                    </form>


                                    <div class="group btmspace-50 demo">
                                        <div class="one_half first border">
                                            1                    
                                        </div>
                                        <div class="one_half border">
                                            <div>Location</div>
                                            <div class="group btmspace-50 demo">
                                                <div class="one_half first border">Result</div>
                                                <div class="one_half border">Sort by</div>
                                            </div>
                                        </div>
                                    </div>
                                </header>
                                <div class="row list-unstyled">
                                    <c:if test="${not empty listResult}">
                                        <c:forEach items="${listResult}" var="result">
                                            <div class="col-md-3 mb-3" style="width: 25%;">
                                                <div>
                                                    <a href="#"><img src="./images/demo/gallery/01.png" alt=""></a>
                                                </div>
                                                <div class="group btmspace-50 demo">
                                                    <div class="one_half first border">Cost: ${result.getPrice()}vnd </div>
                                                </div>
                                                <div>
                                                    Address: ${result.getHouse().getHouse_owner().getAddress()}
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty listResult}">
                                        <div class="col-md-12">
                                            <h1>No results found.</h1>
                                        </div>
                                    </c:if>
                                </div>


                            </figure>
                        </div>
                        <!-- ################################################################################################ -->
                        <!-- ################################################################################################ -->
                        <nav class="pagination">
                            <ul>
                                <li><a href="#">&laquo; Previous</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><strong>&hellip;</strong></li>
                                <li><a href="#">6</a></li>
                                <li class="current"><strong>7</strong></li>
                                <li><a href="#">8</a></li>
                                <li><a href="#">9</a></li>
                                <li><strong>&hellip;</strong></li>
                                <li><a href="#">14</a></li>
                                <li><a href="#">15</a></li>
                                <li><a href="#">Next &raquo;</a></li>
                            </ul>
                        </nav>
                        <!-- ################################################################################################ -->
                    </div>
                    <!-- ################################################################################################ -->
                    <!-- / main body -->
                    <div class="clear"></div>
                </main>
            </div>


        </div>

    </body>
</html>
