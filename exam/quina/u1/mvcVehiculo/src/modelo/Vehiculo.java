package modelo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Vehiculo extends Propietario{
    
     private String placa;
      private String modelo;
      private String color;
      private String tipo;
    
    
    public Vehiculo(String nombre, String apellido, String cedula,String direccion,String telefono,String placa,
            String modelo,String color,String tipo){
        
        super(nombre,apellido,cedula,direccion,telefono);
        
        this.placa=placa;
        this.modelo= modelo;
        this.color= color;
        this.tipo=tipo;
        
    }
    
    //SOBREESCRITURA Y POLIFORMISMO
    
    @Override
    
    public String mostrarInformacion(){
       return super.mostrarInformacion() + "\n Placa: "+placa +"MODELO: "+modelo +"Color: "+color+"TIPO: "+tipo;
        
    }
    
}
