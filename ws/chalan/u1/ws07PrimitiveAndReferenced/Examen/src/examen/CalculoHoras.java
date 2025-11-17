/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class CalculoHoras extends Empleado {
    
    private double numHoras;
    private double horasTrab=240;
    private double horasNormales;
    
    public CalculoHoras(String nombre, String apellido, String cedula, double sueldo, String cargo, double numHoras, double horasTrab, double horasNormales){
        super(nombre, apellido, cedula, sueldo, cargo);
        
        this.numHoras = numHoras;
        this.horasNormales = horasNormales;
    
    }
    
    public void setNumHoras(){
        this.numHoras=numHoras;
    }
    
    public void setHorasNormales(){
        this.horasNormales = horasNormales;
    }
                   
    public double getNumHoras(){
    return numHoras;
    }
    
    public double getHorasNormales(){
    return horasNormales;
    }
    
    
    public double calcular25(){
        return (getSueldo()/horasTrab)*1.25*numHoras;
    
    }
    
    public double calcular50(){
        return (getSueldo()/horasTrab)*1.50*numHoras;
    
    }
    
    public double calcular100(){
        return (getSueldo()/horasTrab)*2*numHoras;
    
    }
    
    public void mostrarInfo25(){
        
    System.out.println("Numero de Horas: "+numHoras);
    System.out.println("Horas trabajadas: ");
    
    
    System.out.println("Salario al 25%: "+calcular25());
    System.out.println("Salario al 50%: "+calcular50());
    System.out.println("Salario al 100%: "+calcular100());
    }
    
    
    
    
}
