package educationInstitute;

import java.util.List;

public class Student extends educationInstitute.Entity<Integer> {
    private String major;
    private List<String> courses;


    public Student(Integer id, String name, String major, List<String> courses) {
        super(id, name);
        this.major = major;
        this.courses = courses;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<String> getCourse() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public void printDetails() {

    }

    /*@Override
    public Entity<Integer> updateEntity(String name) {
        return null;
    }*/
}

