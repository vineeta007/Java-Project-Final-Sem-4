import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class IntegratedApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private LoginPanel loginPanel;
    private StudentManagementPanel studentManagementPanel;
    private StudentMarksPanel studentMarksPanel;
    private Map<String, String> userCredentials;

    public IntegratedApp() {
        // Initialize the frame
        frame = new JFrame("Integrated App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Initialize the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Initialize the login panel
        loginPanel = new LoginPanel();
        mainPanel.add(loginPanel, "LoginPanel");

        // Initialize the student management panel
        studentManagementPanel = new StudentManagementPanel();
        mainPanel.add(studentManagementPanel, "StudentManagementPanel");

        // Initialize the student marks panel
        studentMarksPanel = new StudentMarksPanel();
        mainPanel.add(studentMarksPanel, "StudentMarksPanel");

        // Initialize user credentials
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "admin");
        userCredentials.put("student", "student");
        userCredentials.put("teacher", "teacher");

        // Add main panel to frame and set visible
        frame.add(mainPanel);
        frame.setVisible(true);

        // Start with the login panel
        cardLayout.show(mainPanel, "LoginPanel");

        // Set up login panel action listener
        loginPanel.setLoginButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginPanel.getUsername();
                String password = loginPanel.getPassword();

                // Check credentials
                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    // If login successful, determine user type and show corresponding panel
                    String userType = username.equals("admin") ? "Admin" : (username.equals("student") ? "Student" : "Teacher");
                    switch (userType) {
                        case "Admin":
                            cardLayout.show(mainPanel, "StudentManagementPanel");
                            break;
                        case "Student":
                            cardLayout.show(mainPanel, "StudentMarksPanel");
                            break;
                        case "Teacher":
                            // Placeholder for future functionality
                            JOptionPane.showMessageDialog(frame, "Welcome, Teacher!");
                            break;
                    }
                } else {
                    // If login failed, show error message
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IntegratedApp();
            }
        });
    }
}

class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        setLayout(new GridLayout(3, 2));

        // Components initialization
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Add components to panel
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}

class StudentManagementPanel extends JPanel {
    public StudentManagementPanel() {
        // Placeholder panel for student management system
        JLabel label = new JLabel("Student Management Panel");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
    }
}

class StudentMarksPanel extends JPanel {
    public StudentMarksPanel() {
        // Placeholder panel for student marks system
        JLabel label = new JLabel("Student Marks Panel");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
    }
}

