/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Empleado {
    
    private String nombre;
    private String apellido;
    private String cedula;
    private double sueldo;
    private String cargo;
    
    
    public Empleado(String nombre, String apellido, String cedula, double sueldo, String cargo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.sueldo = sueldo;
        this.cargo = cargo;
    }
    
    public void setNombre(){
        this.nombre=nombre;
    }
    
    public void setApellido(){
        this.apellido=apellido;
    }
    
    public void setCedula(){
        this.cedula=cedula;
    }
    
    public void setSueldo(){
        this.sueldo=sueldo;
    }
    
    public void setCargo(){
        this.cargo=cargo;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public String getApellido(){
    return apellido;
    }
    
    public String getCedula(){
    return cedula;
    }
    
    public double getSueldo(){
    return sueldo;
    }
    
    public String getCargo(){
    return cargo;
    }
    
    public void mostrarInfo(){
    System.out.println("Nombre: "+nombre);
    System.out.println("Apellido: "+apellido);
    System.out.println("Cedula: "+cedula);
    System.out.println("Sueldo: "+sueldo);
    System.out.println("Cargo: "+cargo);
    }
    
    
    
    
    
    
}
