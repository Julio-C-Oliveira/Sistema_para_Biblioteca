package org.example.delays;

import org.example.notifications.Notifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Delays {
    private static  Map<String, List<Map<String, String>>> delaysCache = null;

    public static Map<String, List<Map<String, String>>> getDelaysCache() {
        return delaysCache;
    }
    public static void setDelaysCache(Map<String, List<Map<String, String>>> delaysCache) {
        Delays.delaysCache = delaysCache;
    }

    public static void loadToMemory() {
        Delays.setDelaysCache(DelaysDataManager.readDelayData());
    }

    public static List<Map<String, String>> getDelaysByUsername(String username) {
        Map<String, List<Map<String, String>>> users = getDelaysCache();
        List<Map<String, String>> userDelays;

        if (users.containsKey(username)) {
            userDelays = users.get(username);
            return userDelays;

        } else {
            return null;
        }
    }

    public static void removeDelaysByUsernameAndTitle(String username, String title) {
        DelaysDataManager.removeDelay(username, title);
    }
}
