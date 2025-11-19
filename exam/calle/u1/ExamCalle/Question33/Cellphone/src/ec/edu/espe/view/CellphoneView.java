package ec.edu.espe.view;
import ec.edu.espe.controller.CellPhoneController;
import ec.edu.espe.CellPhone.model.CellPhone;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Emily Calle
 */
public class CellphoneView {
    private static CellPhoneController controller = new CellPhoneController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n--- Cell Phone Management Menu ---");
            System.out.println("1. Add Cell Phone");
        System.out.println("2. View Cell Phone List");
            System.out.print("Select an option: ");
            
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addCellPhoneOption();
                        break;
                    case 2:
                        displayCellPhonesOption();
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input..");
                scanner.nextLine();
                option = 0;
            }

        } while (option != 3);
    }

    private static void addCellPhoneOption() {
        System.out.println("\n--- Add New Cell Phone----- ");
        
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        
        System.out.print("Enter Price: $");
        double price = scanner.nextDouble();
        scanner.nextLine();

        CellPhone newPhone = new CellPhone(id, brand, price);
        controller.addCellPhone(newPhone);
    }

    private static void displayCellPhonesOption() {
        System.out.println("\n--- Cell Phone List ---");
        List<CellPhone> cellPhones = controller.loadCellPhones();

        if (cellPhones.isEmpty()) {
            System.out.println("The cell phone list is empty.");
        } else {
            for (CellPhone phone : cellPhones) {
                System.out.println(phone);
            }
        }
    }
}

