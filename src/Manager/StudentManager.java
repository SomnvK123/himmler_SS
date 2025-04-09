package Manager;

import Entity.Student;

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
    public Student updateStudent(String studentId, String fullName, LocalDate dateOfBirth, double averageScore) {
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) {
                s.setFullName(fullName);
                s.setDateOfBirth(dateOfBirth);
                s.setAverageScore(averageScore);
                return s;
            }
        }
        return null;
    }

    // Xóa sinh viên
    public boolean deleteStudent(String studentId) {
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    // List all student
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Entity.Student list is empty");
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

    //display student detail
    public void displayStudentDetails(Student s) {
        if (s == null) {
            System.out.println("Entity.Student " + s + " not found");
            return;
        }
        System.out.println("Entity.Student ID found: " + s.getStudentId());

        System.out.printf("%-10s %-20s %-15s %-10s\n", "ID", "Full Name", "Date of Birth", "Score");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("%-10s %-20s %-15s %-10s\n",
                    s.getStudentId(),
                    s.getFullName(),
                    s.getDateOfBirth().format(formatter),
                    s.getAverageScore());
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


