package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.display.UserTypes;
import org.example.user.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUser {
    public static UserDTO getUser(int id){
        String sql = "SELECT id, name, role FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                String nameReturned = rs.getString("name");
                UserTypes roleReturned =  UserTypes.fromId(rs.getInt("role"));
                System.out.println("User found");
                return new UserDTO(id, nameReturned, roleReturned, "");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null; // Retorna null se não encontrar um usuário
    }

    public static UserDTO getUser(String name) {
        String sql = "SELECT id, name, role FROM user WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nameReturned = rs.getString("name");
                UserTypes roleReturned = UserTypes.fromId(rs.getInt("role"));
                System.out.println("User found");
                return new UserDTO(id, nameReturned, roleReturned, "");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("User not found");
        return null; // Retorna null se não encontrar um usuário
    }

    public static UserDTO getUser(String name, String password) {
        String sql = "SELECT id, name, role, password FROM user WHERE name = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nameReturned = rs.getString("name");
                UserTypes typeReturned =  UserTypes.fromId(rs.getInt("role"));
                String passwordReturned = rs.getString("password");
                System.out.println("User found");
                return new UserDTO(id, nameReturned, typeReturned, passwordReturned);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null; // Retorna null se não encontrar um usuário
    }
}

