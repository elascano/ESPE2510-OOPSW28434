
package ec.edu.espe.doll.controller;

import ec.edu.espe.doll.model.Doll;
import java.util.ArrayList;

/**
 *
 * @author LABS-ESPE
 */
public class DollController {
    
    
    private ArrayList <Doll> dolls = new ArrayList;
    
    
    public boolean addDoll(Doll doll){
        for (Doll d: dolls){
            if (d.getId()==doll.getId()){
                System.out.println("Error id");
                return false;
            }
        }
        dolls.add(doll);
    }

    public void displayDolls(){
        if (dolls.isEmpty()){
            System.out.println("No dolls");
        }else {
            System.out.println("-- Dolls --");
            for (Doll d : dolls){
                System.out.println("d");
            }
        }
    }
    
    public void saveJson(String filename){
        try (File writer = new FileWriter (filename)){
            new Gson().toJson(dolls, writer);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    } 
    
    public void loadFromJson (String filename){
        try (FileReader reader = new FileReader (filename)){
            Type listType = new TypeToken <ArrayList<Doll>>();
            dolls = new Gson().fromJson(reader, listType);
            if (dolls == null) dolls = new ArrayList <>;
            
        }catch (Exception e){
            dolls = new ArrayList <>();
        }
    }
}
