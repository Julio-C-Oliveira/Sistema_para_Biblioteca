package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.database.Role.InsertUserRole;

import java.sql.SQLException;

public class InsertUser {

    public static void create(String name, int[] roles, String password){
        String sql = "INSERT INTO user(name,password) VALUES(?,?)";

        try (var conn = DatabaseConnection.connect();
            var pstmt = conn.prepareStatement(sql)) {
            System.out.println("Inserting " + name);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            // Conexão com tabela intermediária
            // Obtém o ID gerado
            int generatedId = -1;
            try (var generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Obtém o valor da primeira coluna (ID)
                    System.out.println("Successfully inserted user with ID: " + generatedId);
                } else {
                    System.err.println("Failed to retrieve generated ID.");
                }
            }
            InsertUserRole.create(generatedId, roles);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("USER CREATED SUCCESSFULLY");
    }
}
