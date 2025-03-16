package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.SQLException;
import java.util.Arrays;

public class InsertUserRole {
    public static void create(int userId, int[] roles){
        String sql = "INSERT INTO user_role(user_id, role) VALUES(?, ?)";

        try (var conn = DatabaseConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {
            for (int role : roles) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, role);
                pstmt.executeUpdate();
            }

            System.out.println("ASSOCIATING USER " + userId + " WITH ROLE(S) " + Arrays.toString(roles));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("SUCCESSFULLY INSERTED USER ROLE");
    }
}
