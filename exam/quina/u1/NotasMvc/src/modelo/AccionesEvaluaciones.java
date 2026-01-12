package modelo;

import java.util.ArrayList; //guardadr el arreglo
import java.util.Collections; //devuelve elementos del arreglo
import java.util.List; //INSTANCIAR LA LISTA DEL ARREGLO

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class AccionesEvaluaciones {
    
    //permit5en guardar en arreglos las aciones (añade)
    
    private final List<Evaluacion> lista = new ArrayList<>();
     
    public void agregar (Evaluacion e){
        
        lista.add(e);
        
    }
    
    public List<Evaluacion > listar(){
        return Collections.unmodifiableList(lista);
    }
    
    
}
