/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package encapsulamiento;
import java.util.Scanner;
/**
 *
 * @author aless
 */
public class Encapsulamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----REGISTRO DEL PRODUCTO-----");
        
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();
        
        System.out.println("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.println("Ingrese el stock inicial: ");
        int stock = sc.nextInt();
        
        //instancia del objeto producto
        Producto p1 = new Producto(nombre, precio,stock );
        
        //instanciar el inventario
        Inventario inv = new Inventario();
        
        System.out.println("Informacion del producto registrado: ");
        inv.mostrarDatos(p1);
        
        System.out.println("Ingrese el stock a modificar (- +): ");
        int cantidad = sc.nextInt();
        
        inv.actualizarStock(p1, cantidad);
        
        System.out.println("Actualizacion: ");
        inv.mostrarDatos(p1);        
        
             
        
        
        
        

        
    }
    
}
