import ec.edu.espe.painting.view.FrmView;
import javax.swing.SwingUtilities;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmView().setVisible(true);
        });
    }
}
