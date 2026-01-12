
/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
            new SistemaIncidencias().setVisible(true);
        });
    }
}
