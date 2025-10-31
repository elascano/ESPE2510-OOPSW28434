package model;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
import java.util.Random;

public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;
    private Random random = new Random();
    
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
    
    public void doStuff() {
        cluck();
        eat();
        wander();
        drink();
        
        char[] eggSizes = {'S', 'M', 'L'};
        char randomSize = eggSizes[random.nextInt(eggSizes.length)];
        layAnEgg(randomSize);
        
        int randomAmount = random.nextInt(3) + 1;
        poop(randomAmount);
    }
    
    private void cluck() {
        System.out.println("Chicken " + name + " is clucking: cluck, cluck, cluck");
    }
    
    private void eat() {
        System.out.println("Chicken " + name + " is eating grains");
    }
    
    private void wander() {
        System.out.println("Chicken " + name + " is wandering around");
    }
    
    private void drink() {
        System.out.println("Chicken " + name + " is drinking water");
    }
    
    private Egg layAnEgg(char size) {
        Egg egg = new Egg(size);
        System.out.println("Chicken " + name + " laid a " + size + " size egg!");
        return egg;
    }
    
    private Poop poop(int amount) {
        Poop poop = new Poop(amount);
        System.out.println("Chicken " + name + " pooped " + amount + " times");
        return poop;
    }
   
    public int getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
    public boolean isMolting() { return isMolting; }
    
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setAge(int age) { this.age = age; }
    public void setMolting(boolean isMolting) { this.isMolting = isMolting; }
    
    @Override
    public String toString() {
        return String.format("Chicken{id=%d, name='%s', color='%s', age=%d, isMolting=%s}", 
                           id, name, color, age, isMolting);
    }
}