<%-- 
    Document   : addHouse
    Created on : Jan 14, 2024, 6:12:30 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complete a Form to Authenication</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/framework.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/layout.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/manh.css"/>
    </head>
    <body id="top">
        <jsp:include page="header.jsp"/>
        <div class="borderEntity hoc clear row1 wrapper" style=" min-height: 500px;height: fit-content;
             padding:2vw;
             background-color: rgba(
             red, green, blue); ">

            <form method="post" action="../post/add">
                <fieldset>
                    <h1 style="text-align: center; margin-top: 20px;">Create your new post</h1>

                    <table style="width: 100%;">
                        <tr>
                            <td style="width: 15%;"><label for="address">Address:</label></td>
                            <td style="width: 85%;"><input id="address" class="btmspace-15" type="text" name="location"
                                                           placeholder="Enter your address"required></td>
                        </tr>
                        <tr>
                            <td><label for="type">Type:</label></td>
                            <td>
                                <select id="type" name="type">
                                    <c:forEach items="${types}" var="type">
                                        <option value="${type.type_of_house_id}">${type.type_of_house_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><td><label for="price">Price:</label></td>
                            <td><input type="number" id="price" name="price" placeholder="Enter the price"></td>
                        </tr>
                        <tr>
                            <td><label for="roomNumber">Number of Rooms:</label></td>
                            <td><input id="roomNumber" class="btmspace-15" type="text" name="number_of_room"
                                       placeholder="Enter the number of rooms"></td>
                        </tr>
                        <tr>
                            <td><label for="area">Area:</label></td>
                            <td><input id="area" class="btmspace-15" type="text" name="area" placeholder="Enter the area">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="moreInformation">More Information:</label></td>
                            <td><input id="moreInformation" class="btmspace-15" type="text" name="description"
                                       placeholder="Enter more information"
                                       style="width: 100%; min-height: 70px; height: fit-content;"></td>
                        </tr>
                        <tr>
                            <td><label for="upload">Upload File:</label></td>
                            <td><input id="upload" class="btmspace-15" type="file" value=""
                                       placeholder="Upload your file"></td>
                        </tr>
                        <tr>
                            <td>Purpose:</td>
                            <td>
                                <c:forEach items="${purposes}" var="purpose">
                                    <span for="purpose_${purpose.purpose_id}">${purpose.purpose_name}</span>
                                    <input type="radio" id="purpose_${purpose.purpose_id}" name="purpose" value="${purpose.purpose_id}" placeholder="">
                                </c:forEach>
                            </td>
                        </tr>
                    </table>

                </fieldset>
                <div class="hoc clear" style="
                     right: 20px; display: flex;
                     flex-direction: row-reverse;
                     padding: 10px;
                     ">
                    <button class="btn">Cancel</button>
                    <input type="submit" class="btn submit" value="Submit">
                </div>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
