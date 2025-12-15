<%@ page import="java.util.*, model.Result" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>Leaderboard – ${quiz.title} (Quiz ID: ${quiz.id})</h2>

    <table>
        <tr>
            <th>#</th><th>User</th><th>Score</th>
        </tr>

        <%
            int i=1;
            List<Result> r = (List<Result>) request.getAttribute("results");
            for (Result res : r) {
        %>
        <tr>
            <td><%=i++%></td>
            <td><%=res.getUserName()%></td>
            <td><%=res.getScore()%></td>
    
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
