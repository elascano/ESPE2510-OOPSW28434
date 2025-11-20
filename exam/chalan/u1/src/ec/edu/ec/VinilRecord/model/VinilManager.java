
package ec.edu.ec.VinilRecord.model;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class VinilManager {
    
        
    private int chickenId;
    private String name;
    private String age;
    private String breed;

    public VinilManager() {}

    public VinilManager(int chickenId, String name, String age, String breed) {
        this.chickenId = chickenId;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    // --- Getters y Setters ---
    public int getChickenId() { return chickenId; }
    public void setChickenId(int chickenId) { this.chickenId = chickenId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
}
    
    
    
    
