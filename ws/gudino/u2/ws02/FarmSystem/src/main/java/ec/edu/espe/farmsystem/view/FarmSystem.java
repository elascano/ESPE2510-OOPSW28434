package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import java.util.Date;

public abstract class FarmSystem {
    
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
        
        xCoordinate =10;
        yCoordinate =2;
        weight = 10.4F;
        gender = "male";
        isAbleToReproduce = false;
        
        bornOn = new Date(2025, 2, 1);
        breed = "Holstein";
        location= new Location (xCoordinate, yCoordinate);
        cage = new Cage (1, "stable for cows", 2, location);
        
        FarmAnimal farmAnimal;
        //FarmAnimal farmAnimal = new FarmAnimal (1, breed, gender, bornOn, weight, isAbleToReproduce, cage){};
        farmAnimal = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal -->"+ farmAnimal);
    }
}