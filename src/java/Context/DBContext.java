package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public Connection getConnection() {
        String db = "house_finder_project1";
        String url = "jdbc:sqlserver://DESKTOP-KFBA1BO\\SQLEXPRESS:1433;databaseName=house_finder_project1";
        String user = "sa";
        String password = "123";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        Connection connection = dbContext.getConnection();

        if (connection != null) {
            System.out.println("Connected to the database!");
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
