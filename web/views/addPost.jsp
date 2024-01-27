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
        <title>Create your post</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/fontawesome-free/css/fontawesome-all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="container justify-content-center align-items-center vh-100">
            <form method="post" action="../post/add" class="p-5" style="background-color: #2b3035; border-radius: 30px">
                <fieldset>
                    <h1 class="text-center mt-4">Create your new post</h1>

                    <div class="row mb-3">
                        <label for="address" class="col-sm-3 col-form-label text-end">Address:</label>
                        <div class="col-sm">
                            <input id="address" type="text" name="location" class="form-control" placeholder="Enter your address" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="type" class="col-sm-3 col-form-label text-end">Type:</label>
                        <div class="col-sm">
                            <select id="type" name="type" class="form-select">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.type_of_house_id}">${type.type_of_house_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="price" class="col-sm-3 col-form-label text-end">Price:</label>
                        <div class="col-sm">
                            <input type="number" id="price" name="price" class="form-control" placeholder="Enter the price">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="roomNumber" class="col-sm-3 col-form-label text-end">Number of Rooms:</label>
                        <div class="col-sm">
                            <input id="roomNumber" type="text" name="number_of_room" class="form-control" placeholder="Enter the number of rooms">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="area" class="col-sm-3 col-form-label text-end">Area(m<sup>2</sup>):</label>
                        <div class="col-sm">
                            <input id="area" type="text" name="area" class="form-control" placeholder="Enter the area">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="moreInformation" class="col-sm-3 col-form-label text-end">Description:</label>
                        <div class="col-sm">
                            <input id="moreInformation" type="text" name="description" class="form-control" placeholder="Enter more information">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="upload" class="col-sm-3 col-form-label text-end">Image:</label>
                        <div class="col-sm">
                            <input id="upload" type="file" class="form-control" placeholder="Upload your file">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label text-end">Purpose:</label>
                        <div class="col-sm">
                            <c:forEach items="${purposes}" var="purpose">
                                <div class="form-check form-check-inline">
                                    <input type="radio" id="purpose_${purpose.purpose_id}" name="purpose" value="${purpose.purpose_id}" class="form-check-input">
                                    <label for="purpose_${purpose.purpose_id}" class="form-check-label">${purpose.purpose_name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </fieldset>
                <div class="text-end">
                    <input type="submit" class="btn btn-light" value="Create">
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
