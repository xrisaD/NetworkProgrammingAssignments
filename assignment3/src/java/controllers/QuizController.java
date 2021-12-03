package controllers;

import database.DbHandler;
import models.Question;
import models.Quiz;
import models.Result;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class QuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        ServletContext application = request.getServletContext();
        DbHandler dbh = (DbHandler) application.getAttribute("dbh");

        if (dbh == null) {
            dbh = new DbHandler();
        }
        HttpSession session = request.getSession(true);
        // User u = (User) session.getAttribute("user");

        Quiz q = dbh.findQuizById(quizId);
        session.setAttribute("quiz", q);
        RequestDispatcher rd = request.getRequestDispatcher("/quiz.jsp");
        rd.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // compute the result
        HttpSession session = request.getSession(true);
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        ArrayList<Question> questions = quiz.getQuestions();
        int score = 0;
        for (Question question: questions) {
            String[] options = question.getOptions();
            String[] answers = question.getAnswer();
            for (int i = 0; i<options.length ; i++) {
                String isChecked = request.getParameter(Integer.toString(question.getId()) + i);
                if (isChecked != null) {
                    if (answers[i].equals("1")) {
                        score += 1;
                    } else {
                        score -= 1;
                    }
                } else {
                    if (answers[i].equals("0")) {
                        score += 1;
                    } else {
                        score -= 1;
                    }
                }
            }
        }

        // save result
        ServletContext application = request.getServletContext();
        DbHandler dbh = (DbHandler) application.getAttribute("dbh");

        if (dbh == null) {
            dbh = new DbHandler();
        }
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        result.setQuiz(quiz);
        result.setScore(score);
        result.setUser(user);

        dbh.save(result);

        // redirect to result.jsp and show the result
        session.setAttribute("subject", quiz.getSubject());
        session.setAttribute("score", score);
        RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");
        rd.forward(request, response);
    }
}
