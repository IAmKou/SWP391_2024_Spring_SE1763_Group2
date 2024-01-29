
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="">

    <head>
        <title>Search Result</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href="layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
        <link href="layout/styles/manh.css" rel="stylesheet" type="text/css" media="all">
        <style>
        </style>
    </head>

    <body id="top">



        <!-- <div class="bgded overlay " style="background-image:url('images/demo/backgrounds/01.png');"> -->


        <!-- ################################################################################################ -->
        <div class="bgded overlay " style="background-color: #323639 ;height: 100px">
            <!-- ################################################################################################ -->
            <jsp:include page="./views/header.jsp"></jsp:include>


        </div>

        <div class="wrapper row3">
            <section class="hoc container clear">
                <!-- ################################################################################################ -->
                <form>
                    <div class="sectiontitle" style="margin: 20px; padding: 15px; background-color: #f0f0f0;max-width: 100%;">
                        <h4 class="heading" style="font-size: 1.5em; margin-bottom: 10px;">Search Location</h4> 
                        <div style="display: flex; justify-content: space-between;">
                            <div style="flex: 1; margin-right: 10px; padding: 10px; background-color: #eaeaea;">
                                Search Location
                            </div>



                            <div style="flex: 1; margin-right: 10px;">
                                <select style="width: 100%;">
                                    <option>All</option>
                                    <option>For sale</option>
                                    <option>For Rent</option>
                                </select>
                            </div>
                            <div style="flex: 1; margin-right: 10px;">
                                <select style="width: 100%;">
                                    <option>Price</option>
                                    <option>0-200</option>
                                    <option>200-500</option>
                                    <option>500-1k</option>
                                    <option>>1k</option>
                                </select>
                            </div>
                            <div style="flex: 1; margin-right: 10px;">
                                <select style="width: 100%;">
                                    <option>Type</option>
                                </select>
                            </div>
                            <div style="flex: 1; margin-right: 10px;">
                                <select style="width: 100%;">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                            </div>
                            <div style="flex: 1;">
                                <input type="submit" value="Save Search" style="width: 100%; padding: 10px; background-color: #4caf50; color: white; border: none; cursor: pointer;">
                            </div>

                        </div>
                    </div>
                </form>

                <div class="group btmspace-50 demo" style="height: 450px">
                    <div class="one_half first"style="height: 450px">

                        <iframe src="https://www.google.com/maps/embed?pb=!1m13!1m8!1m3!1d119182.54967935712!2d105.56740608691403!3d21.014485710449442!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zMjHCsDAwJzQ3LjIiTiAxMDXCsDMxJzMxLjYiRQ!5e0!3m2!1svi!2s!4v1706501089517!5m2!1svi!2s" 
                                width="100%" height="100%" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

                    </div>
                    <div class="one_half"style="height: 450px">
                        <div style="height: 10%">
                            <div>Location</div>
                            <div style="display: flex" class="group btmspace-50 demo">
                                <div class="one_half first">Result</div>
                                <div class="one_half">Sort by</div>
                            </div>
                        </div>
                        <div style="height: 80%; display: flex; flex-wrap: wrap;">
    <div>1</div>
    <div>1</div>
    <div>1</div>
    <div>1</div>
</div>

<style>
    div {
        flex: 1 0 50%; /* Chia đều chiều rộng của mỗi div */
        box-sizing: border-box;
    }

    div:nth-child(odd) {
        background-color: lightgray; /* Màu nền cho hàng lẻ */
    }
