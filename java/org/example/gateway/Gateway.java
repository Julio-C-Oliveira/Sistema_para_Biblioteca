package org.example.gateway;

import org.example.database.Stock.DeleteBook;
import org.example.database.Stock.GetBook;
import org.example.database.Stock.InsertBook;
import org.example.database.Stock.UpdateBook;
import org.example.database.User.GenerateInitialUserConfig;
import org.example.login.Login;
import org.example.notices.Notifications;
import org.example.notices.Delays;

import java.io.IOException;
import java.util.*;

import static org.example.utilities.Utils.validateDate;

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
    public static Map<String, String> editCollectionManager(int userRole, Scanner scan){
        Map<String, String> response = new HashMap<>();
        if (userRole != 3){
            response.put("ERROR", "User does not have permission to execute this command");
        }

        ArrayList<Map<String, String>> books = GetBook.getAllBooks();
        for (Map<String, String> book:  books){
            String showData = "ID: " + book.get("id") +
                    " | Título: " + book.get("title") +
                    " | Autor: " + book.get("author") +
                    " | Data de publicação: " + book.get("published_at") +
                    " | Cópias: " + book.get("copies");
            System.out.println(showData);
        }
        System.out.println("Digite o ID da obra a ser alterada: ");
        int id = scan.nextInt();
        scan.nextLine(); // Limpar o buffer
        Map<String, String> book = GetBook.getBookByID(id);

        String previousTitle = book.get("title");
        String previousAuthor = book.get("author");
        int previousCopies = Integer.parseInt(book.get("copies"));
        String previousPublishedAt = book.get("published_at");

        String textEditChoice = """
        O que se quer editar?:
        [1] - Título errôneo.
        [2] - Autor errôneo.
        [3] - Data de publicação.
        [4] - Adicionar número de cópias.
        [5] - Subtrair número de cópias.
        [6] - Cancelar.
        Insira a sua escolha:\s""";

        System.out.print(textEditChoice);
        int input = scan.nextInt();
        scan.nextLine(); // Limpar o buffer

        switch (input) {
            case 1:
                System.out.print("Título novo: ");
                String newTitle = scan.nextLine();
                UpdateBook.updateBookTitleByID(id, newTitle);
                response.put("SUCCESS", previousTitle + " has been changed to " + newTitle);
                break;

            case 2:
                System.out.print("Autor novo: ");
                String author = scan.nextLine();
                UpdateBook.updateBookAuthorByID(id, author);
                response.put("SUCCESS", previousAuthor + " has been changed to " + author);
                break;

            case 3:
                System.out.print("Nova data de publicação: ");
                String publishedAt = scan.nextLine();
                if(!validateDate(publishedAt)){
                    response.put("FAILED", " put correct date");
                    break;
                }
                UpdateBook.updateBookPublishedAtByID(id, publishedAt);
                response.put("SUCCESS", previousPublishedAt + " has been changed to " + publishedAt);
                break;

            case 4:
                UpdateBook.updateBookCopiesByID(id, previousCopies + 1);
                response.put("SUCCESS", previousTitle + " copies(" + previousCopies + ") has been changed to " + (previousCopies + 1));
                break;
            case 5:
                UpdateBook.updateBookCopiesByID(id, previousCopies - 1);
                response.put("SUCCESS", previousTitle + " copies(" + previousCopies + ") has been changed to " + (previousCopies - 1));
                break;
            default:
                response.put("FAILED", "canceled by default");
                break;
        }
        return response;
    }

    public static Map<String, String> editCollectionLibrary(int userRole, Scanner scan){
        Map<String, String> response = new HashMap<>();
        if (userRole != 2){
            response.put("ERROR", "User does not have permission to execute this command");
        }

        ArrayList<Map<String, String>> books = GetBook.getAllBooks();
        for (Map<String, String> book:  books){
            String showData = "ID: " + book.get("id") +
                    " | Título: " + book.get("title") +
                    " | Autor: " + book.get("author") +
                    " | Data de publicação: " + book.get("published_at") +
                    " | Cópias: " + book.get("copies");
            System.out.println(showData);
        }
        System.out.println("Digite o ID da obra a ser alterada: ");
        int id = scan.nextInt();
        scan.nextLine(); // Limpar o buffer
        Map<String, String> book = GetBook.getBookByID(id);

        String previousTitle = book.get("title");
        String previousAuthor = book.get("author");
        int previousCopies = Integer.parseInt(book.get("copies"));
        String previousPublishedAt = book.get("published_at");

        String textEditChoice = """
            O que se quer editar?:
            [1] - Adicionar número de cópias.
            [2] - Subtrair número de cópias.
            [3] - Cancelar.
            Insira a sua escolha:\s""";

        System.out.print(textEditChoice);
        int input = scan.nextInt();
        scan.nextLine(); // Limpar o buffer

        switch (input) {
            case 1:
                UpdateBook.updateBookCopiesByID(id, previousCopies + 1);
                response.put("SUCCESS", previousTitle + " copies(" + previousCopies + ") has been changed to " + (previousCopies + 1));
                break;
            case 2:
                UpdateBook.updateBookCopiesByID(id, previousCopies - 1);
                response.put("SUCCESS", previousTitle + " copies(" + previousCopies + ") has been changed to " + (previousCopies - 1));
                break;
            default:
                response.put("FAILED", "canceled by default");
                break;
        }
        return response;
    }
}