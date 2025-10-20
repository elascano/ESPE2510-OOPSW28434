package ec_edu_espe_chickenfarmsimulator_model;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 * @version 0.1
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean itsMolting;

    @Override
    public String toString() {
        return "Chicken{" + "\nid ->\t\t " + id + ", \nname ->\t\t " + name + ", \ncolor ->\t " + color + ", \nage ->\t\t " + age + ", \nitsMolting ->\t " + itsMolting + '}';
    }
    

    
    public Chicken(int id, String name, String color, int age, boolean itsMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.itsMolting = itsMolting;
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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
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
     * @return the itsMolting
     */
    public boolean isItsMolting() {
        return itsMolting;
    }

    /**
     * @param itsMolting the itsMolting to set
     */
    public void setItsMolting(boolean itsMolting) {
        this.itsMolting = itsMolting;
    }
    
    

}
