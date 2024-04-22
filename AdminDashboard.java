import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton studentManagementButton;
    private JButton studentMarksButton;
    private JButton viewReportButton;

    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        studentManagementButton = new JButton("Student Management System");
        studentMarksButton = new JButton("Student Marks System");
        viewReportButton = new JButton("View Report");

        mainPanel.add(studentManagementButton);
        mainPanel.add(studentMarksButton);
        mainPanel.add(viewReportButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Student Management System window
                new StudentManagementSystem();
            }
        });

        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Student Marks System window
                new StudentMarksSystem();
            }
        });

        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open View Report window
                JOptionPane.showMessageDialog(frame, "Opening View Report...");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard();
            }
        });
    }
}
