package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class FarmSystem {
    public static void main(String[] args) {
        int id;
        String breed;
        Date bornOn;
        boolean isAbleToReproduce;
        float weight;
        String gender;
        Cage cage;
        Location location;
        int xCoordinate;
        int yCoordinate;
        Date lastShearing;
        lastShearing = new Date(2006,11,4);
        xCoordinate = 10;
        yCoordinate = 20;
        isAbleToReproduce=false;
        weight = 10.4F;
        gender="male";
        bornOn = new Date(2025,2,1);
        breed= "Holstein";
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1,"Corral for cows", 2);
        
            FarmAnimal farmAnimal;
            farmAnimal = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
            System.out.println("farmAnimal --> " + farmAnimal);
            farmAnimal = new Cow(true, 1.5F, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage);
            System.out.println("farmAnimal --> " + farmAnimal);
            farmAnimal = new Pig(3, breed, bornOn, gender, isAbleToReproduce, weight, cage);
            System.out.println("farmAnimal --> " + farmAnimal);
            farmAnimal= new Sheep(lastShearing,4, breed, bornOn, gender, isAbleToReproduce, weight, cage);
            System.out.println("farmAnimal --> " + farmAnimal);
        
        
    }
}