package com.omegron.config;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserSession {
    private long id;
    private String username;

    public long id(){
        return id;
    }
    public String username(){
        return username;
    }

    //Login logic
    public void login(long id, String username) {
        this.id = id;
        this.username = username;
    }
    //Is the user logged in?
    public boolean isLoggedIn(){
        return id > 0;
    }

    //Logout logic
    public void logout(){
        id = 0;
        username = null;
    }
}
