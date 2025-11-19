/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class ActualizarEmpleado {
    
    public void mostrarInfo(Empleado e){
        System.out.println("Nombre: " + e.getNombre());
        System.out.println("Apellido: " + e.getApellido());
        System.out.println("Cedula: " + e.getCedula());
        System.out.println("Cargo: " + e.getCargo());
        System.out.println("sueldo: " + e.getSueldo());
 
        
    }
    
    public void actualizarNombre(Empleado e, String nombreNuevo){
        e.setNombre(nombreNuevo);
    }
    
    public void actualizarApellido(Empleado e, String cedulaNueva){
        e.setCedula(cedulaNueva);
    }
    
    public void actualizarCedula(Empleado e, String cedulaNueva){
        e.setCedula(cedulaNueva);
    }
    
    public void actualizarCargo(Empleado e, String cedulaNueva){
        e.setCedula(cedulaNueva);
    }
    
    public void actualizarSueldo(Empleado e, String cedulaNueva){
        e.setCedula(cedulaNueva);
    }
   
}
