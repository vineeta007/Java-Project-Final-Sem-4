import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StudentManagementSystem extends Application {

    private BorderPane root;
    private Scene scene;
    private Map<String, User> users = new HashMap<>();
    private User loggedInUser;
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Marks> studentMarks = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        // Create layout components
        root = new BorderPane();
        scene = new Scene(root);

        // Set up sign up/login view
        createSignUpLoginView();

        // Show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createSignUpLoginView() {
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(50));

        Label titleLabel = new Label("Welcome to Student Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-font-size: 18px;");
        signUpButton.setOnAction(event -> {
            // Handle sign up button click
            showSignUpView();
        });

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 18px;");
        loginButton.setOnAction(event -> {
            // Handle login button click
            showLoginView();
        });

        vbox.getChildren().addAll(titleLabel, signUpButton, loginButton);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        root.setCenter(vbox);
    }

    private void showSignUpView() {
        VBox signUpBox = new VBox();
        signUpBox.setSpacing(10);
        signUpBox.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Teacher", "Parent", "Student");

        signUpBox.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                new Label("Role:"), roleComboBox,
                new Button("Sign Up")
        );

        root.setCenter(signUpBox);

        // Handle sign up button click
        ((Button) signUpBox.getChildren().get(signUpBox.getChildren().size() - 1)).setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue();
            signUp(username, password, role);
            // After sign up, show login view
            showLoginView();
        });
    }

    private void showLoginView() {
        VBox loginBox = new VBox();
        loginBox.setSpacing(10);
        loginBox.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        loginBox.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                new Button("Login")
        );

        root.setCenter(loginBox);

        // Handle login button click
        ((Button) loginBox.getChildren().get(loginBox.getChildren().size() - 1)).setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (login(username, password)) {
                // If login successful, show dashboard
                showDashboard();
            } else {
                // If login failed, show error message
                showError("Invalid username or password.");
            }
        });
    }

    private void signUp(String username, String password, String role) {
        users.put(username, new User(username, password, role));
    }

    private boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    private void showDashboard() {
        VBox dashboardBox = new VBox();
        dashboardBox.setSpacing(10);
        dashboardBox.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welcome, " + loggedInUser.getUsername() + "!");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            loggedInUser = null;
            createSignUpLoginView();
        });

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(event -> {
            showAddStudentView();
        });

        Button updateStudentButton = new Button("Update Student");
        updateStudentButton.setOnAction(event -> {
            showUpdateStudentView();
        });

        Button deleteStudentButton = new Button("Delete Student");
        deleteStudentButton.setOnAction(event -> {
            showDeleteStudentView();
        });

        Button viewMarksButton = new Button("View Marks");
        viewMarksButton.setOnAction(event -> {
            showViewMarksView();
        });

        dashboardBox.getChildren().addAll(welcomeLabel, addStudentButton, updateStudentButton, deleteStudentButton, viewMarksButton, logoutButton);
        root.setCenter(dashboardBox);
    }

    private void showAddStudentView() {
        VBox addStudentBox = new VBox();
        addStudentBox.setSpacing(10);
        addStudentBox.setPadding(new Insets(20));

        TextField nameField = new TextField();
        TextField parentNameField = new TextField();
        TextField ageField = new TextField();
        TextField disorderField = new TextField();
        TextField addressField = new TextField();
        TextField genderField = new TextField();

        addStudentBox.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Parent Name:"), parentNameField,
                new Label("Age:"), ageField,
                new Label("Disorder:"), disorderField,
                new Label("Address:"), addressField,
                new Label("Gender:"), genderField,
                new Button("Add Student")
        );

        root.setCenter(addStudentBox);

        // Handle add student button click
        ((Button) addStudentBox.getChildren().get(addStudentBox.getChildren().size() - 1)).setOnAction(event -> {
            String name = nameField.getText();
            String parentName = parentNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String disorder = disorderField.getText();
            String address = addressField.getText();
            String gender = genderField.getText();
            addStudent(name, parentName, age, disorder, address, gender);
            // After adding student, show dashboard
            showDashboard();
        });
    }

    private void addStudent(String name, String parentName, int age, String disorder, String address, String gender) {
        String studentId = "S" + (students.size() + 1);
        students.put(studentId, new Student(studentId, name, parentName, age, disorder, address, gender));
    }

    private void showUpdateStudentView() {
        // Implement update student view
    }

    private void showDeleteStudentView() {
        // Implement delete student view
    }

    private void showViewMarksView() {
        VBox viewMarksBox = new VBox();
        viewMarksBox.setSpacing(10);
        viewMarksBox.setPadding(new Insets(20));

        TextField studentIdField = new TextField();

        viewMarksBox.getChildren().addAll(
                new Label("Student ID:"), studentIdField,
                new Button("View Report")
        );

        root.setCenter(viewMarksBox);

        // Handle view report button click
        ((Button) viewMarksBox.getChildren().get(viewMarksBox.getChildren().size() - 1)).setOnAction(event -> {
            String studentId = studentIdField.getText();
            if (students.containsKey(studentId)) {
                viewStudentMarks(studentId);
            } else {
                showError("Student not found.");
            }
        });
    }

    private void viewStudentMarks(String studentId) {
        Student student = students.get(studentId);
        if (studentMarks.containsKey(studentId)) {
            Marks marks = studentMarks.get(studentId);
            // Show marks report
            TextArea reportTextArea = new TextArea();
            reportTextArea.appendText("Student Name: " + student.getName() + "\n");
            reportTextArea.appendText("Attendance: " + marks.getAttendance() + "\n");
            reportTextArea.appendText("Subject Marks: \n");
            for (int i = 0; i < marks.getSubjectsMarks().length; i++) {
                reportTextArea.appendText("Subject " + (i + 1) + ": " + marks.getSubjectsMarks()[i] + "\n");
            }
            VBox reportBox = new VBox(reportTextArea);
            Stage reportStage = new Stage();
            reportStage.setScene(new Scene(reportBox, 400, 300));
            reportStage.setTitle("Marks Report");
            reportStage.show();
        } else {
            showError("Marks not available for this student.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class User {
        private String username;
        private String password;
        private String role;

        public User(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }

    class Student {
        private String id;
        private String name;
        private String parentName;
        private int age;
        private String disorder;
        private String address;
        private String gender;

        public Student(String id, String name, String parentName, int age, String disorder, String address, String gender) {
            this.id = id;
            this.name = name;
            this.parentName = parentName;
            this.age = age;
            this.disorder = disorder;
            this.address = address;
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getParentName() {
            return parentName;
        }

        public int getAge() {
            return age;
        }

        public String getDisorder() {
            return disorder;
        }

        public String getAddress() {
            return address;
        }

        public String getGender() {
            return gender;
        }
    }

    class Marks {
        private double attendance;
        private double[] subjectsMarks = new double[5];

        public Marks(double attendance, double[] subjectsMarks) {
            this.attendance = attendance;
            this.subjectsMarks = subjectsMarks;
        }

        public double getAttendance() {
            return attendance;
        }

        public double[] getSubjectsMarks() {
            return subjectsMarks;
        }
    }
}

