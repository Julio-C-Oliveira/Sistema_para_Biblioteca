package org.example.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utils {
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
            if (Utils.isValidUppercaseString(username.toUpperCase())) {
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

    public static String inputString(Scanner scanner, String text) {
        String string;
        while (true) {
            System.out.print(text);
            string = scanner.nextLine();
            if (!string.trim().isEmpty()) {
                return string;
            }
        }
    }

    public static int[] stringToList(String input) {
        String[] s1 = input.replaceAll("\\[", "")
                .replaceAll("]", "").replaceAll("\\s", "")
                .split(",");

        int[] arr = new int[s1.length];

        for (int i = 0; i < s1.length; i++) {
            arr[i] = Integer.parseInt(s1[i]);
        }
        return arr;
    }

    public static List<Integer> arrayInStringToArray(String arrayInString) {
        return Arrays.stream(arrayInString.replaceAll("[\\[\\]]", "").replaceAll(" ", "").split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
    public static boolean validateDate(String data) {
        // Verifica se a string está no formato básico esperado
        if (data == null || !data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Isso faz com que a validação seja rigorosa

        try {
            // Tenta fazer o parse da data
            Date dataParseada = sdf.parse(data);

            // Verificação adicional para garantir que o formato está exato
            // Isso evita que "1/1/2023" seja considerado válido quando formatado como "01/01/2023"
            String dataFormatada = sdf.format(dataParseada);
            if (!data.equals(dataFormatada)) {
                return false;
            }

            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}