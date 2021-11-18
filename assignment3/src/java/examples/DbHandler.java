/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

public class DbHandler {
    
    public User[] getUsers(){
        User[] users = new User[2];
        users[0] = new User();
        users[0].setUsername("ada@kthse");
        users[0].setPassword("12345");
        users[1] = new User();
        users[1].setUsername("beda@kth.se");
        users[1].setPassword("qwerty");
        return users;
    }
    
    public boolean validate(User u){
        for(User tmp : getUsers()){
            if(tmp.getUsername() == u.getUsername() && tmp.getPassword() == u.getPassword())
                return true;
        }
        return false;
    }
}
