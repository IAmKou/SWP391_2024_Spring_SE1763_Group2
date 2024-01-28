<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>User Profile</h1>
    <form action="updateUser" method="post">
        <label for="fullName">Full Name:</label>
        <input type="text" name="full_name" id="fullName" value="${param.fullName}"><br>

        <label for="userName">User Name:</label>
        <input type="text" name="userName" id="userName" value="${param.userName}"><br>

        <label for="passWord">Password:</label>
        <input type="password" name="passWord" id="passWord" value="${param.passWord}"><br>

        <label for="address">Address</label>
        <input type="text" name="address" id="address" value="${param.address}"><br>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" id="phone" value="${param.phone}"><br>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="${param.email}"><br>

        <input type="submit" value="Update">
    </form>

    <a href="changePassword.jsp">Change Password</a>
</body>
</html>