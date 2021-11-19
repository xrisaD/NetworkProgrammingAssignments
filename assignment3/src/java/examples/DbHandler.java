/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import models.Quiz;
import models.Result;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    
    public User[] getUsers(){
        User[] users = new User[2];
        Quiz[] guizzes = this.getQuizzes();
        // user 0
        users[0] = new User();
        users[0].setUsername("ada@kthse");
        users[0].setPassword("12345");
        List<Result> results = new ArrayList<>();
        Result result1 = new Result();
        result1.setUser(users[0]);
        result1.setScore(100);
        result1.setQuiz(guizzes[0]);

        Result result2 = new Result();
        result2.setUser(users[0]);
        result2.setScore(80);
        result2.setQuiz(guizzes[1]);

        results.add(result1);

        users[0].setResults(results);

        // user 1
        users[1] = new User();
        users[1].setUsername("beda@kth.se");
        users[1].setPassword("qwerty");
        return users;
    }

    public Quiz[] getQuizzes() {
        Quiz[] quizzes = new Quiz[2];
        quizzes[0] = new Quiz();
        quizzes[0].setSubject("Quiz 1");

        quizzes[1] = new Quiz();
        quizzes[1].setSubject("Quiz 2");
        return quizzes;
    }

    public boolean validate(User u){
        for(User tmp : getUsers()){
            if(tmp.getUsername() == u.getUsername() && tmp.getPassword() == u.getPassword())
                return true;
        }
        return false;
    }
}
