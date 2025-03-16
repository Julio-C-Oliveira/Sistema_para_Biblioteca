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

public class GetUser {
    public static Map<String, String> getUser(int id){
        String sql = "SELECT id, name FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                String name = rs.getString("name");
                ArrayList<Integer> roles = GetUserRole.getUserRole(id);
                //System.out.println("GET (USER): USER FOUND");
                return Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Arrays.toString(roles.toArray())
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //System.out.println("GET (USER): USER NOT FOUND");
        return null;
    }

    public static Map<String, String> getUser(String name) {
        String sql = "SELECT id, name FROM user WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                int id = rs.getInt("id");
                ArrayList<Integer> roles = GetUserRole.getUserRole(id);
                //System.out.println("GET (USER): USER FOUND");
                return Map.of(
                        "id", Integer.toString(id),
                        "name", name,
                        "role", Arrays.toString(roles.toArray())
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //System.out.println("GET (USER): USER NOT FOUND");
        return null;
    }

    public static Boolean getUser(String name, String password) {
        String sql = "SELECT name, password FROM user WHERE name = ? AND password = ?";

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

