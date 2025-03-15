package org.example.database.User;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserTable {
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "	id INTEGER PRIMARY KEY," +
                "	name text NOT NULL UNIQUE," + // Adicionando UNIQUE aqui
                "	role int NOT NULL," +
                "   password text NOT NULL" +
                ");";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
