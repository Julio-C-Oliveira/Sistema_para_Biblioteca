package org.example.notifications;

import java.util.HashMap;
import java.util.Map;

public class Notifications {
    private static  Map<String, Map<String, Map<String, String>>> notificationsCache = null;

    public static Map<String, Map<String, Map<String, String>>> getNotificationsCache() {
        return notificationsCache;
    }
    public static void setNotificationsCache(Map<String, Map<String, Map<String, String>>> notificationsCache) {
        Notifications.notificationsCache = notificationsCache;
    }

    public static void keepNotifications() {
        Notifications.setNotificationsCache(NotificationsDataManager.readNotificationData());
    }

    public static Map<String, String> getNotificationsByUsername(String username, Integer role) {
        Map<String, Map<String, Map<String, String>>> users = getNotificationsCache();
        Map<String, Map<String, String>> userNotifications;
        Map<String, String> userRoleNotifications = null;

        Map<String, String> error = new HashMap<>();


        if (users.containsKey(username)) {
            userNotifications = Notifications.getNotificationsCache().get(username);
            if (userNotifications.containsKey(role.toString())) {
                userRoleNotifications = userNotifications.get(role.toString());
            } else {
                error.put("ERROR", "The role does not allow");
                userRoleNotifications = error;
            }
        } else {
            error.put("ERROR", "User does not exist");
            userRoleNotifications = error;
        }
        return userRoleNotifications;
    }
}
