package ec.edu.espe.strategy.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. The Art Of Programming
 */
public class InsertionSort implements SortingStrategy {

    public int[] sort(int data[]) {
        if (data == null || data.length == 0) {
            return data;
        }
        
        int n = data.length;
        for (int i = 1; i < n; i++) {
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
