package controller;

import model.SortStrategy;

public class InsertionSort implements SortStrategy {

@Override
    public int[] sort(int[] data) {
        int[] arr = data.clone();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
