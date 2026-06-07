import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:passwords.db");
            System.out.println("Connected to SQLite!");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
