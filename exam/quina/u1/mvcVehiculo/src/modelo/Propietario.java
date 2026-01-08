package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Propietario {
    
    
    
protected String nombre;
    protected String apellido;
    protected String cedula;
     protected String direccion;
      protected String telefono;
    
    
    
    public Propietario(String nombre, String apellido, String cedula,String direccion,String telefono){
        
        this.nombre=nombre;
        this.apellido=apellido;
        this.cedula=cedula;
        this.direccion=direccion;
        this.telefono=telefono;
    }
    
    
    
    public String mostrarInformacion(){
         return "\n NOMBRE: "+nombre +"\n APELLIDO: "+apellido +"\n CEDULA: "+cedula +"\n Direccion: "+direccion +"\n Telefono: "+telefono;
    }
    
    
    
    
}

