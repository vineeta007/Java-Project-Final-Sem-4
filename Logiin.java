import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private JFrame frame;
    private JPanel panel;
    private JPanel middlePanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JComboBox<String> userTypeComboBox;
    private Map<String, String> userCredentials;

    public Login() {
        frame = new JFrame("Login");
        panel = new JPanel(new BorderLayout()) {
            // Override paintComponent to set background color
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(225, 190, 231)); // Light purple color
            }
        };
        middlePanel = new JPanel(new GridBagLayout()) {
            // Override paintComponent to set background color
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(255, 255, 153)); // Light yellow color
            }
        };
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

        // Welcome label at top
        JLabel welcomeLabel = new JLabel("WELCOME TO UNIVERSAL SCHOOL");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Middle panel with login information
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        middlePanel.add(new JLabel("User Type:"), gbc);
        gbc.gridx = 1;
        middlePanel.add(userTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        middlePanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        middlePanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        middlePanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        middlePanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        middlePanel.add(signupButton, gbc);
        gbc.gridx = 1;
        middlePanel.add(loginButton, gbc);

        // Thank you label at bottom
        JLabel thankYouLabel = new JLabel("THANK YOU FOR LOGGING IN");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        thankYouLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(thankYouLabel, BorderLayout.SOUTH);

        // Add middle panel
        panel.add(middlePanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Set font style to elegant and luxurious
        Font font = new Font("Arial", Font.BOLD, 16);
        for (Component comp : middlePanel.getComponents()) {
            if (comp instanceof JLabel || comp instanceof JButton || comp instanceof JComboBox) {
                comp.setFont(font);
            }
        }

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                String credentialsKey = userType + "_" + username;

                // Check if the provided credentials match
                if (userCredentials.containsKey(credentialsKey) && userCredentials.get(credentialsKey).equals(password)) {
                    // Redirect to dashboard based on user role
                    redirectToDashboard(userType);
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

    private void redirectToDashboard(String userType) {
        // Close the login frame
        frame.dispose();

        // Redirect to the appropriate dashboard based on user role
        if (userType.equals("Admin") || userType.equals("Teacher")) {
            new Dashboard(userType);
        } else {
            new Dashboard("student"); // For students or parents, we treat them as students for simplicity
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}

class Dashboard {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton viewStudentButton;
    private JButton updateButton;
    private JButton logoutButton;
    private String currentUserRole;

    public Dashboard(String userRole) {
        currentUserRole = userRole;

        frame = new JFrame("Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        viewStudentButton = new JButton("View Students");
        updateButton = new JButton("Update Information");
        logoutButton = new JButton("Logout");

        mainPanel.add(viewStudentButton);
        mainPanel.add(updateButton);
        mainPanel.add(logoutButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        viewStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUserRole.equals("student") || currentUserRole.equals("parent")) {
                    // Redirect to view students page for students and parents
                    JOptionPane.showMessageDialog(frame, "Redirecting to view students page...");
                } else {
                    // Redirect to student management system for admins and teachers
                    JOptionPane.showMessageDialog(frame, "Redirecting to student management system...");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUserRole.equals("admin") || currentUserRole.equals("teacher")) {
                    // Redirect to update information page for admins and teachers
                    JOptionPane.showMessageDialog(frame, "Redirecting to update information page...");
                } else {
                    // Show access denied for students and parents
                    JOptionPane.showMessageDialog(frame, "Access Denied!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform logout operation
                JOptionPane.showMessageDialog(frame, "Logged out successfully.");
                frame.dispose();
                // Redirect to login page
                // Implement your login page redirection logic here
                new Login();
            }
        });
    }
}
