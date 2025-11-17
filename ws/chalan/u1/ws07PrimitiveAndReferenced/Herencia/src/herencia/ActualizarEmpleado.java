/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia;

/**
 *
 * @author alessito
 */
public class ActualizarEmpleado {
    public void mostrarInfo(Empleado e){
        System.out.println("Nombre: " + e.getNombre());
        System.out.println("Cedula: " + e.getCedula());
        System.out.println("Salario base: " + e.getSalarioBase());
        
    }
    
    public void actualizarNombre(Empleado e, String nombreNuevo){
        e.setNombre(nombreNuevo);
    }
    
    public void actualizarCedula(Empleado e, String cedulaNueva){
        e.setCedula(cedulaNueva);
    }
    
    public void actualizarSalario(Empleado e, double salarioNuevo){
        e.setSalarioBase(salarioNuevo);
    }
}
