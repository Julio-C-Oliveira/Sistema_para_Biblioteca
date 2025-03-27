package org.example.display;


import org.example.gateway.Gateway;
import org.example.notices.Delays;
import org.example.notices.Notifications;
import org.example.utilities.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserDisplay {
    private boolean state = true;

    public UserDisplay() {}

    public static void displayNotifications(String username, String target, UserTypes userRole, UserTypes targetRole, String printNotificationsModel) {
        List<Map<String, String>> userRoleNotifications = Gateway.getNotificationsByUsername(username, target, userRole.getId(), targetRole.getId());
        System.out.println();

        for (Map<String, String> userNotification : userRoleNotifications) {
            String title = userNotification.entrySet().iterator().next().getKey();
            System.out.printf(printNotificationsModel + "\n", title, userNotification.get(title));
        }
        System.out.println();
    }
    public static void removeNotification(String username, String target, UserTypes userRole, UserTypes targetRole, String messageTitle, String printRemoveNotificationsModel) {
        Map<String, String> response = Gateway.removeNotificationsByUsernameAndTitle(username, target, userRole.getId(), targetRole.getId(), messageTitle);
        String key = response.keySet().iterator().next();
        Notifications.loadToMemory(); // Atualizar os dados em cache;
        System.out.printf(printRemoveNotificationsModel + "\n", key, response.get(key));
    }

    public static void displayDelays(String username, String target, UserTypes userRole, UserTypes targetRole, String printDelaysModel) {
        List<Map<String, String>> targetDelays = Gateway.getDelaysByUsername(username, target, userRole.getId(), targetRole.getId());
        System.out.println();

        for (Map<String, String> delay : targetDelays) {
            String title = delay.entrySet().iterator().next().getKey();
            System.out.printf(printDelaysModel + "\n", title, delay.get(title));
        }
        System.out.println();
    }

    public static void removeDelay(String username, String target, UserTypes userRole, UserTypes targetRole, String messageTitle, String printRemoveDelaysModel) {
        Map<String, String> response = Gateway.removeDelaysByUsernameAndTitle(username, target, userRole.getId(), targetRole.getId(), messageTitle);
        String key = response.keySet().iterator().next();
        Delays.loadToMemory(); // Atualizar os dados em cache;
        System.out.printf(printRemoveDelaysModel + "\n", key, response.get(key));
    }

    public static void addCollection(int userRole, Scanner scan){
        System.out.print("Título: ");
        String title = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine();
        System.out.print("Data de publicação: ");
        String published_at = scan.nextLine();
        System.out.print("Número de cópias: ");
        int copies = scan.nextInt();

        Map<String, String> response = Gateway.addCollection(userRole, title, author, published_at, copies);
        String key = response.keySet().iterator().next();
        System.out.println(response.get(key));
    }

    public static void removeCollectionByTitle(int userRole, Scanner scan){
        System.out.print("Título: ");
        String title = scan.nextLine();
        Map<String, String> response = Gateway.removeCollectionByTitle(userRole, title);
        String key = response.keySet().iterator().next();
        System.out.println(response.get(key));
    }

    public static void removeCollectionByAuthor(int userRole, Scanner scan){
        System.out.print("Autor: ");
        String author = scan.nextLine();
        Map<String, String> response = Gateway.removeCollectionByAuthor(userRole, author);
        String key = response.keySet().iterator().next();
        System.out.println(response.get(key));
    }

    public static void editCollection(int userRole, Scanner scan){
        Map<String, String> response = Gateway.editCollection(userRole, scan);
        String key = response.keySet().iterator().next();
        System.out.println(response.get(key));
    }

    public void runInterface() {
        // Definir constantes:
        String textSelectUserType =
                """
                        Selecione um módulo abaixo:
                        [1] - Leitor.
                        [2] - Bibliotecário.
                        [3] - Gerente.
                        Insira a sua escolha:\s""";
        String textSelectTargetType =
                """
                        Selecione a função do alvo abaixo:
                        [1] - Leitor.
                        [2] - Bibliotecário.
                        [3] - Gerente.
                        Insira a sua escolha:\s""";
        String usernameInputText =
                "Insira o nome de usuário cadastrado: ";
        String targetInputText =
                "Insira o nome do usuário alvo cadastrado: ";
        String passwordInputText =
                "Insira a senha cadastrada: ";
        String loggedInText =
                "Login efetuado com sucesso, logado como %s, bem-vindo usuário %s.";
        String readerFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Excluir notificação.
                        [3] - Verificar disponibilidade de livros.
                        [4] - Visualizar resumos de livros.
                        [5] - Visualizar pendências.
                        [6] - Ver minha estante e prazos.
                        [7] - Logout.
                        Insira a sua escolha:\s""";
        String librarianFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Excluir notificação.
                        [3] - Emprestar livro.
                        [4] - Reservar livro.
                        [5] - Verificar pendências de usuários.
                        [6] - Remover pendências de usuários.
                        [7] - Logout.
                        Insira a sua escolha:\s""";
        String managerFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Excluir notificação.
                        [3] - Adicionar coleção.
                        [4] - Excluir coleção por título.
                        [5] - Excluir coleção por autor.
                        [6] - Editar coleção.
                        [7] - Logout.
                        Insira a sua escolha:\s""";
        String printNotificationsModel =
                """
                        Título: %s
                        Conteúdo: %s\s""";
        String printDelaysModel =
                """
                        Título: %s
                        Data limite: %s\s""";
        String insertMessageTitleText = "Insira o titulo da notificação: ";
        String printRemoveNotificationsModel =
                """
                        Resultado: %s
                        Conteúdo: %s\s""";
        String printRemoveDelayModel =
                """
                        Resultado: %s
                        Conteúdo: %s\s""";
        String printRemoveDelaysModel =
                """
                        Resultado: %s
                        Conteúdo: %s\s""";

        // Iniciar variáveis
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        int[] validTypes = UserTypes.getValidRange();
        int choiceFunction = 0;
        String username = "";
        String messageTitle = "";
        String delayTitle = "";
        Map<String, String> loginRequestMessage = new HashMap<>();
        Map<String, Object> serverLoginResponse = new HashMap<>();
        List<Integer> userRoles = null;
        Map<UserTypes, Integer> numberOfFunctions = new HashMap<>();

        // Núemero de funções de cada tipo de usuário:
        numberOfFunctions.put(UserTypes.READER, 7);
        numberOfFunctions.put(UserTypes.LIBRARIAN, 7);
        numberOfFunctions.put(UserTypes.MANAGER, 6);

        // Criar o banco dedados
        //Gateway.startDatabases();
        // Carregar os dados dos bancos
        Gateway.keepDatabasesToMemory();

        // Lógica
        do {
            choice = Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectUserType, validTypes[0], validTypes[1]);

            loginRequestMessage.put("name", Utils.inputUser(scanner, usernameInputText));
            loginRequestMessage.put("password", Utils.inputPassword(scanner, passwordInputText));

            serverLoginResponse = Gateway.requestLogin(loginRequestMessage);

            userRoles = (List<Integer>) serverLoginResponse.get("roles");

            if (userRoles.contains(choice) || (boolean) serverLoginResponse.get("isValid")) {
                break;
            }
            else System.out.println("Você não tem permissão para logar na função escolhida, ou errou os dados cadastrais. Tente novamente.");
        } while(true);

        username = loginRequestMessage.get("name");
        UserTypes role = UserTypes.fromId(choice);
        System.out.println();
        System.out.printf(loggedInText + "\n\n", role, username);

        while (this.state) {
            switch (role) {
                case READER:
                    choiceFunction = Utils.validateIfInputIsAnIntAndIsInARange(scanner, readerFunctionsText, 1, numberOfFunctions.get(role));
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(username, username, role, role, printNotificationsModel);
                            break;
                        case 2:
                            messageTitle = Utils.inputString(scanner, insertMessageTitleText);
                            UserDisplay.removeNotification(username, username, role, role, messageTitle, printRemoveNotificationsModel);
                            break;
                        case 5:
                            UserDisplay.displayDelays(username, username, role, role, printRemoveDelaysModel);
                            break;
                    }
                    break;
                case LIBRARIAN:
                    choiceFunction = Utils.validateIfInputIsAnIntAndIsInARange(scanner, librarianFunctionsText, 1, numberOfFunctions.get(role));
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    printNotificationsModel);
                            break;
                        case 2:
                            messageTitle = Utils.inputString(scanner, insertMessageTitleText);
                            UserDisplay.removeNotification(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    messageTitle,
                                    printRemoveNotificationsModel);
                            break;
                        case 5:
                            UserDisplay.displayDelays(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    printDelaysModel);
                            break;
                        case 6:
                            delayTitle = Utils.inputString(scanner, insertMessageTitleText);
                            UserDisplay.removeDelay(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    delayTitle,
                                    printRemoveDelayModel);
                            break;
                    }
                    break;
                case MANAGER:
                    choiceFunction = Utils.validateIfInputIsAnIntAndIsInARange(scanner, managerFunctionsText, 1, numberOfFunctions.get(role));
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    printNotificationsModel);
                            break;
                        case 2:
                            messageTitle = Utils.inputString(scanner, insertMessageTitleText);
                            UserDisplay.removeNotification(
                                    username,
                                    Utils.inputUser(scanner, targetInputText),
                                    role,
                                    UserTypes.fromId(Utils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectTargetType, validTypes[0], validTypes[1])),
                                    messageTitle,
                                    printRemoveNotificationsModel);
                            break;
                        case 3:
                            UserDisplay.addCollection(role.getId(), scanner);
                            break;
                        case 4:
                            UserDisplay.removeCollectionByTitle(role.getId(), scanner);
                            break;
                        case 5:
                            UserDisplay.removeCollectionByAuthor(role.getId(), scanner);
                            break;
                        case 6:
                            UserDisplay.editCollection(role.getId(), scanner);
                            break;
                    }
                    break;
            }
        }
    }
}
