package gui;

import educationInstitute.Entity;
import educationInstitute.Student;
import educationInstitute.Lecturer;
import educationInstitute.Course;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GraphicUserOutput {

    public static void openUpdateDialogueStudent(Stage updateStage, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        Button update = new Button("Update");

        Scene scene = new Scene(grid, 600, 400);
        updateStage.setScene(scene);
        updateStage.setTitle("Select Entity to Update");
        updateStage.show();

        ToggleGroup tg = new ToggleGroup();

        if (entityCollections.containsKey(entity)) {
            List<? extends Entity<?>> entityList = entityCollections.get(entity);
            int row = 0;

            for (Entity<?> e : entityList) {
                RadioButton entityRadioButton = new RadioButton(e.getName());
                entityRadioButton.setToggleGroup(tg);
                grid.addRow(row++, entityRadioButton);
            }

            grid.add(update, 1, row);

            update.setOnAction(event -> {
                RadioButton selectedOption = (RadioButton) tg.getSelectedToggle();
                if (selectedOption != null) {
                    openUpdateStudentForm(new Stage(), selectedOption.getText(), entity, entityCollections);
                }
            });
        }
    }

    public static void openUpdateStudentForm(Stage updateEntity, String entityName, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        if (entity.equals("students")) {
            List<Student> students = (List<Student>) entityCollections.get(entity);

            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(entityName)) {
                    TextField newId = new TextField(String.valueOf(student.getId()));
                    TextField newName = new TextField(student.getName());
                    TextField newMajor = new TextField(student.getMajor());
                    TextArea newCourses = new TextArea(String.join("\n", student.getCourse()));

                    grid.addRow(0, new Label("ID:"), newId);
                    grid.addRow(1, new Label("Name:"), newName);
                    grid.addRow(2, new Label("Major:"), newMajor);
                    grid.addRow(3, new Label("Courses:"), newCourses);

                    Button saveButton = new Button("Save");
                    grid.add(saveButton, 1, 4);

                    saveButton.setOnAction(e -> {
                        try {
                            int id = Integer.parseInt(newId.getText());
                            student.setId(id);
                            student.setName(newName.getText());
                            student.setMajor(newMajor.getText());
                            student.setCourses(Arrays.asList(newCourses.getText().split("\n")));
                            updateEntity.close();
                        } catch (NumberFormatException ex) {
                            System.out.println("Please enter a valid integer for ID.");
                        }
                    });
                }
            }
        }

        Scene scene = new Scene(grid, 600, 400);
        updateEntity.setScene(scene);
        updateEntity.setTitle("Update Entity");
        updateEntity.show();
    }

    // New method for updating Lecturers
    public static void openUpdateDialogueLecturer(Stage updateStage, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        Button update = new Button("Update");

        Scene scene = new Scene(grid, 600, 400);
        updateStage.setScene(scene);
        updateStage.setTitle("Select Lecturer to Update");
        updateStage.show();

        ToggleGroup tg = new ToggleGroup();

        if (entityCollections.containsKey(entity)) {
            List<? extends Entity<?>> entityList = entityCollections.get(entity);
            int row = 0;

            for (Entity<?> e : entityList) {
                RadioButton entityRadioButton = new RadioButton(e.getName());
                entityRadioButton.setToggleGroup(tg);
                grid.addRow(row++, entityRadioButton);
            }

            grid.add(update, 1, row);

            update.setOnAction(event -> {
                RadioButton selectedOption = (RadioButton) tg.getSelectedToggle();
                if (selectedOption != null) {
                    openUpdateLecturerForm(new Stage(), selectedOption.getText(), entity, entityCollections);
                }
            });
        }
    }

    public static void openUpdateLecturerForm(Stage updateEntity, String entityName, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        if (entity.equals("lecturers")) {
            List<Lecturer> lecturers = (List<Lecturer>) entityCollections.get(entity);

            for (Lecturer lecturer : lecturers) {
                if (lecturer.getName().equalsIgnoreCase(entityName)) {
                    TextField newId = new TextField(String.valueOf(lecturer.getId()));
                    TextField newName = new TextField(lecturer.getName());
                    TextField newDepartment = new TextField(lecturer.getDepartment());

                    grid.addRow(0, new Label("ID:"), newId);
                    grid.addRow(1, new Label("Name:"), newName);
                    grid.addRow(2, new Label("Department:"), newDepartment);

                    Button saveButton = new Button("Save");
                    grid.add(saveButton, 1, 3);

                    saveButton.setOnAction(e -> {
                        try {
                            int id = Integer.parseInt(newId.getText());
                            lecturer.setId(id);
                            lecturer.setName(newName.getText());
                            lecturer.setDepartment(newDepartment.getText());
                            updateEntity.close();
                        } catch (NumberFormatException ex) {
                            System.out.println("Please enter a valid integer for ID.");
                        }
                    });
                }
            }
        }

        Scene scene = new Scene(grid, 600, 400);
        updateEntity.setScene(scene);
        updateEntity.setTitle("Update Lecturer");
        updateEntity.show();
    }

    // New method for updating Courses
    public static void openUpdateDialogueCourse(Stage updateStage, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        Button update = new Button("Update");

        Scene scene = new Scene(grid, 600, 400);
        updateStage.setScene(scene);
        updateStage.setTitle("Select Course to Update");
        updateStage.show();

        ToggleGroup tg = new ToggleGroup();

        if (entityCollections.containsKey(entity)) {
            List<? extends Entity<?>> entityList = entityCollections.get(entity);
            int row = 0;

            for (Entity<?> e : entityList) {
                RadioButton entityRadioButton = new RadioButton(e.getName());
                entityRadioButton.setToggleGroup(tg);
                grid.addRow(row++, entityRadioButton);
            }

            grid.add(update, 1, row);

            update.setOnAction(event -> {
                RadioButton selectedOption = (RadioButton) tg.getSelectedToggle();
                if (selectedOption != null) {
                    openUpdateCourseForm(new Stage(), selectedOption.getText(), entity, entityCollections);
                }
            });
        }
    }

    public static void openUpdateCourseForm(Stage updateEntity, String entityName, String entity, HashMap<String, List<? extends Entity<?>>> entityCollections) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        if (entity.equals("courses")) {
            List<Course> courses = (List<Course>) entityCollections.get(entity);

            for (Course course : courses) {
                if (course.getName().equalsIgnoreCase(entityName)) {
                    TextField newId = new TextField(String.valueOf(course.getId()));
                    TextField newName = new TextField(course.getName());
                    TextField newLecturerName = new TextField(course.getLecturerName());
                    TextArea newParticipants = new TextArea(String.join("\n", course.getParticipants()));

                    grid.addRow(0, new Label("ID:"), newId);
                    grid.addRow(1, new Label("Name:"), newName);
                    grid.addRow(2, new Label("Lecturer Name:"), newLecturerName);
                    grid.addRow(3, new Label("Participants:"), newParticipants);

                    Button saveButton = new Button("Save");
                    grid.add(saveButton, 1, 4);

                    saveButton.setOnAction(e -> {
                        try {
                            course.setId(newId.getText());
                            course.setName(newName.getText());
                            course.setLecturerName(newLecturerName.getText());
                            course.setParticipants(Arrays.asList(newParticipants.getText().split("\n")));
                            updateEntity.close();
                        } catch (NumberFormatException ex) {
                            System.out.println("Please enter a valid integer for ID.");
                        }
                    });
                }
            }
        }

        Scene scene = new Scene(grid, 600, 400);
        updateEntity.setScene(scene);
        updateEntity.setTitle("Update Course");
        updateEntity.show();
    }
}
