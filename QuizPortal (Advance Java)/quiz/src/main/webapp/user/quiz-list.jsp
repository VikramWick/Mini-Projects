<%@ page import="model.Quiz, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quiz List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Available Quizzes</h2>

    <table>
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Take Quiz</th>
        </tr>

        <%
            List<Quiz> list = (List<Quiz>) request.getAttribute("list");
            int i=1;
            for (Quiz q : list) {
        %>
        <tr>
            <td><%=i++%></td>
            <td><%=q.getTitle()%></td>
            <td><a class="btn" href="${pageContext.request.contextPath}/quiz/start?id=<%=q.getId()%>">Start</a></td>
        </tr>
        <% } %>

    </table>
</div>

</body>
</html>
