package com.espe.chickenfarm.model;

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

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
    public boolean isMolting() { return isMolting; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setAge(int age) { this.age = age; }
    public void setMolting(boolean molting) { isMolting = molting; }

    public Poop poop(int amount) {
        Poop poopInstance = new Poop(amount);
        System.out.printf("Chicken %s is pooping a %s%n", name, poopInstance);
        return poopInstance;
    }

    public Egg layAnEgg(String size) {
        Egg egg = new Egg(size);
        System.out.printf("Chicken %s is laying a %s size egg%n", name, egg.getSize());
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

    public void cluck() { 
        System.out.printf("Chicken %s is clucking, cluck, cluck, cluck%n", name); 
    }
    
    public void eat() { 
        System.out.printf("Chicken %s is eating%n", name); 
    }
    
    public void wander() { 
        System.out.printf("Chicken %s is wandering%n", name); 
    }
    
    public void drink() { 
        System.out.printf("Chicken %s is drinking%n", name); 
    }

    @Override
    public String toString() {
        return String.format("Chicken{id: %d\t name: %s\t color: %s\t age: %d\t isMolting: %b}", 
                           id, name, color, age, isMolting);
    }
}