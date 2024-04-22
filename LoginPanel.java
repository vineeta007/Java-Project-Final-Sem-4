import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JComboBox<String> roleComboBox;
    private JButton loginButton;
    private JButton signUpButton;

    public LoginPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        roleComboBox = new JComboBox<>(new String[]{"Administrator", "Teacher"});
        inputPanel.add(new JLabel("Select Role:"));
        inputPanel.add(roleComboBox);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        inputPanel.add(loginButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signUpButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                String username = JOptionPane.showInputDialog(null, "Enter username:");
                if (username != null && !username.isEmpty()) {
                    // Perform login based on selected role and username
                    if (selectedRole.equals("Administrator")) {
                        // Redirect to administrator dashboard
                        JOptionPane.showMessageDialog(null, "Redirecting to Administrator Dashboard");
                    } else {
                        // Redirect to teacher dashboard
                        JOptionPane.showMessageDialog(null, "Redirecting to Teacher Dashboard");
                    }
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform signup based on selected role
                String selectedRole = (String) roleComboBox.getSelectedItem();
                if (selectedRole.equals("Administrator")) {
                    // Redirect to administrator sign up page
                    JOptionPane.showMessageDialog(null, "Redirecting to Administrator Sign Up");
                } else {
                    // Redirect to teacher sign up page
                    JOptionPane.showMessageDialog(null, "Redirecting to Teacher Sign Up");
                }
            }
        });
    }
}
