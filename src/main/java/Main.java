import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection conn = DBConnection.connect();
        if (conn == null) {
            System.out.println("Database connection is unavailable.");
            return;
        }

        try {
            Statement stmt = conn.createStatement();

            // Create tables
            String breachedTable = "CREATE TABLE IF NOT EXISTS breached_passwords (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "password TEXT NOT NULL UNIQUE" +
                    ");";

            String logTable = "CREATE TABLE IF NOT EXISTS logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "password TEXT," +
                    "strength TEXT," +
                    "breached BOOLEAN" +
                    ");";

            stmt.execute(breachedTable);
            stmt.execute(logTable);

            // Insert sample breached passwords (avoid duplicates)
            String insert = "INSERT OR IGNORE INTO breached_passwords(password) VALUES" +
                    "('123456')," +
                    "('password')," +
                    "('admin')," +
                    "('qwerty');";

            stmt.execute(insert);

            // Test password
            String testPassword = "Admin123!";

            int score = PasswordStrengthChecker.calculateScore(testPassword);
            String strength = PasswordStrengthChecker.checkStrength(testPassword);

            boolean breached = BreachChecker.isBreached(testPassword);
            PasswordLogger.logResult(testPassword, strength, breached);
            System.out.println("Breached: " + breached);

            System.out.println("Tables created!");
            System.out.println("Sample data inserted!");

            System.out.println("Password: " + testPassword);
            System.out.println("Score: " + score);
            System.out.println("Strength: " + strength);
            System.out.println("Breached: " + breached);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}