
package chickenfarmsimulator_model;

/**
 *
 * @author Adrian Toapanta
 */
public class Chicken {
    private int id;
    private String name;
    private String Color;
    private int age;
    private boolean isMolting;
    @Override

    public String toString() {
        return "chicken{" + "id=" + id + ", name=" + name + ", Color=" + Color + ", age=" + age + ", isMolting=" + isMolting + '}';
    }
    
public void setId(int id)   {
        this.id =id; 
} 

public int getId(){
    return id;
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
     * @return the Color
     */
    public String getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(String Color) {
        this.Color = Color;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }
}

