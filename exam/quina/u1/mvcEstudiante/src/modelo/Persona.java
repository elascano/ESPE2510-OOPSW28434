package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Persona {
    
    protected String nombre;
    protected String apellido;
    protected String cedula;
    
    
    
    public Persona(String nombre, String apellido, String cedula){
        
        this.nombre=nombre;
        this.apellido=apellido;
        this.cedula=cedula;
    }
    
    
    
    public String mostrarInformacion(){
         return "\n NOMBRE: "+nombre +"\n APELLIDO: "+apellido +"\n CEDULA: "+cedula;
    }
    
    
    
}
