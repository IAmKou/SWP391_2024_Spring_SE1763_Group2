<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <div>
        <jsp:include page="./views/header.jsp"/>

        <div class="container mt-4">
            <h2>Feedback History</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-table">Post</th>
                        <th class="text-table">User</th>
                        <th class="text-table">Number of Stars</th>
                        <th class="text-table" colspan="2">Comment</th>
                        <th class="text-table">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listFeedback}" var="listF">


                        <tr>
                            <td class="text-table">${listF.getPost_id().getPost_id()}</td>
                            <td class="text-table">${listF.getUser_id().getUser_id()}</td>
                            <td class="text-table">${listF.getNumber_of_star()}</td>
                            <td class="text-table" colspan="2">${listF.getComment()}.</td>
                            <td class="text-table">
                                <button>Edit</button>
                                <button>Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="./views/footer.jsp"/>
    </div>
</body>
