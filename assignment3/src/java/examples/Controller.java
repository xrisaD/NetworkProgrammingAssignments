package examples;

import models.Quiz;
import models.Result;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;


public class Controller extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                // Creating the session object has been moved to getpost (compared to the lecture)
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                User u;
                if (session.isNew()){
                    u = new User();
                    u.setUsername(username);
                    u.setPassword(password);
                    session.setAttribute("user", u);
                }else{
                    // retrieve the already existing user
                    u = (User)session.getAttribute("user");
                }

                ServletContext application = request.getServletContext();
                DbHandler dbh = (DbHandler)application.getAttribute("dbh");

                if (dbh==null) {
                    dbh = new DbHandler();
                }

                Quiz[] quizzes = dbh.getQuizzes();
                session.setAttribute("quizzes", quizzes);
                if("login".equals(request.getParameter("action"))){
                    List<Result> results = u.getResults();
                    System.out.println(u.getResults());
                    session.setAttribute("results", results);

                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
                
    }

 

}
