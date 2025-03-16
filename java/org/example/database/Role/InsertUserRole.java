package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.SQLException;
import java.util.Arrays;

public class InsertUserRole {
    public static void create(int user_id, int[] roles){
        String sql = "INSERT INTO user_role(user_id, role) VALUES(?, ?)";

        try (var conn = DatabaseConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {
            for (int role : roles) {
                pstmt.setInt(1, user_id);
                pstmt.setInt(2, role);
                pstmt.executeUpdate();
            }

            System.out.println("ASSOCIATING USER " + user_id + " WITH ROLE(S) " + Arrays.toString(roles));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("SUCCESSFULLY INSERTED USER ROLE");
    }
}
