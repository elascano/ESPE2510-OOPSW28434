/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia2;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class EmpleadoPorHoras extends Empleado {
    
    
    private int horasTrabajadas;
    private double valorHora;
    
    public EmpleadoPorHoras(String nombre,String cedula,double salarioBase, int horasTrabajadas, double valorHora ){
        super(nombre, cedula, salarioBase); // accede los valores o carga los valores que llega desde empleado(que son heredados)
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    
    }
    
    //get set
    
    public void setHorasTrabajadas(int horasTrabajadas){
        this.horasTrabajadas = horasTrabajadas;
    }
    
    public int getHorasTrabajadas(){
        return horasTrabajadas;
    }
    
    public void setValorHora(double valorHora){
        
        if(valorHora >=0){
        this.valorHora = valorHora;
        }else {
                System.out.println("VALOR HORA INVALIDO, NO PUEDE SER NEGATIVO");
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

