<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz" %>
<%@ page import="models.Result" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
</head>
<body>
<h1></h1>

<article class="center">
    <h2>Quizzes</h2>
    <ul>
        <%
            Quiz[] quizzes = (Quiz[]) session.getAttribute("quizzes");
            for(Quiz q : quizzes){
        %>
        <li><%= q.getSubject() %></li>
        <%
            }
        %>
    </ul>
</article>

<article class="center">
    <h2>Scores</h2>

    <table>
        <tr>
            <th>Quiz Subject</th>
            <th>Score</th>
        </tr>
        <%
            // pre defined variables are request, response, out, session, application
            List<Result> results = (List<Result>) session.getAttribute("results");
            for(Result r : results){
        %>
        <tr>
            <td><%= r.getQuiz().getSubject() %></td>
            <td><%= r.getScore() %></td>
        </tr>
        <%
            }
        %>
    </table>

</article>

</body>
</html>