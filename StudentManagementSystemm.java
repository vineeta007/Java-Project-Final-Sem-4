import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class Person {
    private String name;
    private String address;
    private String gender;

    public Person(String name, String address, String gender) {
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }
}

class Student extends Person {
    private String disorder;
    private String parentName;

    public Student(String name, String disorder, String parentName, String address, String gender) {
        super(name, address, gender);
        this.disorder = disorder;
        this.parentName = parentName;
    }

    public String getDisorder() {
        return disorder;
    }

    public String getParentName() {
        return parentName;
    }
}

public class StudentManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField disorderField;
    private JTextField parentNameField;
    private JTextField addressField;
    private JComboBox<String> genderComboBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private Map<String, Student> studentRecords;

    public StudentManagementSystem() {
        frame = new JFrame("Student Management System");
        panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Add spacing between rows and columns
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel tablePanel = new JPanel(new BorderLayout());

        nameField = new JTextField();
        disorderField = new JTextField();
        parentNameField = new JTextField();
        addressField = new JTextField();
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        studentRecords = new HashMap<>();

        panel.setBackground(new Color(238, 223, 236)); // Light purple background

        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        inputPanel.setBackground(new Color(255, 255, 204)); // Light yellow background for input fields

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Disorder:"));
        inputPanel.add(disorderField);

        inputPanel.add(new JLabel("Parent Name:"));
        inputPanel.add(parentNameField);

        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderComboBox);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Disorder");
        tableModel.addColumn("Parent Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Gender");

        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size and style for table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        addButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase button font size
        updateButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase button font size
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase button font size

        addButton.setBackground(new Color(255, 255, 153)); // Light yellow background for buttons
        updateButton.setBackground(new Color(255, 255, 153)); // Light yellow background for buttons
        deleteButton.setBackground(new Color(255, 255, 153)); // Light yellow background for buttons

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(tablePanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String disorder = disorderField.getText();
                String parentName = parentNameField.getText();
                String address = addressField.getText();
                String gender = (String) genderComboBox.getSelectedItem();

                Student student = new Student(name, disorder, parentName, address, gender);
                addStudent(student);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a student to update", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = nameField.getText();
                String disorder = disorderField.getText();
                String parentName = parentNameField.getText();
                String address = addressField.getText();
                String gender = (String) genderComboBox.getSelectedItem();

                Student student = new Student(name, disorder, parentName, address, gender);
                updateStudent(selectedRow, student);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a student to delete", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                deleteStudent(selectedRow);
            }
        });

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Increased frame size for better visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private void addStudent(Student student) {
        studentRecords.put(student.getName(), student);
        updateTable();
        clearFields();
    }

    private void updateStudent(int row, Student student) {
        String oldName = studentTable.getValueAt(row, 0).toString();
        studentRecords.remove(oldName);
        studentRecords.put(student.getName(), student);
        updateTable();
        clearFields();
    }

    private void deleteStudent(int row) {
        String name = studentTable.getValueAt(row, 0).toString();
        studentRecords.remove(name);
        updateTable();
        clearFields();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Student student : studentRecords.values()) {
            Vector<String> row = new Vector<>();
            row.add(student.getName());
            row.add(student.getDisorder());
            row.add(student.getParentName());
            row.add(student.getAddress());
            row.add(student.getGender());
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        nameField.setText("");
        disorderField.setText("");
        parentNameField.setText("");
        addressField.setText("");
        genderComboBox.setSelectedIndex(0);
    }

    public JPanel getMainPanel() {
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem();
            }
        });
    }
}
