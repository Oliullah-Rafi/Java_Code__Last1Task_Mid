
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/task2";
    private static final String USER = "root";
/*private static final String PASSWORD = ""; */


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }

        return DriverManager.getConnection(URL, USER, "");
    }
}

