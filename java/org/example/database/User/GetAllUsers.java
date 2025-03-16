package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.database.Role.GetUserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
                ArrayList<Integer> roles = GetUserRole.getUserRole(id);
                //System.out.println("TESTE TESTE");
                //System.out.println(roles.toString());
                String password = rs.getString("password");
                Map<String, String> currentUser = Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Arrays.toString(roles.toArray()),
                        "password", password
                );
                users.add(currentUser);
            }

            //System.out.println("GETALL (USER): ALL USERS FOUND");
            return users;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //System.out.println("GETALL (USER): NO USERS FOUND");
        return null;
    }
}
