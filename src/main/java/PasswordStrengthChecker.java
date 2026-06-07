public class PasswordStrengthChecker {

    private static final String SPECIAL_CHARACTER_REGEX = ".*[^A-Za-z0-9].*";

    public static int calculateScore(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (hasSpecialCharacter(password)) score++;

        return score;
    }

    public static String checkStrength(String password) {
        int score = calculateScore(password);

        if (score <= 2) return "Weak";
        if (score == 3 || score == 4) return "Medium";
        return "Strong";
    }

    public static String getSuggestions(String password) {
        StringBuilder suggestions = new StringBuilder();

        if (password.length() < 8) {
            suggestions.append("Use at least 8 characters. ");
        }
        if (!password.matches(".*[A-Z].*")) {
            suggestions.append("Add uppercase letters. ");
        }
        if (!password.matches(".*[a-z].*")) {
            suggestions.append("Add lowercase letters. ");
        }
        if (!password.matches(".*\\d.*")) {
            suggestions.append("Include numbers. ");
        }
        if (!hasSpecialCharacter(password)) {
            suggestions.append("Add special characters. ");
        }

        if (suggestions.length() == 0) {
            return "Strong password!";
        }

        return suggestions.toString().trim();
    }

    private static boolean hasSpecialCharacter(String password) {
        return password.matches(SPECIAL_CHARACTER_REGEX);
    }
}
