package ec.edu.espe.flashdrive.view;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
import ec.edu.espe.flashdrive.model.FlashDrive;
import ec.edu.espe.flashdrive.model.Storage;
import ec.edu.espe.flashdrive.utils.FileManagement;
import java.util.Scanner;

public class FlashDriveApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManagement.loadGlobalList();

        boolean active = true;

        while (active) {
            System.out.println("\nFLASH DRIVE MANAGER");
            System.out.println("1. Create new FlashDrive");
            System.out.println("2. View All Devices");
            System.out.print("Select option: ");
            
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("\nNew Flash Drive");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Brand: ");
                    String brand = scanner.nextLine();
                    
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    
                    String capacity = null;
                    try {
                        System.out.print("Capacity (GB): ");
                        capacity = scanner.nextLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Capacity must be a number. Setting to 0.");
                    }

                    FlashDrive newUsb = new FlashDrive(id, brand, capacity, color);
                
                    Storage.addDevice(newUsb);
                    System.out.println("Saving Flash Drive");
                    FileManagement.saveGlobalList();
                    active = false;
                    break;

                case "2":
                    System.out.println("\nStorage");
                    if (Storage.allStorageDevices.isEmpty()) {
                        System.out.println("The Flas Drive list is empty.");
                    } else {
                        for (FlashDrive usb : Storage.allStorageDevices) {
                            System.out.println(usb);
                        }
                    }
                    break;
                    


                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}
