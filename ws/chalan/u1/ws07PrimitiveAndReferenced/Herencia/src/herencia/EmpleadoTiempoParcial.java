/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia;

/**
 *
 * @author aless
 */
public class EmpleadoTiempoParcial extends Empleado {
    private double bonificacion;
    
    public EmpleadoTiempoParcial(String nombre,String cedula,double salarioBase, double bonificacion){
        super(nombre, cedula, salarioBase);
        this.bonificacion = bonificacion;
    
    }
    
    public void setBonificacion(double bonificacion){
        this.bonificacion = bonificacion;
    }
    
    public double getBonificacion(){
        return bonificacion;
    }
    
    public double calcularSalarioTotal(){
    
        return getSalarioBase() + 0.2 * getSalarioBase();
    }
    
    @Override
    public void mostrarInfo(){
        System.out.println("INFORMACION EMPLEADO TIEMPO PARCIAL");
        super.mostrarInfo();
        System.out.println("Bonificacion: " + bonificacion);
        System.out.println("Salario total: " + calcularSalarioTotal());
        
        
    }
}
