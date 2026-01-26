package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Emily Calle, @ESPE
 */

public abstract class CaffeineBeverage {
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

    public void pourInCup() { 
        System.out.println("Pouring into cup"); 
    }

    public abstract void brew();
    public abstract void addCondiments();

    public boolean wantsCondiments() { 
        return true; 
    }
}