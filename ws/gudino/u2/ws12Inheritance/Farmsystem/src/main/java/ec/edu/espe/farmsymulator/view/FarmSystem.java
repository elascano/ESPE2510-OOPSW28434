//Chicken//
//package ec.edu.espe.farmsymulator.view;
//
//import ec.edu.espe.farmsymulator.model.Cage;
//import ec.edu.espe.farmsymulator.model.Chicken;
//import ec.edu.espe.farmsymulator.model.Location;
//import ec.edu.espe.farmsymulator.model.FarmAnimal;
//import java.util.Date;
//
///**
// *
// * @author Bryan Gudino
// */
//public class FarmSystem {
//
//    public static void main(String[] args) {
//        
//    int id;
//    String breed;
//    Date date;
//    String gender;
//    boolean isAbleToReproduce;
//    float weight;
//    Cage cage;
//    Location location;
//    int xCordinate;
//    int yCordinate;
//    
//    xCordinate = 10;
//    yCordinate = 20;
//    weight = 10.4F;
//    gender="male";
//    isAbleToReproduce = false;
//    
//    date = new Date(2025, 2, 1);
//    breed = "Holstein";
//    location = new Location(xCordinate, yCordinate);
//    cage = new Cage(1, "STABLE FOR COWS", 2, location);
//    
//    
//        FarmAnimal farmAnimal;
//        farmAnimal = new Chicken(true, 0, 1, breed, date, gender, isAbleToReproduce, weight, cage);
//        
//        System.out.println("farmAnimal--->" + farmAnimal);
//    }
//    
////}

//Cow//
//package ec.edu.espe.farmsymulator.view;
//
//import ec.edu.espe.farmsymulator.model.Cage;
//import ec.edu.espe.farmsymulator.model.Cow;
//import ec.edu.espe.farmsymulator.model.Location;
//import ec.edu.espe.farmsymulator.model.FarmAnimal;
//import java.util.Date;
//
///**
// *
// * @author Bryan Gudino
// */
//public class FarmSystem {
//
//    public static void main(String[] args) {
//        
//    int id;
//    String breed;
//    Date date;
//    String gender;
//    boolean isAbleToReproduce;
//    float weight;
//    Cage cage;
//    Location location;
//    int xCordinate;
//    int yCordinate;
//    
//    // Variable for Cow
//    boolean isProducingMilk;
//    float litersADay;
//    
//    xCordinate = 10;
//    yCordinate = 20;
//    
//    date = new Date(2025, 2, 1);
//    breed = "Holstein"; 
//    location = new Location(xCordinate, yCordinate);
//    cage = new Cage(1, "STABLE FOR COWS", 2, location);
//    
//    id = 2; 
//    weight = 650.0F; 
//    gender = "female"; 
//    isAbleToReproduce = true; 
//    
//    isProducingMilk = true;
//    litersADay = 25.5F; 
//    
//    
//        FarmAnimal farmAnimal;
//        farmAnimal = new Cow(isProducingMilk, litersADay, id, breed, date, gender, isAbleToReproduce, weight, cage);
//        
//        System.out.println("farmAnimal (Cow)--->" + farmAnimal);
//        
//    }
//    
//}

//Sheep//
//package ec.edu.espe.farmsymulator.view;
//
//import ec.edu.espe.farmsymulator.model.Cage;
//import ec.edu.espe.farmsymulator.model.Location;
//import ec.edu.espe.farmsymulator.model.FarmAnimal;
//import ec.edu.espe.farmsymulator.model.Sheep; 
//import java.util.Date;
//
///**
// *
// * @author Bryan Gudino
// */
//public class FarmSystem {
//
//    public static void main(String[] args) {
//        
//        int id;
//        String breed;
//        Date dateBorn;
//        Date lastSheeringDate;
//        String gender;
//        boolean isAbleToReproduce;
//        float weight;
//        Cage cage;
//        Location location;
//        int xCordinate;
//        int yCordinate;
//        
//        xCordinate = 10;
//        yCordinate = 20;
//        location = new Location(xCordinate, yCordinate);
//        cage = new Cage(1, "BARN FOR SHEEP", 3, location);
//
//        id = 3;
//        breed = "Merino";
//        dateBorn = new Date(2024, 0, 15); 
//        gender = "female";
//        weight = 45.5F; 
//        isAbleToReproduce = true;
//        
//        lastSheeringDate = new Date(2024, 5, 10); 
//
//        FarmAnimal farmAnimal;
//        farmAnimal = new Sheep(lastSheeringDate, id, breed, dateBorn, gender, isAbleToReproduce, weight, cage);
//        
//        System.out.println("farmAnimal (Sheep)--->" + farmAnimal);
//    } 
//}

//Pig//
package ec.edu.espe.farmsymulator.view;

import ec.edu.espe.farmsymulator.model.Cage;
import ec.edu.espe.farmsymulator.model.Location;
import ec.edu.espe.farmsymulator.model.FarmAnimal;
import ec.edu.espe.farmsymulator.model.Pig; // Importar Pig
import java.util.Date;

/**
 *
 * @author Bryan Gudino
 */
public class FarmSystem {

    public static void main(String[] args) {
        
        int id;
        String breed;
        Date dateBorn;
        String gender;
        boolean isAbleToReproduce;
        float weight;
        Cage cage;
        Location location;
        int xCordinate;
        int yCordinate;
        
        xCordinate = 15;
        yCordinate = 15;
        location = new Location(xCordinate, yCordinate);
        
        cage = new Cage(1, "STY FOR PIGS", 4, location); 

        id = 4;
        breed = "Landrace"; 
        dateBorn = new Date(2024, 8, 20); 
        gender = "male";
        weight = 120.5F; 
        isAbleToReproduce = true; 

        FarmAnimal farmAnimal;
        farmAnimal = new Pig(id, breed, dateBorn, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("farmAnimal (Pig)--->" + farmAnimal);
    }
    
}