package ec.edu.espe.strategycode.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class SortingContext {

    private SortingStrategy ss;

    public int[] sort(final int[] data) {
        final int size = data.length;
        ss = setSortStrategy(size);
        return ss.sort(data);
    }

    public SortingStrategy setSortStrategy(final int n) {
        if (n > 0 && n < 30) {
            ss = new BubbleSort();
        }

        if (n >= 30 && n < 100) {
            ss = new InsertionSort();
        }

        if (n >= 100) {
            ss = new QuickSort();
        }

        return ss;
    }
}
