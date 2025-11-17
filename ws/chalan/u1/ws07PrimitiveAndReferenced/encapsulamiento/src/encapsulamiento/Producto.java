/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encapsulamiento;

/**
 *
 * @author aless
 */
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    
    public Producto (String Nombre, double Precio,int Stock){
        nombre = Nombre;
        precio = Precio;
        stock = Stock;
    }
    public void modificarStock(int cantidad){
        stock += cantidad ;
    }
    public void mostrar(){
        System.out.println(nombre + " - " + precio + " - " + stock);
    
    } 
}
