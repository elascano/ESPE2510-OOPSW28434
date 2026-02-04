package ec.edu.espe.strategycode.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class SortApp {
    public static void main(final String[] args) {
        final int[] data = {3, 6, 4, 6, 7, 8, 5, 6, 7, 5, 3, 3};

        SortingContext sortingContext = new SortingContext();
        int[] sortedList = sortingContext.sort(data);
    }
}
