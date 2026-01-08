package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Persona {
    private String nombre;
    private String apellido;
    private String cedula;
    
    public Persona(String nombre,String apellido,String cedula){
              this.nombre=nombre;
        this.apellido=apellido;
        this.cedula=cedula;
    }
     public String mostrarInformacion(){
         return "\n NOMBRE: "+nombre +"\n APELLIDO: "+apellido +"\n CEDULA: "+cedula;
    }
}
