package ec.edu.espe.hwstrategy.model;

public class SortingContext {
    private SortingStrategy ss;

    public int[] sort(int data[]) {
        int size = data.length;
        ss = setSortStrategy(size);
        return ss.sort(data);
    }

    // Select Strategy
    public SortingStrategy setSortStrategy(int n) {
        if (n > 0 && n < 30) {
            return new BubbleSort();
        } else if (n >= 30 && n < 100) {
            return new InsertionSort();
        } else {
            return new QuickSort();
        }
    }
    }
