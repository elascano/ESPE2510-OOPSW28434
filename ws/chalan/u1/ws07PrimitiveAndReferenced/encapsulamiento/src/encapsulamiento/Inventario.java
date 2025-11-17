/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encapsulamiento;

/**
 *
 * @author aless
 */
public class Inventario {
    public void mostrarDatos(Producto p){
        p.mostrar();  
    }
    
    public void actualizarStock(Producto p,  int cantidad){
        p.modificarStock(cantidad);
    }
}
