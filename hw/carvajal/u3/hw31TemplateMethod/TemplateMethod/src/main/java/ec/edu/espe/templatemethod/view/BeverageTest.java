package ec.edu.espe.templatemethod.view;
import ec.edu.espe.templatemethod.model.*;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class BeverageTest {
    public static void main(String[] args){
        
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        
        System.out.println(" Making tea..");
        tea.prepareRecipe();
        
        System.out.println(" Making coffe...");
        coffee.prepareRecipe();                
        
    }
}
