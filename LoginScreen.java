import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {

        setTitle("Library Management - Login");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(244, 246, 249));
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);

        // ===== Title =====
        JLabel title = new JLabel("Library System");
        title.setFont(titleFont);
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(95, 20, 250, 40);
        panel.add(title);

        // ===== Username =====
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(labelFont);
        userLabel.setBounds(80, 80, 100, 25);
        panel.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(80, 105, 220, 30);
        panel.add(username);

        // ===== Password =====
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(labelFont);
        passLabel.setBounds(80, 145, 100, 25);
        panel.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(80, 170, 220, 30);
        panel.add(password);

        // ===== Login Button =====
        JButton login = new JButton("Login");
        login.setBounds(140, 220, 120, 35);
        login.setBackground(new Color(33, 58, 89));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        panel.add(login);

        // ===== Login Logic =====
        login.addActionListener(e -> {

            String enteredUsername = username.getText().trim();
            String enteredPassword = String.valueOf(password.getPassword()).trim();

            // Simple Hardcoded Login
            if ("admin".equalsIgnoreCase(enteredUsername) &&
                    "1234".equals(enteredPassword)) {

                new MainPage();   // Open your MainPage
                dispose();        // Close Login window

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
