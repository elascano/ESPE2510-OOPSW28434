


 
package ec.espe.edu.chickenfarmsimulator.model;


public class Chicken {
    private int id;
    private String name;
    private String  color;
    private int age;
    private boolean isMolting;
    
    private void cluck(){
        System.out.println("The chicken" + name + "is cloacking Cluck Cluck Cluck");
    }
    
    private void eat(){
        System.out.println("The chicken "+ name + " is eating ");
    }
    
   
    
    public Poop poop (int amount){
        Poop poop;
        poop = new Poop (amount);
        System.out.println("Chicken " + name + "is pooping a " + poop );
        return poop;
    }
    
    public Egg layAnEgg (char size){
        Egg egg;
        egg = new Egg (size);
        System.out.println("Chicken " + name + "is laying a " + egg.getSize() + "size egg");
        return egg;
            
    }
    
    private void drink (){
        System.out.println("Chickn "+name+ "is drinking");
        
    }
    
    private void wander (){
        System.out.println("Chickn "+name+ "is wandering");
        
    }
    
    
     public void doStuff(){
        cluck ();
        eat ();
        poop (3);
        layAnEgg('L');
    }
    
    
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color + ", age=" + age + ", isMolting=" + isMolting + '}';
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