package org.example.display;

import org.example.gateway.Gateway;
import org.example.notifications.Notifications;
import org.example.utilities.InputUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserDisplay {
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
                        [2] - Verificar disponibilidade de livros.
                        [3] - Visualizar resumos de livros.
                        [4] - Visualizar pendências.
                        [5] - Ver minha estante e prazos.
                        Insira a sua escolha:\s""";
        String librarianFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Emprestar livro.
                        [3] - Reservar livro.
                        [4] - Verificar pendências de usuários.
                        Insira a sua escolha:\s""";
        String managerFunctionsText =
                """
                        Selecione uma das funções abaixo:
                        [1] - Visualizar notificações.
                        [2] - Adicionar coleção.
                        [3] - Excluir coleção.
                        [4] - Editar coleção.
                        Insira a sua escolha:\s""";
        String printNotificationsModel =
                """
                        Título: %s
                        Conteúdo: %s\s""";

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

        switch (role) {
            case READER:
                choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, readerFunctionsText, 1, 5);
                System.out.println();
                switch (choiceFunction) {
                    case 1:
                        UserDisplay.displayNotifications(username, username, role, role, printNotificationsModel);
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