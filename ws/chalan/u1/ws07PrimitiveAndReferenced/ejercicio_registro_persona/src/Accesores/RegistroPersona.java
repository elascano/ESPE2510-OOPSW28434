/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accesores;

/**
 *
 * @author aless
 */
public class RegistroPersona {
    public void mostrarDatos (Persona p){
        System.out.println("Nombre " + p.getNombre());
        System.out.println("Edad " + p.getEdad());
        System.out.println("Cedula " + p.getCedula());      
    }
    
    public void actualizarNombre(Persona p, String nuevoNombre){
        p.setNombre(nuevoNombre);
    }
    
    public void actualizarEdad(Persona p, int nuevaEdad){
        p.setEdad(nuevaEdad);
    }
    
    public void actualizarCedula(Persona p, String nuevaCedula){
        p.setCedula(nuevaCedula);
    }
}
