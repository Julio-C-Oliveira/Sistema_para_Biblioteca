package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetUserRole {
    public static ArrayList<Integer> getUserRole(int user_id){
        String sql = "SELECT role FROM user_role WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Integer> roles = new ArrayList<>();
            if (rs.next()) {
                System.out.println("PASSANDO AQUUI??? " + rs.getInt("role"));
                roles.add(rs.getInt("role"));
            }
            System.out.println("GET (USER_ROLE): ROLE(S) FOUND");
            return roles;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("GET (USER_ROLE): ROLE(S) NOT FOUND");
        return null;
    }
}
