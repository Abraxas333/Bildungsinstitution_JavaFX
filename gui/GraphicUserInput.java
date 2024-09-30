package gui;

import Database.DataSaver;
import educationInstitute.Entity;
import educationInstitute.Student;
import educationInstitute.Lecturer;
import educationInstitute.Course;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.scene.layout.VBox;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GraphicUserInput {

    // No constructor needed as we're not passing the Main instance

    // Opens the Student form and works with entityCollections
    public static void openStudentForm(Stage stage, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        // Create the form for adding a new student
        GridPane grid = new GridPane();
        grid.setVgap(5);  // Reduce vertical spacing between rows
        grid.setHgap(10); // Keep some horizontal spacing between label and field

        // Create TextFields and TextArea for input
        TextField studentIdField = new TextField();
        TextField studentNameField = new TextField();
        TextField studentMajorField = new TextField();
        TextArea studentCoursesArea = new TextArea();

        Button submitButton = new Button("Submit");

        // Add components to the grid
        grid.addRow(0, new Label("Student ID:"), studentIdField);
        grid.addRow(1, new Label("Name:"), studentNameField);
        grid.addRow(2, new Label("Major:"), studentMajorField);

        // Use CSS to adjust the position of the "Comma-separated" label
        Label coursesLabel = new Label("Courses:");
        Label commaSeparatedLabel = new Label("(Comma-separated)");
        commaSeparatedLabel.setStyle("-fx-padding: -5 0 0 0;");  // Move closer to the line

        // Add both labels in a VBox with tighter control
        VBox coursesBox = new VBox(2);  // 2 pixels of vertical space between the two labels
        coursesBox.getChildren().addAll(coursesLabel, commaSeparatedLabel);

        // Add the course label box and text area
        grid.addRow(3, coursesBox, studentCoursesArea);
        grid.add(submitButton, 1, 4);

        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(studentIdField.getText());
            String name = studentNameField.getText();
            String major = studentMajorField.getText();
            List<String> courses = List.of(studentCoursesArea.getText().split(","));

            // Add new student to entityCollections
            Student student = new Student(id, name, major, courses);
            if (entityCollections.containsKey("students")) {
                List<Student> students = (List<Student>) entityCollections.get("students");
                students.add(student);
            } else {
                entityCollections.put("students", new ArrayList<>(List.of(student)));

            }
            try {
                DataSaver.saveEntity(student);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });

        // Reduce window size and display the stage
        Scene scene = new Scene(grid, 600, 300);  // Smaller window size
        stage.setScene(scene);
        stage.setTitle("Add Student");
        stage.show();
    }


    public static void openLecturerForm(Stage stage, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        // Create the form for adding a new lecturer
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField lecturerIdField = new TextField();
        TextField lecturerNameField = new TextField();
        TextField lecturerDeptField = new TextField();

        Button submitButton = new Button("Submit");

        grid.addRow(0, new Label("Lecturer ID:"), lecturerIdField);
        grid.addRow(1, new Label("Name:"), lecturerNameField);
        grid.addRow(2, new Label("Department:"), lecturerDeptField);
        grid.add(submitButton, 1, 3);

        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(lecturerIdField.getText());
            String name = lecturerNameField.getText();
            String department = lecturerDeptField.getText();

            // Add new lecturer to entityCollections
            Lecturer lecturer = new Lecturer(id, name, department);
            if (entityCollections.containsKey("lecturers")) {
                List<Lecturer> lecturers = (List<Lecturer>) entityCollections.get("lecturers");
                lecturers.add(lecturer);
            } else {
                entityCollections.put("lecturers", List.of(lecturer));
            }
            try {
                DataSaver.saveEntity(lecturer);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Add Lecturer");
        stage.show();
    }

    public static void openCourseForm(Stage stage, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        // Create the form for adding a new course
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField courseIdField = new TextField();
        TextField courseNameField = new TextField();
        TextField lecturerNameField = new TextField();
        TextArea courseParticipantsField = new TextArea();
        Button submitButton = new Button("Submit");

        grid.addRow(0, new Label("Course ID:"), courseIdField);
        grid.addRow(1, new Label("Name:"), courseNameField);
        grid.addRow(2, new Label("Lecturer:"), lecturerNameField);
        grid.addRow(3, new Label("Course Participants"), courseParticipantsField);
        grid.add(submitButton, 1, 4);

        submitButton.setOnAction(e -> {
            String id = courseIdField.getText();
            String name = courseNameField.getText();
            String lecturerName = lecturerNameField.getText();
            List<String> participants = List.of(courseParticipantsField.getText().split(","));

            // Create a new Course object
            Course course = new Course(id, name, lecturerName, participants);

            // Add the new course to the entityCollections
            if (entityCollections.containsKey("courses")) {
                List<Course> courses = (List<Course>) entityCollections.get("courses");
                courses.add(course);
            } else {
                List<Course> courses = new ArrayList<>();
                courses.add(course);
                entityCollections.put("courses", courses);
            }
            try {
                DataSaver.saveEntity(course);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            // Close the form stage after submission
            stage.close();
        });
        Scene scene = new Scene(grid, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Add Course");
        stage.show();
    }
}
