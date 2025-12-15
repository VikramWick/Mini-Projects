<!DOCTYPE html>
<html>
<head>
    <title>Add Question</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>Admin</h2>
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/questions?action=list">Questions</a>
</div>

<div class="content">
    <h2>Add Question</h2>

    <form action="${pageContext.request.contextPath}/admin/questions" method="post">
        <input type="text" name="question" placeholder="Enter question" required>
        <input type="text" name="category" placeholder="Category" required>
        <input type="text" name="category" placeholder="Category" required>

        <input type="text" name="option1" placeholder="Option A" required>
        <input type="text" name="option2" placeholder="Option B" required>
        <input type="text" name="option3" placeholder="Option C" required>
        <input type="text" name="option4" placeholder="Option D" required>

        <input type="text" name="correct" placeholder="Correct Option (A/B/C/D)" required>

        <button type="submit">Save Question</button>
    </form>

</div>
</body>
</html>
