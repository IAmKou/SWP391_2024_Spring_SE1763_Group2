<%-- 
    Document   : updateHouse
    Created on : Jan 15, 2024, 1:14:29 PM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/fontawesome-free/css/fontawesome-all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <script>
            function chooseFile(fileInput) {
                var container = document.getElementById("image");
                container.innerHTML = ''; // Xóa tất cả các ảnh cũ trước khi thêm mới
                if (fileInput.files && fileInput.files.length > 0) {
                    for (var i = 0; i < fileInput.files.length; i++) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var img = new Image();
                            img.onload = function () {
                                container.appendChild(img);
                            };
                            img.src = e.target.result;
                        };
                        reader.readAsDataURL(fileInput.files[i]);
                    }
                }
            }

            function uploadImages() {
                var formData = new FormData();
                var fileInput = document.getElementById('fileInput');

                // Lặp qua các tệp đã chọn và thêm chúng vào đối tượng FormData
                if (fileInput.files.length > 0) {
                    for (var i = 0; i < fileInput.files.length; i++) {
                        var file = fileInput.files[i];
                        formData.append('image[]', file); // Sử dụng 'image[]' để tạo một mảng các hình ảnh
                    }
                }

                // Gửi multipart request bằng AJAX
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/post/update', true);
                xhr.send(formData);
            }
        </script>
        <style>
            #image img {
                width: 100px;
                height: 100px;
                margin: 5px;
                border-radius: 10px; /* Đặt giá trị border-radius tùy ý */
            }
            .image-preview {
                border-radius: 10px;
                margin-bottom: 10px;
                background-color: #198754; /* Màu nền mặc định */
            }
            .image{
                border-radius: 10px;
                margin-bottom: 10px;
                background-color: #E0E0E0; /* Màu nền mặc định */
            }
            .alert-message {
                color: #ffffff;
                padding: 10px 20px;
                margin: 10px 0;
                border-radius: 5px;
                text-align: center;
            }

        </style>
        <title>Update your post</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="container justify-content-center align-items-center vh-100">
            <form method="post" action="../post/update" enctype="multipart/form-data" class="p-5" style="background-color: #2b3035; border-radius: 30px">
                <fieldset>
                    <div class="row mb-3">
                        <div class="col-sm-3"></div>
                        <div class="col-sm">
                            <c:if test="${alert ne null}">
                                <div class="alert alert-warning h4">
                                    ${alert}
                                </div>
                            </c:if>
                            <c:if test="${success ne null}">
                                <div class="alert alert-success center h4">
                                    ${success}
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <input type="hidden" value="${sessionScope.post.house.house_id}" name="house_id">
                    <input type="hidden" name="post_id" value="${sessionScope.post.post_id}">
                    <div class="row mb-3">
                        <label for="address" class="col-sm-3 col-form-label text-end">Address:</label>
                        <div class="col-sm">
                            <input id="address" class="form-control" type="text" name="location" value="${sessionScope.post.house.location}" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="type" class="col-sm-3 col-form-label text-end">Type:</label>
                        <div class="col-sm">
                            <select id="type" name="type" class="form-select">
                                <c:set var="post" value="${post}" scope="session" />
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.type_of_house_id}" ${type.type_of_house_id eq post.house.type_of_house.type_of_house_id  ? 'selected' : ''}>
                                        ${type.type_of_house_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="price" class="col-sm-3 col-form-label text-end">Price($):</label>
                        <div class="col-sm">
                            <input type="number" id="price" name="price" class="form-control" value="${sessionScope.post.price}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="roomNumber" class="col-sm-3 col-form-label text-end">Number of Rooms:</label>
                        <div class="col-sm">
                            <input id="roomNumber" class="form-control" type="text" name="number_of_room" value="${sessionScope.post.house.number_of_room}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="area" class="col-sm-3 col-form-label text-end">Area(m<sup>2</sup>):</label>
                        <div class="col-sm">
                            <input id="area" class="form-control" type="text" name="area" value="${sessionScope.post.house.area}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="moreInformation" class="col-sm-3 col-form-label text-end">More Information:</label>
                        <div class="col-sm">
                            <textarea class="form-control" id="exampleFormControlTextarea1" name="description"  rows="5" spellcheck="false" style="height: 200px;">${sessionScope.post.house.description}</textarea>                        
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label text-end">Purpose:</label>
                        <div class="col-sm">
                            <c:forEach items="${purposes}" var="purpose">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="purpose" value="${purpose.purpose_id}" ${purpose.purpose_id eq post.purpose.purpose_id  ? 'checked' : ''}>
                                    <label class="form-check-label">
                                        ${purpose.purpose_name}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm-3"></div>
                        <div class="col-sm">
                            <c:if test="${sessionScope.post.house.image ne null}">
                                <div class="image">
                                    <c:forEach items="${sessionScope.post.house.image}" var="image">
                                        <img src="data:image/jpeg;base64,${image.getImageDataAsBase64()}" alt="hinh anh" style="width: 100px; height: 100px; border-radius: 5px; margin: 5px"/>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <div id="image" class="image-preview"></div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="upload" class="col-sm-3 col-form-label text-end">Image:</label>
                        <div class="col-sm">
                            <input id="fileInput" type="file" class="form-control" placeholder="Upload your file" name="image" accept="image/*" onchange="chooseFile(this)" multiple>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm-3"></div>
                        <div class="col-sm">
                            <div class="alert alert-info" role="alert">
                                Note: When you select and upload new image files, 
                                all existing image files will be replaced by the new ones. 
                                This means that no images from previous uploads will be retained. 
                                Please ensure that you have backed up or stored any necessary image files before proceeding with the upload.
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="text-end">
                    <input type="submit" class="btn btn-light" value="Update" onclick="uploadImages()">
                </div>
            </form>
            <div class="mt-5"><jsp:include page="footer.jsp"/></div>
        </div>
    </body>
</html>
