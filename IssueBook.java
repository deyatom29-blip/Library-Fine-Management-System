import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class IssueBook extends JFrame {

    JTextField sid, sname, bid, bname, issue, due;

    public IssueBook() {

        setTitle("Issue Book");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== Gradient Background Panel =====
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

        // ===== Form Panel =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        sid = new JTextField();
        sname = new JTextField();
        bid = new JTextField();
        bname = new JTextField();
        issue = new JTextField();
        due = new JTextField();

        panel.add(new JLabel("Student ID"));
        panel.add(sid);

        panel.add(new JLabel("Student Name"));
        panel.add(sname);

        panel.add(new JLabel("Book ID"));
        panel.add(bid);

        panel.add(new JLabel("Book Name"));
        panel.add(bname);

        panel.add(new JLabel("Issue Date (YYYY-MM-DD)"));
        panel.add(issue);

        panel.add(new JLabel("Due Date (YYYY-MM-DD)"));
        panel.add(due);

        // ===== Buttons =====
        JButton addBtn = new JButton("Issue Book");
        addBtn.setBackground(new Color(33, 58, 89));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);

        JButton backBtn = new JButton("Back to Home");
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);

        panel.add(backBtn);
        panel.add(addBtn);

        gradientPanel.add(panel, BorderLayout.CENTER);

        // ===== Button Actions =====
        addBtn.addActionListener(e -> saveData());

        backBtn.addActionListener(e -> {
            new MainPage();   // Opens MainPage
            dispose();        // Closes IssueBook window
        });

        setVisible(true);
    }

    void saveData() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO books(student_id,student_name,book_id,book_name,issue_date,due_date) VALUES(?,?,?,?,?,?)"
            );

            ps.setString(1, sid.getText());
            ps.setString(2, sname.getText());
            ps.setString(3, bid.getText());
            ps.setString(4, bname.getText());
            ps.setDate(5, Date.valueOf(issue.getText()));
            ps.setDate(6, Date.valueOf(due.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Issued Successfully");

            sid.setText("");
            sname.setText("");
            bid.setText("");
            bname.setText("");
            issue.setText("");
            due.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}