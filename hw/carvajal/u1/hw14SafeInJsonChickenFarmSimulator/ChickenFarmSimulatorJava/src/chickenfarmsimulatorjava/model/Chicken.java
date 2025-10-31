
package chickenfarmsimulatorjava.model;

/**
 *
 * @author Gabriel
 */
public class Chicken {
    private int id;
    private String name;
    private int age;
    private boolean molting;

    public Chicken(int id, String name, int age, boolean molting) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.molting = molting;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMolting() {
        return molting;
    }

    @Override
    public String toString() {
        return String.format("Chicken %d | Name: %s | Age: %d | Molting: %s",
                id, name, age, molting);
    }
}
