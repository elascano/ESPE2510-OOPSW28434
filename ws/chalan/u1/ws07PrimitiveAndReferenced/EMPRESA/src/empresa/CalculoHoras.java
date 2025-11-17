/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class CalculoHoras extends Empleado {
    
    private int numeroDehoras;
    private int horasTrabajadas;
    
    public CalculoHoras(String nombre, String apellido, String cedula, String cargo, double sueldo, int numeroDeHoras, int horasTrabajadas){
        super(nombre, apellido, cedula,cargo,sueldo);
    
        setnumeroDehoras();
        sethorasTrabajadas();
    
    }
    
    public void setnumeroDehoras(int numeroDehoras){
            this.numeroDehoras = numeroDehoras;
        }
    
    public int getnumeroDeHoras(){
        return numeroDehoras;
    }
    
    public void sethorasTrabajadas(int horasTrabajadas){
            this.horasTrabajadas = horasTrabajadas;
        }
    
    public int gethorasTrabajadas(){
        return horasTrabajadas;
    }
    
    
    public double Sueldo25(){
        return getSueldo() *0.25;
    }
    
    public double Sueldo50(){
        return getSueldo() *0.50;
    }
    
    public double Sueldo100(){
        return getSueldo() *0.50;
    }
    
    public double Sueldo25(){
        return getSueldo() *0.25;
    }
    
    
    
    
    
    
    }
    
