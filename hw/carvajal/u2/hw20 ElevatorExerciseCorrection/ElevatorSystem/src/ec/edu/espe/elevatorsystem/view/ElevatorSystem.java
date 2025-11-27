package ec.edu.espe.elevatorsystem.view;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
import java.util.Scanner;
import ec.edu.espe.elevatorsystem.controller.ControlSystem;




public class ElevatorSystem {

    private ControlSystem controller = new ControlSystem();

    public void showMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ELEVATOR SYSTEM===");
        System.out.print("Enter the floor you wish to go to: ");

        int floor = sc.nextInt();

        requestElevator(floor);
    }

    public void requestElevator(int floor) {
        controller.assignElevator(floor);
    }

   public static void main(String[] args) {
    ElevatorSystem system = new ElevatorSystem();
    system.showMenu();
    }
}
