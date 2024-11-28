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

            // Clear the table before inserting data
            String clearTableSQL = "DELETE FROM Employees";
            stmt.executeUpdate(clearTableSQL);

            // Insert new rows
            String insertDataSQL = "INSERT INTO Employees (id, name, age) VALUES " +
                    "(1, 'Albina', 19), " +
                    "(2, 'Aidana', 25)";
            stmt.executeUpdate(insertDataSQL);

            // Query and print rows
            String querySQL = "SELECT * FROM Employees";
            try (ResultSet rs = stmt.executeQuery(querySQL)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Age: " + rs.getInt("age"));
                }
            }

            // Update a row
            String updateSQL = "UPDATE Employees SET age = 30 WHERE id = 2";
            stmt.executeUpdate(updateSQL);

            // Delete a row
            String deleteSQL = "DELETE FROM Employees WHERE id = 1";
            stmt.executeUpdate(deleteSQL);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
