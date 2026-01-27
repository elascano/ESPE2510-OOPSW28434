package ec.edu.espe.hwstrategy.view;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import ec.edu.espe.hwstrategy.controller.SortController;
import java.util.Arrays;

// Client Class
public class SortApp {
    public static void main(String[] args) {
        int data[] = {3, 6, 4, 6, 7, 8, 5, 6, 7, 5, 3, 3};
        
        // Initializing Controller
        SortController controller = new SortController();
        
        // Request Sort
        int[] sortedList = controller.sortData(data);
        
        System.out.println("List order: " + Arrays.toString(sortedList));
    }
}