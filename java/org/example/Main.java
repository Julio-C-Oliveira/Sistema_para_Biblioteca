package org.example;

import org.example.database.User.GenerateInitialUserConfig;
import org.example.display.UserDisplay;

import java.io.IOException;

public class Main {
    public final static String basePath = "/home/juliocoliveira/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";

    public static void main(String[] args) throws IOException {
        GenerateInitialUserConfig initialUsers = new GenerateInitialUserConfig();

        initialUsers.generateInitialConfig();

        UserDisplay display = new UserDisplay();

        display.runInterface();
    }
}
