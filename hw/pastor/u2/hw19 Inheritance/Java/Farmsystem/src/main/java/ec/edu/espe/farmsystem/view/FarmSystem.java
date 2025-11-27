package ec.edu.espe.farmsystem.view;
import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.Cow;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class FarmSystem {
    public static void main(String[] args) {
        Location location1 = new Location(10, 20);
        Location location2 = new Location(30, 40);
        Cage coop = new Cage(1, "Chicken Coop", 1, location1);
        Cage stable = new Cage(2, "Cow Stable", 2, location2);
        Cage pen = new Cage(3, "Pig Pen", 3, location1);

        ArrayList<FarmAnimal> farmAnimals = new ArrayList<>();

        FarmAnimal chicken = new Chicken(true, 5, 1, "Leghorn", new Date(), "female", true, 3.5F, coop, location1);
        FarmAnimal cow = new Cow(true, 15.5F, 2, "Holstein", new Date(), "female", true, 600.0F, stable, location2);
        FarmAnimal pig = new Pig(false, 3, "Duroc", new Date(), "male", true, 120.5F, pen, location1);
        FarmAnimal sheep = new Sheep(new Date(), 4, "Merino", new Date(), "female", true, 70.0F, stable, location2);

        farmAnimals.add(chicken);
        farmAnimals.add(cow);
        farmAnimals.add(pig);
        farmAnimals.add(sheep);

        System.out.println("--- My Farm Animals ---");
        for (FarmAnimal animal : farmAnimals) {
            System.out.println(animal.toString());
            if(animal instanceof Cow){
                System.out.println("  -> This is a Cow, Milk production: " + ((Cow)animal).getLittersADay());
            }
        }
        
        System.out.println("\nTotal animals in farm: " + farmAnimals.size());
    }
    
    
}
