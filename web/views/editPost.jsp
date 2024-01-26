<%-- 
    Document   : updateHouse
    Created on : Jan 15, 2024, 1:14:29 PM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="borderEntity hoc clear row1 wrapper" style=" min-height: 500px;height: fit-content;
             padding:2vw;
             background-color: rgba(
             red, green, blue); ">
            
            <form method="post" action="../post/update">
                <fieldset>
                    <h1 style="text-align: center; margin-top: 20px;">Edit Post</h1>

                    <table style="width: 100%;">
                        <tr>
                            <td style="width: 15%;"><label for="address">Address:</label></td>
                            <td style="width: 85%;"><input id="address" class="btmspace-15" type="text" name="location"
                                                           value="${requestScope.post.house.location}"required></td>
                        </tr>
                        <tr>
                            <td><label for="type">Type:</label></td>
                            <td>
                                <select id="type" name="type">
                                    <c:set var="post" value="${post}" scope="request" />
                                    <c:forEach items="${types}" var="type">
                                        
                                        <option value="${type.type_of_house_id}" ${type.type_of_house_name eq post.house.type_of_house.type_of_house_name  ? 'selected' : ''}>
                                            ${type.type_of_house_name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><td><label for="price">Price:</label></td>
                            <td><input type="number" id="price" name="price" value="${requestScope.post.price}"></td>
                        </tr>
                        <tr>
                            <td><label for="roomNumber">Number of Rooms:</label></td>
                            <td><input id="roomNumber" class="btmspace-15" type="text" name="number_of_room"
                                       value="${requestScope.post.house.number_of_room}"></td>
                        </tr>
                        <tr>
                            <td><label for="area">Area:</label></td>
                            <td><input id="area" class="btmspace-15" type="text" name="area" value="${requestScope.post.house.area}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="moreInformation">More Information:</label></td>
                            <td><input id="moreInformation" class="btmspace-15" type="text" name="description"
                                       value="${requestScope.post.house.description}"
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
                                <c:set var="post" value="${post}" scope="request" />
                                <c:forEach items="${purposes}" var="purpose">
                                    <span>${purpose.purpose_name}</span>
                                    <input type="radio" name="purpose" value="${purpose.purpose_id}" ${purpose.purpose_name eq post.purpose.purpose_name  ? 'checked' : ''}>
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
                    <input type="submit" class="btn submit" value="Update">
                </div>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
