<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Result" %>
<!DOCTYPE html>

<head>
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
</head>
<body>
<h1>Hi, <%= session.getAttribute("username") %>!</h1>
<img src="images/image1.png" alt="a notebook and a pen">

<article class="center">
    <h2>Quizzes</h2>
    <form method="get" action="${pageContext.request.contextPath}/QuizController">
        <%
            ArrayList<Quiz> quizzes = (ArrayList<Quiz>) application.getAttribute("quizzes");
            for(Quiz q : quizzes){
        %>
        <div class="centerRadios">
            <input type="radio" name="quizId" id="<%= q.getId() %>" value="<%= q.getId() %>"><%= q.getSubject() %>
        </div>
        <%
            }
        %>
        <input type="hidden" name="action" value="chooseQuiz">
        <input type="submit" value="Choose Quiz">
    </form>
</article>

<article class="center">
    <h2>Results</h2>
    <table class="center_table">
        <tr>
            <th>Quiz subject</th>
            <th>Score</th>
        </tr>
        <%
            ArrayList<Result> results = (ArrayList<Result>) session.getAttribute("results");
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