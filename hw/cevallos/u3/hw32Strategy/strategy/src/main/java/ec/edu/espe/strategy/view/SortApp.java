/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategy.view;

import ec.edu.espe.strategy.model.SortingContext;
import java.util.Arrays;

/**
 *
 * @author Mateo Cevallos
 */
public class SortApp {
     public static void main(String args[]) {
        int data[] = {3, 6, 4, 6, 7, 8, 5, 6, 7, 5, 3, 3};
        
        System.out.println("Array original: " + Arrays.toString(data));
        
        SortingContext sc = new SortingContext();
        int sortedList[] = sc.sort(data);
        
        System.out.println("Array ordenado: " + Arrays.toString(sortedList));
    }
}
