package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;    
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;    
import ec.edu.espe.farmsystem.model.Sheep;  
import java.util.Date;

public class FarmSystem{
    
    public static void main (String[]args){
    
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
        
        
        bornOn = new Date(2025, 2, 1); 
        breed = "Holstein";
        
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "Corral for cows", 2, location);
        
        System.out.println("--- Farm System  ---");
        
        FarmAnimal chicken;
        chicken = new Chicken(true, 0, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal --> Chicken{" + chicken.toString().replace("Chicken{", "FarmAnimal --> Chicken{") );
        ((Chicken)chicken).layAnEgg();
        System.out.println("Laid Eggs: " + ((Chicken)chicken).getLaidEggs());
        System.out.println("-".repeat(30));
        
        FarmAnimal cow;
        cow = new Cow(true, 1.5F, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal --> Cow{" + cow.toString().replace("Cow{", "FarmAnimal --> Cow{") );
        ((Cow)cow).milk();
        System.out.println("-".repeat(30));

       
        Date lastSheeringDate = new Date(bornOn.getTime() + (10 * 24 * 60 * 60 * 1000L));
        FarmAnimal sheep;
        sheep = new Sheep(lastSheeringDate, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal --> Sheep{" + sheep.toString().replace("Sheep{", "FarmAnimal --> Sheep{") );
        ((Sheep)sheep).cutWhool();
        System.out.println("-".repeat(30));

        FarmAnimal pig;
        pig = new Pig(4, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal --> Pig{" + pig.toString().replace("Pig{", "FarmAnimal --> Pig{") );
    }
}