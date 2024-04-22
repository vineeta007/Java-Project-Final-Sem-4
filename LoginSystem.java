import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSystem {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel errorMessageLabel;

    private Dashboard dashboard;
    private StudentManagementSystem studentManagementSystem;
    private StudentMarksSystem studentMarksSystem;

    // Dummy user credentials (username, password)
    private String[][] userCredentials = {
            {"admin", "admin"},
            {"teacher", "teacher"},
            {"student", "student"},
            {"parent", "parent"}
    };

    public LoginSystem() {
        frame = new JFrame("Login System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        errorMessageLabel = new JLabel("");

        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(signupButton);
        mainPanel.add(errorMessageLabel);

        frame.add(mainPanel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    errorMessageLabel.setText(""); // Clear error message
                    redirectToDashboard(username);
                } else {
                    errorMessageLabel.setText("Invalid username or password");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open sign-up page
                // Implement your sign-up page logic here
                JOptionPane.showMessageDialog(frame, "Redirecting to sign-up page...");
            }
        });
    }

    private boolean authenticate(String username, String password) {
        for (String[] credentials : userCredentials) {
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void redirectToDashboard(String username) {
        if (username.equals("admin")) {
            if (dashboard == null) {
                dashboard = new Dashboard(username);
            }
        } else if (username.equals("teacher") || username.equals("student") || username.equals("parent")) {
            if (studentManagementSystem == null) {
                studentManagementSystem = new StudentManagementSystem(username);
            }
        } else {
            if (studentMarksSystem == null) {
                studentMarksSystem = new StudentMarksSystem(username);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginSystem();
            }
        });
    }
}

class Dashboard extends JFrame {
    public Dashboard(String username) {
        setTitle("Dashboard - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

class StudentManagementSystem extends JFrame {
    public StudentManagementSystem(String username) {
        setTitle("Student Management System - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

class StudentMarksSystem extends JFrame {
    public StudentMarksSystem(String username) {
        setTitle("Student Marks System - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
