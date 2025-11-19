/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accesores;

/**
 *
 * @author aless
 */
public class Contacto {
    private String telefono;
    private String correo;
    
    public Contacto(String telefono, String correo){
        setTelefono(telefono);
        setCorreo(correo);
    }
    
    public void setTelefono(String telefono){
        if(telefono != null && telefono.length() == 10){
            this.telefono = telefono;
        }else{
            System.out.println("El telefono debe tener 10 digitos.");
        }
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public String getTelefono(){
        return telefono;
    }
}

