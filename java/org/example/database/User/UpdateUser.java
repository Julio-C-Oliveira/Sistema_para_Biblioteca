package org.example.database.User;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUser {
    public static boolean updateUser(int id, int role) {
        String sql = "UPDATE user SET role = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                 pstmt.setInt(1, role);
                 pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            System.out.println("Successfully updated user");
            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.out.println("User not found");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(int id, String password) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                 pstmt.setString(1, password);
                 pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            System.out.println("Successfully updated user");
            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.out.println("User not found");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(String name, int role) {
        String sql = "UPDATE user SET role = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                 pstmt.setInt(1, role);
                 pstmt.setString(2, name);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            System.out.println("Successfully updated user");
            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.out.println("User not found");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateUser(String name, String password) {
        String sql = "UPDATE user SET password = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println();
            pstmt.setString(1, password);
            pstmt.setString(2, name);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            System.out.println(affectedRows > 0);

            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.out.println("User not found");
            System.err.println(e.getMessage());
            return false;
        }
    }
}
