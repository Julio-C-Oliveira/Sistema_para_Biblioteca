package utilities;

import java.util.Scanner;

public class InputUtils {
    public static int validateIfInputIsAnIntAndIsInARange(Scanner scanner, String texto, int inicio, int fim) {
        int opcaoInInt = Integer.MAX_VALUE;
        while (true) {
            System.out.print(texto);
            String opcaoInString = scanner.nextLine();
            try {
                opcaoInInt = Integer.parseInt(opcaoInString);
            } catch (NumberFormatException e) {
                System.out.println("O valor inserido não pode ser convertido para int");
            }
            if (opcaoInInt >= inicio && opcaoInInt <= fim) break;
            else System.out.println("O valor inserido está fora do intervalo disponível");
        }
        return opcaoInInt;
    }
}
