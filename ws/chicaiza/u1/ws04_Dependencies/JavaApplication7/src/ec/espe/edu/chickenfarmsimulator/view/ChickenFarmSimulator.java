/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.chickenfarmsimulator.view;
import ec.espe.edu.chickenfarmsimulator.model.Chicken;
import java.util.Scanner;

public class ChickenFarmSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chicken chicken = null; // not created yet
        int option;

        do {
            System.out.println("\n===== CHICKEN FARM SIMULATOR =====");
            System.out.println("1. Enter chicken data");
            System.out.println("2. Show chicken data");
            System.out.println("3. Make the chicken do stuff");
            System.out.println("4. Make the chicken lay an egg");
            System.out.println("5. Make the chicken poop");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();

                    System.out.print("Is molting? (true/false): ");
                    boolean isMolting = scanner.nextBoolean();

                    chicken = new Chicken(id, name, color, age, isMolting);
                    System.out.println("Chicken registered successfully.");
                }
                case 2 -> {
                    if (chicken != null) {
                        System.out.println(chicken);
                    } else {
                        System.out.println("No chicken registered.");
                    }
                }
                case 3 -> {
                    if (chicken != null) {
                        chicken.doStuff();
                    } else {
                        System.out.println("No chicken registered.");
                    }
                }
                case 4 -> {
                    if (chicken != null) {
                        System.out.print("Enter egg size (S, M, L): ");
                        char size = scanner.next().charAt(0);
                        chicken.layAnEgg(size);
                    } else {
                        System.out.println("No chicken registered.");
                    }
                }
                case 5 -> {
                    if (chicken != null) {
                        System.out.print("Enter poop amount: ");
                        int amount = scanner.nextInt();
                        chicken.poop(amount);
                    } else {
                        System.out.println("No chicken registered.");
                    }
                }
                case 0 -> System.out.println("Exiting the simulator...");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);

        scanner.close();
    }
}