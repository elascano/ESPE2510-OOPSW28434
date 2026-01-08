package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Estudiante extends Persona{
    
    private String carrera;
     private String modalidad;
      private String jornada ;
       private String semestre ;
        private String periodo;
         private String facultad;
         private double nota1;
    private double nota2;
    private double nota3;
         
         
    
    
    public Estudiante(String nombre,String apellido,String cedula,String carrera,
            String modalidad, String jornada ,String semestre,String periodo,String facultad, double nota1, double nota2, double nota3){
        super(nombre,apellido,cedula);
        
        this.carrera=carrera;
        this.modalidad=modalidad;
        this.jornada=jornada;
        this.semestre=semestre;
        this.periodo=periodo;
        this.facultad=facultad;
        this.nota1=nota1;
        this.nota2=nota2;
        this.nota3=nota3;
    }
    public double calcularPromedio() {
        return (nota1 + nota2 + nota3) / 3;
    }
   
    
}
//modalidad
//jornada
//semestre
//periodo
//facultad