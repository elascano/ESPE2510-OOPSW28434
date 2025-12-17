

package ec.edu.espe.q61_70.model;

/**
 *
 * @author Thais Santorum
 * 
 */
public class Actor {
    private int id;
    private String name;
    private float monthlySalary;
    private float yearlySalary;

    public Actor(int id, String name, float monthlySalary, float yearlySalary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = monthlySalary;
        this.yearlySalary = yearlySalary;
    }

    @Override
    public String toString() {
        return "Actor{" + "id=" + id + ", name=" + name + ", monthlySalary=" + monthlySalary + ", yearlySalary=" + yearlySalary + '}';
    }

    
    
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the monthlySalary
     */
    public float getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * @param monthlySalary the monthlySalary to set
     */
    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     * @return the yearlySalary
     */
    public float getYearlySalary() {
        return yearlySalary;
    }

    /**
     * @param yearlySalary the yearlySalary to set
     */
    public void setYearlySalary(float yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
    
    
    
    
    

}
