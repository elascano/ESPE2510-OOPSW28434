package ec.edu.espe.composite.model;

/**
 *
 * @author Paulo Ramos
 */
abstract class Employee {
     String name = "not assigned yet";
     String tittle = "not assigned yet";
     
     public void stateName(){
         System.out.println(tittle + " " + name);
     }
}
