package ec.edu.espe.strategy.model;

/**
 *
 * @author Mat eo Aymacaña, T.A.P. The Art Of Programming
 */
public class BubbleSort implements SortingStrategy {

    public int[] sort(int data[]) {
        if (data == null || data.length == 0) {
            return data;
        }
        
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        return data;
    }
}
