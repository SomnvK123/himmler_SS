import Entity.Student;
import Manager.StudentManager;

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
        System.out.println("\nEntity.Student Management System");
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

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static LocalDate inputDateOfBirth() {
        System.out.print("Enter date of birth (dd-MM-yyyy): ");
        String dobString = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dobString, formatter);
    }

    private static double inputAverageScore() {
        //check average score >= 0 || <= 10
        double averageScore;
        while (true) {
            System.out.print("Enter average score: ");
            try {
                averageScore = Double.parseDouble(sc.nextLine());
                if (averageScore < 0 || averageScore > 10) {
                    System.out.println("Average score must be >= 0 || <= 10. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return averageScore;
    }

    private static void checkStudent(String studentId) {
        Student existingStudent = students.searchStudentByID(studentId);
        if (existingStudent != null) {
            System.out.println("Entity.Student with ID " + studentId + " already exists.");
            return;
        }
    }

    private static void addStudent() {
        try {
            //input data
            String studentId = getInput("Enter student ID: ");
            //check studentID exist
            checkStudent(studentId);
            // input
            String fullName = getInput("Enter student full name: ");
            LocalDate dateOfBirth = inputDateOfBirth();
            double averageScore = inputAverageScore();

            //add student function
            Student student = new Student(studentId, fullName, dateOfBirth, averageScore);
            students.addStudent(student);
            System.out.println("Entity.Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        //input data
        String studentId = getInput("Enter student ID: ");

        //check studentID exist
        checkStudent(studentId);
        // input
        String fullName = getInput("Enter student full name: ");
        LocalDate dateOfBirth = inputDateOfBirth();
        double averageScore = inputAverageScore();
        // update student
        Student student = students.updateStudent(studentId, fullName, dateOfBirth, averageScore);
        if (student != null) {
            System.out.println("Student updated successfully!");
            students.displayStudentDetails(student);
            } else {
                System.out.println("Student not found.");
            }
    }

    private static void deleteStudent() {
        String studentId = getInput("Enter student ID: ");
        if (students.deleteStudent(studentId)) {
            System.out.println("Student deleted successfully!");
            students.listAllStudents();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByID() {
        String studentId = getInput("Enter student ID: ");
        Student student = students.searchStudentByID(studentId);
        // Tìm kiếm sinh viên theo ID
        if (student != null) {
            System.out.println("Student found!");
            // In ra thông tin sinh viên
            students.displayStudentDetails(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByName() {
        String fullName = getInput("Enter student full name: ");

        Student stu = students.searchStudentByFullName(fullName);
        if (stu != null) {
            System.out.println("Student found!");
            students.displayStudentDetails(stu);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displaySortMenu() {
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }


    private static void sortStudentByAverageScore() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sort by Average Score ===");
            displaySortMenu();

            int choiceAve;
            try {
                choiceAve = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 2.");
                continue;
            }

            switch (choiceAve) {
                case 1: {
                    students.sortStudentByAverageScore(true);
                    System.out.println("Sorted by average score (ascending):");
                    students.listAllStudents();
                    break;
                }
                case 2: {
                    students.sortStudentByAverageScore(false);
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
            displaySortMenu();
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
