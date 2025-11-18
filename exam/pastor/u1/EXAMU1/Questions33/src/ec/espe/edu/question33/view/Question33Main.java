package ec.espe.edu.question33.view;
import java.util.ArrayList;
import java.util.Scanner;
import ec.espe.edu.repaso.model.start;
/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Question33Main {
    private Scanner sc;
    
    public Question33Main() {
        sc = new Scanner(System.in);
    }

    public String viewMenu() {
        System.out.println("\n--- EXAMEN JAVA MVC ---");
        System.out.println("1. New start");
        System.out.println("3. New Color");
        System.out.println("4. Delete start");
        System.out.println("5. Edit Color");
        System.out.println("7. Edir Author");
        System.out.print("Option: ");
        return sc.nextLine();
    }

    // Retornamos un arreglo de objetos o String para simplificar
    public start setDatastart() {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Price: ");
            float price = Float.parseFloat(sc.nextLine());
            return new start(name,author,price);
        } catch (NumberFormatException e) {
            System.out.println("Error");
            return null;
        }
    }

    public int setNumber(String mensaje) {
        try {
            System.out.print(mensaje);
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double setDouble(String mensaje) {
        try {
            System.out.print(mensaje);
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void showColors(ArrayList<start> list) {
        System.out.println("\n--- list ---");
        if (list.isEmpty()) System.out.println("(Vacía)");
        
        for (int i = 0; i < list.size(); i++) {
            start start = list.get(i);
            System.out.println(i + ". " + start.getName() + " (" + start.Author) + ") - Price: " + start.getPrice());
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}

