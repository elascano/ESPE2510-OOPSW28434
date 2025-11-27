package ec.edu.espe.farmsystem.view;
import java.util.Date;
import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
        
/**
 *
 * @author Steven Loza, @ESPE
 */
public class FarmSystem {
    public static void main(String[] args) {
        int id;
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
        gender = "male";
        isAbleToReproduce = false;
        
        bornOn = new Date(2025,03,01);
        breed = "Holstein";
        location = new Location(xCoordinate, yCoordinate){};
        cage = new Cage(1, "Stable for cows", 2, location);
        
        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(true, 0, 1, breed, bornOn, gender, true, weight, cage);
        System.out.println("farmAnimal --> Chicken " + farmAnimal);
        
        farmAnimal = new Pig(2, breed, bornOn, gender, false, weight, cage);
        System.out.println("farmAnimal --> Pig " + farmAnimal);
        
        farmAnimal = new Cow(false, weight, 3, breed, bornOn, gender, true, weight, cage);
        System.out.println("farmAnimal --> Cow " + farmAnimal);
        
        farmAnimal = new Sheep(bornOn, 4, breed, bornOn, gender, false, weight, cage);
        System.out.println("farmAnimal --> Sheep " + farmAnimal);
        
    } 
}
