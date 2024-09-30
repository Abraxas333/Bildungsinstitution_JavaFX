package educationInstitute;

public class Lecturer extends educationInstitute.Entity<Integer> {

    private String department;

    public Lecturer(int id, String name, String department) {
        super(id, name);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void printDetails() {

    }

    /*@Override
    public Entity<Integer> updateEntity(String name) {
        return null;
    }*/
}