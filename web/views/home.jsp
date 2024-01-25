<%-- 
    Document   : home
    Created on : Jan 25, 2024, 9:52:05 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href="${pageContext.request.contextPath}/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
        <link href="${pageContext.request.contextPath}/layout/styles/manh.css" rel="stylesheet" type="text/css" media="all">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div id="pageintro" class="hoc clear">
            <article>
                <h4 class="heading" style="white-space: nowrap;">Explore your new Destination</h4>
                <footer style="height: 300px;">
                    <form class="group" method="post" action="#">
                        <fieldset>
                            <legend>Sign-Up:</legend>
                            <input type="text" value="" placeholder="Enter here&hellip;">
                            <button class="fas fa-sign-in-alt" type="submit" title="Submit"><em>Submit</em></button>
                        </fieldset>
                    </form>
                </footer>
            </article>
        </div>

        <div class="wrapper row3">
            <section class="hoc container clear">
                <div class="sectiontitle">
                    <h4 class="heading">Most requested</h4>
                    <p>House has most number of order</p>
                </div>
                <div id="latest" class="group">
                    <article class="one_third first borderEntity topproheight"><a class="imgover" href="#">
                            <img class="imgAuth" src="${pageContext.request.contextPath}/images/house1.jpg" style="border-radius: 4px;" alt=""></a>
                        <div class="excerpt">
                            <time>
                                <h6 class="heading" style="white-space: nowrap; overflow: hidden;"
                                    title=" Hà Nôi City, Tran Duy Hung Street,1234567890">
                                    Hà Nôi City, Tran Duy Hung Street, 1234567890
                                </h6>
                                <ul class="meta">
                                    <li style="line-height: 1.8;">
                                        <i class="fas fa-tags rgtspace-5"></i>
                                        <a href="search.html">4 <span> rooms</span> </a>,
                                        <a href="search.html">120 <span>m<sup>2</sup></span></a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="search.html">1.500.000.000 <span>VND</span> </a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="search.html">For Sale</a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="search.html">Available</a>
                                    </li>
                                </ul>
                            </time>
                            <p>
                                <span> dolor phasellus ornare dui vel euismod ultrices orci libero pulvinar justo quis condimentum
                                    quam.</span>
                                <span><a class="" href="housedetail.html">View more</a></span>
                            </p>
                        </div>
                    </article>
                    <article class="one_third borderEntity topproheight"><a class="imgover" href="#">
                            <img class="imgAuth" src="${pageContext.request.contextPath}/images/house2.jpg" style="border-radius: 4px;" alt=""></a>
                        <div class="excerpt">
                            <time>
                                <h6 class="heading" style="white-space: nowrap; overflow: hidden;"
                                    title=" Hà Nôi City, Tran Duy Hung Street,1234567890">
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
                                        <a href="#">For Sale</a>
                                    </li>
                                    <li style="line-height: 1.8;">
                                        <a href="#">Available</a>
                                    </li>
                                </ul>

                            </time>
                            <p>
                                <span> dolor phasellus ornare dui vel euismod ultrices orci libero pulvinar justo quis condimentum
                                    quam.</span>
                                <span><a class="" href="#">View more</a></span>
                            </p>
                        </div>
                    </article>
                    <article class="one_third borderEntity topproheight"><a class="imgover" href="#">
                            <img class="imgAuth" src="${pageContext.request.contextPath}/images/house3.jpg" style="border-radius: 4px;" alt=""></a>
                        <div class="excerpt">
                            <!-- <time datetime="2045-04-03T08:15+00:00">
                            <i class="far fa-calendar-alt"></i> 03<sup>rd</sup> April 2045 @
                            15:00pm
                          </time> -->
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
                </div>
            </section>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
