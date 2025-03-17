package org.example.delays;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelaysDataManager {
    private final static String relativeDelayDataPath = "main/jsonData/delayData.json";
    private final static String delayDataPath = String.format("%s/%s", Main.basePath, DelaysDataManager.relativeDelayDataPath);

    public DelaysDataManager() {}

    public static void createDelayData() throws IOException {
        // Criação do ObjectMapper para manipulação de JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Mapa de usuários e suas pendências
        Map<String, Map<String, List<Map<String, String>>>> users = new HashMap<>();

        // Adicionando os usuários e suas pendências
        users.put("KLEBER", createDelays("Fahrenheit 451", "12/12/2012"));

        // Gravando o conteúdo em um arquivo JSON
        File jsonFile = new File(DelaysDataManager.delayDataPath);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, users);
            //System.out.println("Arquivo JSON criado em: " + jsonFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo JSON: " + e.getMessage());
        }
    }

    private static Map<String, List<Map<String, String>>> createDelays(String... args) {
        Map<String, List<Map<String, String>>> delays = new HashMap<>();

        for (int i = 0; i < args.length; i += 3) {
            Map<String, String> delayDetails = new HashMap<>();
            List<Map<String, String>> delayDetailsList = new ArrayList<>();

            delayDetails.put(args[i + 1], args[i + 2]);
            delayDetailsList.add(delayDetails);
            delays.put(args[i], delayDetailsList);
        }
        return delays;
    }

    static public Map<String, List<Map<String, String>>> readDelayData(){
        Map<String, List<Map<String, String>>> users = null;

        try {
            // Caminho do arquivo JSON
            File jsonFile = new File(DelaysDataManager.delayDataPath);

            // Instanciando o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Lendo o JSON e armazenando em uma variável
            users = objectMapper.readValue(jsonFile, Map.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void addDelay(String user, String role, String messageTitle, String messageContent) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Para salvar o JSON formatado

        Map<String, Map<String, List<Map<String, String>>>> users;

        File file = new File(DelaysDataManager.delayDataPath);

        try {
            // Verifica se o arquivo JSON existe
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<Map<String, Map<String, List<Map<String, String>>>>>() {});
            } else {
                users = new HashMap<>();
            }

            // Se o usuário ainda não existe, adiciona um novo
            users.putIfAbsent(user, new HashMap<>());

            // Se o tipo de usuário ainda não existe, adiciona um novo
            users.get(user).putIfAbsent(role, new ArrayList<>());

            // Cria a nova pendência
            Map<String, String> delay = new HashMap<>();
            delay.put(messageTitle, messageContent);

            // Adiciona a pendência na lista correspondente a role
            users.get(user).get(role).add(delay);

            // Salva as mudanças no arquivo JSON
            objectMapper.writeValue(file, users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeDelay(String user, String title) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(DelaysDataManager.delayDataPath);
        if (!file.exists()) {
            return "JSON file not found.";
        }

        // Lê o JSON do arquivo
        Map<String, List<Map<String, String>>> users = null;
        try {
            users = objectMapper.readValue(file, new TypeReference<Map<String, List<Map<String, String>>>>() {});
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Verifica se o usuário existe
        if (users.containsKey(user)) {
            List<Map<String, String>> delays = users.get(user);
            // Remove a pendência com o título correspondente
            boolean removed = delays.removeIf(delay -> delay.containsKey(title));
            if (removed) {
                // Salva as mudanças no arquivo JSON
                try {
                    objectMapper.writeValue(file, users);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return "Delay successfully removed";

            } else {
               return "Delay not found";
            }
        } else {
            return "User not found";
        }

    }
}