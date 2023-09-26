package School;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SQLSchool {

    // Övning 6
    // 2- använd jdbc för att skapa en ny sqlite-databas med namnet "school.db"
    public static void main(String[] args) {

        // call only the methods here

        //CreateDB();

        /*InsertStudent("Kalle", 30, "A");
        InsertStudent("Lisa", 30, "B");
        InsertStudent("Eric", 30, "C");
        InsertStudent("Adam", 30, "D");*/

        //PrintStudents();
        //UpdateAge("Lisa", 40);
        //DeleteStudent("Lisa");
        //System.out.println("New students: \n");
        //PrintStudents();


        //Make simple program with switch case:
        boolean loop = true;
            while (loop) {
                try {

                    System.out.println("Chose an option: \n" +
                            "(1) Add student\n" +
                            "(2) Print all student information\n" +
                            "(3) Update age on student\n" +
                            "(4) Delete student\n" +
                            "(5) Create DataBase\n" +
                            "(6) Exit program");
                    Scanner scanner = new Scanner(System.in);
                    int inputOption = scanner.nextInt();

                    switch (inputOption) {
                        // Add student name, age, grade
                        case 1:
                            System.out.println("Name: ");
                            String name = scanner.next();

                            System.out.println("Age: ");
                            int age = scanner.nextInt();

                            System.out.println("Grade: ");
                            String grade = scanner.next();

                            InsertStudent(name, age , grade);
                            System.out.println("Added student!" );
                            break;

                        // Prints all students in the db
                        case 2:
                            PrintStudents();
                            break;

                        // Updates the age of an existing student, needs an if statement if the student exist or not
                        case 3:
                            System.out.println("Name: ");
                            String nameChange = scanner.next();

                            System.out.println("New age: ");
                            int ageChange = scanner.nextShort();

                            UpdateAge(nameChange, ageChange);

                            System.out.println("Changed age on student!");
                            break;

                        // Deletes a student  DeleteStudent("Lisa");
                        case 4:
                            System.out.println("Name: ");
                            String nameToDelete = scanner.next();

                            DeleteStudent(nameToDelete);
                            break;

                        // is currently hard coded to be a certain name
                        case 5:
                            CreateDB();
                            break;

                        // Implemented a way to exit loop, since it won't stop since it's in a try catch
                        case 6:
                            loop = false;
                            break;

                        default:
                            System.out.println("Write a correct number next time");

                    }
                }catch (InputMismatchException e){
                    System.out.println("Write a number next time");
                }
            }
    }

    private static void DeleteStudent(String name){

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");

            String sql = "DELETE FROM students WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.executeUpdate();

            conn.close();
            pstmt.close();



        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private static void UpdateAge(String name, int newAge){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db"); // creates the school database


            String sql = "UPDATE students SET age = ? WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newAge);
            pstmt.setString(2, name);
            pstmt.executeUpdate();


            //String sql = "UPDATE students SET age = 30 WHERE name = 'Eric' ";



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void CreateDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db"); // creates the school database
            Statement stmt = conn.createStatement();

            // övning 7
            // 2- skapa en tabell med namnet "students" med följande kolumner: id,name,age,grade
            String sql = "CREATE TABLE IF NOT EXISTS students(" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "age INTEGER NOT NULL," +
                    "grade TEXT NOT NULL)";

            stmt.execute(sql);


            conn.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void InsertStudent(String name, int age,String grade) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");

            String sql = "INSERT INTO students(name,age,grade) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,grade);

            pstmt.executeUpdate();

            conn.close();
            pstmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void PrintStudents(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            Statement stmt = conn.createStatement();

            String sql = "Select name,age FROM students";

            ResultSet rs = stmt.executeQuery(sql);



            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("Name: "+ name + " " + "Age: "+ age);
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
