import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboards extends JFrame {
    public Dashboards() {
        setTitle("Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton studentManagementButton = new JButton("Student Management");
        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to Student Management System
                StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
                studentManagementSystem.setVisible(true);
                dispose(); // Close the dashboard window
            }
        });

        JButton studentMarksButton = new JButton("Student Marks");
        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to Student Marks
                StudentMarks studentMarks = new StudentMarks();
                studentMarks.setVisible(true);
                dispose(); // Close the dashboard window
            }
        });

        panel.add(studentManagementButton);
        panel.add(studentMarksButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
}

// Student Management System class (placeholder)
class StudentManagementSystem extends JFrame {
    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Student Management System"));

        // Add components for managing student information (e.g., text fields, buttons)

        add(panel);
    }
}

// Student Marks class (placeholder)
class StudentMarks extends JFrame {
    public StudentMarks() {
        setTitle("Student Marks");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Student Marks"));

        // Add components for managing student marks (e.g., text fields, buttons)

        add(panel);
    }
}
