package display;

import gateway.Gateway;
import utilities.InputUtils;

import java.util.Scanner;

public class UserDisplay {
    private  UserTypes userTypes = null;

    public UserDisplay() {}

    public void runInterface() {
        String textSelectUserType =
                "Selecione um módulo abaixo:\n" +
                "[1] - Leitor.\n" +
                "[2] - Bibliotecário.\n" +
                "[3] - Gerente.\n" +
                "Insira a sua escolha: ";
        String usernameInputText =
                "Insira o nome de usuário cadastrado: ";
        String passwordInputText =
                "Insira a senha cadastrada: ";

        Scanner scanner = new Scanner(System.in);

        int[] validTypes = UserTypes.getValidRange();
        int choice = InputUtils.validateIfInputIsAnIntAndIsInARange(scanner, textSelectUserType, validTypes[0], validTypes[1]);

        String username = InputUtils.inputUser(scanner, usernameInputText);
        String password = InputUtils.inputPassword(scanner, passwordInputText);


    }
}
