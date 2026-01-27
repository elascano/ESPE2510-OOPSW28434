package ec.edu.espe.hwstrategy.controller;

import ec.edu.espe.hwstrategy.model.SortingContext;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class SortController {
    private SortingContext context;

    public SortController() {
        this.context = new SortingContext();
    }

    // Call Strategy
    public int[] sortData(int[] data) {
        return context.sort(data);
    }
}
