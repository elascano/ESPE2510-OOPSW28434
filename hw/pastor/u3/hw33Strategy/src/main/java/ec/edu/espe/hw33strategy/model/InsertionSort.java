package ec.edu.espe.hw33strategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class InsertionSort implements SortingStrategy {

    @Override
    public int[] sort(int data[]) {
        System.out.println("Usando InsertionSort");
        java.util.Arrays.sort(data);
        return data;
    }
}
