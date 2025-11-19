/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package empresa;
import java.util.Scanner;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class EMPRESA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
            System.out.println("---INGRESE LA INFORMACION---");
        
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
        
            System.out.println("Apellido: ");
            String apellido = sc.nextLine();
        
            System.out.println("Cedula: ");
            String cedula = sc.nextLine();
            
            System.out.println("Cargo: ");
            String cargo = sc.nextLine();
            
            System.out.println("Sueldo: ");
            double sueldo = sc.nextDouble();

            sc.nextLine();
            
            Empleado empleadoTemp = new Empleado(nombre, apellido, cedula, cargo, sueldo);
            
            
            
            ActualizarEmpleado registro = new ActualizarEmpleado();
            registro.mostrarInfo(empleadoTemp);
            
            sc.nextLine();

            System.out.println("ACTUALIZAR DATOS");
            
            
            System.out.println("Nombre: ");
            registro.actualizarNombre(empleadoTemp,sc.nextLine());
        
            System.out.println("Apellido: ");
            registro.actualizarCedula(empleadoTemp,sc.nextLine());
            
            System.out.println("Cedula: ");
            registro.actualizarCedula(empleadoTemp,sc.nextLine());
            
            System.out.println("Cargo: ");
            registro.actualizarCedula(empleadoTemp,sc.nextLine());
            
            System.out.println("Sueldo: ");
            registro.actualizarCedula(empleadoTemp,sc.nextLine());

    
    
    }
    
}
