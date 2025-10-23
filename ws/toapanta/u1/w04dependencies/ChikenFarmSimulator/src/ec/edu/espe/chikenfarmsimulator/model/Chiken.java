<<<<<<< HEAD:ws/loza/u1/New Folder/ChickenFarmSimuladorx/src/ec/edu/espe/chickenfarmsimulador/model/Chicken.java
package ec.edu.espe.chickenfarmsimulador.model;

/**
 *
 * @author Joseph Medina
 * @version 0.1
 */

public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;
   
    public void doStuff(){
        cluck();
        eat();
        cluck();
        poop(2);
        poop(3);
        wander();
        drink();
        layAnEgg('M');
       
    }
    public Poop poop(int amount){
      Poop poop;
      poop = new Poop(amount);
      System.out.println("chicken " + name + " is pooping a " + poop);
        return poop;
    }
   
    /**
     * It create an egg of size size and returns ti the calling function
     * @param size Size of the eggs with possible values S, M, L
     * @return and Egg of size sizze.
     */
    public Egg layAnEgg(char size){
        Egg egg = new Egg(size);
        System.out.println("chicken " + name + "is laying a " + egg.getSize() + "size egg");
        return egg;
    }

    @Override
    public String toString() {
        return "Chiken: " + "\nid --> \t\t" + id + " \nname --> \t" + name + " \ncolor --> \t" + color + " \nage --> \t" + age + " \nisMolting --> \t" + isMolting ;
    }
   
    public void wander(){
        System.out.println("chicken" + name + " is wandering");
    }
   
    public void drink(){
        System.out.println("chicken" + name + " is drinking");
    }
   
    private void eat(){
        System.out.println("chicken " + name + " is eating, grains");
    }

    private void cluck(){
        System.out.println("chicken " + name + " is clucking");
    }
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
=======
package ec.edu.espe.chikenfarmsimulator.model;

/**
 *
 * @author ADRIAN TOAPANTA
 * @version 0.1
 */ 
public class Chiken {
    private int id;
    private String name; 
    private String color;
    private int age;
    private boolean isMolting;

    @Override
    public String toString() {
        return "\nChiken{" + "\nid-->\t\t" + id + ", \nname-->\t\t" + name + ", \ncolor-->\t" + color + ", \nage-->\t" + age + ", \nisMolting-->" + isMolting + '}';
    }
  
    public Chiken(int id, String name, String color, int age, boolean isMolting) {
>>>>>>> 665e6e00da6ff82c416599260ee29a5a4d54f48f:ws/toapanta/u1/w04dependencies/ChikenFarmSimulator/src/ec/edu/espe/chikenfarmsimulator/model/Chiken.java
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
   

<<<<<<< HEAD:ws/loza/u1/New Folder/ChickenFarmSimuladorx/src/ec/edu/espe/chickenfarmsimulador/model/Chicken.java
=======
    
    
>>>>>>> 665e6e00da6ff82c416599260ee29a5a4d54f48f:ws/toapanta/u1/w04dependencies/ChikenFarmSimulator/src/ec/edu/espe/chikenfarmsimulator/model/Chiken.java
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
<<<<<<< HEAD:ws/loza/u1/New Folder/ChickenFarmSimuladorx/src/ec/edu/espe/chickenfarmsimulador/model/Chicken.java
   
   
}
=======
      
}
>>>>>>> 665e6e00da6ff82c416599260ee29a5a4d54f48f:ws/toapanta/u1/w04dependencies/ChikenFarmSimulator/src/ec/edu/espe/chikenfarmsimulator/model/Chiken.java
