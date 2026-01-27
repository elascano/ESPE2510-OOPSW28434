package ec.edu.espe.strategycode.model;

/**
 *
 * @author Cesar Vargas, Paradigm, @ESPE
 */
public class SortApp {
public static void main(String args[]) {
        int data[] = {3, 6, 4, 6, 7, 8, 5, 6, 7, 5, 3, 3};
        
        SortingContext sc = new SortingContext();
        int sortedList[] = sc.sort(data);
    }
}
