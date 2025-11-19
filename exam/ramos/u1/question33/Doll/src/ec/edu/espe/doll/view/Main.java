package ec.edu.espe.doll.view;

import ec.edu.espe.doll.controller.DollController;
import ec.edu.espe.doll.model.Doll;
import java.util.Scanner;

/**
 *
 * @author LABS-ESPE
 */
public class Main {
    
    public static void main (String []args){
        DollController controller = new DollController();
        controller.loadFromJson("dolls.json");
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            try {
                System.out.println("Ingresse el Id Doll:");
                int id = Integer.parseInt(scanner.nextLine());
                
                boolean exist = controller getDolls().stream().anyMath(d-> d.getId()==id);
                if(exist){
                    System.out.println("Id ay existernte");
                    continue;
                    
                }
                
                System.out.println("Nombre Doll");
                String name = scanner.nextLine();
                
                System.out.println(" Material Dol");
                String material = scanner.nextLine();
                
                                System.out.println(" Precio Doll");
                double price = Double.parseDouble(scanner.nextLine());

              
                if (price < 0){
                    System.out.println("IOngrese un valor mayor  a cero");
                    continue;
                }
                
                controller.addDoll(new Doll(id, name, material, price));
                
                
                
            }catch (Exception e){
                System.out.println("Valor invalido ");
                continue;
            }
            System.out.println("Añadir otra Doll (y/n)");
            if (!scanner.nextLine().equalsIgnoreCase("y"))
                break;
            
        }
        
        controller.displayDolls();
        controller.saveJson("dools.json");
        System.out.println("Doll añadido correctamente");
    }
    
}
