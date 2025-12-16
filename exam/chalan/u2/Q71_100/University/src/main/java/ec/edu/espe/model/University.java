package ec.edu.espe.model;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class University {
    private String id;
    private String name;
    private int age;
    private int foundationYear;

    public University(String id, String name, int age, int foundationYear) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.foundationYear = foundationYear;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getFoundationYear() {
        return foundationYear;
    }
}