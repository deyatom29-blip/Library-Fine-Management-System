import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnBook extends JFrame {

    JTextField bookIdField, returnDateField;
    JButton returnBtn, backBtn;

    public ReturnBook() {

        setTitle("Return Book");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== Gradient Background =====
        JPanel gradientPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(33, 58, 89),
                        getWidth(), getHeight(), new Color(52, 93, 129)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        gradientPanel.setLayout(new GridBagLayout());
        setContentPane(gradientPanel);

        // ===== White Panel =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.WHITE);

        panel.add(new JLabel("Enter Book ID:"));
        bookIdField = new JTextField();
        panel.add(bookIdField);

        panel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        returnDateField = new JTextField();
        panel.add(returnDateField);

        backBtn = new JButton("Back to Home");
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);

        returnBtn = new JButton("Return Book");
        returnBtn.setBackground(new Color(33, 58, 89));
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setFocusPainted(false);

        panel.add(backBtn);
        panel.add(returnBtn);

        gradientPanel.add(panel);

        // ===== Button Actions =====
        returnBtn.addActionListener(e ->
                returnBook(bookIdField.getText(), returnDateField.getText())
        );

        backBtn.addActionListener(e -> {
            new MainPage();   // Open MainPage
            dispose();        // Close ReturnBook window
        });

        setVisible(true);
    }

    // ===== Return Logic =====
    void returnBook(String bookId, String returnDateInput) {

        if (bookId.isEmpty() || returnDateInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT due_date FROM books WHERE book_id = ? AND return_date IS NULL"
            );
            ps.setString(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate returnDate = LocalDate.parse(returnDateInput);

                long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

                double fine = 0;
                if (daysLate > 0) {
                    fine = daysLate * 5;
                }

                PreparedStatement update = con.prepareStatement(
                        "UPDATE books SET return_date = ? WHERE book_id = ?"
                );
                update.setDate(1, Date.valueOf(returnDate));
                update.setString(2, bookId);
                update.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Book Returned Successfully!\nFine: ₹" + fine);

                bookIdField.setText("");
                returnDateField.setText("");

            } else {
                JOptionPane.showMessageDialog(this,
                        "Book not found or already returned.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Date Format!\nUse YYYY-MM-DD");
        }
    }

    public static void main(String[] args) {
        new ReturnBook();
    }
}