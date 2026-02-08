package controller;

import model.*;
//import controller.strategy.*;

/**
 * CONTEXT del patrón Strategy
 */
public class SortContext {

    private SortStrategy strategy;
    private SortRepository repository;

    public SortContext(int[] data, SortRepository repository) {
        this.repository = repository;
        this.strategy = chooseStrategy(data.length); // ← AQUÍ SE ELIGE
    }

    // AQUÍ VIVE LA DECISIÓN DE ESTRATEGIA (CONTROLLER)
    private SortStrategy chooseStrategy(int size) {
        if (size <= 5) {
            return new BubbleSort();
        } else if (size <= 10) {
            return new InsertionSort();
        } else {
            return new QuickSort();
        }
    }

    public int[] sort(int[] data) {
        int[] original = data.clone();   // copia real del input
        int[] sorted = strategy.sort(data);

        repository.save(original, sorted, getStrategyName());
        return sorted;
    }

    public String getStrategyName() {
        return strategy.getClass().getSimpleName();
    }
}
