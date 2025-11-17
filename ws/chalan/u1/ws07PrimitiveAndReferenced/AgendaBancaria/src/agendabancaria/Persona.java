/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendabancaria;

/**
 *
 * @author aless
 */
public class Persona {
    
    private String nombre;
    private String apellido;
    
        public Persona(String Nombre, String Apellido){
            nombre = Nombre;
            apellido = Apellido;
        
        }
        
        public void mostrarInfo(){
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);

        }
    
    
}
