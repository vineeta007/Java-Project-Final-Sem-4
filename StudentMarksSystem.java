import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class StudentMarksSystem {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField gradeLevelField;
    private JTextField subjectField;
    private JTextField marksField;
    private JTextField attendanceField;
    private Map<String, Map<String, StudentData>> studentMarks;
    private Map<String, Integer> defaultMarks;

    public StudentMarksSystem() {
        frame = new JFrame("Student Marks System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 5)); // Reduced rows by 1
        studentMarks = new HashMap<>();
        defaultMarks = new HashMap<>();
        defaultMarks.put("Math", 0);
        defaultMarks.put("Science", 0);
        defaultMarks.put("English", 0);

        nameField = new JTextField(20);
        gradeLevelField = new JTextField(20);
        subjectField = new JTextField(20);
        marksField = new JTextField(20);
        attendanceField = new JTextField(20);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade Level:"));
        inputPanel.add(gradeLevelField);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectField);
        inputPanel.add(new JLabel("Marks:"));
        inputPanel.add(marksField);
        inputPanel.add(new JLabel("Attendance:"));
        inputPanel.add(attendanceField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String gradeLevel = gradeLevelField.getText();
                if (!name.isEmpty()) { // Check if name is not empty
                    StudentData data = new StudentData();
                    data.setMarks(Integer.parseInt(marksField.getText()));
                    data.setAttendance(Integer.parseInt(attendanceField.getText()));
                    studentMarks.put(name, new HashMap<>());
                    studentMarks.get(name).put(subjectField.getText(), data);
                    JOptionPane.showMessageDialog(frame, "Student added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter student name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(addButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentMarksSystem();
            }
        });
    }

    class StudentData {
        private int marks;
        private int attendance;

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public int getAttendance() {
            return attendance;
        }

        public void setAttendance(int attendance) {
            this.attendance = attendance;
        }
    }
}

