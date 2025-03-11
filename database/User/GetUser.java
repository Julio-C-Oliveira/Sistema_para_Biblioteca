package database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import display.UserTypes;
import user.UserDTO;

public class GetUser {
    public static UserDTO getUser(String name, UserTypes type, String password) {
        String sql = "SELECT id, name, type, password FROM user WHERE name = ? AND type = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, type.getId()); // Convertendo Enum para INT
            pstmt.setString(3, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nameReturned = rs.getString("name");
                UserTypes typeReturned =  UserTypes.fromId(rs.getInt("type"));
                String passwordReturned = rs.getString("password");

                return new UserDTO(id, nameReturned, typeReturned, passwordReturned);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null; // Retorna null se não encontrar um usuário
    }
}

