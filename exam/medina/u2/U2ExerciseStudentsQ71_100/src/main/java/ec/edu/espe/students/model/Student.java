package ec.edu.espe.students.model;

import org.bson.types.ObjectId;

public class Student {

    private ObjectId id;
    private String name;
    private int age;
    private String career;

    public Student() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCareer() {
        return career;
    }
}
