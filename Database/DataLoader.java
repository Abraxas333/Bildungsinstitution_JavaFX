package Database;

import Main.Main;
import educationInstitute.Entity;
import educationInstitute.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    public static void getEntity() {
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                String query1 = "SELECT * FROM students";
                try (PreparedStatement stmt1 = conn.prepareStatement(query1);
                     ResultSet rs1 = stmt1.executeQuery()) {

                    List<Student> students = new ArrayList<>();

                    while (rs1.next()) {
                        int id = rs1.getInt("id");
                        String name = rs1.getString("name");
                        String major = rs1.getString("major");

                        List<String> studentCourses = new ArrayList<>();
                        String query2 = "SELECT name FROM courses WHERE id = ?";
                        try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                            stmt2.setInt(1, id);
                            try (ResultSet rs2 = stmt2.executeQuery()) {
                                while (rs2.next()) {
                                    studentCourses.add(rs2.getString("name"));
                                }
                            }
                        }

                        students.add(new Student(id, name, major, studentCourses));
                    }

                    // Access the static entityCollections from Main
                    HashMap<String, List<? extends Entity<?>>> entityCollections = Main.getEntityCollections();

                    // Add the loaded students to entityCollections
                    entityCollections.put("students", students);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while loading entities: " + e.getMessage());
        }
    }
}
