<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Question" %>
<%@ page import="models.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <title>Quiz</title>
</head>
    <body>
    <% Quiz quiz = (Quiz) session.getAttribute("quiz"); %>
    <h1><%= quiz.getSubject() %></h1>
    <img src="images/image1.png" alt="a notebook and a pen">

    <div class="center">
        <form action="/network-programming/QuizController" method="post">
            <%
                ArrayList<Question> questions = quiz.getQuestions();
                for (Question q: questions) {
            %>
            <div class="question_container" >
                <p><%= q.getText()%></p>
                <%
                    String[] options = q.getOptions();
                    for (int i = 0; i<options.length ; i++){
                %>
                    <input type="checkbox" value="<%=i%>" name="<%=Integer.toString(q.getId())+i%>" />
                    <label><%=options[i]%></label>
                <%
                    }
                %>
            </div>
            <%
                }
            %>
            <input class="submit" type="submit" value="Submit">
        </form>
    </div>
</body>
</html>