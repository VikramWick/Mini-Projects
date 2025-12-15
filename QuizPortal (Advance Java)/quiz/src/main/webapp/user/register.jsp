<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="card">
    <h2>Create Account</h2>

    <form action="${pageContext.request.contextPath}/auth" method="post">
        <input type="hidden" name="action" value="register">
        <input type="text" name="name" placeholder="Enter Name" required>
        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>
