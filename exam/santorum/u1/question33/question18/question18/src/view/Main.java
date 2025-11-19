
package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Box;
import model.Scissors;




        
        
        
        
        
        

/**
 *
 * @author Thais Santórum Team 6 - Paradigm, @ESPE
 */
        
 


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Scissors> scissorS;

        while (true) {

            System.out.println("Menu");
            System.out.println("1. Add Scissors");
            System.out.println("2. Show Scissors");
            System.out.println("Leave");
            System.out.print("Choose an number ");
            String option = scanner.nextLine();

            
            
        
            
            
            switch (option) {

                case "1":
                    System.out.println("\nAdd scissors");

                    System.out.print("Enter the ID : ");
                    int scId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter the brand : ");
                    String brand = scanner.nextLine();

                    System.out.print("Enter the material : ");
                    String material = scanner.nextLine();

                    System.out.print("Enter the color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter the size: ");
                    String size = scanner.nextLine();


}