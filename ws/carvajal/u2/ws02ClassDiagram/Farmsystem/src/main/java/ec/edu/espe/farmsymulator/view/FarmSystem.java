package ec.edu.espe.farmsymulator.view;

import ec.edu.espe.farmsymulator.model.Cage;
import ec.edu.espe.farmsymulator.model.Chicken;
import ec.edu.espe.farmsymulator.model.Cow;
import ec.edu.espe.farmsymulator.model.Location;
import ec.edu.espe.farmsymulator.model.FarmAnimal;
import ec.edu.espe.farmsymulator.model.Pig;
import ec.edu.espe.farmsymulator.model.Sheep;
import java.util.Date;

/**
 *
 * @author TheArtOfProgramming - Josue Carvajal
 */
public class FarmSystem {

    public static void main(String[] args) {
        
    int id;
    String breed;
    Date date;
    String gender;
    boolean isAbleToReproduce;
    float weight;
    Cage cage;
    Location location;
    int xCordinate;
    int yCordinate;
    
    xCordinate = 10;
    yCordinate = 20;
    weight = 10.4F;
    gender="male";
    isAbleToReproduce = false;
    
    date = new Date(2025, 2, 1);
    breed = "Holstein";
    location = new Location(xCordinate, yCordinate);
    cage = new Cage(1, "STABLE FOR COWS", 2, location);
    
    
        FarmAnimal farmAnimal;
        
        farmAnimal = new Chicken(true, 0, 1, breed, date, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal--->" + farmAnimal);
        
        farmAnimal = new Pig (2, breed, date, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal--->" + farmAnimal);
        
        farmAnimal = new Cow(true, 5, 3, breed, date, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal--->" + farmAnimal);
        
        farmAnimal = new Sheep(date, 5, breed, date, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal--->" + farmAnimal);
    }
    
}
