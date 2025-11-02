package ChickenFarmSimulatorJava;

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

    public boolean isMolting() {
        return isMolting;
    }

    public void setMolting(boolean molting) {
        isMolting = molting;
    }

    @Override
    public String toString() {
        return "Chicken { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", isMolting=" + isMolting +
                " }";
    }
}