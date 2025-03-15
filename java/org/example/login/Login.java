package org.example.login;

import org.example.database.User.GetUser;

import java.util.Map;


public class Login {
    public Boolean validateLogin(Map<String, String> user){
        return GetUser.getUser(user.get("name"), user.get("password"));
    }
}
