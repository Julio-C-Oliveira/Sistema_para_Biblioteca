package org.example.gateway;

import org.example.database.User.GenerateInitialUserConfig;
import org.example.login.Login;
import org.example.notifications.Notifications;
import org.example.delays.Delays;

import java.util.*;

public class Gateway {
    public static void startDatabases() {
        GenerateInitialUserConfig initialUsers = new GenerateInitialUserConfig();

        initialUsers.generateInitialConfig();
    }

    public static void keepDatabasesToMemory() {
        Notifications.loadToMemory(); // Puxa as notificações para memória pra agilizar a utilização.
        Delays.loadToMemory(); // Puxa as notificações para memória pra agilizar a utilização.
    }

    private static boolean validatePermission(String user, String target, Integer userRole, Integer targetRole) {
        if (user.equals(target)) return true;
        else {
            if (userRole == 3) {
                return targetRole == 1 || targetRole == 2;
            } else if (userRole == 2) {
                return targetRole == 1;
            } else return false;
        }
    }
    public static List<Map<String, String>> getNotificationsByUsername(String user, String target, Integer userRole, Integer targetRole) {
        List<Map<String, String>> userRoleNotifications = new ArrayList<>();

        if(Gateway.validatePermission(user, target, userRole, targetRole)) userRoleNotifications = Notifications.getNotificationsByUsername(target, targetRole);
        else {
            Map<String, String> error = new HashMap<>();
            error.put("ERROR", "User does not have permission to execute this command");
            userRoleNotifications.add(error);
        }

        return userRoleNotifications;
    }

    public static Map<String, String> removeNotificationsByUsernameAndTitle(String user, String target, Integer userRole, Integer targetRole, String title) {
        Map<String, String> response = new HashMap<>();

        if(Gateway.validatePermission(user, target, userRole, targetRole)) {
            Notifications.removeNotificationsByUsernameAndTitle(target, targetRole.toString(), title);
            response.put("SUCCESS", String.format("%s", title));
        }
        else response.put("ERROR", "User does not have permission to execute this command");

        return response;
    }

    public static Map<String, Object> requestLogin(Map<String, String> loginRequestMessage) {
        Map<String, Object> serviceResponse = new HashMap<>();

        boolean isValid = Login.validateLogin(loginRequestMessage);

        if (isValid) {
            serviceResponse.put("isValid", isValid);
            serviceResponse.put("roles", Login.getUserRoles(loginRequestMessage));
        } else {
            serviceResponse.put("isValid", false);
            serviceResponse.put("roles", new ArrayList<>());
        }
        return serviceResponse;
    }

    public static List<Map<String, String>> getDelaysByUsername(String username, String target, Integer userRole, Integer targetRole) {
        List<Map<String, String>> userRoleDelays = new ArrayList<>();

        if (username.equals(target)) {
            userRoleDelays = Delays.getDelaysByUsername(username);
        } else {
            if (userRole == 2 && targetRole == 1) {
                userRoleDelays = Delays.getDelaysByUsername(target);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", "User does not have permission to execute this command");
                userRoleDelays.add(error);
            }
        }

        return userRoleDelays;
    }
    public static Map<String, String> removeDelaysByUsernameAndTitle(String username, String target, Integer userRole, Integer targetrole, String title) {
        Map<String, String> response = new HashMap<>();

        if (username.equals(target)) {
            Delays.removeDelaysByUsernameAndTitle(target, title);
            response.put("SUCCESS", String.format("%s", title));

        } else {
            response.put("", "");
            if ((userRole == 3 || userRole == 2) && targetrole == 1) {
                Delays.removeDelaysByUsernameAndTitle(target, title);
                response.put("SUCCESS", String.format("%s", title));
            } else {
                response.put("ERROR", "User does not have permission to execute this command");
            }
        }
        return response;
    }
}
