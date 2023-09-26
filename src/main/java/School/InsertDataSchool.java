package School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataSchool {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            // "name TEXT NOT NULL," +
            //  "age INTEGER," +
            //  "grade text)";
            // övning 8
            // 2- Lägg till funktionalitet för att infoga minst tre studenter i "students"- tabellen
            // 3- Använd preparedstatement för att undvika SQL-injektion

            String sql = "INSERT INTO students(name, age, grade) VALUES(?,?,?)"; //? is the fields to be filled

            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setString(1,"Vigor Lundkvist");
            pstmt.setInt(2,20);
            pstmt.setString(3, "A");
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
