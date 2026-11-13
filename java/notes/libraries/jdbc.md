# JDBC (Java Database Connectivity)

**JDBC (Java Database Connectivity)** is a standard Java API that enables applications to interact with relational databases.  
It provides a set of interfaces and classes for performing database operations such as retrieving, inserting, updating, and deleting data.  
JDBC also supports executing SQL statements and managing database structures like creating or dropping tables.

Although JDBC is powerful, it is considered a **low-level API**. For real-world projects, developers often prefer higher-level frameworks such as **JPA (Java Persistence API)** or **Hibernate**, which simplify database interaction and reduce boilerplate code.

### Steps in Using JDBC

1. **Import the SQL Package**
   ```java
   import java.sql.*;
   ```

2. **Load the Driver Class**
   ```java
   Class.forName("com.mysql.cj.jdbc.Driver");
   ```
    The driver class comes from the com.mysql.cj.jdbc package.

3. **Establish the Connection**
   ```java
   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java11","root","root");
   ```
    The connection object is used to execute SQL queries and manage the database connection. 
    - **localhost** → MySQL server host (IP or hostname)
    - **3306** → default MySQL port
    - **java11** → database name
    - **root** / root → username and password

4. **Create a Statement**
   ```java
   Statement stmt = con.createStatement();
   ```

5. **Execute a Query**
   ```java
   ResultSet rs = stmt.executeQuery("select * from emp");
   ```

6. **Process the Result**
   ```java
   while(rs.next()){
       System.out.println(
        rs.getInt(1)+" "
        +rs.getString(2)+" "
        +rs.getString(3)+" "
        +rs.getString(4));
   };
   ```

7. **Close the Connection**
   ```java
   con.close();
   ```

### JDBC Connection Pooling

Connection pooling improves performance by reusing database connections instead of creating a new one for each request. This reduces overhead and is widely used in enterprise applications. Popular connection pooling libraries include HikariCP, C3P0, and Apache DBCP.

### JDBC ORM (Java Object Relational Mapping)

While JDBC provides direct database interaction, ORM frameworks like Hibernate or JPA map Java objects to database tables. This allows developers to work with objects instead of writing raw SQL, making the code cleaner, more maintainable, and less error-prone.

### Sample 3-Layer Architecture (Order Example)

In enterprise applications, JDBC is often used within a 3-layer architecture for better structure and maintainability:

1. **Model Layer**: Java Model (Entity Layer) – Represents data objects.
    ```java
    public class Order {
        private int id;
        private String customerName;
        private double amount;

        // Constructors, getters, setters
    }
    ```

2. **DAO Layer**: Data Access Object (DAO) – Handles database operations.
    ```java
    import java.sql.*;
    import java.util.*;

    public class OrderDAO {
        private Connection con;

        public OrderDAO(Connection con) {
            this.con = con;
        }

        public void saveOrder(Order order) throws SQLException {
            String sql = "INSERT INTO orders (id, customer_name, amount) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getId());
            ps.setString(2, order.getCustomerName());
            ps.setDouble(3, order.getAmount());
            ps.executeUpdate();
        }

        public List<Order> getAllOrders() throws SQLException {
            List<Order> orders = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getDouble("amount")
                ));
            }
            return orders;
        }
    }
    ```
3. **Service Layer**: Service Layer – Contains business logic and uses DAO.
    ```java
    import java.sql.*;
    import java.util.*;

    public class OrderService {
        private OrderDAO orderDAO;

        public OrderService(Connection con) {
            this.orderDAO = new OrderDAO(con);
        }

        public void placeOrder(Order order) throws SQLException {
            // Business logic: e.g., validate order before saving
            if (order.getAmount() > 0) {
                orderDAO.saveOrder(order);
                System.out.println("Order placed successfully!");
            } else {
                System.out.println("Invalid order amount.");
            }
        }

        public List<Order> listOrders() throws SQLException {
            return orderDAO.getAllOrders();
        }
    }
    ```
4. **Main Layer** (Controller Layer / UI Layer) – Interacts with the user.
    ```java
    public class Main {
        public static void main(String[] args) {
            try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java11", "root", "root")) {

                OrderService orderService = new OrderService(con);

                // Create and save an order
                Order order = new Order(1, "Alice", 150.0);
                orderService.placeOrder(order);

                // Retrieve and display orders
                for (Order o : orderService.listOrders()) {
                    System.out.println(o.getId() + " - " + o.getCustomerName() + " - $" + o.getAmount());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    ```

**Notes:** In this structure:
- Model defines the data.
- DAO handles all database interactions via JDBC.
- Service enforces business rules and workflows.
- Controller/Main connects the service with the user interface.