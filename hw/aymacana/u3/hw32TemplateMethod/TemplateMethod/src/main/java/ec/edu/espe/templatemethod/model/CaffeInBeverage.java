package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public abstract class CaffeInBeverage {

    public void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (wantCondiments()) {
            addCondiments();
        }
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    abstract void brew();

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    abstract void addCondiments();

    boolean wantCondiments() {
        return true;
    }

}
