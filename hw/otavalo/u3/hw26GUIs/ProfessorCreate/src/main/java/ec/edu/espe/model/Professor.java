package ec.edu.espe.model;

/**
 *
 * @author Arelys
 */
public class Professor {
    private String id;
    private String fullName;
    private String subject;
    private double salary;
    private double bonus;

    public Professor(String id, String fullName, String subject, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.subject = subject;
        this.salary = salary;
        this.bonus = salary * 0.05; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    
}
