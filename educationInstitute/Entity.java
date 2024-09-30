package educationInstitute;

public abstract class Entity<T> {
    protected T id;
    protected String name;

    public Entity(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void printDetails();

    //public abstract Entity<T> updateEntity(String name, List)
}
