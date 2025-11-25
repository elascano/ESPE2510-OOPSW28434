package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
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
        Date bornDate = new Date(124, 5, 15);
        Date shearingDate = new Date(124, 0, 15);

        Cow cow1 = new Cow(true, 0.0f, 1, "Holstein", bornDate, "female", true, 450.5f, cowCage);
        Cow cow2 = new Cow(false, 0.0f, 2, "Jersey", bornDate, "female", false, 380.2f, cowCage);
        Cow cow3 = new Cow(true, 5.5f, 3, "Angus", bornDate, "female", true, 520.8f, cowCage);

        cows.add(cow1);
        cows.add(cow2);
        cows.add(cow3);

        cow1.milk(8.2f);
        cow3.milk(3.5f);

        Chicken chicken1 = new Chicken(false, 0, 4, "Rhode Island Red",
                bornDate, "female", true, 2.5f, chickenCage);
        Chicken chicken2 = new Chicken(true, 5, 5, "Leghorn",
                bornDate, "female", true, 2.1f, chickenCage);
        Chicken chicken3 = new Chicken(false, 12, 6, "Plymouth Rock",
                bornDate, "male", false, 3.2f, chickenCage);

        chickens.add(chicken1);
        chickens.add(chicken2);
        chickens.add(chicken3);

        chicken1.layAnEgg();
        chicken2.layAnEgg();
        chicken2.layAnEgg();
        chicken3.layAnEgg();

        Pig pig1 = new Pig(7, "Duroc", bornDate, "male", true, 120.5f, pigCage);
        Pig pig2 = new Pig(8, "Yorkshire", bornDate, "female", true, 95.3f, pigCage);
        Pig pig3 = new Pig(9, "Hampshire", bornDate, "male", false, 150.8f, pigCage);

        pigs.add(pig1);
        pigs.add(pig2);
        pigs.add(pig3);

        Sheep sheep1 = new Sheep(null, 10, "Merino", bornDate, "female", true, 65.2f, sheepCage);
        Sheep sheep2 = new Sheep(today, 11, "Dorset", bornDate, "male", false, 72.5f, sheepCage);
        Sheep sheep3 = new Sheep(shearingDate, 12, "Suffolk", bornDate, "female", true, 58.7f, sheepCage);

        sheeps.add(sheep1);
        sheeps.add(sheep2);
        sheeps.add(sheep3);

        sheep1.shear();
        sheep3.cutWool();

        System.out.println("--- COWS ---");
        for (int i = 0; i < cows.size(); i++) {
            Cow cow = cows.get(i);
            System.out.println("Cow " + (i + 1) + ":");
            System.out.println("   - ID: " + cow.getId());
            System.out.println("   - Breed: " + cow.getBreet());
            System.out.println("   - Producing milk: " + cow.isIsProducingMilk());
            System.out.println("   - Liters today: " + cow.getLitersADay() + "L");
            System.out.println("   - Weight: " + cow.getWeight() + "kg");
            System.out.println("   - Cage: " + cow.getCage().getDescrption());
        }

        System.out.println("\n--- CHICKENS ---");
        for (int i = 0; i < chickens.size(); i++) {
            Chicken chicken = chickens.get(i);
            System.out.println("Chicken " + (i + 1) + ":");
            System.out.println("   - ID: " + chicken.getId());
            System.out.println("   - Breed: " + chicken.getBreet());
            System.out.println("   - Molting: " + chicken.isIsMolting());
            System.out.println("   - Eggs laid: " + chicken.getLaidEggs());
            System.out.println("   - Weight: " + chicken.getWeight() + "kg");
            System.out.println("   - Gender: " + chicken.getGender());
            System.out.println("   - Cage: " + chicken.getCage().getDescrption());
        }

        System.out.println("\n--- PIGS ---");
        for (int i = 0; i < pigs.size(); i++) {
            Pig pig = pigs.get(i);
            System.out.println("Pig " + (i + 1) + ":");
            System.out.println("   - ID: " + pig.getId());
            System.out.println("   - Breed: " + pig.getBreet());
            System.out.println("   - Weight: " + pig.getWeight() + "kg");
            System.out.println("   - Gender: " + pig.getGender());
            System.out.println("   - Able to reproduce: " + pig.isIsAbleToReproduce());
        }

        System.out.println("\n--- SHEEP ---");
        for (int i = 0; i < sheeps.size(); i++) {
            Sheep sheep = sheeps.get(i);
            System.out.println("Sheep " + (i + 1) + ":");
            System.out.println("   - ID: " + sheep.getId());
            System.out.println("   - Breed: " + sheep.getBreet());
            System.out.println("   - Last shearing: " + sheep.getLastShearing());
            System.out.println("   - Weight: " + sheep.getWeight() + "kg");
            System.out.println("   - Gender: " + sheep.getGender());
        }

    }
}
