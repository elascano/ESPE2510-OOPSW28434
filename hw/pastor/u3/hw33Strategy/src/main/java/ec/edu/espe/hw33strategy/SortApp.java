package ec.edu.espe.hw33strategy;

import ec.edu.espe.hw33strategy.model.SortingContext;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class SortApp {

    public static void main(String[] args) {
        int data[] = {3,6,4,6,7,8,5,6,7,5,3,3};
        SortingContext sc = new SortingContext();
        int sortedList[] = sc.sort(data);
        for (int i : sortedList) {
            System.out.print(i + " ");
        }
    }
}
