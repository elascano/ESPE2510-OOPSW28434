package ec.edu.espe.hw32strategy;

import ec.edu.espe.hw32strategy.model.SortingContext;

/**
 *
 * @author Joseph B. Medina
 */
public class SortApp {
    
    public static void main(String[] args) {
        
        int data[] = {3, 6, 4, 6, 7, 8, 5, 6, 7, 5, 3, 3};
        SortingContext sortingContext = new SortingContext();
        int sortedList [] = sortingContext.sort(data);
        
        
        
    }
    

}
