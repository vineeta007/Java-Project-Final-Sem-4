import java.util.*;

class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void loadStudents(List<String> studentData) {
        students.clear();
        for (String data : studentData) {
            String[] parts = data.split(",");
            if (parts.length == 3) {
                students.add(new Student(parts[0], Integer.parseInt(parts[1]), parts[2]));
            }
        }
    }

    public String getStudentsInfo() {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }
}
