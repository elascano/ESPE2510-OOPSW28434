/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia;

/**
 *
 * @author aless
 */
public class EmpleadoPorHoras extends Empleado {
    
    
    private int horasTrabajadas;
    private double valorHora;
    
    public EmpleadoPorHoras(String nombre,String cedula,double salarioBase, int horasTrabajadas, double valorHora ){
        super(nombre, cedula, salarioBase); // accede los valores o carga los valores que llega desde empleado(que son heredados)
        setHorasTrabajadas(horasTrabajadas);
        setValorHora(valorHora);   
    }
    
    //get set
    
    public void setHorasTrabajadas(int horasTrabajadas){
        if(horasTrabajadas > 0 && horasTrabajadas <= 20){
            this.horasTrabajadas = horasTrabajadas;
        }else{
            System.out.println("Horas invalidas. No puede ser negativo o no puede exceder a 20");
        }
    }
    
    public int getHorasTrabajadas(){
        return horasTrabajadas;
    }
    
    public void setValorHora(double valorHora){
        if (valorHora >=0){
            this.valorHora = valorHora;
        }else {
            System.out.println("Valor de hora invalido. No puede ser negativo.");
        }
    }
    
    public double getValorHora(){
        return valorHora;
    }
    //metodo para el calculo de horas
    
    public double calcularSalarioTotal(){
        return getSalarioBase() + ( getValorHora() * getHorasTrabajadas() );
    }
   
    @Override
    public void mostrarInfo(){
        System.out.println("INFORMACION EMPLEADO POR HORAS");
        super.mostrarInfo();
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        System.out.println("Valor por Hora: " + valorHora);
        System.out.println("Salario total: " + calcularSalarioTotal());
  
    }
}
