/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.view.Notebook;

/**
 *
 * @author Pablo Collaguazo
 */
import java.util.Scanner;

public class NotebookView {
    private Scanner scanner;
    
    public NotebookView() {
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📓 SISTEMA DE GESTIÓN DE NOTEBOOKS");
        System.out.println("=".repeat(50));
        System.out.println("1. Agregar Notebook");
        System.out.println("2. Mostrar Todos los Notebooks");
        System.out.println("3. Buscar Notebook por ID");
        System.out.println("4. Actualizar Notebook");
        System.out.println("5. Eliminar Notebook");
        System.out.println("6. Estadísticas");
        System.out.println("7. Salir");
        System.out.println("=".repeat(50));
    }
    
    public int getMenuChoice() {
        System.out.print("Seleccione una opción (1-7): ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayError(String error) {
        System.out.println("❌ " + error);
    }
    
    public void displaySuccess(String success) {
        System.out.println("✅ " + success);
    }
    
    public void pressEnterToContinue() {
        System.out.print("\n⏎ Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    public void displayHeader(String header) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(header);
        System.out.println("=".repeat(50));
    }
    
    public void closeScanner() {
        scanner.close();
    }
}