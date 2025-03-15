package org.example.database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class GenerateInitialUserConfig {
    private final ArrayList<Map<String, String>> usersToAdd = new ArrayList<>();
    private final List<String> names = List.of(
            "ANA", "BRUNO", "CARLOS", "DANIELA", "EDUARDO", "FERNANDA", "GABRIEL",
            "HELENA", "IGOR", "JULIANA", "KLEBER", "LARISSA", "MARCOS", "NATALIAROMANOFF",
            "FLAVINHODOPNEU", "AYAME", "VIKTOR"
    );

    private boolean checkInitConfig(){
        ArrayList<Map<String, String>> usersFromDB = GetAllUsers.getAll();
        assert usersFromDB != null;
        if (usersFromDB.isEmpty())
            return false;

        for (int i = 0; i < usersFromDB.size(); i++) {
            if(Objects.equals(usersFromDB.get(i).get("name"), names.get(i))){
                return true;
            }
        }
        return false;
    }

    public void generateInitialConfig(){
        CreateUserTable.createTable();
        if(checkInitConfig()){
            System.out.println("Configurações já inicializadas");
            return;
        }
        Map<String, String> ana = Map.of(
                "name", "ANA",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> bruno = Map.of(
                "name", "BRUNO",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> carlos = Map.of(
                "name", "CARLOS",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> daniela = Map.of(
                "name", "DANIELA",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> eduardo = Map.of(
                "name", "EDUARDO",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> fernanda = Map.of(
                "name", "FERNANDA",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> gabriel = Map.of(
                "name", "GABRIEL",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> helena = Map.of(
                "name", "HELENA",
                "role", "2",
                "password", "abcd"
        );
        Map<String, String> igor = Map.of(
                "name", "IGOR",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> juliana = Map.of(
                "name", "JULIANA",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> kleber = Map.of(
                "name", "KLEBER",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> larissa = Map.of(
                "name", "LARISSA",
                "role", "3",
                "password", "abcd"
        );
        Map<String, String> marcos = Map.of(
                "name", "MARCOS",
                "role", "2",
                "password", "abcd"
        );
        Map<String, String> nataliaromanoff = Map.of(
                "name", "NATALIAROMANOFF",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> flavinhodopneu = Map.of(
                "name", "FLAVINHODOPNEU",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> ayame = Map.of(
                "name", "AYAME",
                "role", "1",
                "password", "abcd"
        );
        Map<String, String> viktor = Map.of(
                "name", "VIKTOR",
                "role", "1",
                "password", "abcd"
        );

        usersToAdd.add(ana);
        usersToAdd.add(bruno);
        usersToAdd.add(carlos);
        usersToAdd.add(daniela);
        usersToAdd.add(eduardo);
        usersToAdd.add(fernanda);
        usersToAdd.add(gabriel);
        usersToAdd.add(helena);
        usersToAdd.add(igor);
        usersToAdd.add(juliana);
        usersToAdd.add(kleber);
        usersToAdd.add(larissa);
        usersToAdd.add(marcos);
        usersToAdd.add(nataliaromanoff);
        usersToAdd.add(flavinhodopneu);
        usersToAdd.add(ayame);
        usersToAdd.add(viktor);

        for (Map<String, String> user: usersToAdd){
            InsertUser.create(user.get("name"), Integer.parseInt(user.get("role")), user.get("password"));
        }
    }
}
