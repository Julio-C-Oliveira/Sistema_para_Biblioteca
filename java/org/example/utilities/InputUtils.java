package org.example.utilities;

import java.util.Scanner;

public class InputUtils {
    public static int validateIfInputIsAnIntAndIsInARange(Scanner scanner, String text, int start, int end) {
        int choiceInInt = Integer.MAX_VALUE;
        while (true) {
            System.out.print(text);
            String opcaoInString = scanner.nextLine();
            try {
                choiceInInt = Integer.parseInt(opcaoInString);
            } catch (NumberFormatException e) {
                System.out.println("O valor inserido não pode ser convertido para int");
            }
            if (choiceInInt >= start && choiceInInt <= end) break;
            else System.out.println("O valor inserido está fora do intervalo disponível");
        }
        return choiceInInt;
    }

    public static boolean isValidUppercaseString(String str) {
        for (char c : str.toCharArray()) {
            if (c < 'A' || c > 'Z') { // ASCII 'A' = 65, 'Z' = 90, java trata o char como se fosse o número na tabela ascii
                return false;
            }
        }
        return true;
    }

    public static String inputUser(Scanner scanner, String text) {
        String username;
        while (true) {
            System.out.print(text);
            username = scanner.nextLine();
            if (InputUtils.isValidUppercaseString(username.toUpperCase())) {
                return username.toUpperCase();
            }
        }
    }

    public static String inputPassword(Scanner scanner, String text) {
        String password;
        while (true) {
            System.out.print(text);
            password = scanner.nextLine();
            if (!password.trim().isEmpty()) {
                return password;
            }
        }
    }
}
