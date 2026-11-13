package com.example;

import java.sql.*;                        // Import JDBC classes
import io.github.cdimascio.dotenv.Dotenv; // Import dotenv for loading environment variables

public class App {
        /**
         * A simple JDBC demo that connects to a MySQL database, executes a query,
         * and prints out the results.
         * 
         * Steps:
         * 1. Load environment variables from a .env file
         * 2. Establish a connection to MySQL
         * 3. Create a Statement object
         * 4. Execute a query and get results
         * 5. Iterate over the results
         * 6. Close resources
         * 
         * Notes:
         * 1. Dependencies needed in Maven (pom.xml):
         *    - MySQL JDBC driver
         *    - dotenv-java for loading .env (optional, can use System.getenv instead)
         * 2. Setting up MySQL for practice
         * 3. Running the project in VS Code
         * 4. JDBC Flow Summary
         * 
         * @param args unused
         */
    public static void main(String[] args) {

        
        // STEP 1: Load environment variables from a .env file
        // This allows you to store sensitive credentials (DB URL, user, password)
        // outside of your code, so they are not hard-coded.
        Dotenv dotenv = Dotenv.load();

        // Retrieve DB connection info from environment variables
        String url = dotenv.get("DB_URL");           // Example: jdbc:mysql://localhost:3306/practice
        String user = dotenv.get("DB_USER");         // MySQL username
        String password = dotenv.get("DB_PASSWORD"); // MySQL password

        
        // STEP 2: Establish a connection to MySQL
        // DriverManager handles establishing the connection to the database.
        // It uses the JDBC URL, username, and password.
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to MySQL!");

            
            // STEP 3: Create a Statement object
            // Statement allows you to execute SQL queries on the database
            Statement stmt = conn.createStatement();

            
            // STEP 4: Execute a query and get results
            // ResultSet holds the data returned by the query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            
            // STEP 5: Iterate over the results
            while (rs.next()) {
                // Get data from columns by column name or index
                System.out.println(rs.getInt("id") + " - " + rs.getString("first_name"));
            }

            
            // STEP 6: Close resources
            // It's important to close ResultSet and Statement to free resources
            rs.close();
            stmt.close();

        } catch (Exception e) {
            // Handle SQL or connection errors
            e.printStackTrace();
        }

        
        // NOTES:
        // 1. Dependencies needed in Maven (pom.xml):
        //    - MySQL JDBC driver:
        //      <dependency>
        //          <groupId>com.mysql</groupId>
        //          <artifactId>mysql-connector-j</artifactId>
        //          <version>8.4.0</version>
        //      </dependency>
        //
        //    - dotenv-java for loading .env (optional, can use System.getenv instead):
        //      <dependency>
        //          <groupId>io.github.cdimascio</groupId>
        //          <artifactId>dotenv-java</artifactId>
        //          <version>3.0.0</version>
        //      </dependency>
        //
        // 2. Setting up MySQL for practice:
        //    - Install MySQL locally (https://dev.mysql.com/downloads/)
        //    - Start the MySQL service
        //    - Create a database: CREATE DATABASE practice;
        //    - Create a table: 
        //        CREATE TABLE users (
        //            id INT AUTO_INCREMENT PRIMARY KEY,
        //            first_name VARCHAR(50),
        //            last_name VARCHAR(50)
        //        );
        //    - Insert sample data:
        //        INSERT INTO users (first_name, last_name) VALUES ('Alice', 'Smith');
        //
        // 3. Running the project in VS Code:
        //    - Make sure Maven is installed: mvn -v
        //    - Compile: mvn compile
        //    - Run: mvn exec:java -Dexec.mainClass="com.example.App"
        //
        // 4. JDBC Flow Summary:
        //    - Load JDBC driver (optional in modern Java)
        //    - Establish connection
        //    - Create Statement or PreparedStatement
        //    - Execute SQL query or update
        //    - Process ResultSet
        //    - Close resources
    }
}
