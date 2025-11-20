/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ec.VinilRecord.view;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */

import ec.edu.ec.VinilRecord.model.VinilManager;
import ec.edu.ec.VinilRecord.model.VinilRecord;
import java.util.InputMismatchException;
import java.util.List;

public class VinilMain {
    private final VinilRecord farmModel = new VinilRecord();
    private final VinilManager farmView = new VinilManager(); 

    public static void main(String[] args) {
       VinilMain simulator = new VinilMain();
        simulator.runSimulator();
    }
    public void runSimulator() {
        int userOption = 0;
        while (userOption != 5) {
            try {
                VinilManager.displayMenu();
                userOption = VinilManager.askForOption();

                switch (userOption) {
                    case 1:
                        addChicken();
                        break;
                    case 2:
                        viewAllChickens();
                        break;
                    case 3:
                        editChicken();
                        break;
                    case 4:
                        deleteChicken();
                        break;
                    case 5:
                        vinilmanager.showMessage("Saliendo del simulador... ¡Adiós!");
                        break;
                    default:
                        vinilmanager.showMessage("Opción no válida, intente de nuevo.");
                }
            } catch (InputMismatchException exception) {
                farmView.showMessage("Advertencia: Ingrese solo el número de la opción.");
                userOption = 0;
            } catch (Exception exception) {
                farmView.showMessage("[ERROR INESPERADO] Ocurrió un fallo: " + exception.getMessage());
                userOption = 0;
            }
        }
        farmView.closeScanner();
    }


    private void addChicken() {
        farmView.showMessage("\n--- Agregar Nuevo Pollo ---");
        String name = farmView.askForValidatedText("Nombre (Solo texto): ");
        String age = farmView.askForValidatedAge("Edad (Solo enteros): ");
        String breed = farmView.askForValidatedText("Raza (Solo texto): ");

        farmModel.createChicken(name, age, breed);
        farmView.showMessage("Pollo guardado exitosamente.");
    }

    private void viewAllChickens() {
        List chickenList = farmModel.getAllChickens();
        farmView.printChickenList(chickenList);
    }

    private void editChicken() {
        List chickenList = farmModel.getAllChickens();
        farmView.printChickenList(chickenList);

        if (chickenList.isEmpty()) return;

        int idToEdit = farmView.askForChickenId();

        farmView.showMessage("\n--- Ingrese Nuevos Datos ---");
        String newName = farmView.askForValidatedText("Nuevo Nombre: ");
        String newAge = farmView.askForValidatedAge("Nueva Edad: ");
        String newBreed = farmView.askForValidatedText("Nueva Raza: ");

        boolean success = farmModel.updateChicken(idToEdit, newName, newAge, newBreed);

        if (success) {
            farmView.showMessage("Pollo actualizado correctamente.");
        } else {
            farmView.showMessage("Error: ID no encontrado.");
        }
    }

    private void deleteChicken() {
        List chickenList = farmModel.getAllChickens();
        farmView.printChickenList(chickenList);

        if (chickenList.isEmpty()) return;

        int idToDelete = farmView.askForChickenId();
        boolean success = farmModel.deleteChicken(idToDelete);

        if (success) {
            farmView.showMessage("Pollo eliminado del sistema.");
        } else {
            farmView.showMessage("Error: ID no encontrado.");
        }
    }