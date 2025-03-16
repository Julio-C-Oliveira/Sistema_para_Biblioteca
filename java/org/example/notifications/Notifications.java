package org.example.notifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notifications {
    private static  Map<String, Map<String, List<Map<String, String>>>> notificationsCache = null;

    public static Map<String, Map<String, List<Map<String, String>>>> getNotificationsCache() {
        return notificationsCache;
    }
    public static void setNotificationsCache(Map<String, Map<String, List<Map<String, String>>>> notificationsCache) {
        Notifications.notificationsCache = notificationsCache;
    }

    public static void loadToMemory() {
        Notifications.setNotificationsCache(NotificationsDataManager.readNotificationData());
    }

    public static List<Map<String, String>> getNotificationsByUsername(String username, Integer role) {
        Map<String, Map<String, List<Map<String, String>>>> users = getNotificationsCache();
        Map<String, List<Map<String, String>>> userNotifications;
        List<Map<String, String>> userRoleNotifications = null;

        List<Map<String, String>> errorList = new ArrayList<>();
        Map<String, String> error = new HashMap<>();


        if (users.containsKey(username)) {
            userNotifications = Notifications.getNotificationsCache().get(username);
            if (userNotifications.containsKey(role.toString())) {
                userRoleNotifications = userNotifications.get(role.toString());
            } else {
                System.out.println(userNotifications);
                System.out.println(role);
                System.out.println(userNotifications.containsKey(role.toString()));
                error.put("ERROR", "The role does not allow");
                errorList.add(error);
                userRoleNotifications = errorList;
            }
        } else {
            error.put("ERROR", "User does not exist");
            errorList.add(error);
            userRoleNotifications = errorList;
        }
        return userRoleNotifications;
    }

    public static String removeNotificationsByUsernameAndTitle(String user, String role, String title) {
        return NotificationsDataManager.removeNotification(user, role, title);
    }
}