<%-- 
    Document   : test
    Created on : Feb 21, 2024, 11:03:38 AM
    Author     : FPTSHOP
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function chooseFile(fileInput) {
                var container = document.getElementById("image-container");
                container.innerHTML = ''; // Xóa tất cả các ảnh cũ trước khi thêm mới

                if (fileInput.files && fileInput.files.length > 0) {
                    for (var i = 0; i < fileInput.files.length; i++) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var img = document.createElement("img");
                            img.src = e.target.result;
                            img.width = 200; // Đặt kích thước ảnh
                            img.height = 200;
                            container.appendChild(img); // Thêm ảnh vào phần tử container
                        };
                        reader.readAsDataURL(fileInput.files[i]);
                    }
                }
            }
            function uploadImages() {
                var formData = new FormData();
                var fileInput = document.getElementById('fileInput');

                for (var i = 0; i < fileInput.files.length; i++) {
                    var file = fileInput.files[i];
                    formData.append('image', file);

                    var img = document.createElement('img');
                    img.src = URL.createObjectURL(file);
                    img.width = 200;
                    img.height = 200;
                    previewImages.appendChild(img);
                }

                // Gửi multipart request bằng AJAX
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/test', true);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        alert('Upload thành công!');
                    } else {
                        alert('Upload thất bại!');
                    }
                };
                xhr.send(formData);
            }
        </script>
    </head>
    <body>
        <c:forEach var="image" items="${images}">
            <!-- Hiển thị ảnh từ dữ liệu Base64 -->
            <img src="data:image/jpeg;base64,${image.getImageDataAsBase64()} " width="200px" height="200px"/>
        </c:forEach>

        <form action="test" method="post" enctype="multipart/form-data">
            <div id="image-container"></div>
            <input type="submit" class="btn btn-light" onclick="uploadImages()" value="Create">
        </form>
    </body>
</html>
