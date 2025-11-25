package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class FarmSystem {

    public static void main(String[] args) {

        ArrayList<Cow> cows = new ArrayList<>();
        ArrayList<Chicken> chickens = new ArrayList<>();
        ArrayList<Pig> pigs = new ArrayList<>();
        ArrayList<Sheep> sheeps = new ArrayList<>();

       
        Location location1 = new Location(10, 20);
        Location location2 = new Location(15, 25);
        Location location3 = new Location(5, 15);
        Location location4 = new Location(30, 40);

   
        Cage cowCage = new Cage(1, "Cow barn", 2, location1);
        Cage chickenCage = new Cage(2, "Chicken coop", 1, location2);
        Cage sheepCage = new Cage(3, "Sheep pen", 3, location3);
        Cage pigCage = new Cage(4, "Pig pen", 3, location4);

        
        Date today = new Date();
        Date bornDate1 = new Date(123, 3, 15);
        Date bornDate2 = new Date(122, 7, 10);
        Date bornDate3 = new Date(121, 1, 5);

        Cow cow1 = new Cow(true, 0.5F, 1, "Holstein", bornDate1, "female", true, 0, cowCage);
        Cow cow2 = new Cow(true, 0.6F, 2, "Holstein", bornDate2, "female", true, 0, cowCage);
        Cow cow3 = new Cow(false, 0.4F, 3, "Jersey", bornDate3, "male", false, 0, cowCage);

        cows.add(cow1);
        cows.add(cow2);
        cows.add(cow3);

        
        Chicken chicken1 = new Chicken(false, 0, 1, "Rhode Island Red",
                bornDate1, "female", true, 2.5f, chickenCage);

        Chicken chicken2 = new Chicken(true, 5, 2, "Leghorn",
                bornDate2, "female", true, 2.1f, chickenCage);

        Chicken chicken3 = new Chicken(false, 12, 3, "Plymouth Rock",
                bornDate3, "male", false, 3.2f, chickenCage);

        chickens.add(chicken1);
        chickens.add(chicken2);
        chickens.add(chicken3);

        chicken1.layAnEgg();
        chicken2.layAnEgg();
        chicken2.layAnEgg();

        Pig pig1 = new Pig(1, "Large White", bornDate1, "male", true, 120.5f, pigCage);
        Pig pig2 = new Pig(2, "Duroc", bornDate2, "female", true, 105.3f, pigCage);
        Pig pig3 = new Pig(3, "Landrace", bornDate3, "male", true, 130.0f, pigCage);

        pigs.add(pig1);
        pigs.add(pig2);
        pigs.add(pig3);

        Sheep sheep1 = new Sheep(null, 1, "Merino", bornDate1, "female", true, 55.4f, sheepCage);
        Sheep sheep2 = new Sheep(new Date(), 2, "Suffolk", bornDate2, "male", true, 62.8f, sheepCage);
        Sheep sheep3 = new Sheep(new Date(124, 0, 15), 3, "Dorper", bornDate3, "female", true, 48.2f, sheepCage);

        sheeps.add(sheep1);
        sheeps.add(sheep2);
        sheeps.add(sheep3);

        sheep1.shear();
        sheep3.cutWhool();

      
        System.out.println("--- COWS ON THE FARM (" + cows.size() + ") ---");
        for (int i = 0; i < cows.size(); i++) {
            Cow cow = cows.get(i);
            System.out.println("Cow " + (i + 1) + ": " + cow);
            System.out.println("   - Producing milk: " + cow.isIsProducingMilk());
            System.out.println("   - Liters today: " + cow.getLittersADay() + "L");
            System.out.println("   - Cage: " + cow.getCage().getDescription());
        }

        System.out.println("\n--- CHICKENS ON THE FARM (" + chickens.size() + ") ---");
        for (int i = 0; i < chickens.size(); i++) {
            Chicken chicken = chickens.get(i);
            System.out.println("Chicken " + (i + 1) + ":");
            System.out.println("   - Breed: " + chicken.getBreed());
            System.out.println("   - Molting: " + chicken.isIsMolting());
            System.out.println("   - Eggs laid: " + chicken.getLaidEggs());
            System.out.println("   - Weight: " + chicken.getWeight() + "kg");
            System.out.println("   - Cage: " + chicken.getCage().getDescription());
        }

        System.out.println("\n--- PIGS ON THE FARM (" + pigs.size() + ") ---");
        for (int i = 0; i < pigs.size(); i++) {
            Pig pig = pigs.get(i);
            System.out.println("Pig " + (i + 1) + ": " + pig);
            System.out.println("   - Weight: " + pig.getWeight() + "kg");
            System.out.println("   - Cage: " + pig.getCage().getDescription());
        }

        System.out.println("\n--- SHEEP ON THE FARM (" + sheeps.size() + ") ---");
        for (int i = 0; i < sheeps.size(); i++) {
            Sheep sheep = sheeps.get(i);
            System.out.println("Sheep " + (i + 1) + ":");
            System.out.println("   - Weight: " + sheep.getWeight() + "kg");
            System.out.println("   - Last shearing: " + sheep.getLastSheering());
            System.out.println("   - Cage: " + sheep.getCage().getDescription());
        }
    }
}
