package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserRoleTable {
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user_role (" +
                     " user_id INTEGER," +
                     " role INTEGER," +
                     " PRIMARY KEY (user_id, role)," +
                     " FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE" +
                     ");";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("TABLE (USER_ROLE): TABLE CREATED SUCCESSFULLY");
    }
}
