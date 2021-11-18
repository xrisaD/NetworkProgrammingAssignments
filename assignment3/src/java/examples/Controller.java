package examples;

import java.io.IOException;
import java.io.PrintWriter;
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
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);
                User u;
                if (session.isNew()){
                    u = new User();
                    u.setUsername(request.getParameter("username"));
                    u.setPassword(request.getParameter("password"));
                    session.setAttribute("user", u);
                }else{
                    // retrieve the already existring user
                    u = (User)session.getAttribute("user");
                }
                ServletContext application = request.getServletContext();
                DbHandler dbh = (DbHandler)application.getAttribute("dbh");
                if(dbh==null)
                    dbh = new DbHandler();
                User[] users = dbh.getUsers();
                application.setAttribute("users", users);
                if("login".equals(request.getParameter("action"))){
                    // check if user is authorized with the "dbh" object
                    RequestDispatcher rd = request.getRequestDispatcher("/users.jsp");
                    rd.forward(request, response);
                }
                
    }

 

}
