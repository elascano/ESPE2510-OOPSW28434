package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Mateo Cevallos
 * @version 0.1
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;

    
public Poop poop(int amount){
    Poop poop;
    poop = new Poop(amount);
    System.out.println("chicken" + name + " is pooping a "+ poop);
    return poop;
}

/**
 * It creates an egg of size and returns to the calling function
 * @param size size of eggs with possibel values S, M ,L 
 * @return an Egg and its size
 */

public Egg layAnEgg(char size){
    Egg egg = new Egg(size);
    System.out.println("The chicken:" + name + "is laying a " + egg.getSize() + " size egg");
    return egg;
}
    
    @Override
    public String toString() {
        return "\nChicken{" + "\nid ->\t\t" + id + ", \nname -> \t" + name + ", \ncolor -> \t" + color + ", \nage -> \t\t" + age + ", \nisMolting -> \t" + isMolting + '}';
    }
    
    private void cluck(){
        System.out.println("The chicken is clucking " + name + " cluck,cluck,cluck");
    }
    
    private void eat(){
        System.out.println("The chicken "+ name + " is eating grains");
    }
    
    public void wander(){
        System.out.println("chicken"+ name + " is wandering");
    }
    
    public void drink(){
        System.out.println("chicken "+ name + " is drinking");
    }
    
    public void doStuff(){
        cluck();
        eat();
        poop(2);
        poop(3);
        wander();
        drink();
        layAnEgg('M');
        layAnEgg('L');
        
    }
    
    

    
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
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
