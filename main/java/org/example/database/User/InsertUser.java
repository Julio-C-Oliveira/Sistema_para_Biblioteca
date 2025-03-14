package org.example.database.User;

import org.example.database.DatabaseConnection;
import org.example.display.UserTypes;

import java.sql.SQLException;

public class InsertUser {

    public static void create(String name, UserTypes role, String password){
        String sql = "INSERT INTO user(name,role,password) VALUES(?,?,?)";

        try (var conn = DatabaseConnection.connect();
            var pstmt = conn.prepareStatement(sql)) {
            System.out.println("Making test");
            for(int i = 0; i < 3; i++){
                pstmt.setString(1, name);
                pstmt.setInt(2, role.getId());
                pstmt.setString(3, password);
                pstmt.executeUpdate();
            }
            System.out.println("Successfully inserted user");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
