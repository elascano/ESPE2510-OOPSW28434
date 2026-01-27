package ec.edu.espe.hwstrategy.model;

public class InsertionSort implements SortingStrategy {
    @Override
    public int[] sort(int[] data) {
        // Apply Strategy
        for (int i = 1; i < data.length; ++i) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
        return data;
    }
}