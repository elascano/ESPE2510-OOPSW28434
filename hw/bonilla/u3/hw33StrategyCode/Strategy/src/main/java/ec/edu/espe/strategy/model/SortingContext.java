package ec.edu.espe.strategy.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class SortingContext {

    private SortingStrategy ss;

    public int[] sort(int[] data) {
        int size = data.length;
        ss = setSortStrategy(size);
        System.out.println("Selected strategy: " + ss.getClass().getSimpleName());
        return ss.sort(data);
    }

    public SortingStrategy setSortStrategy(int n) {
        if (n > 0 && n < 30) {
            ss = new BubbleSort();
        } else if (n >= 30 && n < 100) {
            ss = new InsertionSort();
        } else {
            ss = new QuickSort();
        }
        return ss;
    }
}