package org.example;

import org.example.database.User.*;
import org.example.display.UserDisplay;
import org.example.login.Login;
import org.example.notifications.Notifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public final static String basePath = "~/IdeaProjects/Sistema_para_Biblioteca_Gradle/src/";

    public static void main(String[] args) throws IOException {
        // CORRIGIR BUD DO ID

        CreateUserTable.createTable();
        // Criando alguns participantes
        InsertUser.create("Savio", 2, "bacuri");
        InsertUser.create("Julio", 1, "goiaba");
        InsertUser.create("Luiz", 3, "cupuacu");
        InsertUser.create("Reginaldo", 3, "maca");
        InsertUser.create("Yuri", 3, "pera");

        // Testando atualização de participante
        System.out.println("Update:");
        UpdateUser.updateUser("Yuri", "buriti");

        // Lendo participantes

         Map<String, String> yuri = GetUser.getUser("Yuri");
         System.out.println(yuri.get("name"));

         Map<String, String> alguem = GetUser.getUser(3);
         System.out.println(alguem.get("role"));

        // Testando deleção de participante
         //DeleteUser.delete(4);

        // Testando o "login"
        Login login = new Login();
        Map<String, String> user = Map.of(
                "name", "Luiz",
                "role", "1",
                "password", "buriti"
        );
        if(login.validateLogin(user)){
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        ArrayList<Map<String, String>> users = GetAllUsers.getAll();
        for (Map<String, String> i : users) {
            System.out.println(i.get("name"));
        }

//        UserDisplay display = new UserDisplay();

//        display.runInterface();
    }
}
