<%@ page import="java.util.*, model.Question, dao.QuestionDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>Admin</h2>
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">Dashboard</a>
</div>

<div class="content">
    <h2>Create Quiz</h2>

    <form action="${pageContext.request.contextPath}/admin/create-quiz" method="post">
        <input type="text" name="title" placeholder="Quiz Title" required>
        <input type="text" name="category" placeholder="Category" required>

        <h3>Select Questions</h3>
        <table>
            <tr><th>Select</th><th>Question</th></tr>
            <%
                List<Question> q = (List<Question>) request.getAttribute("list");
                if (q == null) {
                    QuestionDao dao = new QuestionDao();
                    q = dao.getAll();
                }
                for (Question question : q) {
            %>
            <tr>
                <td><input type="checkbox" name="qId" value="<%=question.getId()%>"></td>
                <td><%=question.getQuestionText()%></td>
            </tr>
            <% } %>
        </table>

        <button type="submit">Create Quiz</button>
    </form>
</div>

</body>
</html>
