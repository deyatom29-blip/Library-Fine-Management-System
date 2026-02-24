import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame {

    public MainPage() {

        setTitle("Library Book Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background color
        getContentPane().setBackground(new Color(230, 233, 237));
        setLayout(new BorderLayout());

        // ===== Title =====
        JLabel title = new JLabel("Library Book Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ===== Grid Panel =====
        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 40, 40));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300));
        gridPanel.setBackground(new Color(230, 233, 237));

        // Buttons
        JButton btnIssue = createButton("Issue Book");
        JButton btnReturn = createButton("Return & Fine");
        JButton btnView = createButton("View Records");
        JButton btnExit = createButton("Exit");

        gridPanel.add(btnIssue);
        gridPanel.add(btnReturn);
        gridPanel.add(btnView);
        gridPanel.add(btnExit);

        add(gridPanel, BorderLayout.CENTER);

        // ===== Navigation Code Added Here =====

        btnIssue.addActionListener(e -> new IssueBook());


        btnReturn.addActionListener(e -> new ReturnBook());

        btnView.addActionListener(e -> new ViewRecords());

        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Simple styled button
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(33, 58, 89));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        new MainPage();
    }
}