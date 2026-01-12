
/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
import java.util.ArrayList;

public class GestorIncidencias {

    private ArrayList<Incidencia> incidencias;
    private int contadorId;

    public GestorIncidencias() {
        incidencias = new ArrayList<>();
        contadorId = 101;
    }

    public void registrarIncidencia(String descripcion, String prioridad, String estado) {
        Incidencia inc = new Incidencia(contadorId++, descripcion, prioridad, estado);
        incidencias.add(inc);
    }

    public boolean cambiarEstado(int id, String nuevoEstado) {
        for (Incidencia inc : incidencias) {
            if (inc.getId() == id) {
                inc.setEstado(nuevoEstado);
                return true;
            }
        }
        return false;
    }

    public String obtenerListado() {
        StringBuilder sb = new StringBuilder();
        for (Incidencia inc : incidencias) {
            sb.append(inc.mostrarDetalle());
        }
        return sb.toString();
    }
}

