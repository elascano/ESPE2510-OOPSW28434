package ec.edu.espe.hwtemplatemethod.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public abstract class CaffeineBeverage {

    public final void prepareRecipe() { // Template method
        boilWater();
        brew();
        pourInCup();
        if (wantsCondiments()) { // Hook
            addCondiments();
        }
    }

    public void boilWater() {
        System.out.println("Boiling water"); // Fixed step
    }

    public abstract void brew(); // Variable step

    public void pourInCup() {
        System.out.println("Pouring into cup"); // Fixed step
    }

    public abstract void addCondiments(); // Variable step

    public boolean wantsCondiments() {
        return true; // Default hook
    }
}
