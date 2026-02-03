/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategy.model;

/**
 *
 * @author Mateo Cevallos
 */
public class SortingContext {
     private SortingStrategy ss;

    public int[] sort(int data[]) {
        int size = data.length; // CORRECCIÓN: length es una propiedad, no un método
        ss = setSortStrategy(size);
        return ss.sort(data);
    }

    public SortingStrategy setSortStrategy(int n) {
        if (n > 0 && n < 30) {
            ss = (SortingStrategy) new BubbleSort();
        } else if (n >= 30 && n < 100) {
            ss = new InsertionSort();
        } else if (n >= 100) {
            ss = (SortingStrategy) new QuickSort();
        }
        return ss;
    }
}
