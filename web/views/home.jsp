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
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/demo/image-removebg-preview.png">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div id="pageintro" class="hoc clear" >
            <article>
                <h4 class="heading" style="white-space: nowrap;">Explore your new Destination</h4>
                <footer style="height: 300px;">
                    <form class="group" method="get" action="SearchPostController">
                        <fieldset>
                            <legend>Sign-Up:</legend>
                            <input type="text" name="content" placeholder="Search smth">
                            <button type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                </svg>
                            </button>
                            <select class="form-select" name="type">
                                <option value="address">Address</option>
                                <option value="number">Number Of Room</option>
                                <option value="user">House owner</option>
                            </select>
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
                <div id="latest" class="group" style="border: 1px solid red">
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
        <div class="wrapper row3">
            <main class="hoc container clear">
                <section id="introblocks">
                    <div class="sectiontitle">
                        <h6 class="heading">What do you want</h6>
                        <p></p>
                    </div>
                    <ul class="nospace group func" style="align-items: baseline;
                        padding: auto;
                        display: grid;
                        grid-auto-flow: column;">

                        <li class="one_quarter  borderEntity"
                            style="display: grid; width: 90%; align-items: center; height: 350px;">
                            <article><a href="${pageContext.request.contextPath}/Search?type=2"><i class="fas fa-golf-ball"></i></a>
                                <h4 class="heading">Rent</h4>
                                <p>See all of House for rent with a filter</p>
                                <footer><a class="btn" href="#">More Details</a></footer>
                            </article>
                        </li>

                        <li class="one_quarter borderEntity " style=" width: 90%; align-items: center;
                            display: grid; height: 350px;">
                            <article><a href="${pageContext.request.contextPath}/Search?type=1"><i class="fas fa-ribbon"></i></a>
                                <h4 class="heading">Buy</h4>
                                <p></p>See all of House for sale with a filter</p>
                                <footer><a class="btn" href="#">More Details</a></footer>
                            </article>
                        </li>

                        <li class="one_quarter borderEntity"
                            style=" width: 90%; align-items: center; display: grid; height: 350px;">
                            <article><a href="#"><i class="fas fa-tablets"></i></a>
                                <h4 class="heading">Sell</h4>
                                <p>Create a post to help you selling a House</p>
                                <footer><a class="btn" href="${pageContext.request.contextPath}/post/add">More Details</a></footer>
                            </article>
                        </li>

                    </ul>
                </section>
                <div class="clear"></div>
            </main>
        </div>               
        <p style="color: red">${requestScope.msg}</p>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
