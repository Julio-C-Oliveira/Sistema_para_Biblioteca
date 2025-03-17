package org.example;

import org.example.display.UserDisplay;

public class Main {
    public final static String basePath = "/home/juliocoliveira/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";
//    public final static String basePath = "/home/savmiranda/IdeaProjects/Biblioteca/src/";

    public static void main(String[] args) {
        UserDisplay display = new UserDisplay();

        display.runInterface();
    }
}
