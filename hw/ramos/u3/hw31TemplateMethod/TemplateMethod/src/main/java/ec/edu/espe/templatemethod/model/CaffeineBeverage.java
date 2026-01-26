package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class CaffeineBeverage {
    
    public void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if (wantsCondiments()){
            addCondiments();
        }
    }
    
    void boilWater(){
        System.out.println("Boiling Water");
    }
    
    abstract void brew();
    
    void pourInCup(){
        System.out.println("Pouring into cup");
    }
    
    abstract void addCondiments();
    boolean wantsCondiments(){
        return true;
    }
}
