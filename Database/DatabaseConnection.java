package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/entities";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database Connection successful!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database Connection failed:", e);
        }
        return conn;
    }
}