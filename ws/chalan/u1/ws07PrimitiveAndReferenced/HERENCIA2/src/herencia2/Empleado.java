/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia2;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Empleado {
    
    private String nombre;
    private String cedula;
    private double salarioBase;
    
    //crear los constructores

    public Empleado(String nombre,String cedula,double salarioBase){
        
        this.nombre = nombre;
        this.cedula = setCedula(cedula);
        this.salarioBase = salarioBase;
    }
    
    
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    
    public String setCedula(String cedula){
        if(cedula != null && cedula.length()==10){
            return cedula;
        }else {
            return "Cedula invalida.";
        }
    }

    public void setSalarioBase(double salarioBase){
        this.salarioBase = salarioBase;
    }

    public double getSalarioBase(){
        return salarioBase;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getCedula(){
        return cedula;
    }
    
    public void validarSalario(double salarioBase)throws SalarioException{
            if (salarioBase <0 || salarioBase>2000){
                throw new SalarioException("ERROR SALARIO:"+salarioBase+" No cumple con los parametros de hasta 2000 o es negativo" );
            }
    
    
    
    }
    
    public void mostrarInfo(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Cedula: " + cedula);
        System.out.println("Salario Base: " + salarioBase);

    
    }
    
}