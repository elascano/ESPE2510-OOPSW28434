package ec.espe.edu.chickenFarmSimulator.model;
import java.util.Random;
/**
 *
 * @author Mathews Pastor
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;
    private int eggProduced;
    private static final Random rand = new Random();
    
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
        this.eggProduced = 0;
    }
    
    public void cluck(){
        System.out.println("chicken " + name + "is clucking");
    }
    
    public void eat(){
        System.out.println("chicken " + name + "is eating");
    }
    
    public void wander(){
        System.out.println("chicken " + name + "is wandering");
    }
    
    public void drink(){
        System.out.println("chicken " + name + "is drinking");
    }
    
    public Egg layAnEgg(){
        Egg egg = new Egg();
        this.eggProduced++;
        System.out.println("chicken " + name + "is laying an " + egg.ToString());
        return egg;
    }
    
    public Poop poop(){
        Poop poop = new Poop();
        System.out.println("chicken " + name + "is pooping " + poop.toString());
        return poop;
    }
    
    public int doStuff(){
        int eggsObtainedFromThisSession = 0;
        for(int i = 0; i < 5; i++){
            int action = rand.nextInt(6);
            switch(action){
                case 0:
                    cluck();
                    break;
                case 1:
                    eat();
                    break;
                case 2:
                    wander();
                    break;
                case 3:
                    drink();
                    break;
                case 4:
                    layAnEgg();
                    eggsObtainedFromThisSession++;
                    break;
                case 5:
                    poop();
                    break;
            }
        }
        return eggsObtainedFromThisSession;
    }

    @Override
    public String toString() {
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color + ", age=" + age + ", isMolting=" + isMolting + ", eggProduced=" + eggProduced + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsMolting() {
        return isMolting;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public int getEggProduced() {
        return eggProduced;
    }

    public void setEggProduced(int eggProduced) {
        this.eggProduced = eggProduced;
    }
}
