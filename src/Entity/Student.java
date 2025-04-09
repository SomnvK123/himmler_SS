package Entity;

import java.time.LocalDate;

public class Student {
    private String studentId;
    private String fullName;
    private LocalDate dateOfBirth;
    private double averageScore;

    public Student(){}

    public Student(String studentId, String fullName, LocalDate dateOfBirth, double averageScore) {
    this.studentId = studentId;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.averageScore = averageScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
