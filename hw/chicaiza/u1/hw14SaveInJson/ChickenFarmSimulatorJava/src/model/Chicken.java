package chickenfarmsimulator.model;

public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;
    
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
    public boolean isMolting() { return isMolting; }
    
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setAge(int age) { this.age = age; }
    public void setMolting(boolean molting) { isMolting = molting; }
    
    public void cluck() {
        System.out.println("Chicken " + name + " is clucking: Cluck cluck cluck!");
    }
    
    public void wander() {
        System.out.println("Chicken " + name + " is wandering around...");
    }
    
    public void eat() {
        System.out.println("Chicken " + name + " is eating grains");
    }
    
    public void drink() {
        System.out.println("Chicken " + name + " is drinking water");
    }
    
    public void poop() {
        System.out.println("Chicken " + name + " is pooping");
    }
    
    public void layEgg() {
        System.out.println("Chicken " + name + " laid an egg!");
    }
}