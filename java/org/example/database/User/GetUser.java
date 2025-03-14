package org.example.database.User;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GetUser {
    public static Map<String, String> getUser(int id){
        String sql = "SELECT id, name, role FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int role =  rs.getInt("role");
                System.out.println("User found");
                return Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Integer.toString(role)
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null; // Retorna null se não encontrar um usuário
    }

    public static Map<String, String> getUser(String name) {
        String sql = "SELECT id, name, role FROM user WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int role = rs.getInt("role");
                System.out.println("User found");
                return Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Integer.toString(role)
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("User not found");
        return null; // Retorna null se não encontrar um usuário
    }

    public static Boolean getUser(String name, String password) {
        String sql = "SELECT id, name, role, password FROM user WHERE name = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}

