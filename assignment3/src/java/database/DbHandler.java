/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DbHandler {
    Connection connection;

    public DbHandler () {
        connection = new LoadDriver().connect();
    }

    public User validate(User u){
        for(User tmp : getUsers()){
            if(Objects.equals(tmp.getUsername(), u.getUsername()) && Objects.equals(tmp.getPassword(), u.getPassword()))
                return tmp;
        }
        return null;
    }

    public ArrayList<User> getUsers () {
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from users");

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<Result> getResults(int userId) {
        Statement stmt = null;
        ArrayList<Result> results = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from results");

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Result result = new Result();
                result.setQuiz(findQuizById(rs.getInt("quiz_id")));
                result.setScore(rs.getInt("score"));
                results.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }



    public Quiz findQuizById (int quizId) {

        Statement stmt = null;
        Quiz q = new Quiz();
        // get quiz
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes WHERE id = "+quizId);
            rs.next();
            q.setId(quizId);
            q.setSubject(rs.getString("subject"));
            q.setId(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get questions
        ArrayList<Question> questions = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT questions.id, questions.text, questions.options, questions.answer  FROM questions INNER JOIN selector ON selector.question_id = questions.id WHERE selector.quiz_id = "+quizId);
            while (rs.next()){
                Question question = new Question();
                question.setText(rs.getString("text"));
                String answer = rs.getString("answer");
                String[] answers = answer.split("/");
                question.setAnswer(answers);
                String options = rs.getString("options");
                question.setOptions(options.split("/"));
                question.setId(rs.getInt("id"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        q.setQuestions(questions);

        return q;
    }

    public ArrayList<Quiz> getQuizzes () {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from quizzes");

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Quiz q = new Quiz();
                q.setSubject(rs.getString("subject"));
                q.setId(rs.getInt("id"));
                quizzes.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public void save (Result r) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO results (user_id, quiz_id, score) VALUES ('" + r.getUser().getId() + "','" + r.getQuiz().getId() + "','" + r.getScore() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
