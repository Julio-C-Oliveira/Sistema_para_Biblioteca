package org.example.display;

import org.example.gateway.Gateway;
import org.example.notifications.Notifications;
import org.example.utilities.InputUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserDisplay {
    private boolean state = true;
    private UserTypes userTypes = null;

    public UserDisplay() {}

    public static void displayNotifications(String username, String target, UserTypes userRole, UserTypes targetRole, String printNotificationsModel) {
        List<Map<String, String>> userRoleNotifications = Gateway.getNotificationsByUsername(username, target, userRole.getId(), targetRole.getId());
        for (Map<String, String> userNotification : userRoleNotifications) {
            String title = userNotification.entrySet().iterator().next().getKey();
            System.out.printf(printNotificationsModel + "\n", title, userNotification.get(title));
        }
        System.out.println();
    }
    public static void removeNotification(String username, String target, UserTypes userRole, UserTypes targetRole, String messageTitle) {
        Gateway.removeNotificationsByUsernameAndTitle(username, target, userRole.getId(), targetRole.getId(), messageTitle);
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
        String usernameInputText =
                "Insira o nome de usuário cadastrado: ";
        String passwordInputText =
                "Insira a senha cadastrada: ";
        String loggedInText =
                "Login efetuado com sucesso, logado como %s";
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
                        [6] - Logout.
                        Insira a sua escolha:\s""";
        String managerFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Excluir notificação.
                        [3] - Adicionar coleção.
                        [4] - Excluir coleção.
                        [5] - Editar coleção.
                        [6] - Logout.
                        Insira a sua escolha:\s""";
        String printNotificationsModel =
                """
                        Título: %s
                        Conteúdo: %s\s""";
        String insertMessageTitleText = "Insira o titulo da notificação: ";

        // Iniciar variáveis
        boolean performClassSelection = true;
        boolean performLoginRequest = true;
        Scanner scanner = new Scanner(System.in);
        int[] validTypes = UserTypes.getValidRange();
        int roleID = 0;
        boolean isValid = false;
        int choiceFunction = 0;
        String username = "";
        String password = "";
        String messageTitle = "";

        // Carregar os dados dos bancos
        Gateway.keepDatabasesToMemory();

        // Lógica
        do {
            if (performClassSelection) {
                int choice = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectUserType, validTypes[0], validTypes[1]);

                username = InputUtils.inputUser(scanner, usernameInputText);
                password = InputUtils.inputPassword(scanner, passwordInputText);
            }
            if (performLoginRequest) {
                // -> Chamar a função de Login aqui <-, após receber as informações eu verifico a role e a confirmação de válidade que a função irá retornar, por enquanto vou utilizar essas variáveis.
                roleID = 1;
                isValid = true;
            }

            if (isValid) {
                performLoginRequest = false;
                performClassSelection = false;
                break;
            }
        } while((performClassSelection || performLoginRequest) & !isValid);

        UserTypes role = UserTypes.fromId(roleID);
        System.out.println();
        System.out.printf(loggedInText + "\n\n", role);

        while (this.state) {
            switch (role) {
                case READER:
                    choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, readerFunctionsText, 1, 5);
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(username, username, role, role, printNotificationsModel);
                            break;
                        case 2:
                            messageTitle = InputUtils.inputString(scanner, insertMessageTitleText);
                            UserDisplay.removeNotification(username, username, role, role, messageTitle);
                            break;
                    }
                    break;
                case LIBRARIAN:
                    choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, librarianFunctionsText, 1, 4);
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(username, username, role, role, printNotificationsModel);
                            break;
                    }
                    break;
                case MANAGER:
                    choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, managerFunctionsText, 1, 4);
                    System.out.println();
                    switch (choiceFunction) {
                        case 1:
                            UserDisplay.displayNotifications(username, username, role, role, printNotificationsModel);
                            break;
                    }
                    break;
            }
        }
    }
}
