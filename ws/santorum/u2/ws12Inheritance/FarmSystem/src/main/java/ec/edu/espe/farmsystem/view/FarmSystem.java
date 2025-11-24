package ec.edu.espe.farmsystem.view;


import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import java.util.Date;
/**
 *
 * @author Thais Santórum
 */
public class FarmSystem {
    
    
    public static void main(String[] args){
        
        int id = 0;
        String breed;
        Date bornOn;
        String gender;
        boolean isAbleToReproduce;
        float weight;
        Cage cage;
        Location location;
        int xCoordinate;
        int yCoordinate;
        
        
        
        xCoordinate = 10;
        yCoordinate = 20;
        weight = 10.4F;
        gender = "Male";
        isAbleToReproduce = false;
        
        bornOn = new Date(2025, 2, 1);
        breed = "Holstein";
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "Stable for cows.", 2, location);
        
        
        
        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("Farm Animal = " + farmAnimal);
    }
    
}
