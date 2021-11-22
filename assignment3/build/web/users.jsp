<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controllers.*" %>
<%@ page import="models.User" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        
        <h1>Users</h1>
        <table><tr><th>Username</th><th>Password</th></tr>
        <%
            // pre defined variables are request, response, out, session, application
            User[] users = (User[])application.getAttribute("users");
            for(User u : users){
        %>
    <tr>
        <td><%= u.getUsername() %></td>
        <td><%= u.getPassword() %></td>
    </tr>

        <%
            }
        %>
        </table>

    </body>
</html>