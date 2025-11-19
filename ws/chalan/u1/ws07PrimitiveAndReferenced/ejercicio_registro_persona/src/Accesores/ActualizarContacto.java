/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accesores;

/**
 *
 * @author aless
 */
public class ActualizarContacto {
    public void mostrarContacto(Contacto c){
        System.out.println("Telefono: " + c.getTelefono());
        System.out.println("Correo: " + c.getCorreo());
        
    }
    
    public void actualizarTelefono(Contacto c, String nuevoTelefono){
        c.setTelefono(nuevoTelefono);
    }
    
    public void actualizarCorreo(Contacto c, String nuevoCorreo){
        c.setCorreo(nuevoCorreo);
    }
}
