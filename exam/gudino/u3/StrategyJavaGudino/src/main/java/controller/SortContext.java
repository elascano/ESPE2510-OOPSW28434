package controller;

import model.*;

/**
 * CONTEXT Strategy
 */
public class SortContext {

    private SortStrategy strategy;
    private SortRepository repository;

    public SortContext(int[] data, SortRepository repository) {
        this.repository = repository;
        this.strategy = chooseStrategy(data.length);
    }
 
    private SortStrategy chooseStrategy(int size) {
        if (size <= 6) {
            return new BubbleSort();
    } else if (size <= 10) {
        return new InsertionSort();
    } else {
        return new QuickSort();
    }
}

    public int[] sort(int[] data) {
        int[] original = data.clone();   
        int[] sorted = strategy.sort(data);

        repository.save(original, sorted, getStrategyName());
        return sorted;
    }

    public String getStrategyName() {
        return strategy.getClass().getSimpleName();
    }
}
