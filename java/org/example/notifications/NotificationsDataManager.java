package org.example.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsDataManager {
    private final static String relativeNotificationDataPath = "main/jsonData/notificationData.json";
    private final static String notificationDataPath = String.format("%s/%s", Main.basePath, NotificationsDataManager.relativeNotificationDataPath);

    public NotificationsDataManager() {}

    public static void createNotificationData() throws IOException {
        // Criação do ObjectMapper para manipulação de JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Mapa de usuários e suas notificações
        Map<String, Map<String, List<Map<String, String>>>> users = new HashMap<>();

        // Adicionando os usuários e suas notificações
        users.put("ANA", createNotifications("1", "Bem-vindo", "Seu cadastro como leitor foi aprovado."));
        users.put("BRUNO", createNotifications("1", "Lembrete", "Devolução do livro '1984' vence amanhã.",
                "3", "Alerta", "A biblioteca atingiu o limite de orçamento."));
        users.put("CARLOS", createNotifications("1", "Dica", "Novos livros de ficção adicionados.",
                "2", "Aviso", "Manutenção do sistema agendada para sexta.",
                "3", "Relatório", "Análise de desempenho dos funcionários disponível."));
        users.put("DANIELA", createNotifications("1", "Notificação", "Seu livro reservado está pronto para retirada."));
        users.put("EDUARDO", createNotifications("1", "Alerta", "Seu livro emprestado vence hoje."));
        users.put("FERNANDA", createNotifications("1", "Promoção", "Desconto de 10% em livros digitais."));
        users.put("GABRIEL", createNotifications("1", "Sugestão", "Participe do clube de leitura este mês."));
        users.put("HELENA", createNotifications("2", "Dica", "Organize os livros pelo novo sistema de cores."));
        users.put("IGOR", createNotifications("1", "Lembrete", "Renovação de cadastro necessária."));
        users.put("JULIANA", createNotifications("1", "Promoção", "Ganhe pontos ao emprestar livros esta semana."));
        users.put("KLEBER", createNotifications("1", "Notificação", "Seu livro favorito foi renovado no catálogo."));
        users.put("LARISSA", createNotifications("3", "Resumo", "Visitas aumentaram 20% este mês."));
        users.put("MARCOS", createNotifications("2", "Informação", "Chegada de novos audiolivros anunciada."));
        users.put("NATALIAROMANOFF", createNotifications("1", "Lembrete", "O clube do livro começa às 19h."));
        users.put("FLAVINHODOPNEU", createNotifications("1", "Sugestão", "Participe do fórum de leitura online."));
        users.put("AYAME", createNotifications("1", "Sugestão", "Reintegre a organização."));
        users.put("VIKTOR", createNotifications("1", "Sugestão", "Continue em busca da gloriosa evolução, THE GLORIOUS EVOLUTION"));

        // Gravando o conteúdo em um arquivo JSON
        File jsonFile = new File(NotificationsDataManager.notificationDataPath);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, users);
            //System.out.println("Arquivo JSON criado em: " + jsonFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo JSON: " + e.getMessage());
        }
    }

    private static Map<String, List<Map<String, String>>> createNotifications(String... args) {
        Map<String, List<Map<String, String>>> notifications = new HashMap<>();

        for (int i = 0; i < args.length; i += 3) {
            Map<String, String> notificationDetails = new HashMap<>();
            List<Map<String, String>> notificationDetailsList = new ArrayList<>();

            notificationDetails.put(args[i + 1], args[i + 2]);
            notificationDetailsList.add(notificationDetails);
            notifications.put(args[i], notificationDetailsList);
        }
        return notifications;
    }

    static public Map<String, Map<String, List<Map<String, String>>>> readNotificationData(){ // falta adaptar para o modo atual de lista
        Map<String, Map<String, List<Map<String, String>>>> users = null;

        try {
            // Caminho do arquivo JSON
            File jsonFile = new File(NotificationsDataManager.notificationDataPath);

            // Instanciando o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Lendo o JSON e armazenando em uma variável
            users = objectMapper.readValue(jsonFile, Map.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void addNotification(String user, String role, String messageTitle, String messageContent) { // falta adaptar para o modo atual de lista
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Para salvar o JSON formatado

        Map<String, Map<String, Map<String, String>>> users;

        File file = new File(NotificationsDataManager.notificationDataPath);
        try {
            // Verifica se o arquivo JSON existe
            if (file.exists()) {
                users = objectMapper.readValue(file, Map.class);
            } else {
                users = new HashMap<>();
            }

            // Se o usuário ainda não existe, adiciona um novo
            users.putIfAbsent(user, new HashMap<>());

            // Se o tipo de usuário ainda não existe, adiciona um novo
            users.get(user).putIfAbsent(role, new HashMap<>());

            // Adiciona a notificação ao tipo do usuário
            users.get(user).get(role).put(messageTitle, messageContent);

            // Salva as mudanças no arquivo JSON
            objectMapper.writeValue(file, users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removerNotificacao(String usuario, String tipo, String titulo) { // falta adaptar para o modo atual de lista
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(NotificationsDataManager.notificationDataPath);
        if (!file.exists()) {
            System.out.println("Arquivo JSON não encontrado.");
            return;
        }

        try {
            // Lê o JSON do arquivo
            Map<String, Map<String, Map<String, String>>> users = objectMapper.readValue(file, Map.class);

            // Verifica se o usuário existe
            if (users.containsKey(usuario)) {
                Map<String, Map<String, String>> role = users.get(usuario);

                // Verifica se o tipo de usuário existe
                if (role.containsKey(tipo)) {
                    Map<String, String> notification = role.get(tipo);

                    // Remove a notificação específica
                    if (notification.containsKey(titulo)) {
                        notification.remove(titulo);

                        // Se não houver mais notificações no tipo, remove o tipo
                        if (notification.isEmpty()) {
                            role.remove(tipo);
                        }

                        // Se não houver mais tipos, remove o usuário
                        if (role.isEmpty()) {
                            users.remove(usuario);
                        }

                        // Salva as mudanças no arquivo JSON
                        objectMapper.writeValue(file, users);
                    } else {
                        System.out.println("Notificação não encontrada.");
                    }
                } else {
                    System.out.println("Tipo de usuário não encontrado.");
                }
            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}