import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager students = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice;
            try {
                choice = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 8.");
                continue;
            }

            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    students.listAllStudents();
                    break;
                case 5:
                    searchStudentByID();
                    break;
                case 6:
                    searchStudentByName();
                    break;
                case 7:
                    sortStudentByAverageScore();
                    break;
                case 8:
                    sortStudentByName();
                    break;
                case 0:
                    System.out.println("End program");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. List All Students");
        System.out.println("5. Search Student By ID");
        System.out.println("6. Search Student By Name");
        System.out.println("7. Sort Student By Average Score");
        System.out.println("8. Sort Student By Full Name");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        try {

            //input data
            System.out.print("Enter student ID: ");
            String studentId = sc.nextLine();

            //check studentID exist
            Student existingStudent = students.searchStudentByID(studentId);
            if (existingStudent != null) {
                System.out.println("Student with ID " + studentId + " already exists.");
                return;
            }

            System.out.print("Enter full name: ");
            String fullName = sc.nextLine();

            System.out.print("Enter date of birth (dd-MM-yyyy): ");
            String dobString = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);

            //check average score >= 0
            double averageScore;
            while (true) {
                System.out.print("Enter average score: ");
                try {
                    averageScore = Double.parseDouble(sc.nextLine());
                    if (averageScore < 0) {
                        System.out.println("Average score must be >= 0. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            //add student function
            Student student = new Student(studentId, fullName, dateOfBirth, averageScore);
            students.addStudent(student);
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }


    private static void updateStudent() {

        //input data
        System.out.print("Enter student ID to update: ");
        String studentId = sc.nextLine();

        //check studentID exist
        Student existingStudent = students.searchStudentByID(studentId);
        if (existingStudent == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.print("Enter new full name: ");
        String fullName = sc.nextLine();

        System.out.print("Enter new date of birth (dd-MM-yyyy): ");
        String dobString = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);

            //check input average score >= 0
            double averageScore;
            while (true) {
                System.out.print("Enter new average score: ");
                try {
                    averageScore = Double.parseDouble(sc.nextLine());
                    if (averageScore < 0) {
                        System.out.println("Average score must be >= 0. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            if (students.updateStudent(studentId, fullName, dateOfBirth, averageScore)) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String studentId = sc.nextLine();
        if (students.deleteStudent(studentId)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByID() {
        System.out.print("Enter student ID to search: ");
        String studentId = sc.nextLine();

        Student student = students.searchStudentByID(studentId);
        // Tìm kiếm sinh viên theo ID
        if (student != null) {
            System.out.println("Student found!");
            // In ra thông tin sinh viên
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Full Name: " + student.getFullName());
            System.out.println("Date of Birth: " + student.getDateOfBirth().toString());
            System.out.println("Average Score: " + student.getAverageScore());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByName() {
        System.out.print("Enter fullname of student to search: ");
        String fullName = sc.nextLine();

        Student stu = students.searchStudentByFullName(fullName);
        if (stu != null) {
            System.out.println("Student found!");
            System.out.println("Student ID: " + stu.getStudentId());
            System.out.println("Full Name: " + stu.getFullName());
            System.out.println("Date of Birth: " + stu.getDateOfBirth().toString());
            System.out.println("Average Score: " + stu.getAverageScore());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void sortStudentByAverageScore() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sort by Average Score ===");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choiceAve;
            try {
                choiceAve = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 2.");
                continue;
            }

            switch (choiceAve) {
                case 1: {
                    students.sortStudentByAverageScore(true); // Tăng dần
                    System.out.println("Sorted by average score (ascending):");
                    students.listAllStudents();
                    break;
                }
                case 2: {
                    students.sortStudentByAverageScore(false); // Giảm dần
                    System.out.println("Sorted by average score (descending):");
                    students.listAllStudents();
                    break;
                }
                case 0:
                    return;
                default:
                    System.out.println("Error! Please enter a number between 0 and 2.");
            }
        }
    }

    private static void sortStudentByName() {

        while (true) {
            System.out.println("\n=== Sort by Name ===");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choiceName;
            try {
                choiceName = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 2.");
                continue;
            }

            switch (choiceName) {
                case 1: {
                    students.sortStudentByName(true);
                    System.out.println("Sorted by name (ascending):");
                    students.listAllStudents();
                    break;
                }
                case 2: {
                    students.sortStudentByName(false);
                    System.out.println("Sorted by name (descending):");
                    students.listAllStudents();
                    break;
                }
                case 0:
                    return;
                default:
                    System.out.println("Error! Please enter a number between 0 and 2.");
            }
        }
    }
}
