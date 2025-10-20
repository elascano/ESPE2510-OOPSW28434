
package ec_Edu_espe_chickenfarmsimulator_view;
import chickenfarmsimulator_model.Chicken;

/**
 *
 * @author Adrian Toapanta
 */
public class ChickenFarmSimulator {
   public static void main (String arg[]){
      System.out.println("This is my chicken Farm Simulator"); 
      String owner;
      int id=1;
      String name = "lucy";
      int age = 2;
      boolean insMolting = false;
      
      owner= "Adrian Toapanta";
      
      //clas object
      Chicken chicken;
      chicken =new chincken();
      System.out.println("The chicken is-->"+ chicken); 
      System.out.println("chicken id-->"+ chicken.getId()+ chicken.name);
   }  
}
