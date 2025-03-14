package org.example.display;

import org.example.notifications.Notifications;
import org.example.utilities.InputUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserDisplay {
    private UserTypes userTypes = null;

    public UserDisplay() {}

    public void displayNotifications(String username, Integer role) {
        List<Map<String, String>> userRoleNotifications = Notifications.getNotificationsByUsername(username, role);
    }

    public void runInterface() {
        // Inicializações
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
                        [1] - Verificar disponibilidade de livros.
                        [2] - Visualizar resumos de livros.
                        [3] - Visualizar pendências.
                        [4] - Ver minha estante e prazos.
                        Insira a sua escolha:\s""";
        String librarianFunctionsText =
                """
                        [1] - Emprestar livro.
                        [2] - Reservar livro.
                        [3] - Verificar pendências de usuários.
                        Insira a sua escolha:\s""";
        String managerFunctionsText =
                """
                        [1] - Adicionar coleção.
                        [2] - Excluir coleção.
                        [3] - Editar coleção.
                        Insira a sua escolha:\s""";

        boolean performClassSelection = true;
        boolean performLoginRequest = true;
        Scanner scanner = new Scanner(System.in);
        int[] validTypes = UserTypes.getValidRange();
        int roleID = 0;
        boolean isValid = false;
        int choiceFunction = 0;

        // Lógica
        do {
            if (performClassSelection) {
                int choice = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectUserType, validTypes[0], validTypes[1]);

                String username = InputUtils.inputUser(scanner, usernameInputText);
                String password = InputUtils.inputPassword(scanner, passwordInputText);
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
        System.out.printf(loggedInText, role);

        // -> Exibir as notificações nesse ponto <-

        switch (role) {
            case READER:
                choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, readerFunctionsText, 1, 4);
                break;
            case LIBRARIAN:
                choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, librarianFunctionsText, 1, 3);
                break;
            case MANAGER:
                choiceFunction = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, managerFunctionsText, 1, 3);
                break;
        }
    }
}