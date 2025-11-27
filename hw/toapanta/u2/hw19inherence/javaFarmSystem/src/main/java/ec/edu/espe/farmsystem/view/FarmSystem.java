<<<<<<< HEAD
package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.Date;
/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class FarmSystem {
    public static void main(String[] args) {
        int id;
        String breed;
        Date bornOn;
        String gender;
        String genderSheep;
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
        genderSheep = "female";
        isAbleToReproduce = false;
        
        bornOn = new Date(2025, 2, 12);
        breed = "Holstein";
        
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);
        
        Date bornOnSheep;
        bornOnSheep = new Date(2025, 1, 1);
        
        FarmAnimal chicken;
        chicken = new Chicken(true,0 , 1, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal sheep;
        sheep = new Sheep(bornOnSheep, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal cow;
        cow = new Cow(isAbleToReproduce, weight, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal pig;
        pig = new Pig (4, breed, bornOn, gender, true, weight, cage, location);
        
        System.out.println("farmAnimal --> " + chicken);
        System.out.println("farmAnimal --> " + sheep);
        System.out.println("farmAnimal --> " + cow);
        System.out.println("farmAnimal --> " + pig);
              
    }
    
}
=======
package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.Date;
/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class FarmSystem {
    public static void main(String[] args) {
        int id;
        String breed;
        Date bornOn;
        String gender;
        String genderSheep;
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
        genderSheep = "female";
        isAbleToReproduce = false;
        
        bornOn = new Date(2025, 2, 12);
        breed = "Holstein";
        
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);
        
        Date bornOnSheep;
        bornOnSheep = new Date(2025, 1, 1);
        
        FarmAnimal chicken;
        chicken = new Chicken(true,0 , 1, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal sheep;
        sheep = new Sheep(bornOnSheep, 2, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal cow;
        cow = new Cow(isAbleToReproduce, weight, 3, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        
        FarmAnimal pig;
        pig = new Pig (4, breed, bornOn, gender, true, weight, cage, location);
        
        System.out.println("farmAnimal --> " + chicken);
        System.out.println("farmAnimal --> " + sheep);
        System.out.println("farmAnimal --> " + cow);
        System.out.println("farmAnimal --> " + pig);
              
    }
    
}
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
