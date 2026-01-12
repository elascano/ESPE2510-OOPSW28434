package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Evaluacion extends Estudiante{
    
    private double parcial1;
      private double parcial2;
        private double parcial3;

    public Evaluacion(String nombre, String apellido, String cedula, String carrera,
            String modalidad, String jornada, String semestre, String periodo, String facultad, double nota1, double nota2, double nota3) {
        super(nombre, apellido, cedula, carrera, modalidad, jornada, semestre, periodo, facultad, nota1, nota2, nota3);
    }

    
        
    
}
