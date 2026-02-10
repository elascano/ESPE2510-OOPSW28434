package controller;

import model.SortStrategy;

public class QuickSort implements SortStrategy {

    @Override
    public int[] sort(int[] data) {
    int[] arr = data.clone();

    int start = 11;
    if (start < arr.length) {
        quickSort(arr, start, arr.length - 1);
    }

    return arr;
}
    
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

