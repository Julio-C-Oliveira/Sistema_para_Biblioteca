package org.example.login;

import org.example.database.User.GetUser;
import org.example.user.UserDTO;

import java.util.Map;


public class Login {
    public Boolean validateLogin(Map<String, String> user){
        UserDTO userDTO = GetUser.getUser(user.get("name"), user.get("password"));
        return userDTO != null;
    }
}
