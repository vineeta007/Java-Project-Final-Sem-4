import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Vector;

public class Dashboard {
    private JFrame frame;
    private JPanel panel;
    private JButton studentManagementButton;
    private JButton studentMarksButton;
    private JButton reportButton;
    private JPanel contentPanel;
    private StudentManagementSystem studentManagementSystem;
    private StudentMarksSystem studentMarksSystem;

    public Dashboard() {
        frame = new JFrame("Dashboard");
        panel = new JPanel();
        studentManagementButton = new JButton("Student Management");
        studentMarksButton = new JButton("Student Marks");
        reportButton = new JButton("Report");
        contentPanel = new JPanel(new CardLayout());

        studentManagementSystem = new StudentManagementSystem();
        studentMarksSystem = new StudentMarksSystem();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.add(studentManagementButton);
        panel.add(studentMarksButton);
        panel.add(reportButton);

        contentPanel.add(studentManagementSystem.getMainPanel(), "Student Management");
        contentPanel.add(studentMarksSystem.getMainPanel(), "Student Marks");

        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Student Management");
            }
        });

        studentMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Student Marks");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder report = new StringBuilder("Student Marks Report:\n\n");
                Map<String, Map<String, StudentMarksSystem.StudentData>> marksData = studentMarksSystem.getStudentMarks();
                for (Map.Entry<String, Map<String, StudentMarksSystem.StudentData>> entry : marksData.entrySet()) {
                    report.append("Student: ").append(entry.getKey()).append("\n");
                    Map<String, StudentMarksSystem.StudentData> subjectsData = entry.getValue();
                    for (Map.Entry<String, StudentMarksSystem.StudentData> subjectEntry : subjectsData.entrySet()) {
                        StudentMarksSystem.StudentData data = subjectEntry.getValue();
                        report.append("Subject: ").append(subjectEntry.getKey()).append(", Marks: ").append(data.getMarks()).append(", Attendance: ").append(data.getAttendance()).append("\n");
                    }
                    report.append("\n");
                }
                JTextArea reportTextArea = new JTextArea(report.toString());
                reportTextArea.setEditable(false);
                JOptionPane.showMessageDialog(frame, new JScrollPane(reportTextArea), "Report", JOptionPane.PLAIN_MESSAGE);
            }
        });

        frame.add(panel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Dashboard();
            }
        });
    }
}
