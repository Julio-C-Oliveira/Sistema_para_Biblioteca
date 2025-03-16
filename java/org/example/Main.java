package org.example;

import org.example.database.User.GenerateInitialUserConfig;
import org.example.database.User.GetUser;
import org.example.display.UserDisplay;

import java.io.IOException;
import java.util.Map;

public class Main {
    public final static String basePath = "~/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";

    public static void main(String[] args) throws IOException {
        GenerateInitialUserConfig initialUsers = new GenerateInitialUserConfig();

        initialUsers.generateInitialConfig();

        Map<String, String> teste = GetUser.getUser("GABRIEL");
        System.out.println(teste.get("role"));
//        UserDisplay display = new UserDisplay();

//        display.runInterface();
    }
}
