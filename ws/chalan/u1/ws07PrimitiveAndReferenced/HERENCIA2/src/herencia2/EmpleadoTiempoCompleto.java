/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia2;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class EmpleadoTiempoCompleto extends Empleado {
    private double bono;
    
    public EmpleadoTiempoCompleto(String nombre,String cedula,double salarioBase, double bono){
        super(nombre, cedula, salarioBase);
        this.bono = bono;
    }
    
    public double getBono(){
        return bono;
    }
    
    public void setBono(double bono){
        this.bono = bono;
    }
    
    public double calcularSalarioTotal(){
        return getSalarioBase() + bono;
    }
    
    @Override
    public void mostrarInfo(){
        System.out.println("INFORMACION EMPLEADO TIEMPO COMPLETO");
        super.mostrarInfo();
        System.out.println("Bono: " + bono);
        System.out.println("Salario total: " + calcularSalarioTotal());

    }
    
}
