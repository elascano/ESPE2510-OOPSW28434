/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ChickenFarmSimulator.model;

/**
 *
 * @author Paulo Ramos
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;

    // Constructor
    public Chicken(int id, String name, String color, int age, boolean isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isMolting() { return isMolting; }
    public void setMolting(boolean isMolting) { this.isMolting = isMolting; }

    @Override
    public String toString() {
        return "Chicken{id: " + id + "\t name: " + name + "\t color: " + color + "\t age: " + age + "\t isMolting: " + isMolting + "}";
    }

    // Métodos de comportamiento
    public Poop poop(int amount) {
        Poop poopInstance = new Poop(amount);
        System.out.println("Chicken " + name + " is pooping a " + poopInstance);
        return poopInstance;
    }

    public Egg layAnEgg(String size) {
        Egg egg = new Egg(size);
        System.out.println("Chicken " + name + " is laying a " + egg.getSize() + " size egg");
        return egg;
    }

    public void doStuff() {
        cluck();
        eat();
        cluck();
        poop(2);
        poop(3);
        eat();
        wander();
        drink();
        layAnEgg("M");
        layAnEgg("L");
    }

    public void cluck() { System.out.println("Chicken " + name + " is clucking, cluck, cluck, cluck"); }
    public void eat() { System.out.println("Chicken " + name + " is eating"); }
    public void wander() { System.out.println("Chicken " + name + " is wandering"); }
    public void drink() { System.out.println("Chicken " + name + " is drinking"); }
}


