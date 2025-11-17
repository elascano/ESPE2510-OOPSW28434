/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accesores;

/**
 *
 * @author aless
 */
public class Persona {
    
    private String nombre;
    private int  edad;
    private String cedula;
    
    //constructores
    
    public Persona(String nombre, int edad, String cedula){
        this.nombre = nombre ;
        
        setEdad(edad);
        setCedula(cedula);
    }
    
    public String getNombre(){
        return nombre;
    }
    public int getEdad(){
        return edad;
    }
    
    public String getCedula(){
        return cedula;
    }
    //setter
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setEdad(int edad){
        if (edad >= 0){
            this.edad = edad;
        }else{
            System.out.println("Edad invalida.");
        }    
    }
    
    public void setCedula(String cedula){
        if (cedula != null && cedula.length() == 10 && !cedula.trim().isEmpty()) {
            this.cedula = cedula;
        }else{
            System.out.println("La cedula debe tener 10 digitos.");
        }    
    }
    
    
}
