package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Evaluacion extends Estudiante{
    
     private double parcial1;
      private double parcial2;
        private double parcial3;
        
        //private double resultado;

    public Evaluacion(String nombre, String apellido, String cedula,String carrera,double parcial1, double parcial2, double parcial3) {
        super( nombre, apellido, cedula,carrera);
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.parcial3 = parcial3;
    }

    public double getParcial1() {
        return parcial1;
    }

    public double getParcial2() {
        return parcial2;
    }

    public double getParcial3() {
        return parcial3;
    }

    public void setParcial1(double parcial1) {
        this.parcial1 = parcial1;
    }

    public void setParcial2(double parcial2) {
        this.parcial2 = parcial2;
    }

    public void setParcial3(double parcial3) {
        this.parcial3 = parcial3;
    }
    
    public double calcularPromedio(){
        return (parcial1 +parcial2+parcial3)/3;
        
    
  // resultado = (parcial1 +parcial2+parcial3)/3;

}
    
    public String getEstado(){
        return calcularPromedio()>7.0 ? "Aprobado ": "Reprobado ";
    }
      
        
    
}
