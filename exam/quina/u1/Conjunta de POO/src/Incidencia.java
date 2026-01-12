
/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Incidencia {

    private final int id;
    private String descripcion;
    private String prioridad;
    private String estado;

    public Incidencia(int id, String descripcion, String prioridad, String estado) {
        this.id = id;
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
               " | Prioridad: " + prioridad +
               " | Estado: " + estado +
               "\nDescripción: " + descripcion +
               "\n\n";
    }
}

