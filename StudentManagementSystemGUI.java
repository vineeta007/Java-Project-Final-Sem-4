import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class StudentManagementSystemGUI {
    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;
    private LoginPanel loginPanel;
    private DashboardPanel dashboardPanel;

    public StudentManagementSystemGUI() {
        frame = new JFrame("Student Management System");
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        loginPanel = new LoginPanel();
        dashboardPanel = new DashboardPanel();

        panel.add(loginPanel, "login");
        panel.add(dashboardPanel, "dashboard");

        cardLayout.show(panel, "login");

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class LoginPanel extends JPanel {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JButton signupButton;
        private JComboBox<String> userTypeComboBox;
        private Map<String, String> userCredentials;

        public LoginPanel() {
            setLayout(new BorderLayout());

            JPanel middlePanel = new JPanel(new GridBagLayout());
            middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            usernameField = new JTextField(20);
            passwordField = new JPasswordField(20);
            loginButton = new JButton("Login");
            signupButton = new JButton("Sign Up");
            userTypeComboBox = new JComboBox<>(new String[]{"Admin", "Student", "Teacher"});
            userCredentials = new HashMap<>();

            userCredentials.put("Admin_admin", "password");
            userCredentials.put("Student_student", "password");
            userCredentials.put("Teacher_teacher", "password");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

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

            add(middlePanel, BorderLayout.CENTER);

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String userType = (String) userTypeComboBox.getSelectedItem();
                    String credentialsKey = userType + "_" + username;

                    if (userCredentials.containsKey(credentialsKey) && userCredentials.get(credentialsKey).equals(password)) {
                        cardLayout.show(panel, "dashboard");
                        dashboardPanel.setUserType(userType);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            signupButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle sign up button click event
                    // You can implement sign up functionality here
                    JOptionPane.showMessageDialog(frame, "Sign up functionality not implemented yet!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }

    class DashboardPanel extends JPanel {
        private JButton studentManagementButton;
        private JButton studentMarksButton;
        private String userType;

        public DashboardPanel() {
            setLayout(new BorderLayout());

            JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 10, 10));

            studentManagementButton = new JButton("Student Management System");
            studentMarksButton = new JButton("Student Marks System");

            buttonsPanel.add(studentManagementButton);
            buttonsPanel.add(studentMarksButton);

            add(buttonsPanel, BorderLayout.CENTER);

            studentManagementButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ("Admin".equals(userType) || "Teacher".equals(userType)) {
                        // Handle student management button click event
                        new StudentManagementSystem();
                    } else {
                        JOptionPane.showMessageDialog(frame, "You do not have permission to access Student Management System", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            studentMarksButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle student marks button click event
                    new StudentMarksSystem();
                }
            });
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystemGUI();
            }
        });
    }
}

