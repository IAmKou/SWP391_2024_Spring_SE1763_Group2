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
        <link rel="stylesheet" href="../layout/styles/fontawesome-free/css/fontawesome-all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>Update your post</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="container justify-content-center align-items-center vh-100">
            <form method="post" action="../post/update" class="p-5" style="background-color: #2b3035; border-radius: 30px">
                <fieldset>
                    <h1 class="text-center mt-4">Edit Post</h1>
                    <input type="hidden" value="${requestScope.post.house.house_id}" name="house_id">
                    <input type="hidden" name="post_id" value="${requestScope.post.post_id}">
                    <div class="row mb-3">
                        <label for="address" class="col-sm-3 col-form-label text-end">Address:</label>
                        <div class="col-sm">
                            <input id="address" class="form-control" type="text" name="location" value="${requestScope.post.house.location}" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="type" class="col-sm-3 col-form-label text-end">Type:</label>
                        <div class="col-sm">
                            <select id="type" name="type" class="form-select">
                                <c:set var="post" value="${post}" scope="request" />
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.type_of_house_id}" ${type.type_of_house_id eq post.house.type_of_house.type_of_house_id  ? 'selected' : ''}>
                                        ${type.type_of_house_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="price" class="col-sm-3 col-form-label text-end">Price:</label>
                        <div class="col-sm">
                            <input type="number" id="price" name="price" class="form-control" value="${requestScope.post.price}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="roomNumber" class="col-sm-3 col-form-label text-end">Number of Rooms:</label>
                        <div class="col-sm">
                            <input id="roomNumber" class="form-control" type="text" name="number_of_room" value="${requestScope.post.house.number_of_room}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="area" class="col-sm-3 col-form-label text-end">Area:</label>
                        <div class="col-sm">
                            <input id="area" class="form-control" type="text" name="area" value="${requestScope.post.house.area}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="moreInformation" class="col-sm-3 col-form-label text-end">More Information:</label>
                        <div class="col-sm">
                            <input id="moreInformation" class="form-control" type="text" name="description" value="${requestScope.post.house.description}" style="min-height: 70px;">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="upload" class="col-sm-3 col-form-label text-end">Upload File:</label>
                        <div class="col-sm">
                            <input id="upload" class="form-control" type="file" value="">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label text-end">Purpose:</label>
                        <div class="col-sm">
                            <c:set var="post" value="${post}" scope="request" />
                            <c:forEach items="${purposes}" var="purpose">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="purpose" id="purpose_${purpose.purpose_id}" value="${purpose.purpose_id}" ${purpose.purpose_id eq post.purpose.purpose_id  ? 'checked' : ''}>
                                    <label class="form-check-label" for="purpose_${purpose.purpose_id}">
                                        ${purpose.purpose_name}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="text-end">
                            <input type="submit" class="btn btn-light" value="Update">
                    </div>
                </fieldset>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
