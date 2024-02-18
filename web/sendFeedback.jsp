<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/styles/profile.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha384-J3f5C5uJ5uwSIyV8sUqUXJSN2SpXa6cV/wKp9ylV+h3MzCvUAdyVoHHYZ5cZDcPW" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 20px;
            margin-top: 50px;
        }

        h2 {
            color: #007bff;
        }

        .rating {
            margin-top: 10px;
        }

        .rating label {
            cursor: pointer;
            color: #ffc107; /* Yellow color for stars */
            font-size: 2em;
        }

        .rating input:checked + label:before {
            color: #ff8c00; /* Orange color for filled star */
        }

        .mb-3 {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        textarea {
            resize: none;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Feedback Form</h2>
        <form action="send-feedback" method="post">

            <!-- Star Ratings -->
            <div class="mb-3">
                <label for="starRating">Rate your experience:</label>
                <div class="rating">
                    <input type="radio" name="star" value="5" id="star5"><label for="star5"><i class="fa-regular fa-star"></i></label>
                    <input type="radio" name="star" value="4" id="star4"><label for="star4"><i class="fa-regular fa-star"></i></label>
                    <input type="radio" name="star" value="3" id="star3"><label for="star3"><i class="fa-regular fa-star"></i></label>
                    <input type="radio" name="star" value="2" id="star2"><label for="star2"><i class="fa-regular fa-star"></i></label>
                    <input type="radio" name="star" value="1" id="star1"><label for="star1"><i class="fa-regular fa-star"></i></label>
                </div>
            </div>

            <!-- Comment -->
            <div class="mb-3">
                <label for="comment">Your feedback:</label>
                <textarea name="comment" id="comment" rows="4" class="form-control"></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit Feedback</button>
        </form>
        <p>
            ${mess}
        </p>
    </div>

</body>
</html>
