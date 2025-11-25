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
 * @author Thais Santorum, Paradigm
 */


public class FarmSystem {
    public static void main(String[] args) {
        
        int xCoordinate = 10;
        int yCoordinate = 20;
        Location location = new Location(xCoordinate, yCoordinate);

        Cage cage = new Cage(1, "Corral for cows", 2);

        int id = 1;
        String breed = "Holstein";
        Date bornOn = new Date(2025, 2, 1);
        boolean isAbleToReproduce = false;
        float weight = 10.4F;
        String gender = "male";

   
        FarmAnimal chicken = new Chicken(true, 0, id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("Animal --> " + chicken);


        FarmAnimal cow = new Cow(true, 20.5F, 2, "Jersey", bornOn, "female", true, 450.0F, cage);
        System.out.println("Animal --> " + cow);

        
        FarmAnimal sheep = new Sheep(new Date(), 3, "Merino", bornOn, "female", true, 60.0F, cage);
        System.out.println("Animal --> " + sheep);


        FarmAnimal pig = new Pig(3.5F, false, 4, "Landrace", bornOn, "male", false, 120.0F, cage);
        System.out.println("Animal --> " + pig);

    }
}
