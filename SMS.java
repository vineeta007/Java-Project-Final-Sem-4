import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.Serializable; 

class UserProfile implements Serializable {
    private String username;
    private String role;

    public UserProfile(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}

class DashboardPanel extends JPanel {
    private JLabel welcomeLabel;
    private JLabel roleLabel;
    private JButton logoutButton;

    public DashboardPanel(String username, String role) {
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome, " + username);
        roleLabel = new JLabel("Role: " + role);
        logoutButton = new JButton("Logout");

        add(welcomeLabel, BorderLayout.NORTH);
        add(roleLabel, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logout successful!");
                System.exit(0);
            }
        });
    }
}

class NavigationMenu extends JPanel {
    private JButton studentManagementButton;
    private JButton academicToolsButton;
    private JButton communicationChannelsButton;
    private JButton reportsAnalyticsButton;
    private JButton feedbackButton;

    public NavigationMenu() {
        setLayout(new GridLayout(5, 1));

        studentManagementButton = new JButton("Student Management");
        academicToolsButton = new JButton("Academic Tools");
        communicationChannelsButton = new JButton("Communication Channels");
        reportsAnalyticsButton = new JButton("Reports & Analytics");
        feedbackButton = new JButton("Feedback");

        add(studentManagementButton);
        add(academicToolsButton);
        add(communicationChannelsButton);
        add(reportsAnalyticsButton);
        add(feedbackButton);

        // Add action listeners for navigation buttons
        studentManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Navigate to Student Management");
            }
        });

        academicToolsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Navigate to Academic Tools");
            }
        });

        communicationChannelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Navigate to Communication Channels");
            }
        });

        reportsAnalyticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Navigate to Reports & Analytics");
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Navigate to Feedback");
            }
        });
    }
}

class StudentManagementPanel extends JPanel {
    // Implementation for student management panel
    // Include components for student enrollment, attendance monitoring, etc.
}

class AcademicToolsPanel extends JPanel {
    // Implementation for academic tools panel
    // Include components for grading book, lesson planners, etc.
}

class CommunicationChannelsPanel extends JPanel {
    // Implementation for communication channels panel
    // Include components for messaging, forums, etc.
}

class ReportsAnalyticsPanel extends JPanel {
    // Implementation for reports and analytics panel
    // Include components for generating and viewing reports
}

class FeedbackPanel extends JPanel {
    // Implementation for feedback panel
    // Include components for feedback form, surveys, etc.
}

public class SMS {
    private JFrame frame;
    private JPanel currentPanel;
    private Map<String, JPanel> panels;
    private UserProfile currentUser;

    public SMS() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame

        panels = new HashMap<>();

        // Show login panel initially
        showLoginPanel();

        frame.setVisible(true);
    }

    private void showLoginPanel() {
        JPanel loginPanel = new LoginPanel();
        setCurrentPanel(loginPanel);
    }

    private void setupDashboard(String username, String role) {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.add(new DashboardPanel(username, role), BorderLayout.NORTH);
        dashboardPanel.add(new NavigationMenu(), BorderLayout.WEST);
        setCurrentPanel(dashboardPanel);
    }

    private void setCurrentPanel(JPanel panel) {
        if (currentPanel != null) {
            frame.remove(currentPanel);
        }
        currentPanel = panel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SMS();
            }
        });
    }
}


