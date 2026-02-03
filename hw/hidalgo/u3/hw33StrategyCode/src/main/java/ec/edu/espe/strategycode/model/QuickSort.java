package ec.edu.espe.strategycode.model;

/**
 *
 * @author  Mikael Hidalgo, Paradigm, @ESPE
 */
public class QuickSort implements SortingStrategy {
    public int[] sort(int data[]) {
        System.out.println("Selected strategy: QuickSort");
        return data;
    }
}
