package ec.edu.espe.templatemethod.model;
/**
 *
 * @author Mikael Hidalgo
 */
public abstract class CaffeineBeverage {
   // Template Method: define el esqueleto del algoritmo
   public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (wantsCondiments()) {
            addCondiments();
        }
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public abstract void brew();

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    public abstract void addCondiments();

   // Hook (Gancho): las subclases pueden sobrescribirlo
    public boolean wantsCondiments() {
        return true;
    }    
}
