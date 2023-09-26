package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            String sql = "INSERT INTO users(name, email) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"John Doe");
            pstmt.setString(2,"John.doe@example.com");
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
