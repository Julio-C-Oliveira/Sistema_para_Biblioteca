package org.example;

import org.example.display.UserDisplay;
import org.example.notifications.Notifications;
import org.example.notifications.NotificationsDataManager;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Map;

public class Main {
    public final static String basePath = "/home/juliocoliveira/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";

    public static void main(String[] args) throws IOException {
        UserDisplay display = new UserDisplay();

        //display.runInterface();
        Notifications.keepNotifications();
    }
}