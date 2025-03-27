package org.example.database.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GenerateInitialBooksConfig {
    private  final ArrayList<Map<String, String>> booksToAdd = new ArrayList<>();
    private  final List<String> titles = List.of(
            "1984",
            "O Grande Gatsby",
            "A Revolução dos Bichos",
            "Cem Anos de Solidão",
            "Orgulho e Preconceito",
            "O Morro dos Ventos Uivantes",
            "Dom Quixote",
            "Crime e Castigo",
            "Os Irmãos Karamazov",
            "O Senhor das Moscas",
            "O Retrato de Dorian Gray",
            "O Senhor dos Anéis",
            "O Pequeno Príncipe",
            "A Menina que Roubava Livros",
            "A Metamorfose",
            "O Hobbit",
            "O Silmarillion",
            "O Apanhador no Campo de Centeio",
            "O Sol é para Todos",
            "O Nome do Vento",
            "A Vida Invisível de Addie LaRue",
            "O Conto da Aia",
            "A Cor Púrpura"
    );

    private  boolean checkInitConfig(){
        ArrayList<Map<String, String>> usersFromDB = GetBook.getAllBooks();
        assert usersFromDB != null;
        if (usersFromDB.isEmpty())
            return false;

        for (int i = 0; i < usersFromDB.size(); i++) {
            if(Objects.equals(usersFromDB.get(i).get("title"), titles.get(i))){
                return true;
            }
        }
        return false;
    }
    public  void generateInitialConfig(){
        CreateBookTable.createTable();
        System.out.println();

        if(checkInitConfig()){
            System.out.println("CONFIGURAÇÕES JÁ INICIALIZADAS...");
            return;
        }
        Map<String, String> book1 = Map.of(
                "title", "1984",
                "author", "George Orwell",
                "published_at", "08/06/1949",
                "copies", "12"
        );

        Map<String, String> book2 = Map.of(
                "title", "O Grande Gatsby",
                "author", "F. Scott Fitzgerald",
                "published_at", "10/04/1925",
                "copies", "25"
        );

        Map<String, String> book3 = Map.of(
                "title", "A Revolução dos Bichos",
                "author", "George Orwell",
                "published_at", "17/08/1945",
                "copies", "18"
        );

        Map<String, String> book4 = Map.of(
                "title", "Cem Anos de Solidão",
                "author", "Gabriel García Márquez",
                "published_at", "05/06/1967",
                "copies", "30"
        );

        Map<String, String> book5 = Map.of(
                "title", "Orgulho e Preconceito",
                "author", "Jane Austen",
                "published_at", "28/01/1813",
                "copies", "22"
        );

        Map<String, String> book6 = Map.of(
                "title", "O Morro dos Ventos Uivantes",
                "author", "Emily Brontë",
                "published_at", "01/12/1847",
                "copies", "15"
        );

        Map<String, String> book7 = Map.of(
                "title", "Dom Quixote",
                "author", "Miguel de Cervantes",
                "published_at", "16/01/1605",
                "copies", "40"
        );

        Map<String, String> book8 = Map.of(
                "title", "Crime e Castigo",
                "author", "Fiódor Dostoiévski",
                "published_at", "01/01/1866",
                "copies", "20"
        );

        Map<String, String> book9 = Map.of(
                "title", "Os Irmãos Karamazov",
                "author", "Fiódor Dostoiévski",
                "published_at", "01/11/1880",
                "copies", "10"
        );

        Map<String, String> book10 = Map.of(
                "title", "O Senhor das Moscas",
                "author", "William Golding",
                "published_at", "17/09/1954",
                "copies", "14"
        );

        Map<String, String> book11 = Map.of(
                "title", "O Retrato de Dorian Gray",
                "author", "Oscar Wilde",
                "published_at", "20/06/1890",
                "copies", "8"
        );

        Map<String, String> book12 = Map.of(
                "title", "O Senhor dos Anéis",
                "author", "J.R.R. Tolkien",
                "published_at", "29/07/1954",
                "copies", "35"
        );

        Map<String, String> book13 = Map.of(
                "title", "O Pequeno Príncipe",
                "author", "Antoine de Saint-Exupéry",
                "published_at", "06/04/1943",
                "copies", "28"
        );

        Map<String, String> book14 = Map.of(
                "title", "A Menina que Roubava Livros",
                "author", "Markus Zusak",
                "published_at", "01/01/2005",
                "copies", "19"
        );

        Map<String, String> book15 = Map.of(
                "title", "A Metamorfose",
                "author", "Franz Kafka",
                "published_at", "01/01/1915",
                "copies", "11"
        );

        Map<String, String> book16 = Map.of(
                "title", "O Hobbit",
                "author", "J.R.R. Tolkien",
                "published_at", "21/09/1937",
                "copies", "33"
        );

        Map<String, String> book17 = Map.of(
                "title", "O Silmarillion",
                "author", "J.R.R. Tolkien",
                "published_at", "15/09/1977",
                "copies", "7"
        );

        Map<String, String> book18 = Map.of(
                "title", "O Apanhador no Campo de Centeio",
                "author", "J.D. Salinger",
                "published_at", "16/07/1951",
                "copies", "23"
        );

        Map<String, String> book19 = Map.of(
                "title", "O Sol é para Todos",
                "author", "Harper Lee",
                "published_at", "11/07/1960",
                "copies", "17"
        );

        Map<String, String> book20 = Map.of(
                "title", "O Nome do Vento",
                "author", "Patrick Rothfuss",
                "published_at", "27/03/2007",
                "copies", "9"
        );

        Map<String, String> book21 = Map.of(
                "title", "A Vida Invisível de Addie LaRue",
                "author", "V.E. Schwab",
                "published_at", "06/10/2020",
                "copies", "16"
        );

        Map<String, String> book22 = Map.of(
                "title", "O Conto da Aia",
                "author", "Margaret Atwood",
                "published_at", "01/01/1985",
                "copies", "21"
        );

        Map<String, String> book23 = Map.of(
                "title", "A Cor Púrpura",
                "author", "Alice Walker",
                "published_at", "01/01/1982",
                "copies", "13"
        );

        booksToAdd.add(book1);
        booksToAdd.add(book2);
        booksToAdd.add(book3);
        booksToAdd.add(book4);
        booksToAdd.add(book5);
        booksToAdd.add(book6);
        booksToAdd.add(book7);
        booksToAdd.add(book8);
        booksToAdd.add(book9);
        booksToAdd.add(book10);
        booksToAdd.add(book11);
        booksToAdd.add(book12);
        booksToAdd.add(book13);
        booksToAdd.add(book14);
        booksToAdd.add(book15);
        booksToAdd.add(book16);
        booksToAdd.add(book17);
        booksToAdd.add(book18);
        booksToAdd.add(book19);
        booksToAdd.add(book20);
        booksToAdd.add(book21);
        booksToAdd.add(book22);
        booksToAdd.add(book23);

        for (Map<String, String> book: booksToAdd){
            InsertBook.create(book.get("title"), book.get("author"), book.get("published_at"), Integer.parseInt(book.get("copies")));
            System.out.println();
        }
    }
}
