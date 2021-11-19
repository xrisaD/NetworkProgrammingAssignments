package models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    
    private String username;
    private String password;
    private List<Result> results;

    public User() {
    }

    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}