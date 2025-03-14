package org.example;

import org.example.display.UserDisplay;
import org.example.login.Login;
import org.example.notifications.Notifications;
import org.example.notifications.NotificationsDataManager;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Map;

public class Main {
    public final static String basePath = "/home/juliocoliveira/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";

    public static void main(String[] args) throws IOException {
        // CORRIGIR BUD DO ID


        CreateUserTable.createTable();
        // Criando alguns participantes
        InsertUser.create("Savio", UserTypes.LIBRARIAN, "bacuri");
        InsertUser.create("Julio", UserTypes.MANAGER, "goiaba");
        InsertUser.create("Luiz", UserTypes.READER, "maracuja");
        InsertUser.create("Reginaldo", UserTypes.READER, "maca");
        InsertUser.create("Yuri", UserTypes.READER, "pera");

        // Testando atualização de participante
        UpdateUser.updateUser("Luiz", UserTypes.LIBRARIAN, "buriti");

        // Lendo participantes

        UserDTO yuri = GetUser.getUser("Luiz");
//        System.out.println(yuri.toString());
//        UserDTO alguem = GetUser.getUser(3);
//        System.out.println(alguem.toString());
//
//        // Testando deleção de participante
//        DeleteUser.delete(4);
//
        // Testando o "login"
        Login login = new Login();
        Map<String, String> user = Map.of(
                "name", "Luiz",
                "password", "buriti"
        );
        if(login.validateLogin(user)){
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        UserDisplay display = new UserDisplay();

        //display.runInterface();
        Notifications.keepNotifications();
        System.out.println(Notifications.getNotificationsByUsername("VIKTOR", 1));
    }
}