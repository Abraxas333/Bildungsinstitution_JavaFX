package Main;

import Database.DataLoader;
import educationInstitute.Entity;
import gui.GraphicUserInput;
import gui.GraphicUserOutput;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import educationInstitute.Lecturer;
import educationInstitute.Student;
import educationInstitute.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    // Static entityCollections for global access
    private static HashMap<String, List<? extends Entity<?>>> entityCollections = new HashMap<>();
    private List<Student> students = new ArrayList<>();
    private List<Lecturer> lecturers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public Main() {
        // Initialize DataLoader and load entities
        DataLoader.getEntity();  // Loads students into entityCollections from the database
    }

    // Getters for the static HashMap and entity lists
    public static HashMap<String, List<? extends Entity<?>>> getEntityCollections() {
        return entityCollections;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        // Create a new graphicUserInput and GraphicUserOutput
        GraphicUserInput guiInput = new GraphicUserInput();
        GraphicUserOutput guiOutput = new GraphicUserOutput();

        // Create a starting stage with options for adding Student, Lecturer, or Course
        primaryStage.setTitle("Main Menu");

        // Create buttons to navigate to different forms
        Button studentButton = new Button("Add Student");
        Button lecturerButton = new Button("Add Lecturer");
        Button courseButton = new Button("Add Course");
        Button updateStudent = new Button("Update Student");
        Button updateLecturer = new Button("Update Lecturer");
        Button updateCourse = new Button("Update Course");

        // Create a layout for the main menu
        GridPane mainGrid = new GridPane();
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.setPadding(new Insets(20, 20, 20, 20));
        mainGrid.setAlignment(Pos.CENTER);
        // Add buttons to the main grid
        mainGrid.add(studentButton, 0, 0);
        mainGrid.add(lecturerButton, 0, 1);
        mainGrid.add(courseButton, 0, 2);
        mainGrid.add(updateStudent, 0, 3);
        mainGrid.add(updateLecturer, 0, 4);
        mainGrid.add(updateCourse, 0, 5);

        GridPane.setHalignment(studentButton, HPos.CENTER);
        GridPane.setHalignment(lecturerButton, HPos.CENTER);
        GridPane.setHalignment(courseButton, HPos.CENTER);
        GridPane.setHalignment(updateStudent, HPos.CENTER);
        GridPane.setHalignment(updateLecturer, HPos.CENTER);
        GridPane.setHalignment(updateCourse, HPos.CENTER);

        // Set the action for the create entity buttons to open the respective form
        studentButton.setOnAction(e -> {
            Stage studentStage = new Stage();
            GraphicUserInput.openStudentForm(studentStage, entityCollections);
        });

        lecturerButton.setOnAction(e -> {
            Stage lecturerStage = new Stage();
            GraphicUserInput.openLecturerForm(lecturerStage, entityCollections);
        });

        courseButton.setOnAction(e -> {
            Stage courseStage = new Stage();
            GraphicUserInput.openCourseForm(courseStage, entityCollections);
        });

        // Set the action for the update entity buttons to open the respective form
        updateStudent.setOnAction(e -> {
            try {
                Stage updateStudentStage = new Stage();
                GraphicUserOutput.openUpdateDialogueStudent(updateStudentStage, "students", entityCollections);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        updateLecturer.setOnAction(e -> {
            try {
                Stage updateLecturerStage = new Stage();
                GraphicUserOutput.openUpdateDialogueLecturer(updateLecturerStage, "lecturers", entityCollections);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        updateCourse.setOnAction(e -> {
            try {
                Stage updateCourseStage = new Stage();
                GraphicUserOutput.openUpdateDialogueCourse(updateCourseStage, "courses", entityCollections);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Create a scene and display it
        Scene mainScene = new Scene(mainGrid, 300, 250);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }
}
