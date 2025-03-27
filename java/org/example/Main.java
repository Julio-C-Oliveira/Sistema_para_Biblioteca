package org.example;

import org.example.database.Stock.GenerateInitialBooksConfig;
import org.example.database.User.GenerateInitialUserConfig;
import org.example.display.UserDisplay;

public class Main {
    public final static String basePath = "/home/juliocoliveira/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";
    //public final static String basePath = "/home/savio/IdeaProjects/Biblioteca/src/";

    public static void main(String[] args) {
        GenerateInitialUserConfig usersCfg = new GenerateInitialUserConfig();
        GenerateInitialBooksConfig booksCfg = new GenerateInitialBooksConfig();
        usersCfg.generateInitialConfig();
        booksCfg.generateInitialConfig();
        UserDisplay display = UserDisplay.getUserDisplay();

        display.runInterface();
    }
}
