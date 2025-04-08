import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.format.DateTimeFormatter;


public class StudentManager {
    private List<Student> students = new ArrayList<>();

    // Thêm sinh viên
    public void addStudent(Student student) {
        students.add(student);
    }

    // Cập nhật thông tin sinh viên
    public boolean updateStudent(String studentId, String fullName, LocalDate dateOfBirth, double averageScore) {
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) {
                s.setFullName(fullName);
                s.setDateOfBirth(dateOfBirth);
                s.setAverageScore(averageScore);
                return true;
            }
        }
        return false;
    }

    // Xóa sinh viên
    public boolean deleteStudent(String studentId) {
        return students.removeIf(s -> studentId.equals(s.getStudentId()));
    }

    // Liệt kê tất cả sinh viên
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty");
        } else {
            System.out.printf("%-10s %-20s %-15s %-10s\n", "ID", "Full Name", "Date of Birth", "Score");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Student s : students) {
                System.out.printf("%-10s %-20s %-15s %-10s\n",
                        s.getStudentId(),
                        s.getFullName(),
                        s.getDateOfBirth().format(formatter),
                        s.getAverageScore());
            }
        }
    }

    public Student searchStudentByID(String studentId) {
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) {
                return s;
            }
        }
        return null;
    }

    public Student searchStudentByFullName(String fullName) {
        for (Student s : students) {
            if (s.getFullName().toLowerCase().contains(fullName.toLowerCase())) {
                return s;
            }
        }
        return null;
    }

    public void sortStudentByAverageScore(boolean ascending) {
        if (ascending) {
            students.sort(Comparator.comparing(Student::getAverageScore));
        } else {
            students.sort(Comparator.comparing(Student::getAverageScore).reversed());
        }
    }

    public void sortStudentByName(boolean ascending) {
        if (ascending) {
            students.sort(Comparator.comparing(Student::getFullName));
        } else {
            students.sort(Comparator.comparing(Student::getFullName).reversed());
        }
    }
}


