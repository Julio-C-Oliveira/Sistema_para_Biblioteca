package org.example.login;

import org.example.database.User.GetUser;
import org.example.utilities.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Login {
    public static Boolean validateLogin(Map<String, String> user){
        return GetUser.getUser(user.get("name"), user.get("password"));
    }
    public static List<Integer> getUserRoles(Map<String, String> user) {
        return Utils.arrayInStringToArray(GetUser.getUser(user.get("name")).get("role"));
    }
}
