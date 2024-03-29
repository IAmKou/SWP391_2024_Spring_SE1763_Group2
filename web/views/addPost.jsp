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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/addPost.css"/>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
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
                                img.style.width = "100px";
                                img.style.height = "100px";
                                img.style.margin = "8px";
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
                xhr.open('POST', '/post/add', true);
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
        </style>
    </head>
    <body>

        <div><jsp:include page="header.jsp"/></div>
        <div class="container justify-content-center align-items-center vh-100 h-100">

            <form method="post" action="../post/add" enctype="multipart/form-data" class="p-5" style="background-color: #2b3035; border-radius: 30px">
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
                    <div class="row mb-3">
                        <label for="address" class="col-sm-3 col-form-label text-end">Location:</label>
                        <div class="col-sm">
                            <input id="address" type="text" name="location" class="form-control" placeholder="Enter your address" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="type" class="col-sm-3 col-form-label text-end">Type:</label>
                        <div class="col-sm">
                            <select id="type" name="type" class="form-select">
                                <c:forEach items="${sessionScope.types}" var="type">
                                    <option value="${type.type_of_house_id}">
                                        ${type.type_of_house_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="price" class="col-sm-3 col-form-label text-end">Price($):</label>
                        <div class="col-sm">
                            <input type="number" id="price" name="price" class="form-control" placeholder="Enter the price" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="roomNumber" class="col-sm-3 col-form-label text-end">Number of Rooms:</label>
                        <div class="col-sm">
                            <input id="roomNumber" type="number" name="number_of_room" class="form-control" placeholder="Enter the number of rooms">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="area" class="col-sm-3 col-form-label text-end">Area(m<sup>2</sup>):</label>
                        <div class="col-sm">
                            <input id="area" type="number" name="area" class="form-control" placeholder="Enter the area" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="moreInformation" class="col-sm-3 col-form-label text-end">Description:</label>
                        <div class="col-sm">
                            <textarea class="form-control" id="exampleFormControlTextarea1" name="description" rows="5" spellcheck="false" style="height: 200px;"></textarea>                        </div>
                    </div>

                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label text-end">Purpose:</label>
                        <div class="col-sm">
                            <c:forEach items="${sessionScope.purposes}" var="purpose">
                                <div class="form-check form-check-inline">
                                    <input type="radio" id="purpose_${purpose.purpose_id}" name="purpose" value="${purpose.purpose_id}" class="form-check-input" required>
                                    <label for="purpose_${purpose.purpose_id}" class="form-check-label">${purpose.purpose_name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-3"></div>
                        <div class="col-sm">
                            <div id="image" class="image-preview"></div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label for="upload" class="col-sm-3 col-form-label text-end">Image:</label>
                        <div class="col-sm">
                            <input id="upload" id="fileInput" type="file" class="form-control" placeholder="Upload your file" name="image" accept="image/*" onchange="chooseFile(this)" multiple required>
                        </div>
                    </div>

                </fieldset>
                <div class="text-end">
                    <input type="submit" class="btn btn-light" value="Create" onclick="uploadImages()">
                </div>
            </form>
            <div class="mt-5"><jsp:include page="footer.jsp"/></div>
        </div>
    </body>
</html>
