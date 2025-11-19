/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ec.VinilRecord.model;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
import ec.edu.ec.VinilRecord.model.VinilManager; 
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VinilRecord {
    private final Scanner scanner = new Scanner(System.in);
    private static final Pattern TEXT_PATTERN = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");

    public void displayMenu() {
        System.out.println("\n--- 🐔 CHICKEN FARM SIMULATOR 🐔 ---");
        System.out.println("1. Agregar Pollo");
        System.out.println("2. Ver Lista de Pollos");
        System.out.println("3. Editar Pollo");
        System.out.println("4. Borrar Pollo");
        System.out.println("5. Salir");
    }

    public void showMessage(String message) {
        System.out.println(">> " + message);
    }
    
    public int askForOption() throws InputMismatchException {
        System.out.print("Seleccione una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String askForValidatedText(String prompt) {
        String inputData;
        while (true) {
            System.out.print(prompt);
            inputData = scanner.nextLine().trim();
            Matcher matcher = TEXT_PATTERN.matcher(inputData);

            if (matcher.matches() && !inputData.isEmpty()) {
                return inputData;
            } else {
                showMessage("Advertencia: Debe contener solo texto válido.");
            }
        }
    }

    public String askForValidatedAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputData = scanner.nextLine().trim();
            try {
                int age = Integer.parseInt(inputData);
                if (age >= 0) {
                    return inputData;
                } else {
                    showMessage("Advertencia: La edad no puede ser negativa.");
                }
            } catch (NumberFormatException exception) {
                showMessage("Advertencia: La edad debe ser un número entero válido.");
            }
        }
    }

    public int askForChickenId() {
        while (true) {
            System.out.print("Ingrese el ID del pollo: ");
            try {
                int id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } catch (InputMismatchException exception) {
                showMessage("Advertencia: Ingrese solo el número del ID.");
                scanner.nextLine();
            }
        }
    }

    public void printChickenList(List<VinilManager> chickenList) {
        System.out.println("\nID | Nombre          | Edad | Raza");
        System.out.println("----------------------------------------");
        if (chickenList.isEmpty()) {
            System.out.println("No hay pollos registrados en la granja.");
        } else {
            chickenList.forEach(chicken -> 
                System.out.printf("%-3d| %-15s | %-4s | %s\n", 
                    chicken.getChickenId(), 
                    chicken.getName(), 
                    chicken.getAge(), 
                    chicken.getBreed())
            );
        }
    }
    
    public void closeScanner() {
        scanner.close();
    }
}