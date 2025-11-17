/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Empleado {
    
    private String nombre;
    private String apellido;
    private String cedula;
    private String cargo;
    private double sueldo;
    
    
    public Empleado(String nombre,String apellido,String cargo,double sueldo){
        
        if(sueldo < 0){
            throw new SueldoInvalidoExceptio("NO SE PUEDE INGRESAR UN SUELDO NEGATIVO");    
        }
        
        if(cedula != null && cedula.length() == 10){
            throw new CedulaInvalidaException("LA CEDULA DEBE TENER MAS DE 10 DIGITOS.");
        }
        
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setCargo(cargo);
        setSueldo(sueldo);
    }
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    
    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        this.cedula = cedula;
    }
    
    
    public String getCargo(){
        return cargo;
    }
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    
    
    public double getSueldo(){
        return sueldo;
    }
    public void setSueldo(double sueldo){
        this.sueldo = sueldo;
    }
    

    public void mostrarInfo (){
        System.out.println("Nombre " + nombre);
        System.out.println("Apellido " + apellido);
        System.out.println("Cedula " + cedula);
        System.out.println("Cargo " + cargo);  
        System.out.println("Cedula " + sueldo);
    }
    
    
}
