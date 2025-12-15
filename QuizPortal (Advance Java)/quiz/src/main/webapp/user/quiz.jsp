<%@ page import="java.util.*, model.Question, model.Quiz" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attempt Quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <h2>${quiz.title}</h2>

    <form action="${pageContext.request.contextPath}/quiz/submit" method="post">
        <input type="hidden" name="quizId" value="${quiz.id}">

        <%
            int i=1;
            List<Question> qList = (List<Question>) request.getAttribute("questions");
            for (Question q : qList) {
        %>

        <h3><%=i++%>. <%=q.getQuestionText()%></h3>

        <label><input type="radio" name="q_<%=q.getId()%>" value="A"> <%=q.getOption1()%></label><br>
        <label><input type="radio" name="q_<%=q.getId()%>" value="B"> <%=q.getOption2()%></label><br>
        <label><input type="radio" name="q_<%=q.getId()%>" value="C"> <%=q.getOption3()%></label><br>
        <label><input type="radio" name="q_<%=q.getId()%>" value="D"> <%=q.getOption4()%></label><br><br>

        <% } %>

        <button type="submit">Submit Quiz</button>
    </form>
</div>

</body>
</html>
