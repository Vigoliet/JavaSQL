package School;

import java.sql.*;

public class FetchDataSchool {

    // övning 9
    // 2- Skriv en funktion som hämtar alla studenter från "students"- tabellen
    // och skriver ut deras namn och ålder på konsolen
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db"); //connection to db
            Statement stmt = conn.createStatement();

            String sql = "SELECT id,name,age,grade FROM students";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                //String grade = rs.getString("grade");

                System.out.println(" Name: " + name + " Age: " + age);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
