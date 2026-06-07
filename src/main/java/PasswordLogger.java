import java.sql.Connection;
import java.sql.PreparedStatement;

public class PasswordLogger {

    public static void logResult(String password, String strength, boolean breached) {

        String query = "INSERT INTO logs(password, strength, breached) VALUES(?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, password);
            pstmt.setString(2, strength);
            pstmt.setBoolean(3, breached);

            pstmt.executeUpdate();

            System.out.println("Log saved!");

        } catch (Exception e) {
            System.out.println("Logging Error: " + e.getMessage());
        }
    }
}
