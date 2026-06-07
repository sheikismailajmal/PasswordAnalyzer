import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BreachChecker {

    public static boolean isBreached(String password) {

        String query = "SELECT * FROM breached_passwords WHERE password = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, password);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return true;  // password found in breached list
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false; // not found
    }
}
