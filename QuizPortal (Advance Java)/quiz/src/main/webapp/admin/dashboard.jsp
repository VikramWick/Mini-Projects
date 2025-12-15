<%@ page language="java" import="dao.AdminDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>QUIZ ADMIN</h2>
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/questions?action=list">Question Manager</a>
    <a href="${pageContext.request.contextPath}/admin/create-quiz.jsp">Quiz Manager</a>
    <a href="${pageContext.request.contextPath}/admin/logout">Logout</a>
</div>

<div class="content">
    <%
        if (request.getAttribute("totalQuiz") == null) {
            AdminDao dao = new AdminDao();
            request.setAttribute("totalQuiz", dao.getTotalQuizzes());
            request.setAttribute("totalQuestions", dao.getTotalQuestions());
            request.setAttribute("totalUsers", dao.getTotalUsers());
        }
    %>
    <%
        if (request.getAttribute("totalQuiz") == null) {
            AdminDao dao = new AdminDao();
            request.setAttribute("totalQuiz", dao.getTotalQuizzes());
            request.setAttribute("totalQuestions", dao.getTotalQuestions());
            request.setAttribute("totalUsers", dao.getTotalUsers());
        }
    %>
    <h1>Dashboard</h1>
    <p>Total Quizzes: ${totalQuiz}</p>
    <p>Total Questions: ${totalQuestions}</p>
    <p>Total Users: ${totalUsers}</p>
</div>

</body>
</html>
