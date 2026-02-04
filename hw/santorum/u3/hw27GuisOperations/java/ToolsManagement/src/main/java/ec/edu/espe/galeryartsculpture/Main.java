package ec.edu.espe.galeryartsculpture;

import javax.swing.SwingUtilities;
import ec.edu.espe.tools.view.FrmTool;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmTool().setVisible(true);
        });
    }
}
