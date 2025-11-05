package model;

public class chicken {
    private String id;
    private String name;
    private String color;
    private int age;
    private boolean molting;

    public chicken(String id, String name, String color, int age, boolean molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.molting = molting;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
    public boolean isMolting() { return molting; }

    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setAge(int age) { this.age = age; }
    public void setMolting(boolean molting) { this.molting = molting; }

    @Override
    public String toString() {
        return String.format("%-5s | %-10s | %-8s | %-3d | %-3s", id, name, color, age, molting ? "Yes" : "No");
    }
}
