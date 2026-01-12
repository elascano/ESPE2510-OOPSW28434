package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Estudiante extends Persona{
        private String carrera;

    public Estudiante( String nombre, String apellido, String cedula,String carrera) {
        super(nombre, apellido, cedula);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    

    public void setCarrera(String carrera) {
        this.carrera= carrera;
    }
        
    
}
