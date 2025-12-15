<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="card">
    <h2>User Login</h2>
    <form action="${pageContext.request.contextPath}/auth" method="post">
        <input type="hidden" name="action" value="login">
        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <button type="submit">Login</button>
    </form>

    <p>New User? <a href="${pageContext.request.contextPath}/user/register.jsp">Register</a></p>
</div>

</body>
</html>
