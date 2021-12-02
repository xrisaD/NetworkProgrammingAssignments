package controllers;

import database.DbHandler;
import models.Quiz;
import models.Result;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;


public class LoginController extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "/login.html";
        HttpSession session = request.getSession(true);
        if (!session.isNew()){
            Object isValidatedObject = session.getAttribute("isValidated");
            if (isValidatedObject != null) {
                if ((boolean) isValidatedObject){
                    destination = "/index.jsp";
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String destination = "/login.html";
        if ("login".equals(request.getParameter("action"))) {
            // get form's data
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // get dbHandler
            ServletContext application = request.getServletContext();
            DbHandler dbh = (DbHandler) application.getAttribute("dbh");
            if (dbh == null) {
                dbh = new DbHandler();
            }

            User u;
            Object isValidatedObject = session.getAttribute("isValidated");
            Boolean isValidated = true;
            if (session.isNew() || isValidatedObject == null || !(boolean) isValidatedObject) {;
                u = new User();
                u.setUsername(username);
                u.setPassword(password);
                // validate user
                u =  dbh.validate(u);
                if (u == null) {
                    isValidated = false;
                } else {
                    session.setAttribute("user", u);
                    isValidated = true;
                }
                session.setAttribute("isValidated", isValidated);
            }

            if (isValidated) {
                u = (User) session.getAttribute("user");
                destination = "/index.jsp";
                // get all quizzes
                ArrayList<Quiz> quizzes = dbh.getQuizzes();
                application.setAttribute("quizzes", quizzes);
                // get user's results
                ArrayList<Result> results = dbh.getResults(u.getId());
                session.setAttribute("results", results);

                session.setAttribute("username", username);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request, response);
    }

}
