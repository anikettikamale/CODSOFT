import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Student management system is empty.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void saveToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0];
                    int rollNumber = Integer.parseInt(data[1]);
                    String grade = data[2];
                    students.add(new Student(name, rollNumber, grade));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}

public class task3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
        String filePath = "students.txt";

       
        studentManagementSystem.loadFromFile(filePath);

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save students to file");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    
                    if (!name.isEmpty() && !grade.isEmpty()) {
                        studentManagementSystem.addStudent(new Student(name, rollNumber, grade));
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Invalid input. Name and grade are required fields.");
                    }
                    break;

                case "2":
                    System.out.print("Enter the roll number of the student to remove: ");
                    rollNumber = Integer.parseInt(scanner.nextLine());
                    Student studentToRemove = studentManagementSystem.searchStudent(rollNumber);
                    if (studentToRemove != null) {
                        studentManagementSystem.removeStudent(studentToRemove);
                        System.out.println(studentToRemove.getName() + " removed from the student management system.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter the roll number of the student to search for: ");
                    rollNumber = Integer.parseInt(scanner.nextLine());
                    Student studentFound = studentManagementSystem.searchStudent(rollNumber);
                    if (studentFound != null) {
                        System.out.println("Student found:");
                        System.out.println(studentFound);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "4":
                    studentManagementSystem.displayAllStudents();
                    break;

                case "5":
                    studentManagementSystem.saveToFile(filePath);
                    System.out.println("Students saved to file.");
                    break;

                case "6":
                    studentManagementSystem.saveToFile(filePath); 
                    System.out.println("Exiting the Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}
