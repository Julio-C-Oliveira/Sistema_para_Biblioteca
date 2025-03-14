package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.display.UserTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUser {
    public static boolean updateUser(String name, UserTypes role, String password) {
        String sql = "UPDATE user SET name = ?, role = ?, password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, role.getId());
            pstmt.setString(3, password);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            System.out.println("Successfully updated user");
            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
