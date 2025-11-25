package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.*;
import java.util.Date;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class FarmSystem {

    public static void main(String[] args) {
        int id;
        String breet;
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

        bornOn = new Date(2025, 2, 1);
        breet = "Holstein";

        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);

        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(true, 0, 1, breet, bornOn, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("FarmAnimal --> " + farmAnimal);
    }
}