</style>

                    </div>
                </div>

            </section>
        </div>
        <div class="wrapper row3">
            <main class="hoc container clear">
                <!-- main body -->
                <!-- ################################################################################################ -->
                <section id="introblocks">
                    <div class="sectiontitle">
                        <h6 class="heading">What do you want</h6>
                        <p></p>
                    </div>
                    <ul class="nospace group func" style="align-items: baseline;
                        padding: auto;
                        display: grid;
                        /* border: 1px solid black; */
                        grid-auto-flow: column;">

                        <li class="one_quarter  borderEntity"
                            style="display: grid; width: 90%; align-items: center; height: 350px;">
                            <article><a href="#"><i class="fas fa-golf-ball"></i></a>
                                <h4 class="heading">Rent</h4>
                                <p>See all of House for rent wwith a filter</p>
                                <footer><a class="btn" href="#">More Details</a></footer>
                            </article>
                        </li>

                        <li class="one_quarter borderEntity " style=" width: 90%; align-items: center;
                            display: grid; height: 350px;">
                            <article><a href="#"><i class="fas fa-ribbon"></i></a>
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
                                <footer><a class="btn" href="#">More Details</a></footer>
                            </article>
                        </li>

                    </ul>
                </section>
                <!-- ################################################################################################ -->
                <!-- / main body -->
                <div class="clear"></div>
            </main>
        </div>


        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <div class="wrapper row4">
            <footer id="footer" class="hoc clear">
                <!-- ################################################################################################ -->
                <div class="group btmspace-80">
                    <div class="one_half first">
                        <h6 class="heading">Community Chanels</h6>
                        <p>Morbi tincidunt in hac habitasse platea dictumst praesent pretium donec tincidunt laoreet diam nullam
                            augue.</p>
                        <p class="btmspace-30">Tortor rhoncus sed dictum vitae viverra posuere lorem pellentesque suscipit eros vel
                            ante curabitur pretium [<a href="#"><i class="fas fa-arrow-right"></i></a>]</p>
                        <ul class="faico clear">
                            <li><a class="faicon-facebook" href="https://facebook.com/manh.2883"><i class="fab fa-facebook"></i></a>
                            </li>
                            <li><a class="faicon-vk" href="https://discord.gg/TqPPWkC2"><i class="fab fa-discord"></i></a></li>
                        </ul>
                    </div>
                    <div class="one_half">
                        <h6 class="heading">Send us an Email</h6>
                        <p class="nospace btmspace-15">Libero class aptent taciti sociosqu ad litora torquent per conubia nostra.
                        </p>
                        <form method="post" action="#">
                            <fieldset>
                                <legend>Newsletter:</legend>
                                <input class="btmspace-15" type="text" value="" placeholder="Name">
                                <input class="btmspace-15" type="text" value="" placeholder="Email">
                                <button type="submit" value="submit">Submit</button>
                            </fieldset>
                        </form>
                    </div>
                </div>


                <!-- ################################################################################################ -->
                <div id="ctdetails" class="clear">
                    <ul class="nospace clear">
                        <li class="one_quarter first">
                            <div class="block clear"><a href="#"><i class="fas fa-phone"></i></a> <span><strong>Give us a
                                        call:</strong>
                                    +84343102032 (Gamardonn)</span></div>
                        </li>
                        <li class="one_quarter">
                            <div class="block clear"><a href="#"><i class="fas fa-envelope"></i></a> <span><strong>Send us a
                                        mail:</strong> heheh@gmail.com</span></div>
                        </li>
                        <li class="one_quarter">
                            <div class="block clear"><a href="#"><i class="fas fa-clock"></i></a> <span><strong> Monday -
                                        Saturday:</strong> 08.00am - 22.00pm</span></div>
                        </li>
                        <li class="one_quarter">
                            <div class="block clear"><a href="#"><i class="fas fa-map-marker-alt"></i></a> <span><strong>Come visit
                                        us:</strong> Directions to <a href="#">our location</a></span></div>
                        </li>
                    </ul>
                </div>
                <!-- ################################################################################################ -->
            </footer>
        </div>
        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <div class="wrapper row5">
            <div id="copyright" class="hoc clear">
                <!-- ################################################################################################ -->
                <p class="fl_left">Copyright &copy; 2024 - All Rights Reserved</p>
                <p class="fl_right">Template by <a target="_blank" href="https://www.os-templates.com/"
                                                   title="Free Website Templates">OS Templates</a></p>
                <!-- ################################################################################################ -->
            </div>
        </div>
        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <!-- ################################################################################################ -->
        <a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
        <!-- JAVASCRIPTS -->
        <script src="layout/scripts/jquery.min.js"></script>
        <script src="layout/scripts/jquery.backtotop.js"></script>
        <script src="layout/scripts/jquery.mobilemenu.js"></script>
    </body>

</html>