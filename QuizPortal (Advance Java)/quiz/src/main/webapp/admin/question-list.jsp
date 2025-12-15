<%@ page import="java.util.*, model.Question" %>
<!DOCTYPE html>
<html>
<head>
    <title>Question List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>Admin</h2>
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/add-question.jsp">Add Question</a>
</div>

<div class="content">
    <h2>All Questions</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Question</th>
            <th>Category</th>
            <th>Correct</th>
        </tr>
        <%
            List<Question> list = (List<Question>) request.getAttribute("questions");
            if(list != null) {
                for(Question q : list) {
        %>
        <tr>
            <td><%=q.getId()%></td>
            <td><%=q.getQuestionText()%></td>
            <td><%=q.getCategory()%></td>
            <td><%=q.getCorrectOption()%></td>
        </tr>
        <% }} %>
    </table>
</div>
</body>
</html>