<%@ page import="java.util.*, model.Question, model.Quiz" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Detail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="sidebar">
    <h2>Admin</h2>
</div>

<div class="content">
    <h2>Quiz Detail</h2>

    <h3>${quiz.title}</h3>

    <table>
        <tr>
            <th>#</th><th>Question</th><th>A</th><th>B</th><th>C</th><th>D</th><th>Correct</th>
        </tr>

        <%
            int i=1;
            Quiz quizObj = (Quiz) request.getAttribute("quiz");
            List<Question> questions = (quizObj != null) ? quizObj.getQuestions() : new ArrayList<>();
            for (Question q : questions) {
        %>
        <tr>
            <td><%=i++%></td>
            <td><%=q.getQuestionText()%></td>
            <td><%=q.getOption1()%></td>
            <td><%=q.getOption2()%></td>
            <td><%=q.getOption3()%></td>
            <td><%=q.getOption4()%></td>
            <td><%=q.getCorrectOption()%></td>
        </tr>
        <% } %>
    </table>

</div>

</body>
</html>
