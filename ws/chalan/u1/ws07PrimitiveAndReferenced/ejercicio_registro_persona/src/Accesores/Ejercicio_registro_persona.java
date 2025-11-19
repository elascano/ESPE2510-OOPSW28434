/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Accesores;
import java.util.Scanner;

/**
 *
 * @author aless
 */
public class Ejercicio_registro_persona {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistroPersona registro = new RegistroPersona();
        ActualizarContacto actualizarcontacto = new ActualizarContacto();
        
        System.out.println("REGISTRO DE DATOS");
        
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.println("Edad: ");
        int edad = sc.nextInt();
        
        sc.nextLine();

        System.out.println("Cedula: ");
        String cedula = sc.nextLine();
        
        System.out.println("Telefono: ");
        String telefono = sc.nextLine();
        
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        
        Persona persona1 = new Persona(nombre,edad,cedula);
        Contacto contacto1 = new Contacto(telefono, correo);
        
        registro.mostrarDatos(persona1);      
        actualizarcontacto.mostrarContacto(contacto1);
        
        System.out.println("---ACTUALIZAR---");
        
        System.out.println("Nombre nuevo: ");
        registro.actualizarNombre(persona1,sc.nextLine());
        
        System.out.println("Edad: ");
        registro.actualizarEdad(persona1,sc.nextInt());
        
        sc.nextLine();

        System.out.println("Cedula: ");
        registro.actualizarCedula(persona1,sc.nextLine());
        
        System.out.println("Telefono: ");
        actualizarcontacto.actualizarTelefono(contacto1,sc.nextLine());
        
        System.out.println("Correo: ");
        actualizarcontacto.actualizarCorreo(contacto1,sc.nextLine());
        
        System.out.println("---DATOS ACTUAZLIZADOS---");
        
        registro.mostrarDatos(persona1);
        actualizarcontacto.mostrarContacto(contacto1);
        sc.close();


        
        
        
        
        
        
    }
    
}
