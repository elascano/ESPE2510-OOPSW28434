
package model;

/**
 *
 * @author Toapanta Adrian
 */
public class Student {
 
    private final int id; 
    private final String name;
    private final String major;
    private final double gpa;


    public Student(int id, String name, String major, double gpa) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
    }


    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}