import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainApplication {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JComboBox<String> userTypeComboBox;
    private Map<String, String> userCredentials;

    public MainApplication() {
        frame = new JFrame("Login");
        panel = new JPanel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        userTypeComboBox = new JComboBox<>(new String[]{"Admin", "Student", "Teacher"});
        userCredentials = new HashMap<>();

        // Dummy user accounts
        userCredentials.put("Admin_admin", "password");
        userCredentials.put("Student_student", "password");
        userCredentials.put("Teacher_teacher", "password");

        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("User Type:"));
        panel.add(userTypeComboBox);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(signupButton);
        panel.add(loginButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                String credentialsKey = userType + "_" + username;

                // Check if the provided credentials match
                if (userCredentials.containsKey(credentialsKey) && userCredentials.get(credentialsKey).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful as " + userType + "!");
                    // Open the dashboard with the user's role
                    openDashboard(userType);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                String credentialsKey = userType + "_" + username;

                // Check if the user already exists
                if (userCredentials.containsKey(credentialsKey)) {
                    JOptionPane.showMessageDialog(frame, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Add the new user
                    userCredentials.put(credentialsKey, password);
                    JOptionPane.showMessageDialog(frame, "Sign up successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void openDashboard(String userType) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (userType.equals("Admin") || userType.equals("Teacher")) {
                    new AdminDashboard();
                } else {
                    new StudentDashboard();
                }
                frame.dispose(); // Close the login/signup window
            }
        });
    }

    public static void main(String[] args) {
        new MainApplication();
    }
}

class AdminDashboard {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton studentManagementButton;
    private JButton studentMarksButton;
    private JButton viewReportButton;

    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        studentManagementButton = new JButton("Student Management System");
        studentMarksButton = new JButton("Student Marks System");
        viewReportButton = new JButton("View Report");

        mainPanel.add(studentManagementButton);
        mainPanel.add(studentMarksButton);
        mainPanel.add(viewReportButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Add action listeners for buttons
        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open student management system
                JOptionPane.showMessageDialog(frame, "Opening Student Management System...");
            }
        });

        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open student marks system
                JOptionPane.showMessageDialog(frame, "Opening Student Marks System...");
            }
        });

        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open view report
                JOptionPane.showMessageDialog(frame, "Opening View Report...");
            }
        });
    }
}

class StudentDashboard {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton studentMarksButton;
    private JButton viewReportButton;

    public StudentDashboard() {
        frame = new JFrame("Student Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        studentMarksButton = new JButton("Student Marks System");
        viewReportButton = new JButton("View Report");

        mainPanel.add(studentMarksButton);
        mainPanel.add(viewReportButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Add action listeners for buttons
        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open student marks system
                JOptionPane.showMessageDialog(frame, "Opening Student Marks System...");
            }
        });

        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open view report
                JOptionPane.showMessageDialog(frame, "Opening View Report...");
            }
        });
    }
}
