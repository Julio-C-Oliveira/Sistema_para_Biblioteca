package org.example.database.User;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class GetAllUsers {
    public static ArrayList<Map<String, String>> getAll() {
        String sql = "SELECT * FROM user";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Map<String, String>> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int role = rs.getInt("role");
                String password = rs.getString("password");
                Map<String, String> currentUser = Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Integer.toString(role),
                        "password", password
                );
                users.add(currentUser);
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null; // Retorna null se não encontrar um usuário
    }
}
