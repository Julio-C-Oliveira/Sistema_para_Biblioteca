package database.User;

import java.sql.SQLException;

import database.DatabaseConnection;
import display.UserTypes;

public class InsertUser {

    public static void create(String user, UserTypes type, String password){
        String sql = "INSERT INTO user(user,type,password) VALUES(?,?,?)";

        try (var conn = DatabaseConnection.connect();
            var pstmt = conn.prepareStatement(sql)) {

            for(int i = 0; i < 3; i++){
                pstmt.setString(1, user);
                pstmt.setInt(2, type.getId());
                pstmt.setString(3, password);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
