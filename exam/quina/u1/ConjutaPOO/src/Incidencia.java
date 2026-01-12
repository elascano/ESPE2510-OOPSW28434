
/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Incidencia {

    private static int contador = 1;
    private final int id;
     private String descripcion;
    private String prioridad;
    private String estado;

    public enum prioridad {
        Alta, Media, Baja
    }

    public enum estado {
        Abierta, Proceso, Cerrada
    }

    public Incidencia(String descripcion, String prioridad, String estado) {
        this.id = contador++;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

     public String mostrarDetalle() {
        return "ID: " + id +
               " | Descripción: " + descripcion +
               " | Prioridad: " + prioridad +
               " | Estado: " + estado;
    }
}
