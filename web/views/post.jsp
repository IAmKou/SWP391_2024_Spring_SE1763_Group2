<%-- 
    Document   : post
    Created on : Feb 18, 2024, 6:05:44 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <title>JSP Page</title>
        <script src="${pageContext.request.contextPath}/layout/bootstrap-5.3.2-dist/js/bootstrap.min.js"></script>
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>-->

    </head>
    <body>
        <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
         <div class="container">
        <div class="row">
            <div class="col-md-12 mb-4">
                <div class="card m-2">
                    <div class="card-body">
                        <h5 class="card-title fs-4 text-primary mb-3">${post.house.location}</h5>
                        <p class="card-text" style="color: black;">
                            Price: ${post.price} $
                        </p>
                        <p class="card-text" style="color: black;">
                            Area: ${post.house.area} sqft
                        </p>
                        <p class="card-text" style="color: black;">
                            Number of Rooms: ${post.house.number_of_room}
                        </p>
                        <p class="card-text" style="color: black;">
                            House Type: ${post.house.type_of_house.type_of_house_name}
                        </p>
                        <p class="card-text" style="color: black;">
                            Description: ${post.house.description}
                        </p>
                        <p class="card-text" style="color: black;">
                            Poster: ${post.house.house_owner.full_name}
                        </p>
                        <p class="card-text" style="color: black;">
                            Contact Information: ${post.house.house_owner.address}, ${post.house.house_owner.phone_number}, ${post.house.house_owner.email}
                        </p>
                        <div class="btn-group">
                            <a href="${pageContext.request.contextPath}/post/update?post_id=${post.post_id}" class="btn btn-outline-primary">Update</a>
                            <a href="url" class="btn btn-outline-primary">Overview</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div>
    </body>
</html>
