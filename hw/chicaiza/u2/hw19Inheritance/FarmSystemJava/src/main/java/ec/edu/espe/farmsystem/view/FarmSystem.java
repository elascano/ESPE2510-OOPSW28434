package ec.edu.espe.farmsystem.view;
import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.Date;


/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class FarmSystem {
   
    public static void main(String[] args) {
        
        String breed;
        Date date ;
        String gender;
        boolean isAbleToReproduce;
        float weight;
        Cage cage;
        Location location;
        int xCoordinate;
        int yCoordinate;
        
        
        xCoordinate = 10;
        yCoordinate = 10;
        weight =10.4F;
        gender ="male";
        isAbleToReproduce= false;
        
        

       
        
        
        date = new Date(2025 , 2,1);
        breed ="Holstein";
        location = new Location(xCoordinate, yCoordinate);
        cage = new Cage(1, "stable for cows", 2, location);
        
        
        FarmAnimal farmAnimal;
        
        farmAnimal = new Chicken(true, 0, 1, breed, date, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("farmAnimal -->"+farmAnimal);
        
        
        
    
        farmAnimal= new Cow(true, weight, 2, breed, date, gender, isAbleToReproduce, weight, cage);
    
        System.out.println("farmAniaml -->"+farmAnimal);
        
        
        
        date = new Date(2025 , 2,4);
        
        farmAnimal=new Sheep(date, 3, breed, date, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("FarmAnimal -->"+farmAnimal);
        
        
        farmAnimal=new Pig(4, breed, date, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("FarmAnimal -->"+farmAnimal);
        
        
    
    }
    
 
}
