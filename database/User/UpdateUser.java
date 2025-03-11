package database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;
import display.UserTypes;

public class UpdateUser {
    public static boolean updateUser(int id, String name, UserTypes type, String password) {
        String sql = "UPDATE user SET name = ?, type = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, type.getId());
            pstmt.setString(3, password);
            pstmt.setInt(4, id);

            int affectedRows = pstmt.executeUpdate(); // Retorna o número de linhas alteradas
            return affectedRows > 0; // Retorna true se alguma linha foi alterada

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
