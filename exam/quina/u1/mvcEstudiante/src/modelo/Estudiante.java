package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Estudiante extends Persona {
    
    private String carrera;
    
    
    public Estudiante(String nombre, String apellido, String cedula,String carrera){
        
        super(nombre,apellido,cedula);
        
        this.carrera=carrera;
    }
    
    //SOBREESCRITURA Y POLIFORMISMO
    
    @Override
    
    public String mostrarInformacion(){
       return super.mostrarInformacion() + "\n CARRERA: "+carrera;
        
    }
    
}
