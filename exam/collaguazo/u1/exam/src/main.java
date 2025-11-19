/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pablo Collaguazo
 */
import controller.NotebookController;
import view.NotebookView;

public class Main {
    public static void main(String[] args) {
        NotebookView view = new NotebookView();
        NotebookController controller = new NotebookController();
        
        boolean running = true;
        
        while (running) {
            try {
                view.displayMenu();
                int choice = view.getMenuChoice();
                
                switch (choice) {
                    case 1:
                        controller.addNotebook();
                        break;
                    case 2:
                        controller.displayAllNotebooks();
                        break;
                    case 3:
                        controller.searchNotebookById();
                        break;
                    case 4:
                        controller.updateNotebook();
                        break;
                    case 5:
                        controller.deleteNotebook();
                        break;
                    case 6:
                        controller.showStatistics();
                        break;
                    case 7:
                        view.displaySuccess("¡Gracias por usar el Sistema de Gestión de Notebooks!");
                        running = false;
                        break;
                    default:
                        view.displayError("Opción inválida! Por favor seleccione 1-7.");
                }
                
                if (running && choice != 7) {
                    view.pressEnterToContinue();
                }
                
            } catch (Exception e) {
                view.displayError("Error inesperado: " + e.getMessage());
                view.pressEnterToContinue();
            }
        }
        
        // Cerrar scanners
        view.closeScanner();
        controller.closeScanner();
    }
}