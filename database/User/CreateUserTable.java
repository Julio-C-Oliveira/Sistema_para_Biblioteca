package database.User;

import java.sql.Connection;
import java.sql.Statement;

import database.DatabaseConnection;

import java.sql.SQLException;

public class CreateUserTable {
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "	id INTEGER PRIMARY KEY," +
                "	name text NOT NULL," +
                "	type text NOT NULL," +
                "   password text NOT NULL" +
                ");"
        ;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
