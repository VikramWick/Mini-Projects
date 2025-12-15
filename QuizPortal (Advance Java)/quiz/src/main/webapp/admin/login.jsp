<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="card">
    <h2>Admin Login</h2>
    <form action="${pageContext.request.contextPath}/admin/login" method="post">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
        <p style="color:red;">${error}</p>
    </form>
</div>

</body>
</html>
