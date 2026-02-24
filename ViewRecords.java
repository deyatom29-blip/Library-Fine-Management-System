import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ViewRecords extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewRecords() {

        setTitle("View Records");
        setSize(1050, 450);
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

        gradientPanel.setLayout(new BorderLayout());
        setContentPane(gradientPanel);

        // ===== Table Setup (Added Fine Column) =====
        String[] columns = {
                "Student ID",
                "Student Name",
                "Book ID",
                "Book Name",
                "Issue Date",
                "Due Date",
                "Return Date",
                "Return Status",
                "Fine (₹)"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        // ===== Back Button =====
        JButton backBtn = new JButton("Back to Home");
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);

        gradientPanel.add(backBtn, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            new MainPage();
            dispose();
        });

        loadData();

        setVisible(true);
    }

    void loadData() {

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {

                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                Date issueDate = rs.getDate("issue_date");
                Date dueDate = rs.getDate("due_date");
                Date returnDate = rs.getDate("return_date");

                String status;
                double fine = 0;

                if (returnDate == null) {
                    status = "Not Returned";
                } else {
                    status = "Returned";

                    // ===== Fine Calculation =====
                    LocalDate due = dueDate.toLocalDate();
                    LocalDate returned = returnDate.toLocalDate();

                    long daysLate = ChronoUnit.DAYS.between(due, returned);

                    if (daysLate > 0) {
                        fine = daysLate * 5;
                    }
                }

                model.addRow(new Object[]{
                        studentId,
                        studentName,
                        bookId,
                        bookName,
                        issueDate,
                        dueDate,
                        returnDate,
                        status,
                        fine
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewRecords();
    }
}