package org.example;

import java.sql.*;

public class FetchData {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db"); //connection to db
            Statement stmt = conn.createStatement();

            String sql = "SELECT id,name,email FROM users";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("ID: " + id + " name: " + name + "email" + email);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
