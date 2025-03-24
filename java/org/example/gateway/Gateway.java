package org.example.gateway;

import org.example.database.Stock.DeleteBook;
import org.example.database.Stock.GetBook;
import org.example.database.Stock.InsertBook;
import org.example.database.Stock.UpdateBook;
import org.example.database.User.GenerateInitialUserConfig;
import org.example.login.Login;
import org.example.notifications.Notifications;
import org.example.delays.Delays;

import java.io.IOException;
import java.util.*;

public class Gateway {
    public static void startDatabases() throws IOException {
        Notifications.createNotificationData();

        GenerateInitialUserConfig initialUsers = new GenerateInitialUserConfig();
        initialUsers.generateInitialConfig();
    }

    public static void keepDatabasesToMemory() {
        Notifications.loadToMemory(); // Puxa as notificações para memória pra agilizar a utilização.
        Delays.loadToMemory(); // Puxa as notificações para memória pra agilizar a utilização.
    }

    private static boolean validatePermission(String user, String target, Integer userRole, Integer targetRole) {
        if (user.equals(target)) return true;
        else {
            if (userRole == 3) {
                return targetRole == 1 || targetRole == 2;
            } else if (userRole == 2) {
                return targetRole == 1;
            } else return false;
        }
    }
    public static List<Map<String, String>> getNotificationsByUsername(String user, String target, Integer userRole, Integer targetRole) {
        List<Map<String, String>> userRoleNotifications = new ArrayList<>();

        if(Gateway.validatePermission(user, target, userRole, targetRole)) userRoleNotifications = Notifications.getNotificationsByUsername(target, targetRole);
        else {
            Map<String, String> error = new HashMap<>();
            error.put("ERROR", "User does not have permission to execute this command");
            userRoleNotifications.add(error);
        }

        return userRoleNotifications;
    }

    public static Map<String, String> removeNotificationsByUsernameAndTitle(String user, String target, Integer userRole, Integer targetRole, String title) {
        Map<String, String> response = new HashMap<>();

        if(Gateway.validatePermission(user, target, userRole, targetRole)) {
            Notifications.removeNotificationsByUsernameAndTitle(target, targetRole.toString(), title);
            response.put("SUCCESS", String.format("%s", title));
        }
        else response.put("ERROR", "User does not have permission to execute this command");

        return response;
    }

    public static Map<String, Object> requestLogin(Map<String, String> loginRequestMessage) {
        Map<String, Object> serviceResponse = new HashMap<>();

        boolean isValid = Login.validateLogin(loginRequestMessage);

        if (isValid) {
            serviceResponse.put("isValid", isValid);
            serviceResponse.put("roles", Login.getUserRoles(loginRequestMessage));
        } else {
            serviceResponse.put("isValid", false);
            serviceResponse.put("roles", new ArrayList<>());
        }
        return serviceResponse;
    }

    public static List<Map<String, String>> getDelaysByUsername(String username, String target, Integer userRole, Integer targetRole) {
        List<Map<String, String>> userRoleDelays = new ArrayList<>();

        if (username.equals(target)) {
            userRoleDelays = Delays.getDelaysByUsername(username);
        } else {
            if (userRole == 2 && targetRole == 1) {
                userRoleDelays = Delays.getDelaysByUsername(target);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", "User does not have permission to execute this command");
                userRoleDelays.add(error);
            }
        }

        return userRoleDelays;
    }
    public static Map<String, String> removeDelaysByUsernameAndTitle(String username, String target, Integer userRole, Integer targetrole, String title) {
        Map<String, String> response = new HashMap<>();

        if (username.equals(target)) {
            Delays.removeDelaysByUsernameAndTitle(target, title);
            response.put("SUCCESS", String.format("%s", title));

        } else {
            response.put("", "");
            if ((userRole == 3 || userRole == 2) && targetrole == 1) {
                Delays.removeDelaysByUsernameAndTitle(target, title);
                response.put("SUCCESS", String.format("%s", title));
            } else {
                response.put("ERROR", "User does not have permission to execute this command");
            }
        }
        return response;
    }
    public static Map<String, String> addCollection(int userRole, String title, String author, String published_at, int copies){
        Map<String, String> response = new HashMap<>();
        if (userRole != 3){
            response.put("ERROR", "User does not have permission to execute this command");
        }
        InsertBook.create(title, author, published_at, copies);
        response.put("SUCCESS", title + "have been added");
        return response;
    }
    public static Map<String, String> removeCollectionByTitle(int userRole, String title){
        Map<String, String> response = new HashMap<>();
        if (userRole != 3){
            response.put("ERROR", "User does not have permission to execute this command");
        }
        Map<String, String> book = GetBook.getBookByTitle(title);
        int id = Integer.parseInt(book.get("id"));
        DeleteBook.delete(id);
        response.put("SUCCESS", title + "have been deleted");
        return response;
    }
    public static Map<String, String> removeCollectionByAuthor(int userRole, String author){
        Map<String, String> response = new HashMap<>();
        if (userRole != 3){
            response.put("ERROR", "User does not have permission to execute this command");
        }
        Map<String, String> book = GetBook.getBookByAuthor(author);
        int id = Integer.parseInt(book.get("id"));
        DeleteBook.delete(id);
        response.put("SUCCESS", author + " works have been deleted");
        return response;
    }
    public static Map<String, String> editCollection(int userRole, Scanner scan){
        Map<String, String> response = new HashMap<>();
        if (userRole != 3){
            response.put("ERROR", "User does not have permission to execute this command");
        }

        int input = scan.nextInt();
        String toEditString =
                """
                        Selecione o que pretende editar abaixo:
                        [1] - Título.
                        [2] - Autor.
                        [3] - Ano de publicação.
                        Insira a sua escolha:\s""";
        System.out.print(toEditString);
        String title;
        switch (input){
            case 1:
                System.out.print("Título antigo: ");
                title = scan.nextLine();
                System.out.print("Título novo: ");
                String newTitle = scan.nextLine();
                UpdateBook.updateBookTitleByTitle(title, newTitle);
                break;
            case 2:
                System.out.print("Título: ");
                title = scan.nextLine();
                System.out.print("Título novo: ");
                String author = scan.nextLine();
                UpdateBook.updateBookAuthorByTitle(title, author);
                break;
            case 3:
                System.out.print("Título: ");
                title = scan.nextLine();
                System.out.print("Data de publicação: ");
                String published_at = scan.nextLine();
                UpdateBook.updateBookPublishedAtByTitle(title, published_at);
                break;
            default:
                break;
        }
        return response;
    }
}