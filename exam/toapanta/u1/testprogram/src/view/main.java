/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Toapanta Adrian
 */


import java.util.Scanner;
import java.util.ArrayList;
import model.StudentController;
import model.Student;
public class main {
    
    private static final StudentController controller = new StudentController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        inputDataFromKeyboard();

        
        System.out.println("\n============================================");
        System.out.println("   1. save and crete the Json   ");
        System.out.println("============================================");
        
        // 1. Convertir la lista a JSON String
        String finalJson = controller.listToJson();
        System.out.println("\n--- informecion for student ---");
        System.out.println(finalJson);

        System.out.println("\n============================================");
        System.out.println("     2. read and verification of JSON     ");
        System.out.println("============================================");
        

        ArrayList<Student> readList = controller.jsonToList(finalJson);
        
        System.out.println("\n--- change the data of json ---");
        for (Student s : readList) {
            System.out.println("LEÍDO: " + s.toString());
        }
        
        scanner.close();
    }
    
    private static void inputDataFromKeyboard() {
        String continueInput;
        
        System.out.println("--- complete the data ---");
        
        do {
            System.out.print("\nName: ");
            String name = scanner.nextLine();
            
            System.out.print("Asignature: ");
            String major = scanner.nextLine();
            
            double gpa = 0.0;
            boolean validGpa = false;
            while (!validGpa) {
                try {
                    System.out.print("add GPA (0.0-4.0): ");
                    gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa >= 0.0 && gpa <= 4.0) {
                        validGpa = true;
                    } else {
                        System.out.println("[ERROR] the GPA has to be  0.0 y 4.0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] add a valid number for GPA.");
                }
            }
            
            controller.addStudent(name, major, gpa);

            System.out.print("\n¿Do yo need add and other student? (y/n): ");
            continueInput = scanner.nextLine();
            
        } while (continueInput.equalsIgnoreCase("y"));
    }
}