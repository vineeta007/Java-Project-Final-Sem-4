import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class SchoolManagementSystem {
    private JFrame frame;
    private JPanel studentMarksPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel dashboardPanel;
    private JButton studentManagementButton;
    private JButton studentMarksButton;
    private JPanel studentManagementPanel;
    private JTextField nameField;
    private JTextField disorderField;
    private JButton addStudentButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private Map<String, Student> studentRecords;

    public SchoolManagementSystem() {
        // Initialize the frame and components for the login page
        frame = new JFrame("School Management System");
        JPanel loginPanel = new JPanel(new BorderLayout());
        JPanel loginFormPanel = new JPanel(new GridLayout(3, 2));
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        loginFormPanel.add(new JLabel("Username:"));
        loginFormPanel.add(usernameField);
        loginFormPanel.add(new JLabel("Password:"));
        loginFormPanel.add(passwordField);
        loginFormPanel.add(loginButton);
        loginPanel.add(loginFormPanel, BorderLayout.CENTER);

        frame.add(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialize the dashboard panel
        dashboardPanel = new JPanel(new BorderLayout());
        studentManagementButton = new JButton("Student Management");
        studentMarksButton = new JButton("Student Marks");
        dashboardPanel.add(studentManagementButton, BorderLayout.NORTH);
        dashboardPanel.add(studentMarksButton, BorderLayout.SOUTH);

        // Initialize the student management panel
        studentManagementPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        nameField = new JTextField(20);
        disorderField = new JTextField(20);
        addStudentButton = new JButton("Add Student");
        studentRecords = new HashMap<>();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Disorder:"));
        inputPanel.add(disorderField);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Disorder");

        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(studentTable);

        studentManagementPanel.add(inputPanel, BorderLayout.NORTH);
        studentManagementPanel.add(addStudentButton, BorderLayout.CENTER);
        studentManagementPanel.add(scrollPane, BorderLayout.SOUTH);

        // Action listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement user authentication logic
                // On successful login, show the dashboard
                frame.remove(loginPanel);
                frame.add(dashboardPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(dashboardPanel);
                frame.add(studentManagementPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String disorder = disorderField.getText();
                addStudent(name, disorder);
            }
        });

        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(dashboardPanel);
                frame.add(studentMarksPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void addStudent(String name, String disorder) {
        studentRecords.put(name, new Student(name, disorder));
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Student student : studentRecords.values()) {
            Vector<String> row = new Vector<>();
            row.add(student.getName());
            row.add(student.getDisorder());
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SchoolManagementSystem();
            }
        });
    }

    class Student {
        private String name;
        private String disorder;

        public Student(String name, String disorder) {
            this.name = name;
            this.disorder = disorder;
        }

        public String getName() {
            return name;
        }

        public String getDisorder() {
            return disorder;
        }
    }
}
