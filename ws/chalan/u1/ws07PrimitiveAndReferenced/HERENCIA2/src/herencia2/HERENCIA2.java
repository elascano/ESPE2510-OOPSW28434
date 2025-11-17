/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package herencia2;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class HERENCIA2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try {
        Scanner sc = new Scanner(System.in);
        System.out.println("---INGRESE LA INFORMACION---");
        
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.println("Cedula: ");
        String cedula = sc.nextLine();
        
        System.out.println("Salario Base: ");
        double salarioBase = sc.nextDouble();

        sc.nextLine();

        System.out.println("El empleado es de tiempo completo(1). Empleado por horas (2). Empleado tiempo parcial (3): ");
        int tipo = sc.nextInt();
        
        if(tipo == 1){
            System.out.println("Ingrese el bono: ");
            double bono = sc.nextDouble();
            
            EmpleadoTiempoCompleto emp1 = new EmpleadoTiempoCompleto(nombre,cedula,salarioBase,bono);
            emp1.validarSalario(salarioBase);
            
            emp1.mostrarInfo();
        }else if (tipo == 2){
            System.out.println("Ingrese las horas trabajadas: ");
            int horas = sc.nextInt();
            System.out.println("Ingrese el valor de horas: ");
            double valorHora = sc.nextDouble();
            
            EmpleadoPorHoras emp2 = new EmpleadoPorHoras(nombre,cedula,salarioBase,horas,valorHora);
                emp2.mostrarInfo();
        }else if (tipo == 3){
            System.out.println("Ingrese la bonificacion: ");
            double bono = sc.nextDouble();
            
            EmpleadoTiempoParcial emp3 = new EmpleadoTiempoParcial(nombre, cedula, salarioBase,bono);
            emp3.mostrarInfo();
            
        }
        sc.close();
        
        }catch (SalarioException e){
            System.out.println("ERROR: "+e.getMessage());
        
        } catch (Exception e){
            System.out.println("ERROR GENERAL: "+e.getMessage());
            
        }finally{
                System.out.println("FIN DEL PROGRAMA");
        }
    }
    
}
