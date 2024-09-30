package Database;
import Main.Main;
import educationInstitute.Course;
import educationInstitute.Entity;
import educationInstitute.Lecturer;
import educationInstitute.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSaver {

    public static void saveEntity(Entity<?> entity) throws ClassNotFoundException {

        if (entity instanceof Student) {
            String query1 = "INSERT INTO students(id, name, major) VALUES(?,?,?)";
            String query2 = "INSERT INTO students_courses(id, course) VALUES(?,?)";

            try (Connection conn = DatabaseConnection.connect()){
                conn.setAutoCommit(false);

                PreparedStatement stmt1 = conn.prepareStatement(query1);
                stmt1.setInt(1, (int) entity.getId());
                stmt1.setString(2, entity.getName());
                stmt1.setString(3, ((Student) entity).getMajor());
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement(query2);
                for (String course : ((Student) entity).getCourse()) {
                    stmt2.setInt(1, (int) entity.getId());
                    stmt2.setString(2, course);
                    stmt2.executeUpdate();
                }

                conn.commit();
                System.out.println("New Student inserted successfully!");

            } catch (SQLException e) {
                System.err.println("Transaction rolled back due to error:" + e.getMessage());
            }
        }
        else if (entity instanceof Lecturer) {
            String query = "INSERT INTO lecturers(id, name, department) VALUES(?,?,?)";

            try (Connection conn = DatabaseConnection.connect()){
                conn.setAutoCommit(false);

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, (int) entity.getId());
                stmt.setString(2, entity.getName());
                stmt.setString(3, ((Lecturer) entity).getDepartment());
                stmt.executeUpdate();

                conn.commit();
                System.out.println("New Lecturer inserted successfully!");

            } catch (SQLException e) {
                System.err.println("Transaction rolled back due to error:" + e.getMessage());
            }
        }
        else if (entity instanceof Course) {
            String query1 = "INSERT INTO courses(id, name, lecturer) VALUES(?,?,?)";
            String query2 = "INSERT INTO courses_participants(id, participant) VALUES(?,?)"; // Correct column names

            List<Integer> participantsIds = new ArrayList<>();
            Map<String, Integer> participantIdMap = new HashMap<>(); // New map to store participants and their corresponding ids

            // Step 1: Retrieve participants' IDs from the students table
            String selectParticipant = "SELECT id FROM students WHERE name = ?";

            try (Connection conn = DatabaseConnection.connect()) {
                PreparedStatement stmt = conn.prepareStatement(selectParticipant);
                for (String participant : ((Course) entity).getParticipants()) {
                    stmt.setString(1, participant);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        int id = rs.getInt("id");
                        participantIdMap.put(participant, id);  // Store the participant name and ID in the map
                    }
                    rs.close();
                }
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error while retrieving participants IDs:" + e.getMessage());
            }

            // Step 2: Insert course and its participants into the courses_participants table
            try (Connection conn = DatabaseConnection.connect()) {
                conn.setAutoCommit(false);

                PreparedStatement stmt1 = conn.prepareStatement(query1);
                stmt1.setString(1, ((Course) entity).getId());
                stmt1.setString(2, entity.getName());
                stmt1.setString(3, ((Course) entity).getLecturerName());
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement(query2);
                List<String> participants = ((Course) entity).getParticipants();  // Get the participants list

                // Iterate over each participant and get their corresponding ID from the map
                for (String participant : participants) {
                    Integer id = participantIdMap.get(participant); // Get ID from map based on participant name
                    if (id != null) {
                        stmt2.setInt(1, id);  // Insert the correct student ID
                        stmt2.setString(2, participant);  // Insert the participant's name
                        stmt2.executeUpdate();
                    } else {
                        System.err.println("No ID found for participant: " + participant);
                    }
                }

                conn.commit();
                System.out.println("New Course inserted successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                try (Connection conn = DatabaseConnection.connect()) {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        }
    }
}
