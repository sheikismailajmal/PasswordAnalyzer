import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Password Strength Analyzer");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Enter Password:");
        label.setBounds(30, 30, 150, 25);
        frame.add(label);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 30, 180, 25);
        frame.add(passwordField);

        JCheckBox showPassword = new JCheckBox("Show");
        showPassword.setBounds(150, 55, 100, 20);
        frame.add(showPassword);
        showPassword.addActionListener(event -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        JButton button = new JButton("Check");
        button.setBounds(150, 70, 100, 30);
        frame.add(button);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(260, 70, 80, 30);
        frame.add(clearBtn);

        JLabel strengthLabel = new JLabel("Strength: ");
        strengthLabel.setBounds(30, 120, 300, 25);
        frame.add(strengthLabel);

        JLabel breachLabel = new JLabel("Breached: ");
        breachLabel.setBounds(30, 150, 300, 25);
        frame.add(breachLabel);

        JLabel suggestionLabel = new JLabel("Suggestions: ");
        suggestionLabel.setBounds(30, 180, 350, 40);
        frame.add(suggestionLabel);

        clearBtn.addActionListener(event -> {
            passwordField.setText("");
            strengthLabel.setText("Strength: ");
            breachLabel.setText("Breached: ");
            suggestionLabel.setText("Suggestions: ");
            strengthLabel.setForeground(Color.BLACK);
            breachLabel.setForeground(Color.BLACK);
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                String password = new String(passwordField.getPassword());
                if (password.isBlank()) {
                    strengthLabel.setText("Strength: ");
                    breachLabel.setText("Breached: ");
                    suggestionLabel.setText("Suggestions: Enter a password.");
                    strengthLabel.setForeground(Color.BLACK);
                    breachLabel.setForeground(Color.BLACK);
                    return;
                }

                int score = PasswordStrengthChecker.calculateScore(password);
                String strength = PasswordStrengthChecker.checkStrength(password);
                boolean breached = BreachChecker.isBreached(password);
                String suggestions = PasswordStrengthChecker.getSuggestions(password);

                strengthLabel.setText("Strength: " + strength + " (Score: " + score + ")");
                if (suggestions.equals("Strong password!")) {
                    suggestionLabel.setText(suggestions);
                } else {
                    suggestionLabel.setText("Suggestions: " + suggestions);
                }

                if (strength.equals("Weak")) {
                    strengthLabel.setForeground(Color.RED);
                } else if (strength.equals("Medium")) {
                    strengthLabel.setForeground(Color.ORANGE);
                } else {
                    strengthLabel.setForeground(Color.GREEN);
                }

                if (breached) {
                    breachLabel.setText("Warning: This password is found in data breaches!");
                    breachLabel.setForeground(Color.RED);
                } else {
                    breachLabel.setText("Safe: Not found in known breaches");
                    breachLabel.setForeground(Color.GREEN);
                }

                PasswordLogger.logResult(password, strength, breached);
            }
        });

        frame.setVisible(true);
    }
}
