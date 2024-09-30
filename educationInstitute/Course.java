package educationInstitute;

import java.util.List;

public class Course extends educationInstitute.Entity<String> {

    private String lecturerName;
    private List<String> participants;

    public Course(String id, String name, String lecturerName, List<String> participants) {
        super(id, name);
        this.lecturerName = lecturerName;
        this.participants = participants;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public void printDetails() {

    }

    /*@Override
    public Entity<String> updateEntity(String name) {
        return null;
    }*/
}
