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

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
    public String mostrarInformacion(){
         return "\n NOMBRE: "+nombre +"\n APELLIDO: "+apellido +"\n CEDULA: "+cedula;
    }
    
    
    
}
