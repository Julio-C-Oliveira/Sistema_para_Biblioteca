package org.example.gateway;

import org.example.notifications.Notifications;

import java.util.*;

public class Gateway {
    public static void keepDatabasesToMemory() {
        Notifications.loadToMemory(); // Puxa as notificações para memória pra agilizar a utilização.
    }

    public static List<Map<String, String>> getNotificationsByUsername(String username, String target, Integer userRole, Integer targetRole) {
        List<Map<String, String>> userRoleNotifications = new ArrayList<>();

        if (Objects.equals(username, target)) {
            userRoleNotifications = Notifications.getNotificationsByUsername(username, targetRole);
        } else {
            if (userRole == 2 && targetRole == 1) {
                userRoleNotifications = Notifications.getNotificationsByUsername(target, targetRole);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", "User does not have permission to execute this command");
                userRoleNotifications.add(error);
            }
        }

        return userRoleNotifications;
    }

    public static Map<String, String> removeNotificationsByUsernameAndTitle(String user, String target, Integer userRole, Integer targetRole, String title) {
        Map<String, String> response = new HashMap<>();

        if (user.equals(target)) {
            Notifications.removeNotificationsByUsernameAndTitle(target, targetRole.toString(), title);
            response.put("SUCCESS", String.format("%s", title));
        } else {
            response.put("", "");
            if (userRole == 3 || userRole == 2) {
                Notifications.removeNotificationsByUsernameAndTitle(target, targetRole.toString(), title);
                response.put("SUCCESS", String.format("%s", title));
            } else {
                response.put("ERROR", "User does not have permission to execute this command");
            }
        }
        return response;
    }
}
