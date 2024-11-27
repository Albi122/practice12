package Students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:database.db";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {


            String createTableSQL = "CREATE TABLE IF NOT EXISTS Employees (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "age INTEGER)";
            stmt.execute(createTableSQL);


            String insertDataSQL = "INSERT INTO Employees (id, name, age) VALUES " +
                    "(1, 'Alice', 25), " +
                    "(2, 'Bob', 30)";
            stmt.executeUpdate(insertDataSQL);


            String querySQL = "SELECT * FROM Employees";
            try (ResultSet rs = stmt.executeQuery(querySQL)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Age: " + rs.getInt("age"));
                }
            }


            String updateSQL = "UPDATE Employees SET age = 35 WHERE id = 2";
            stmt.executeUpdate(updateSQL);


            String deleteSQL = "DELETE FROM Employees WHERE id = 1";
            stmt.executeUpdate(deleteSQL);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
