import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SMSApp extends JFrame {
    private StudentManager studentManager;
    private CourseManager courseManager;
    private FileManager fileManager;

    private JTextArea studentTextArea;
    private JTextArea courseTextArea;

    public SMSApp() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        studentManager = new StudentManager();
        courseManager = new CourseManager();
        fileManager = new FileManager();

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel studentPanel = new JPanel(new BorderLayout());
        studentTextArea = new JTextArea(20, 30);
        studentTextArea.setEditable(false);
        JScrollPane studentScrollPane = new JScrollPane(studentTextArea);
        studentPanel.add(new JLabel("Student Information", JLabel.CENTER), BorderLayout.NORTH);
        studentPanel.add(studentScrollPane, BorderLayout.CENTER);

        JPanel coursePanel = new JPanel(new BorderLayout());
        courseTextArea = new JTextArea(20, 30);
        courseTextArea.setEditable(false);
        JScrollPane courseScrollPane = new JScrollPane(courseTextArea);
        coursePanel.add(new JLabel("Course Information", JLabel.CENTER), BorderLayout.NORTH);
        coursePanel.add(courseScrollPane, BorderLayout.CENTER);

        mainPanel.add(studentPanel);
        mainPanel.add(coursePanel);

        add(mainPanel);
        setVisible(true);

        loadData();
    }

    private void loadData() {
        try {
            studentManager.loadStudents(fileManager.readStudentsFromFile());
            courseManager.loadCourses(fileManager.readCoursesFromFile());
            updateStudentTextArea();
            updateCourseTextArea();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data from files: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentTextArea() {
        studentTextArea.setText(studentManager.getStudentsInfo());
    }

    private void updateCourseTextArea() {
        courseTextArea.setText(courseManager.getCoursesInfo());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SMSApp();
        });
    }
}
