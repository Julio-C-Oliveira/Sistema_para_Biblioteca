package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.database.Role.InsertUserRole;
import org.example.database.Role.UpdateUserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UpdateUser {
    public static void updateUser(int id, int role) {
        UpdateUserRole.updateRole(id, role);
        System.out.println("USER UPDATED SUCCESSFULLY");
    }

    public static void updateUser(int id, String password) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("USER UPDATED SUCCESSFULLY");
    }

    public static void updateUser(String name, int role) {
        Map<String, String> user = GetUser.getUser(name);
        int user_id = Integer.parseInt(user.get("id"));
        UpdateUserRole.updateRole(user_id, role);
    }

    public static void updateUser(String name, String password) {
        String sql = "UPDATE user SET password = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println();
            pstmt.setString(1, password);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("USER UPDATED SUCCESSFULLY");

    }
}
