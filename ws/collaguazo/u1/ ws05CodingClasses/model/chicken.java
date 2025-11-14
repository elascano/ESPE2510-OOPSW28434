package ec.edu.ec.chickenfarmsimulator.model;

/**
 * Clase que representa una gallina dentro del simulador.
 * Contiene atributos básicos y comportamientos comunes de una gallina.
 * 
 * @author Pablo Collaguazo
 * @version 1.0
 */
public class Chicken {
    
   
    private int id;
    private String name;
    private String featherColor;
    private int age;
    private boolean molting;

   
    public Chicken(int id, String name, String featherColor, int age, boolean molting) {
        this.id = id;
        this.name = name;
        this.featherColor = featherColor;
        this.age = age;
        this.molting = molting;
    }

    private void makeSound() {
        System.out.println("Chicken " + name + " goes cluck-cluck!");
    }

    private void eatFood() {
        System.out.println("Chicken " + name + " is eating some grains.");
    }

    private void moveAround() {
        System.out.println("Chicken " + name + " is walking around the coop.");
    }

    private void drinkWater() {
        System.out.println("Chicken " + name + " is drinking water.");
    }

   
    public void performActivities() {
        makeSound();
        eatFood();
        makeSound();
        releasePoop(2);
        releasePoop(3);
        eatFood();
        moveAround();
        drinkWater();
        produceEgg('M');
        produceEgg('L');
    }

    public Poop releasePoop(int quantity) {
        Poop poop = new Poop(quantity);
        System.out.println("Chicken " + name + " just pooped " + poop);
        return poop;
    }

    /**
     * Genera un huevo de cierto tamaño.
     * 
     * @param size Tamaño del huevo: S, M o L
     * @return Un objeto de tipo Egg del tamaño indicado.
     */
    public Egg produceEgg(char size) {
        Egg egg = new Egg(size);
        System.out.println("Chicken " + name + " laid a " + egg.getSize() + " sized egg.");
        return egg;
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

    public String getFeatherColor() {
        return featherColor;
    }

    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMolting() {
        return molting;
    }

    public void setMolting(boolean molting) {
        this.molting = molting;
    }

    @Override
    public String toString() {
        return "\nChicken{" +
                "\n id = \t\t" + id +
                ", \n name = \t" + name +
                ", \n featherColor = " + featherColor +
                ", \n age = \t\t" + age +
                ", \n molting = \t" + molting +
                "\n}";
    }
}
